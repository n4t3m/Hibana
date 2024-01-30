package com.natem135.hibana.gui;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.gui.screen.Frame;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ClickGUI extends Screen {

    public static final ClickGUI CLICK_GUI_INSTANCE = new ClickGUI();
    private final ArrayList<Frame> frames = new ArrayList<>();


    public ClickGUI() {
        super(Text.of("Click GUI"));
        frames.add(
                new Frame(100, 100, 100, 100, "Demo")
        );
    }

    // DrawScreen equivalent
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for(Frame frame : frames) {
            frame.onRender(context, mouseX, mouseY, delta);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Hibana.LOGGER.info("mouseClicked in ClickGUI Class");
        for(Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
