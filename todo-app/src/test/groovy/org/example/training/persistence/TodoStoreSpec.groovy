package org.example.training.persistence

import org.h2.jdbcx.JdbcConnectionPool
import org.jooq.DSLContext
import org.jooq.impl.DSL
import ratpack.exec.Promise
import ratpack.func.Factory
import ratpack.func.Function
import ratpack.test.exec.ExecHarness
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Subject

import javax.sql.DataSource

import static java.util.UUID.randomUUID
import static org.jooq.SQLDialect.H2

class TodoStoreSpec extends Specification {

    DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test", "user", "pass")

    @AutoCleanup
    DSLContext context = DSL.using(dataSource, H2)

    DatabaseInitializer initializer = new DatabaseInitializer(dataSource)

    @Subject
    TodoStore store = new TodoStore(context)

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    private <T> T result(Factory<Promise<T>> promiseFactory) {
        execHarness.yield(Function.constant(promiseFactory.create())).valueOrThrow
    }

    private String randomId() {
        randomUUID().toString()
    }

    void setup() {
        initializer.init()
    }

    void cleanup() {
        initializer.cleanup()
    }

    def "Null is returned when fetching non-exisiting todos"() {
        expect:
        result { store.fetch(randomId()) } == null
    }

    def "Adding new todos and fetching them"() {
        given:
        def todo = new Todo(contents: "my first todo", completed: true)

        when:
        def id = result { store.add(todo) }

        then:
        id

        when:
        def fetched = result { store.fetch(id) }

        then:
        fetched.id == id
        fetched.contents == todo.contents
        fetched.completed == todo.completed
    }

    def "Adding new todos with a preassigned id"() {
        given:
        def id = randomUUID().toString()
        def todo = new Todo(id: id, contents: "todo with id", completed: false)

        expect:
        result { store.add(todo) } == id

        when:
        def fetched = result { store.fetch(id) }

        then:
        fetched.id == id
        fetched.contents == todo.contents
        fetched.completed == todo.completed
    }

    def "Checking if a todo with a given id exists"() {
        given:
        def existingId = result { store.add(new Todo()) }

        expect:
        result { store.exists(existingId) }
        !result { store.exists(randomUUID().toString()) }
    }


    def "Listing all todos"() {
        given:
        result {
            store.add(new Todo(contents: "first"))
                .replace(store.add(new Todo(contents: "second")))
        }

        when:
        def list = result { store.all() }

        then:
        list*.contents == ["first", "second"]
    }

    def "Update returns false when trying to update a non existing todo"() {
        expect:
        !result { store.update(new Todo(id: randomId())) }
    }

    def "Can modify todos"() {
        given:
        def todo = new Todo(contents: "initial", completed: false)

        when:
        def id = result { store.add(todo) }
        def modified = new Todo(id: id, contents: "modified", completed: true)

        then:
        result { store.update(modified) }

        when:
        def fetched = result { store.fetch(id) }

        then:
        fetched.contents == modified.contents
        fetched.completed == modified.completed
    }

    def "Delete returns false when trying to delete non existing todos"() {
        expect:
        !result { store.delete(randomId()) }
    }

    def "Deleting a todo"() {
        when:
        def id = result { store.add(new Todo()) }

        then:
        result { store.delete(id) }

        and:
        result { store.all() }.empty
    }

    def "Clearing all todos"() {
        given:
        result { store.add(new Todo()) }

        when:
        execHarness.execute(store.clear())

        then:
        result { store.all() }.empty
    }
}
