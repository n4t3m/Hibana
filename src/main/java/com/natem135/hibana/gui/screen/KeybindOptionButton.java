package com.natem135.hibana.gui.screen;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.interfaces.IKeyBinding;
import com.natem135.hibana.modules.Module;
import net.minecraft.client.gui.DrawContext;
import org.apache.commons.logging.Log;

import java.awt.*;
import java.util.logging.Logger;

public class KeybindOptionButton extends OptionButton {
    public KeybindOptionButton(ModuleButton parent, Module module, int offsetIndex) {
        super(parent, module, offsetIndex);
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        super.onRender(context, mouseX, mouseY, delta);
        if(this.parent.getOptionsExtended()) {
            IKeyBinding x = (IKeyBinding)(Object)module.keybind;
            Hibana.LOGGER.info(String.format("%s, %s %s", module.keybind.getBoundKeyLocalizedText().toString(), module.keybind.getTranslationKey(), x.getBoundKey().getLocalizedText().getContent().toString()));
            String _text = String.format("Keybind: %s", module.keybind.getBoundKeyLocalizedText().toString().substring(8, module.keybind.getBoundKeyLocalizedText().toString().length()-1));
            context.drawText(this.client.textRenderer, _text, this.getBorderX() + ((this.getWidth() - client.textRenderer.getWidth(_text))/2), this.getBorderY() + ((this.getHeight() - client.textRenderer.fontHeight) / 2), Color.white.getRGB(), true);
        }
    }
}
