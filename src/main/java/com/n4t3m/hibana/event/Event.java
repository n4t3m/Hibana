package com.n4t3m.hibana.event;

public abstract class Event {
    boolean cancelled;

    public Event() {
        cancelled = false;
    }

    public void cancelEvent() {
        cancelled = true;
    }

    public boolean isEventCancelled() {
        return cancelled;
    }
}
