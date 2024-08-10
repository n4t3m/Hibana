package com.n4t3m.hibana.modules.combat;

import com.n4t3m.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class AutoAimModule extends Module {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    public AutoAimModule() {
        super("AutoAim","combat", GLFW.GLFW_KEY_K);
    }

    public void onTick() {
        assert client.world != null;
        assert client.player != null;
        Entity minDistEntity = null;
        float minDistanceTo = 1000;
        for(Entity e : client.world.getEntities()) {
            if (e!=client.player && client.player.distanceTo(e)<6 && (e instanceof AnimalEntity || e instanceof PlayerEntity)) {
                if(client.player.distanceTo(e)<minDistanceTo) {
                    minDistanceTo = client.player.distanceTo(e);
                    minDistEntity = e;
                }
            }
        }
        if(minDistEntity!=null) {
            client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, minDistEntity.getPos());
        }
    }

}
