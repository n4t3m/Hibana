package com.natem135.hibana.gui;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.gui.screen.CategoryFrame;
import com.natem135.hibana.modules.Module;
import com.natem135.hibana.modules.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClickGUI extends Screen {

    public static final ClickGUI CLICK_GUI_INSTANCE = new ClickGUI();
    private final ArrayList<CategoryFrame> categoryFrames = new ArrayList<>();

    private final AtomicBoolean dragLock;


    public ClickGUI() {
        super(Text.of("Click GUI"));
        HashSet<String>categories = new HashSet<>();
        for(Module m : ModuleManager.mods) {
            categories.add(m.categoryName);
        }
        int _offset = 40;
        int _index = 0;
        for(String category : categories) {
            categoryFrames.add(
                    new CategoryFrame(100 + (_offset*_index), 100, 100, 40, category));
                    _index++;
        }
        dragLock = new AtomicBoolean(false);
    }

    // DrawScreen equivalent
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for(CategoryFrame frame : categoryFrames) {
            frame.updatePositionIfDragged(context, mouseX, mouseY, delta);
            frame.onRender(context, mouseX, mouseY, delta);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Hibana.LOGGER.info("mouseClicked in ClickGUI Class");
        for (int i = categoryFrames.size() - 1; i >= 0; i--) {
            CategoryFrame frame = categoryFrames.get(i);
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

    public boolean requestDragLock() {
        return dragLock.compareAndSet(false, true);
    }

    public void releaseDragLock() {
        dragLock.set(false);
    }

}
