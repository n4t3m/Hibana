package com.natem135.hibana.modules.misc;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;

public class PlayerWatchNotificationModule extends Module {

    private final MinecraftClient client = MinecraftClient.getInstance();

    private final HashSet<String> playersBeingWatchedBy = new HashSet<>();

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
        if(client.world == null || client.player == null) {
            return;
        }
        for(Entity e : client.world.getEntities()) {
            if(e!=client.player && e instanceof PlayerEntity) {
                Vec3d vec3d = e.getRotationVec(1.0f).normalize();
                Vec3d vec3d2 = new Vec3d(client.player.getX() - e.getX() , client.player.getEyeY() - e.getEyeY(), client.player.getZ() - e.getZ());
                double dot_product = vec3d.dotProduct(vec3d2 = vec3d2.normalize());
                if (dot_product > .7 &&  !playersBeingWatchedBy.contains(e.getName().toString()) && client.player.canSee(e)) {
                    Hibana.LOGGER.info(String.format("%s can see me.", e.getName().toString().substring(8, e.getName().toString().length()-1)));
                }
            }
        }
    }
}