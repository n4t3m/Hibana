package com.natem135.hibana.modules;

import java.util.ArrayList;
import java.util.Objects;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.interfaces.ISimpleOption;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.block.Block;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import static com.natem135.hibana.Hibana.XRAY_ENABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.XRAY_DISABLED_SOUND_EVENT;
import static com.natem135.hibana.Hibana.LOGGER;

public class XrayModule extends ToggleableModule {
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    private double starting_gamma = 0.0;

    public XrayModule() {
        super("Xray", GLFW.GLFW_KEY_X);
        blocks.add(Blocks.DIAMOND_ORE);
        blocks.add(Blocks.IRON_ORE);
        blocks.add(Blocks.COAL_ORE);
        blocks.add(Blocks.GOLD_ORE);
        blocks.add(Blocks.EMERALD_ORE);
        module_enabled = false;
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
        client.worldRenderer.reload();
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
        client.worldRenderer.reload();
    }

    public void onTick() { };

    public static boolean shouldRender(Block block) {
        return blocks.contains(block);
    }

}
