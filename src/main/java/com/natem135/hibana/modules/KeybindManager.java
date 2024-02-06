package com.natem135.hibana.modules;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.gui.ClickGUI;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    public static void onKeyPress(int key, int action) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(action == GLFW.GLFW_PRESS && key == GLFW.GLFW_KEY_RIGHT_SHIFT && client.currentScreen==null) {
            Hibana.LOGGER.info(String.format("Enable GUI Key %d Action %d inside IF enable attempt", key, action));
            ClickGUI.CLICK_GUI_INSTANCE.preventImmediateClose();
            client.setScreen(ClickGUI.CLICK_GUI_INSTANCE);
            return;
        }
        else {
            Hibana.LOGGER.info(String.format("Did Not Enable GUI Key %d Action %d inside else", key, action));
        }
        if(action == GLFW.GLFW_PRESS && !ClickGUI.CLICK_GUI_INSTANCE.isSomeModuleRebinding() && client.currentScreen==null) {
            for(Module m : ModuleManager.mods) {
                if(m.keyCode == key) {
                    m.toggle();
                }
            }
        }
    }


}
