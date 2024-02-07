package com.natem135.hibana.mixin;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.modules.KeybindManager;
import com.natem135.hibana.modules.ModuleManager;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method="onKey", at=@At("HEAD"))
    public void onKeyMixin(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        KeybindManager.onKeyPress(key, action);
    }
}
