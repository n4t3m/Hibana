package com.n4t3m.hibana.modules.misc;

import com.n4t3m.hibana.Hibana;
import com.n4t3m.hibana.event.ChatMessageEvent;
import com.n4t3m.hibana.event.Listener;
import com.n4t3m.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class StreamerModeModule extends Module {
    Consumer<ChatMessageEvent> onRecv = StreamerModeModule::onChatMessage;
    Listener chatMessageListener = new Listener(onRecv);

    public StreamerModeModule() {
        super("Streamer Mode","misc", GLFW.GLFW_KEY_O);
    }

    @Override
    public void onEnable() {
        Hibana.getEventManager().addListener(ChatMessageEvent.class, chatMessageListener);
    }

    @Override
    public void onDisable() {
        Hibana.getEventManager().removeListener(ChatMessageEvent.class, chatMessageListener);
    }
    public static void onChatMessage(ChatMessageEvent event) {
        String playerUsername = MinecraftClient.getInstance().getSession().getUsername();
        if(event.getChatMessage().getString().contains(playerUsername)) {
            event.markAsCancelled();
        }
    }

    public void onTick() {}


}
