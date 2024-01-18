package com.natem135.hibana;

import com.natem135.hibana.modules.PlayerFlyModule;
import com.natem135.hibana.modules.XrayModule;
import net.fabricmc.api.ClientModInitializer;
import com.natem135.hibana.modules.ToggleableModule;
import com.natem135.hibana.modules.BoatFlyModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import java.util.Arrays;
import java.util.List;

public class HibanaClient implements ClientModInitializer {
    public static final BoatFlyModule boatFlyModule = new BoatFlyModule();
    public static final XrayModule xrayModule = new XrayModule();
    public static final PlayerFlyModule playerFlyModule = new PlayerFlyModule();

    List<ToggleableModule> mods = Arrays.asList(
            boatFlyModule,
            xrayModule,
            playerFlyModule
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
