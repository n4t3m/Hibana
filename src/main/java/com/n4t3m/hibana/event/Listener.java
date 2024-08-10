package com.n4t3m.hibana.event;

import java.util.function.Consumer;

public class Listener {
    Consumer<? extends Event> action;

    public Listener(Consumer<? extends Event> action) {
        this.action = action;
    }

    public Consumer<? extends Event> getAction() {
        return this.action;
    }
}
