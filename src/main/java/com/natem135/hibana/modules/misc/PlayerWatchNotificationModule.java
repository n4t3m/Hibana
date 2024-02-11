package com.natem135.hibana.modules.misc;

import com.natem135.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class PlayerWatchNotificationModule extends Module {

    private final MinecraftClient client = MinecraftClient.getInstance();

    public PlayerWatchNotificationModule() {
        super("Watched Notify","misc", GLFW.GLFW_KEY_V);
    }

    @Override
    public void onEnable() {
        if(client.player != null) {
            client.player.sendMessage(Text.of("Player watch notifications have been enabled!"));
        }
    }

    @Override
    public void onDisable() {
        if(client.player != null) {
            client.player.sendMessage(Text.of("Player watch notifications have been disabled!"));
        }
    }

    public void onTick() {
    }

}
