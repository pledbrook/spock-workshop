package org.example.training.persistence

import org.jooq.impl.UpdatableRecordImpl

class TodoRecord extends UpdatableRecordImpl<TodoRecord> {

    TodoRecord() {
        super(TodoTable.TODO)
    }

    void setId(String value) {
        setValue(TodoTable.TODO.ID, value)
    }

    String getId() {
        getValue(TodoTable.TODO.ID)
    }

    void setContents(String value) {
        setValue(TodoTable.TODO.CONTENTS, value)
    }

    String getContents() {
        getValue(TodoTable.TODO.CONTENTS)
    }

    void setCompleted(Boolean value) {
        setValue(TodoTable.TODO.COMPLETED, value)
    }

    Boolean getCompleted() {
        getValue(TodoTable.TODO.COMPLETED)
    }

}
