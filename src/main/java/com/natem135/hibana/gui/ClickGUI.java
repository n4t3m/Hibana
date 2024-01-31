package com.natem135.hibana.gui;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.gui.screen.CategoryFrame;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ClickGUI extends Screen {

    public static final ClickGUI CLICK_GUI_INSTANCE = new ClickGUI();
    private final ArrayList<CategoryFrame> categoryFrames = new ArrayList<>();


    public ClickGUI() {
        super(Text.of("Click GUI"));
        categoryFrames.add(
                new CategoryFrame(100, 100, 100, 20, "Demo")
        );
    }

    // DrawScreen equivalent
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for(CategoryFrame frame : categoryFrames) {
            frame.onRender(context, mouseX, mouseY, delta);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Hibana.LOGGER.info("mouseClicked in ClickGUI Class");
        for(CategoryFrame frame : categoryFrames) {
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(CategoryFrame frame : categoryFrames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
