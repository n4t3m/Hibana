package com.natem135.hibana;

import com.natem135.hibana.modules.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Arrays;
import java.util.List;

public class HibanaClient implements ClientModInitializer {
    public static final BoatFlyModule boatFlyModule = new BoatFlyModule();
    public static final XrayModule xrayModule = new XrayModule();
    public static final PlayerFlyModule playerFlyModule = new PlayerFlyModule();
    public static final FullBrightModule fullBrightModule = new FullBrightModule();
    public static final AutoFishSoundModule autoFishSoundModule = new AutoFishSoundModule();
    public  static final AutoRespawnModule autoRespawnModule = new AutoRespawnModule();
    public  static final AutoFishEntityModule autoFishEntityModule = new AutoFishEntityModule();

    public static List<ToggleableModule> mods = Arrays.asList(
            boatFlyModule,
            xrayModule,
            playerFlyModule,
            fullBrightModule,
            autoFishSoundModule,
            autoRespawnModule,
            autoFishEntityModule
    );

    private static final String[] colorCodeMap = {
            "§c", // Red
            "§6", // Gold (Orange Substitute)
            "§6", // Yellow
            "§2", // Dark Green
            "§9", // Blue
            "§d", // Light Purple
            "§5" // Purple
    };

    public static String generateRainbowString(String input) {
        StringBuilder rainbowString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            double percentage = (double) i / (input.length() - 1);
            int colorIndex = (int) Math.round(percentage * (colorCodeMap.length - 1));
            String colorCode = colorCodeMap[colorIndex];

            rainbowString.append(colorCode).append(currentChar);
        }

        return rainbowString.toString();
    }


    @Override
    public void onInitializeClient() {
        for(ToggleableModule mod : mods) {
            Hibana.LOGGER.info(String.format("Initializing %s...", mod.module_name));
            KeyBindingHelper.registerKeyBinding(mod.keybind);
            ClientTickEvents.END_CLIENT_TICK.register(mod::tick);
        }
        HudRenderCallback.EVENT.register(HibanaClient::render);

    }

    private static void render(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        // drawContext.drawText(client.textRenderer, "§cT§6e§6s§2t§9W§do§5rd", 0, 10, 16777215, true);
        int enabled_modules = 0;
        for(ToggleableModule module : HibanaClient.mods) {
            if(module.module_enabled) {
                drawContext.drawText(client.textRenderer, generateRainbowString(module.module_name), 0, 10+(enabled_modules*10), 16777215, true);
                enabled_modules++;
            }
        }
        // drawContext.drawText(client.textRenderer, String.format("§1§3§4§5§6§7§2%s§r", "NATEM135"), 100, 100, 16777215, true);

    }

}
