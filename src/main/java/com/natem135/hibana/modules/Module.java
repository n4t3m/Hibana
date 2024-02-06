package com.natem135.hibana.modules;

import net.minecraft.client.MinecraftClient;
import static com.natem135.hibana.Hibana.LOGGER;

public abstract class Module {
    public final String module_name;

    public final String categoryName;
    public boolean module_enabled = false;

    public int keyCode;

    public Module(String module_name, String category_name, int code) {
        this.module_name = module_name;
        this.categoryName = category_name;
        this.keyCode = code;
    }

    public void tick(MinecraftClient client) {
        if (module_enabled) {
            this.onTick();
        }
    }

    public String getModuleName() {
        return this.module_name;
    }

    public void toggle() {
        if(module_enabled) {
            this.disable();
        }
        else {
            this.enable();
        }
    }

    private void enable() {
        this.module_enabled = true;
        this.onEnable();
        LOGGER.info(String.format("Enabled %s", this.module_name));
    }

    private void disable() {
        this.module_enabled = false;
        this.onDisable();
        LOGGER.info(String.format("Disabled %s", this.module_name));
    }

    public abstract void onTick();
    public void onEnable() {}
    public void onDisable() {}

}
