package org.example.training.events;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pledbrook on 10/12/2015.
 */
public class EventBus {
    private List<Listener> listeners = new LinkedList<Listener>();

    public EventBus register(Listener l) {
        listeners.add(l);
        return this;
    }

    public void send(String msg) {
        for (Listener l : listeners) {
            l.onMessage(msg);
        }
    }

    public void sendAsync(String msg, Callback callback) {
        final List<Listener> listenerListCopy = new ArrayList<>(listeners);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (Listener l : listenerListCopy) {
                    l.onMessage(msg);
                }
                callback.onComplete();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
