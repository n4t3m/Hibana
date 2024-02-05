package com.natem135.hibana.mixin;

import com.natem135.hibana.interfaces.IKeyBinding;
import net.minecraft.client.Keyboard;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements IKeyBinding {
    @Shadow
    private InputUtil.Key boundKey;

    @Override
    public InputUtil.Key getBoundKey() {
        return boundKey;
    }
}
