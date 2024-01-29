package com.natem135.hibana.modules;

import com.natem135.hibana.modules.render.XrayModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Arrays;
import java.util.List;

public class ModuleManager {
    public static final BoatFlyModule boatFlyModule = new BoatFlyModule();
    public static final XrayModule xrayModule = new XrayModule();
    public static final PlayerFlyModule playerFlyModule = new PlayerFlyModule();
    public static final FullBrightModule fullBrightModule = new FullBrightModule();
    public static final AutoFishSoundModule autoFishSoundModule = new AutoFishSoundModule();
    public  static final AutoRespawnModule autoRespawnModule = new AutoRespawnModule();
    public  static final AutoFishEntityModule autoFishEntityModule = new AutoFishEntityModule();
    private static final String[] colorCodeMap = {
            "§c", // Red
            "§6", // Gold (Orange Substitute)
            "§6", // Yellow
            "§2", // Dark Green
            "§9", // Blue
            "§d", // Light Purple
            "§5" // Purple
    };
    public static List<ToggleableModule> mods = Arrays.asList(
            boatFlyModule,
            xrayModule,
            playerFlyModule,
            fullBrightModule,
            autoFishSoundModule,
            autoRespawnModule,
            autoFishEntityModule
    );

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

    public static void renderEnabledModList(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        int enabled_modules = 0;
        for(ToggleableModule module : mods) {
            if(module.module_enabled) {
                drawContext.drawText(client.textRenderer, generateRainbowString(module.module_name), 3, 5+(enabled_modules*10), 16777215, true);
                enabled_modules++;
            }
        }
        //  mods.stream().filter(module -> module.module_enabled).forEach(module -> );
    }
}
