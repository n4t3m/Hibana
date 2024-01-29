package com.natem135.hibana;

import com.natem135.hibana.modules.*;
import com.natem135.hibana.modules.Module;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import java.util.Comparator;

public class HibanaClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ModuleManager.mods.sort(Comparator.comparing(Module::getModuleName));
        for(Module mod : ModuleManager.mods) {
            Hibana.LOGGER.info(String.format("Initializing %s...", mod.module_name));
            KeyBindingHelper.registerKeyBinding(mod.keybind);
            ClientTickEvents.END_CLIENT_TICK.register(mod::tick);
        }
        HudRenderCallback.EVENT.register(ModuleManager::renderEnabledModList);

    }

}
