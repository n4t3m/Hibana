package com.n4t3m.hibana.modules.misc;

import com.n4t3m.hibana.Hibana;
import com.n4t3m.hibana.event.ClientPlayerDeathEvent;
import com.n4t3m.hibana.event.Listener;
import com.n4t3m.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class AutoRespawnModule extends Module {

    Consumer<ClientPlayerDeathEvent> playerDeathConsumer = AutoRespawnModule::onClientPlayerDeath;
    Listener onClientPlayerDeathListener = new Listener(playerDeathConsumer);

    public AutoRespawnModule() {
        super("AutoRespawn","misc", GLFW.GLFW_KEY_BACKSLASH);
    }

    @Override
    public void onEnable() {
        Hibana.getEventManager().addListener(ClientPlayerDeathEvent.class, onClientPlayerDeathListener);
    }

    @Override
    public void onDisable() {
        Hibana.getEventManager().removeListener(ClientPlayerDeathEvent.class, onClientPlayerDeathListener);
    }

    public void onTick() {
    }

    public static void onClientPlayerDeath(ClientPlayerDeathEvent event) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            player.requestRespawn();
        }
    }
}
