package com.natem135.hibana.modules.movement;

import com.natem135.hibana.modules.ToggleableModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import static com.natem135.hibana.Hibana.PLAYER_FLY_ENABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.PLAYER_FLY_DISABLED_SOUND_EVENT;
import java.lang.Math;

public class PlayerFlyModule extends ToggleableModule {
    int tick_timer = 0;
    boolean reduced_last_tick = false;

    final double MAX_SPEED = 2.5;

    public PlayerFlyModule() {
        super("Player Fly", GLFW.GLFW_KEY_COMMA);
    }

    @Override
    public void onEnable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = player.getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), PLAYER_FLY_ENABLED_SOUND_EVENT, SoundCategory.MASTER);
        // Init Local for Bypass
        tick_timer = 40;
    }

    @Override
    public void onDisable() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = player.getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), PLAYER_FLY_DISABLED_SOUND_EVENT, SoundCategory.MASTER);
    }

    public void onTick() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        try{
            tick_timer--;
            if(player!=null){
                Vec3d velocity = player.getVelocity();
                double xVelocity, yVelocity, zVelocity;

                GameOptions gameOptions = MinecraftClient.getInstance().options;

                boolean sprintPressed = gameOptions.sprintKey.isPressed();
                boolean jumpPressed = gameOptions.jumpKey.isPressed();
                boolean crouchPressed = gameOptions.sneakKey.isPressed();

                boolean forwardPressed = gameOptions.forwardKey.isPressed();
//                boolean leftPressed = gameOptions.leftKey.isPressed();
//                boolean rightPressed = gameOptions.rightKey.isPressed();
//                boolean backPressed = gameOptions.backKey.isPressed();

                // X and Z Velocity
                if(forwardPressed && sprintPressed) {
                    xVelocity = Math.min(this.MAX_SPEED, velocity.getX()*1.25);
                    zVelocity = Math.min(this.MAX_SPEED, velocity.getZ()*1.25);
                }
                else {
                    xVelocity = velocity.getX();
                    zVelocity = velocity.getZ();
                }

                // Y Velocity
                if (reduced_last_tick) {
                    yVelocity = 0.2;
                    reduced_last_tick = false;
                }
                else if(tick_timer==0) {
                    yVelocity = -0.2;
                    reduced_last_tick = true;
                    tick_timer = 40;
                }
                else if(jumpPressed){
                    yVelocity = 0.5;
                }
                else if(crouchPressed) {
                    yVelocity = -0.5;
                }
                else {
                    yVelocity = 0;
                }
                player.setVelocity(new Vec3d(xVelocity, yVelocity, zVelocity));
            }
        }
        catch (Exception e) {
            assert true;
        }
    }

}
