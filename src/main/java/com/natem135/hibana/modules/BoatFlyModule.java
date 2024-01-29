package com.natem135.hibana.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import static com.natem135.hibana.Hibana.*;

public class BoatFlyModule extends ToggleableModule {
    int tick_timer = 0;
    boolean reduced_last_tick = false;

    public BoatFlyModule() {
        super("Boat Fly", GLFW.GLFW_KEY_BACKSLASH);
    }

    @Override
    public void onEnable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = player.getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), BOAT_FLY_ENABLED_EVENT, SoundCategory.MASTER);
        // Init Local for Bypass
        tick_timer = 40;
    }

    @Override
    public void onDisable() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = player.getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), BOAT_FLY_DISABLED_EVENT, SoundCategory.MASTER);
    }

    public void onTick() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        try{
            tick_timer--;
            if(player!=null && player.hasVehicle()){
                Entity vehicle = player.getVehicle();
                Vec3d velocity = vehicle.getVelocity();
                // Determine Vertical Velocity
                double xVelocity, yVelocity, zVelocity;

                GameOptions gameOptions = MinecraftClient.getInstance().options;

                // X Velocity
                if(player.isSprinting() || gameOptions.sprintKey.isPressed()) {
                    xVelocity = velocity.getX()*1.1;
                    zVelocity = velocity.getZ()*1.1;
                }
                else {
                    xVelocity = velocity.getX();
                    zVelocity = velocity.getZ();
                }

                // Y Velocity
                if (reduced_last_tick) {
                    yVelocity = 0.75;
                    reduced_last_tick = false;
                }
                else if(tick_timer==0) {
                    yVelocity = -0.2;
                    reduced_last_tick = true;
                    tick_timer = 40;
                }
                else if(player.input.jumping){
                    yVelocity = 0.5;
                }
                else {
                    yVelocity = 0.04;
                }
                vehicle.setVelocity(new Vec3d(xVelocity, yVelocity, zVelocity));
            }
        }
        catch (Exception e) {
            assert true;
        }
    }

}
