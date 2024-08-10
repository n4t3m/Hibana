package com.n4t3m.hibana.modules;

import com.n4t3m.hibana.gui.ClickGUI;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    public static void onKeyPress(int key, int action) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(action == GLFW.GLFW_PRESS && key == GLFW.GLFW_KEY_RIGHT_SHIFT && client.currentScreen==null) {
            ClickGUI.CLICK_GUI_INSTANCE.preventImmediateClose();
            client.setScreen(ClickGUI.CLICK_GUI_INSTANCE);
            return;
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
