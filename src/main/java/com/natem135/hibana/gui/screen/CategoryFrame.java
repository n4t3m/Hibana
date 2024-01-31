package com.natem135.hibana.gui.screen;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;

public class CategoryFrame {
    public  int x, y, width, height, dragXOffset, dragYOffset;
    public String category;
    public boolean drag, extend;
    public ArrayList<ModuleButton>  buttons = new ArrayList<>();
    public MinecraftClient client = MinecraftClient.getInstance();


    public CategoryFrame(int x, int y, int width, int height, String category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;

        this.drag = false;
        this.extend = false;

        buttons.add(
                new ModuleButton(
                        this,
                        ModuleManager.xrayModule,
                        0
                )
        );
        buttons.add(
                new ModuleButton(
                        this,
                        ModuleManager.autoRespawnModule,
                        1
                )
        );
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawBorder(!drag ? x : mouseX-dragXOffset, !drag ? y : mouseY-dragYOffset, width, height, Color.green.getRGB());
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
            if(button==0) {
                Hibana.LOGGER.info("MOUSE CLICKED AND HOVERED");
                extend = !extend;
                Hibana.LOGGER.info(String.format("Extent set to: %b", extend));
            }
            else {
                Hibana.LOGGER.info(String.format("Starting drag of Component at Component x %d y %d and Mouse X %.2f Y %.2f", x, y, mouseX, mouseY));
                drag = true;
                dragXOffset = (int) mouseX - this.x;
                dragYOffset = (int) mouseY - this.y;
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button==1) {
            Hibana.LOGGER.info(String.format("Finished Dragging. X: %.2f, Y: %.2f", mouseX, mouseY));
            drag = false;
            x = (int) mouseX - dragXOffset;
            y = (int) mouseY - dragYOffset;
        }
    }


}

