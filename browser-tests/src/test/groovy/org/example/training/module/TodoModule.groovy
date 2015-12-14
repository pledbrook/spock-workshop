package org.example.training.module

import geb.Module
import org.example.training.page.EditTodoPage

class TodoModule extends Module {

    static content = {
        contents { $("td", 0).text() }
        completed { $("td", 1).text().toBoolean() }
        editButton(to: EditTodoPage) { $("td", 2).find("a") }
        deleteButton() { $("td", 3).find("button") }
    }

    void edit() {
        editButton.click()
    }

    void delete() {
        deleteButton.click()
    }

}
