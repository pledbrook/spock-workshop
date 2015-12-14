import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.example.training.ApplicationModule
import org.example.training.persistence.Todo
import org.example.training.persistence.TodoStore
import ratpack.error.ServerErrorHandler
import ratpack.h2.H2Module
import ratpack.handlebars.HandlebarsModule
import ratpack.handling.Context

import static ratpack.handlebars.Template.handlebarsTemplate
import static io.netty.handler.codec.http.HttpHeaderNames.LOCATION
import static io.netty.handler.codec.http.HttpResponseStatus.*
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import static ratpack.form.Form.form

ratpack {
    bindings {
        module(H2Module)
        module(ApplicationModule)
        module(HandlebarsModule)
    }
    handlers { TodoStore store, ServerErrorHandler originalServerErrorHandler ->
        prefix "api/todos", {
            register {
                add(ServerErrorHandler, { Context context, Throwable throwable ->
                    if (throwable instanceof JsonProcessingException ) {
                        context.response.status(BAD_REQUEST.code()).send()
                    } else {
                        originalServerErrorHandler.error(context, throwable)
                    }
                } as ServerErrorHandler)
            }
            all {
                if ((request.method.post || request.method.put) && !request.contentType.json) {
                    response.status(UNSUPPORTED_MEDIA_TYPE.code()).send()
                } else {
                    next()
                }
            }
            path {
                byMethod {
                    post {
                        parse(Todo).flatMap { todo ->
                            todo.id = null
                            store.add(todo)
                        }.then { id ->
                            response.headers.add(LOCATION, "/api/todos/" + id)
                            response.status(CREATED.code()).send()
                        }
                    }
                    get {
                        store.all().then { todos ->
                            render(json(todos))
                        }
                    }
                }
            }
            path ":id", {
                byMethod {
                    get {
                        store.fetch(pathTokens.id).then { todo ->
                            todo ? render(json(todo)) : response.status(NOT_FOUND.code()).send()
                        }
                    }
                    put {
                        parse(Todo).then { todo ->
                            todo.id = pathTokens.id
                            store.exists(todo.id).flatMap { exists ->
                                if (exists) {
                                    store.update(todo)
                                } else {
                                    response.status(CREATED.code())
                                    store.add(todo)
                                }
                            }.then { response.send() }
                        }
                    }
                    delete {
                        store.delete(pathTokens.id).then { deleted ->
                            if (!deleted) {
                                response.status(NOT_FOUND.code())
                            }
                            response.send()
                        }
                    }
                }
            }
        }
        prefix "todo", {
            path { ObjectMapper mapper ->
                byMethod {
                    get {
                        render handlebarsTemplate("add.html", newTodoPage: true)
                    }
                    post {
                        parse(form()).map { form ->
                            new Todo(contents: form.contents, completed: form.completed.asBoolean())
                        }.flatMap { todo ->
                            store.add(todo)
                        }.then {
                            redirect("/")
                        }
                    }
                }
            }
            path ":id", { ObjectMapper mapper ->
                byMethod {
                    get {
                        store.fetch(pathTokens.id).then { todo ->
                            render handlebarsTemplate("edit.html", todo: todo)
                        }
                    }
                    post {
                        parse(form()).map { form ->
                            new Todo(id: pathTokens.id, contents: form.contents, completed: form.completed.asBoolean())
                        }.flatMap { todo ->
                            store.update(todo)
                        }.then {
                            redirect("/")
                        }
                    }
                }
            }
            post ":id/delete", {
                store.delete(pathTokens.id).then {
                    redirect("/")
                }
            }
        }
        get {
            store.all().then { todos ->
                render handlebarsTemplate("index.html", listPage: true, todos: todos)
            }
        }
        post "clear", {
            store.clear().then {
                redirect "/"
            }
        }
        files {
            files "assets"
        }
    }
}