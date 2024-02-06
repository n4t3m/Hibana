package com.natem135.hibana.gui.screen;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.gui.ClickGUI;
import com.natem135.hibana.modules.Module;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class KeybindOptionButton extends OptionButton {

    private boolean isRebinding;

    public KeybindOptionButton(ModuleButton parent, Module module, int offsetIndex) {
        super(parent, module, offsetIndex);
        isRebinding = false;
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        super.onRender(context, mouseX, mouseY, delta);
        if(this.parent.getOptionsExtended()) {
            String _text = this.getOptionText();
            context.drawText(this.client.textRenderer, _text, this.getBorderX() + ((this.getWidth() - client.textRenderer.getWidth(_text))/2), this.getBorderY() + ((this.getHeight() - client.textRenderer.fontHeight) / 2), Color.white.getRGB(), true);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        if(parent.getOptionsExtended() && isHovered(mouseX, mouseY)) {
            Hibana.LOGGER.info("Clicked on Binding Button");
            if(ClickGUI.CLICK_GUI_INSTANCE.requestRebindLock()) {
                isRebinding = true;
            } else if (isRebinding) {
                isRebinding = false;
                ClickGUI.CLICK_GUI_INSTANCE.releaseRebindLock();
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        if (isRebinding) {
            //module.keybind.setBoundKey(InputUtil.fromKeyCode(keyCode, 0));
            module.keyCode = keyCode;
            isRebinding = false;
            ClickGUI.CLICK_GUI_INSTANCE.releaseRebindLock();
        }
    }

    private String getOptionText() {
        if(isRebinding) {
            return "Rebinding. Click to Cancel.";
        }
        return String.format("Keybind: %s", module.keyCode==0 ? "None" : GLFW.glfwGetKeyName(module.keyCode, GLFW.glfwGetKeyScancode(module.keyCode)));
    }
}
