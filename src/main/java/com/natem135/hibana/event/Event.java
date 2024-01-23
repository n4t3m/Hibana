package com.natem135.hibana.event;

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
