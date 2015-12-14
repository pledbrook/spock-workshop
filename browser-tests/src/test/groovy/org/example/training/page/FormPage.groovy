package org.example.training.page

import geb.Page

class FormPage extends Page {

    static content = {
        submit() { $("button", type: "submit") }
    }

    TodoListPage submitValues(String contentsValue, boolean completedValue) {
        contents = contentsValue
        completed = completedValue
        submit.click()
        browser.at TodoListPage
    }

}
