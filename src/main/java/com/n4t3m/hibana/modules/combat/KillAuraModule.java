package com.n4t3m.hibana.modules.combat;

import com.n4t3m.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAuraModule extends Module {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    public KillAuraModule() {
        super("Kill Aura","combat", GLFW.GLFW_KEY_R);
    }

    public void onTick() {
        if(client.world == null || client.player == null || client.interactionManager == null) {
            return;
        }
        if(client.player.getAttackCooldownProgress(0.5f) != 1.0) {
            return;
        }
        Entity minDistEntity = null;
        float minDistanceTo = 1000;
        for(Entity e : client.world.getEntities()) {
            if (e!=client.player && client.player.distanceTo(e)<6 && (e instanceof AnimalEntity || e instanceof PlayerEntity || e instanceof HostileEntity)) {
                if(client.player.distanceTo(e)<minDistanceTo && client.player.canSee(e)) {
                    minDistanceTo = client.player.distanceTo(e);
                    minDistEntity = e;
                }
            }
        }
        if(minDistEntity!=null) {
            client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, minDistEntity.getPos());
            ClientPlayerInteractionManager clientPlayerInteractionManager = client.interactionManager;
            // Send attack packet then swing hand packet
            clientPlayerInteractionManager.attackEntity(client.player, minDistEntity);
            client.player.swingHand(Hand.MAIN_HAND);
        }
    }

}
