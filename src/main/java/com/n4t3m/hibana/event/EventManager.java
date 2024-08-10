package com.n4t3m.hibana.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class EventManager {
    public HashMap<Class<? extends Event>, ArrayList<Listener>> map = new HashMap<>();

    public void addListener(Class<? extends Event> event_class, Listener listener) {
        ArrayList<Listener> listeners = map.computeIfAbsent(event_class, k -> new ArrayList<>());
        listeners.add(0, listener);
    }

    public void removeListener(Class<? extends Event> event_class, Listener listener) {
        ArrayList<Listener> listeners = map.get(event_class);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    public void notifyListeners(Event event) {
        ArrayList<Listener> listeners = map.get(event.getClass());
        if (listeners != null) {
            for (Listener l : listeners) {
                @SuppressWarnings("unchecked")
                Consumer<Event> action = (Consumer<Event>) l.getAction();
                action.accept(event);
            }
        }
    }
}
