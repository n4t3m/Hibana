package com.natem135.hibana.modules;

import java.util.Objects;

import com.natem135.hibana.util.GammaUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import static com.natem135.hibana.Hibana.FULLBRIGHT_ENABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.FULLBRIGHT_DISABLED_SOUND_EVENT;

public class FullBrightModule extends ToggleableModule {

    public FullBrightModule() {
        super("Fullbright", GLFW.GLFW_KEY_BACKSLASH);
    }

    @Override
    public void onEnable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = Objects.requireNonNull(player).getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), FULLBRIGHT_ENABLED_SOUND_EVENT, SoundCategory.MASTER);
        // Maximize Gamma
        MinecraftClient client = MinecraftClient.getInstance();
        GammaUtils.setMaxGamma(client);
    }

    @Override
    public void onDisable() {
        // Play Sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        World world = Objects.requireNonNull(player).getWorld();
        world.playSound(MinecraftClient.getInstance().player, player.getX(), player.getY(), player.getZ(), FULLBRIGHT_DISABLED_SOUND_EVENT, SoundCategory.MASTER);
        // Reset Gamma
        MinecraftClient client = MinecraftClient.getInstance();
        GammaUtils.resetToDefaultGamma(client);
    }

    public void onTick() { }

}
