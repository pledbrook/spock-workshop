package org.example.training

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.example.training.persistence.DatabaseInitializer
import org.example.training.persistence.TodoStore
import org.jooq.DSLContext
import org.jooq.impl.DSL

import javax.sql.DataSource

import static org.jooq.SQLDialect.H2

class ApplicationModule extends AbstractModule {

    protected void configure() {
        bind(TodoStore)
        bind(DatabaseInitializationService)
        bind(DatabaseInitializer)
    }

    @Provides
    DSLContext provideJooqContext(DataSource dataSource) {
        DSL.using(dataSource, H2)
    }

}
