package org.example.training.page

import geb.Page
import org.example.training.module.TodoModule

class TodoListPage extends Page {

    static at = { title == "TODOs" }

    static content = {
        todos { $("tbody tr")*.module(TodoModule) }
        clearButon { $("#clear") }
    }

    void clear() {
        clearButon.click()
    }

}
