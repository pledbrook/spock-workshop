package org.example.training

import geb.spock.GebSpec
import org.example.training.page.AddTodoPage
import org.example.training.page.EditTodoPage
import org.example.training.page.TodoListPage
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.CloseableApplicationUnderTest
import spock.lang.AutoCleanup

class BrowserSpec extends GebSpec {

    @AutoCleanup
    CloseableApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    void setup() {
        browser.baseUrl = aut.getAddress().toString()
    }

    /**
     * <p>TODO: Add a feature method that adds a couple of todos using the form served at {@code /todo}
     * and then verifies that they have been saved using todos table served at {@code /}.</p>
     *
     * <p>Model the pages using classes extending {@link geb.Page} and ensure that you specify at checkers
     * which utilize page titles. Model entries in the todos table as a list of modules. Use content DSL in your
     * pages and models instead of using explicit selectors in the feature method.</p>
     */
    def "Adding new todos"() {
        when:
        to(AddTodoPage).submitValues("My first todo", false)
        to(AddTodoPage).submitValues("My second todo", true)
        def listPage = at TodoListPage

        then:
        listPage.todos*.contents == ["My first todo", "My second todo"]
        listPage.todos*.completed == [false, true]
    }

    /**
     * <p>TODO: Write a feature method that adds a todo, edits it using the form served after clicking on the
     * {@code Edit} button in the todos table and then verifies that the edits have been applied by inspecting
     * the todos table.</p>
     */
    def "Editing todos"() {
        given:
        def listPage = to(AddTodoPage).submitValues("Original contents", true)

        when:
        listPage.todos.first().edit()

        then:
        contents == "Original contents"
        completed

        when:
        listPage = at(EditTodoPage).submitValues("Modified contents", false)

        then:
        listPage.todos*.contents == ["Modified contents"]
        listPage.todos*.completed == [false]
    }

    /**
     * <p>TODO: Write a feature method that adds a couple of todos, then clicks on one of the {@code Delete} buttons
     * in the todos table and verifies that the todo for which the button has been clicked has been removed.</p>
     */
    def "Deleting todos"() {
        given:
        to(AddTodoPage).submitValues("Kept", true)
        to(AddTodoPage).submitValues("Deleted", false)
        def listPage = at TodoListPage

        when:
        listPage.todos.last().delete()

        then:
        listPage.todos*.contents == ["Kept"]
        listPage.todos*.completed == [true]
    }

    /**
     * <p>TODO: Add a feature method that adds a couple of todos, then clicks on the {@code Clear} button located
     * below the todos table and verify that all todos have been removed.</p>
     */
    def "Clearing todos"() {
        to(AddTodoPage).submitValues("First", false)
        to(AddTodoPage).submitValues("Second", false)
        def listPage = at TodoListPage

        when:
        listPage.clear()

        then:
        listPage.todos.size() == 0
    }
}
