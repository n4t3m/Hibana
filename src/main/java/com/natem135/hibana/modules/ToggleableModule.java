package com.natem135.hibana.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import static com.natem135.hibana.Hibana.LOGGER;

import java.util.Locale;

public abstract class ToggleableModule {
    public KeyBinding keybind;
    public final String module_name;
    public boolean module_enabled = false;

    ToggleableModule(String module_name, int code) {
        keybind = new KeyBinding(
                "key.hibana." + this.getClass().getSimpleName().toLowerCase(Locale.ROOT) + "_toggle",
                code,
                "category.hibana"
                );
        this.module_name = module_name;
    }

    public void tick(MinecraftClient client) {
        if (keybind.wasPressed()) {
            LOGGER.info("Keybind was pressed!");
            this.toggle();
        }
        if (module_enabled) {
            this.onTick();
        }
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

    abstract void onTick();
    void onEnable() {}
    void onDisable() {}

}
