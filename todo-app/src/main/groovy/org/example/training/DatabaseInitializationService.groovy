package org.example.training

import org.example.training.persistence.DatabaseInitializer
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.server.StopEvent

import javax.inject.Inject

class DatabaseInitializationService implements Service {

    DatabaseInitializer databaseInitializer

    @Inject
    DatabaseInitializationService(DatabaseInitializer databaseInitializer) {
        this.databaseInitializer = databaseInitializer
    }

    void onStart(StartEvent event) throws Exception {
        databaseInitializer.init()
    }

    void onStop(StopEvent event) throws Exception {
        databaseInitializer.cleanup()
    }
}
