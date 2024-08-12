package com.n4t3m.hibana.event;

import net.minecraft.text.Text;

public class ChatMessageEvent extends Event{
    private final Text chatMessage;
    private boolean isCancelled;

    public ChatMessageEvent(Text chatMessage) {
        this.chatMessage = chatMessage;
        this.isCancelled = false;
    }

    public void markAsCancelled() {
        this.isCancelled = true;
    }

    public boolean getCancelledStatus() {
        return this.isCancelled;
    }

    public Text getChatMessage() {
        return chatMessage;
    }
}
