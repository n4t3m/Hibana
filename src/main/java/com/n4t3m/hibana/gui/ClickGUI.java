package com.n4t3m.hibana.gui;

import com.n4t3m.hibana.gui.screen.CategoryFrame;
import com.n4t3m.hibana.gui.screen.KeybindOptionButton;
import com.n4t3m.hibana.modules.Module;
import com.n4t3m.hibana.modules.ModuleManager;
import com.n4t3m.hibana.Hibana;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClickGUI extends Screen {

    public static final ClickGUI CLICK_GUI_INSTANCE = new ClickGUI();
    private final ArrayList<CategoryFrame> categoryFrames = new ArrayList<>();

    private final AtomicBoolean dragLock;

    private final AtomicBoolean rebindLock;

    private final AtomicBoolean shouldClose;

    public ClickGUI() {
        super(Text.of("Click GUI"));
        HashSet<String>categories = new HashSet<>();
        for(Module m : ModuleManager.mods) {
            categories.add(m.categoryName);
        }
        int _offset = 120+5;
        int _index = 0;
        for(String category : categories) {
            categoryFrames.add(
                    new CategoryFrame((_offset*_index), 100, 120, 20, category));
                    _index++;
        }
        dragLock = new AtomicBoolean(false);
        rebindLock = new AtomicBoolean(false);
        shouldClose = new AtomicBoolean(true);
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

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if ((keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT || keyCode==GLFW.GLFW_KEY_ESCAPE)) {
            if(this.isSomeModuleRebinding()) {
                this.cancelRebind();
            }
            if(this.shouldClose.get()) {
                Hibana.LOGGER.info("Closing!");
                this.close();
            }
            if(!this.shouldClose.get()) {
                this.shouldClose.set(true);
            }
            return true;
        }
        for(CategoryFrame frame : categoryFrames) {
            frame.keyPressed(keyCode);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean requestRebindLock() {
        return rebindLock.compareAndSet(false, true);
    }

    public void releaseRebindLock() {
        rebindLock.set(false);
    }

    public boolean isSomeModuleRebinding() {
        return rebindLock.get();
    }

    public void cancelRebind() {
        Hibana.LOGGER.info("Cancelling Rebind Process");
        categoryFrames.forEach(category -> category.buttons.forEach(moduleButton -> moduleButton.options.forEach(option -> {
            if(option instanceof KeybindOptionButton button) {
                if(button.isBeingRebinded()) {
                    button.endRebindProcess();
                }
            }
        }
        )));
    }

    public void preventImmediateClose() {
        this.shouldClose.set(false);
    }
}
