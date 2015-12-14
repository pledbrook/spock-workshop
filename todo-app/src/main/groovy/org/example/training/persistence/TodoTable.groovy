package org.example.training.persistence

import org.jooq.TableField
import org.jooq.UniqueKey
import org.jooq.impl.AbstractKeys
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

class TodoTable extends TableImpl<TodoRecord> {

    final Class<? extends TodoRecord> recordType = TodoRecord

    public static final TodoTable TODO = new TodoTable()

    public final TableField<TodoRecord, String> ID = createField("ID", SQLDataType.VARCHAR, this)
    public final TableField<TodoRecord, String> CONTENTS = createField("CONTENTS", SQLDataType.VARCHAR, this)
    public final TableField<TodoRecord, Boolean> COMPLETED = createField("COMPLETED", SQLDataType.BOOLEAN, this)

    TodoTable() {
        super("TODO")
    }

    @Override
    UniqueKey<TodoRecord> getPrimaryKey() {
        Keys.PRIMARY
    }

    private static class Keys extends AbstractKeys {
        public final static UniqueKey<TodoRecord> PRIMARY = createUniqueKey(TODO, TODO.ID)
    }
}
