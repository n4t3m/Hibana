package com.natem135.hibana;

import com.natem135.hibana.gui.ClickGUI;
import com.natem135.hibana.modules.*;
import com.natem135.hibana.modules.Module;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import java.util.Comparator;
import java.util.Locale;

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
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    KeyBinding keybind = new KeyBinding(
            "key.hibana." + this.getClass().getSimpleName().toLowerCase(Locale.ROOT) + "_toggle",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.hibana"
    );
    public void onTick(MinecraftClient client) {
        if(keybind.wasPressed()) {
            if(client.currentScreen!=null && client.currentScreen.equals(ClickGUI.CLICK_GUI_INSTANCE)) {
                ClickGUI.CLICK_GUI_INSTANCE.close();
            }
            else {
                client.setScreen(ClickGUI.CLICK_GUI_INSTANCE);
            }
        }

    }

}
