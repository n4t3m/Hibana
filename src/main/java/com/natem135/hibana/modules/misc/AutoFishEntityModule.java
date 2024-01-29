package com.natem135.hibana.modules.misc;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.CaughtFishEvent;
import com.natem135.hibana.event.Listener;
import com.natem135.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class AutoFishEntityModule extends Module {

    Consumer<CaughtFishEvent> onRecv = AutoFishEntityModule::onFishCaught;
    Listener recvPacketListener = new Listener(onRecv);

    private static int tick_timer = 0;
    private static boolean rod_cool_down = false;

    public AutoFishEntityModule() {
        super("AutoFish (Entity-Based)", GLFW.GLFW_KEY_B);
    }

    @Override
    public void onEnable() {
        Hibana.getEventManager().addListener(CaughtFishEvent.class, recvPacketListener);
    }

    @Override
    public void onDisable() {
        Hibana.getEventManager().removeListener(CaughtFishEvent.class, recvPacketListener);
    }

    public void onTick() {
        tick_timer--;
        if(module_enabled && tick_timer==0 && rod_cool_down) {
            // Recast Rod
            castRod();
        }
    }

    public static void onFishCaught(CaughtFishEvent event) {
        if(!rod_cool_down) {
            castRod();
            rod_cool_down = true;
            tick_timer = 25;
        }
    }

    private static void castRod() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerInteractionManager interactionManager = client.interactionManager;
        if(interactionManager != null) {
            interactionManager.interactItem(client.player, Hand.MAIN_HAND);
        }
        rod_cool_down = false;
    }

}
