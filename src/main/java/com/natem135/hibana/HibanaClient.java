package com.natem135.hibana;

import com.natem135.hibana.modules.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import java.util.Arrays;
import java.util.List;

public class HibanaClient implements ClientModInitializer {
    public static final BoatFlyModule boatFlyModule = new BoatFlyModule();
    public static final XrayModule xrayModule = new XrayModule();
    public static final PlayerFlyModule playerFlyModule = new PlayerFlyModule();
    public static final FullBrightModule fullBrightModule = new FullBrightModule();

    List<ToggleableModule> mods = Arrays.asList(
            boatFlyModule,
            xrayModule,
            playerFlyModule,
            fullBrightModule
    );

    @Override
    public void onInitializeClient() {
        for(ToggleableModule mod : mods) {
            Hibana.LOGGER.info(String.format("Initializing %s...", mod.module_name));
            KeyBindingHelper.registerKeyBinding(mod.keybind);
            ClientTickEvents.END_CLIENT_TICK.register(mod::tick);
        }
    }
}
