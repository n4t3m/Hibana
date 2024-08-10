package com.n4t3m.hibana.gui.screen;

import com.n4t3m.hibana.Hibana;
import com.n4t3m.hibana.gui.ClickGUI;
import com.n4t3m.hibana.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryFrame {
    public  int x, y, width, height, dragXOffset, dragYOffset;
    public String category;
    public boolean drag, extend;
    public ArrayList<ModuleButton> buttons = new ArrayList<>();
    public MinecraftClient client = MinecraftClient.getInstance();


    public CategoryFrame(int x, int y, int width, int height, String category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;

        this.drag = false;
        this.extend = false;

        AtomicInteger _index = new AtomicInteger();
        ModuleManager.mods.stream().filter(module -> Objects.equals(module.categoryName, category))
                .forEach(module -> {
                    buttons.add(new ModuleButton(this, module, _index.get()));
                    _index.getAndIncrement();
                });
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawBorder(x, y, width, height, Color.pink.getRGB());
        context.drawText(client.textRenderer, category, x+((int)(width - client.textRenderer.getWidth(category))/2)+1, y+((int)(height - client.textRenderer.fontHeight)/2)+1, Color.white.getRGB(), true);
        context.fill(x+1,y+1, x+width-1, y+height-1, Color.black.getRGB());
        if(extend) {
            for(ModuleButton button : buttons) {
                button.onRender(context, mouseX, mouseY, delta);
            }
        }
    }

    public void updatePositionIfDragged(DrawContext context, int mouseX, int mouseY, float delta) {
        if(drag) {
            x = mouseX - dragXOffset;
            y = mouseY - dragYOffset;
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX , mouseY)) {
            if(button==1) {
                Hibana.LOGGER.info("MOUSE CLICKED AND HOVERED");
                extend = !extend;
                Hibana.LOGGER.info(String.format("Extent set to: %b", extend));
            }
            else {
                Hibana.LOGGER.info(String.format("Starting drag of Component at Component x %d y %d and Mouse X %.2f Y %.2f", x, y, mouseX, mouseY));
                if(ClickGUI.CLICK_GUI_INSTANCE.requestDragLock()) {
                    drag = true;
                    dragXOffset = (int) mouseX - this.x;
                    dragYOffset = (int) mouseY - this.y;
                }
            }
        }
        for(ModuleButton module : buttons) {
            module.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(drag && button==0) {
            Hibana.LOGGER.info(String.format("Finished Dragging. X: %.2f, Y: %.2f", mouseX, mouseY));
            drag = false;
            x = (int) mouseX - dragXOffset;
            y = (int) mouseY - dragYOffset;
            ClickGUI.CLICK_GUI_INSTANCE.releaseDragLock();
        }
        for(ModuleButton module : buttons) {
            module.mouseReleased(mouseX, mouseY, button);
        }
    }

    public void keyPressed(int keyCode) {
        for(ModuleButton module : buttons) {
            module.keyPressed(keyCode);
        }
    }



}

