package org.example.training.persistence

import org.jooq.DSLContext
import org.jooq.Select
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

import static java.util.UUID.randomUUID
import static org.example.training.persistence.TodoTable.TODO

class TodoStore {

    private final DSLContext jooq

    @Inject
    TodoStore(DSLContext jooq) {
        this.jooq = jooq
    }

    Promise<String> add(Todo todo) {
        def id = todo.id ?: randomUUID().toString()
        todo.id = id
        def record = jooq.newRecord(TODO, todo)
        Blocking.get {
            jooq.executeInsert(record)
        }.map {
            id
        }
    }

    private final Promise<TodoRecord> withId(String id) {
        def select = jooq.selectFrom(TODO).where(TODO.ID.equal(id))
        Blocking.get {
            select.fetchOne()
        }
    }

    Promise<Boolean> exists(String id) {
        withId(id).map {
            it != null
        }
    }

    Promise<Todo> fetch(String id) {
        withId(id).map {
            it?.into(Todo)
        }
    }

    Promise<List<Todo>> all() {
        Blocking.get {
            jooq.fetch(TODO)
        }.map {
            it.into(Todo)
        }
    }

    Promise<Boolean> update(Todo todo) {
        def record = jooq.newRecord(TODO, todo)
        Blocking.get {
            jooq.executeUpdate(record)
        }.map {
            it != 0
        }
    }

    Promise<Boolean> delete(String id) {
        def delete = jooq.deleteFrom(TODO).where(TODO.ID.equal(id))
        Blocking.get {
            jooq.execute(delete)
        }.map {
            it != 0
        }
    }

    Operation clear() {
        def delete = jooq.deleteFrom(TODO)
        Blocking.op {
            jooq.execute(delete)
        }
    }
}
