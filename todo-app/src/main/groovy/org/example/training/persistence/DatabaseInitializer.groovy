package org.example.training.persistence

import groovy.sql.Sql

import javax.inject.Inject
import javax.sql.DataSource

class DatabaseInitializer {

    private final DataSource dataSource

    @Inject
    DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource
    }

    void init() {
        new Sql(dataSource).execute '''
            CREATE TABLE TODO(ID VARCHAR NOT NULL PRIMARY KEY, CONTENTS VARCHAR, COMPLETED BOOLEAN)
        '''
    }

    void cleanup() {
        new Sql(dataSource).execute '''
            DROP ALL OBJECTS
        '''
    }
}
