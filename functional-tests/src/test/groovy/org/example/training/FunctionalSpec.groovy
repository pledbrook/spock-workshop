package org.example.training

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.example.training.persistence.Todo
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.CloseableApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Unroll

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
import static io.netty.handler.codec.http.HttpHeaderNames.LOCATION
import static io.netty.handler.codec.http.HttpResponseStatus.*
import static java.util.UUID.randomUUID
import static ratpack.http.MediaType.APPLICATION_JSON

class FunctionalSpec extends Specification {

    ObjectMapper mapper = new ObjectMapper()

    @AutoCleanup
    CloseableApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient client = testHttpClient(aut)


    private void writeTodoIntoRequest(Todo todo) {
        requestSpec {
            it.body.type(APPLICATION_JSON).text(mapper.writeValueAsString(todo))
        }
    }

    private String add(Todo todo) {
        writeTodoIntoRequest(todo)
        post "api/todos"

        assert response.statusCode == CREATED.code()

        def createdUri = response.headers.get(LOCATION)
        resetRequest()
        createdUri
    }

    /**
     * <p>TODO: Write a feature method that creates a new todo by posting to the {@code api/todo} endpoint which results
     * in a 201 response and then fetches the created todo using the url returned as the value of the {@code Location}
     * header of the post response. Verify that the fields of the fetched todo are the same as the one that were posted,
     * it's a {@code 200} response and that the content type is {@code application/json}.</p>
     * <p>Consider using the various available classes with constants like
     * {@link io.netty.handler.codec.http.HttpHeaderNames}, {@link io.netty.handler.codec.http.HttpResponseStatus} and
     * {@link ratpack.http.MediaType}. Think about using {@link com.fasterxml.jackson.databind.ObjectMapper} to write and
     * parse json for {@link Todo} instances instead of using json string literals to make your tests less susceptible
     * to failures should the formatting of json change.</p>
     */

    def "fetching existing todos"() {
        given:
        def todo = new Todo(contents: "My first todo", completed: false)
        def resourceUri = add(todo)

        when:
        get(resourceUri)

        then:
        response.statusCode == OK.code()
        response.headers.get(CONTENT_TYPE) == APPLICATION_JSON

        when:
        def fetched = mapper.readValue(response.body.text, Todo)

        then:
        fetched.contents == todo.contents
        fetched.completed == todo.completed
    }

    /**
     * <p>TODO: Add a feature method that asserts that a get request to the {@code api/todos/:id} endpoint for an unknown
     * id results in a 404 response/p>
     */

    def "a not found response is returned when fetching unknown todos"() {
        when:
        get "api/todos/${randomUUID()}"

        then:
        response.statusCode == NOT_FOUND.code()
    }

    /**
     * <p>TODO: Write a feature method that creates a couple of todos by posting to the {@code api/todo} endpoint
     * and then verifies that they are listed in a response to a get request to {@code api/todos} endpoint. Verify
     * that the fields of the fetched todos are the same as the one that were posted, it's a {@code 200} response and that
     * the content type is {@code application/json}</p>
     */

    def "listing all todos"() {
        given:
        add(new Todo(contents: "first"))
        add(new Todo(contents: "second"))

        when:
        get "api/todos"

        then:
        response.statusCode == OK.code()
        response.headers.get(CONTENT_TYPE) == APPLICATION_JSON

        when:
        List<Todo> list = mapper.readValue(response.body.text, new TypeReference<List<Todo>>() {})

        then:
        list*.contents == ["first", "second"]
    }

    /**
     * <p>TODO: Write a feature method that checks todo updating functionality by sending a put request to the uri
     * returned as the value of the {@code Location} header of response to a post request creating a new todo. Fetch the
     * updated todo and verify its fields are of the the same values as the ones used to update it.</p>
     */
    def "Updating todos"() {
        given:
        def resourceUri = add(new Todo(contents: "initial", completed: false))

        when:
        def updated = new Todo(contents: "modified", completed: true)
        writeTodoIntoRequest(updated)
        put resourceUri

        then:
        response.statusCode == OK.code()

        when:
        def fetched = mapper.readValue(getText(resourceUri), Todo)

        then:
        fetched.contents == updated.contents
        fetched.completed == updated.completed
    }

    /**
     * <p>TODO: Write a feature method that checks the ability to add a todo with a selected id by sending a put request
     * to the {@link api/todos/:id}. Verify that the response status is 201 and that the todo can be fetched by sending
     * a get request to the same uri that was used for sending the put request.</p>
     */

    def "Creating a new todo with a given id"() {
        given:
        def todo = new Todo(contents: "with id")
        writeTodoIntoRequest(todo)
        def resourceUri = "api/todos/${randomUUID()}"

        when:
        put resourceUri

        then:
        response.statusCode == CREATED.code()

        when:
        def fetched = mapper.readValue(getText(resourceUri), Todo)

        then:
        fetched.contents == todo.contents
    }

    /**
     * <p>TODO: Write a feature method that checks todo deleting functionality by sending a delete request to the uri
     * returned as the value of the {@code Location} header of response to a post request creating a new todo. Verify
     * that a subsequent get reuqest to the same uri results in a 404 response.</p>
     */
    def "Deleting a todo"() {
        given:
        def resourceUri = add(new Todo())

        when:
        delete resourceUri

        then:
        response.statusCode == OK.code()
        get(resourceUri).statusCode == NOT_FOUND.code()
    }

    /**
     * <p>TODO: Add a feature method that asserts that a delete to the {@code api/todos/:id} endpoint for an unknown
     * id results in a response with a 404 status</p>
     */
    def "A not found response is returned when deleting a non existing todo"() {
        when:
        delete "api/todos/${randomUUID()}"

        then:
        response.statusCode == NOT_FOUND.code()
    }

    /**
     * <p>TODO: Write a feature method that verifies that post requests to {@code api/todos} and put requests to the
     * {@code api/todos/:id} with {@code Content-Type} header not set to {@code application/json} result in
     * responses with a 415 status code</p>
     */
    def "Posting and putting non json requests results in unsupported media type response"() {
        when:
        post "api/todos"

        then:
        response.statusCode == UNSUPPORTED_MEDIA_TYPE.code()

        when:
        put "api/todos/${randomUUID()}"

        then:
        response.statusCode == UNSUPPORTED_MEDIA_TYPE.code()
    }


    /**
     * <p>TODO: Write a feature method that verifies that post requests to {@code api/todos} and put requests to the
     * {@code api/todos/:id} with content that is not valid json, or valid json but with properties that don't exist
     * on the {@link Todo} class result in responses with a 400 status code</p>
     */
    @Unroll
    def "Posting or putting invalid json results in bad request response"() {
        given:
        requestSpec {
            it.body.type(APPLICATION_JSON).text(json)
        }

        when:
        post "api/todos"

        then:
        response.statusCode == BAD_REQUEST.code()

        when:
        put "api/todos/${randomUUID()}"

        then:
        response.statusCode == BAD_REQUEST.code()

        where:
        json << ["not valid json", """{"foo": "bar"}"""]
    }

}
