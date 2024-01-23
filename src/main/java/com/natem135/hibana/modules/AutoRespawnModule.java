package com.natem135.hibana.modules;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.Listener;
import com.natem135.hibana.event.ReceivePacketEvent;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class AutoRespawnModule extends ToggleableModule {

    Consumer<ReceivePacketEvent> onRecv = AutoFishSoundModule::onReceivePacket;
    Listener recvPacketListener = new Listener(onRecv);

    public AutoRespawnModule() {
        super("AutoRespawn", GLFW.GLFW_KEY_C);
    }

    @Override void onEnable() {
        Hibana.getEventManager().addListener(ReceivePacketEvent.class, recvPacketListener);
    }

    @Override void onDisable() {
        Hibana.getEventManager().removeListener(ReceivePacketEvent.class, recvPacketListener);
    }

    public void onTick() {
    }
}
