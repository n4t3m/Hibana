package com.natem135.hibana.modules;

import java.util.Objects;

import com.natem135.hibana.interfaces.ISimpleOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import static com.natem135.hibana.Hibana.XRAY_ENABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.XRAY_DISABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.LOGGER;

public class FullBrightModule extends ToggleableModule {

    public FullBrightModule() {
        super("Fullbright", GLFW.GLFW_KEY_BACKSLASH);
    }

    @Override void onEnable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = Objects.requireNonNull(player).getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), XRAY_ENABLED_SOUND_EVENT, SoundCategory.MASTER);
        // Max Gamma
        MinecraftClient client = MinecraftClient.getInstance();
        ISimpleOption<Double> gamma = (ISimpleOption<Double>)(Object)client.options.getGamma();
        gamma.forceSetValue(1000000000.0);
        LOGGER.info(String.format("Set Gamma To %.2f", client.options.getGamma().getValue()));
    }

    @Override void onDisable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = Objects.requireNonNull(player).getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), XRAY_DISABLED_SOUND_EVENT, SoundCategory.MASTER);
        // Reset Gamma
        MinecraftClient client = MinecraftClient.getInstance();
        SimpleOption<Double> gamma = client.options.getGamma();
        gamma.setValue(0.5);
        LOGGER.info(String.format("Set Gamma To %.2f", client.options.getGamma().getValue()));
    }

    public void onTick() { }

}
