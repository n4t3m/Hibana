package com.natem135.hibana.gui.screen;

import com.natem135.hibana.Hibana;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class Frame {
    public  int x, y, width, height, dragX, dragY;
    public String category;
    public boolean drag, extendToCategory;
    public MinecraftClient client = MinecraftClient.getInstance();


    public Frame(int x, int y, int width, int height, String category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;

        this.drag = false;
        this.extendToCategory = false;
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawBorder(x, y, width, height, Color.green.getRGB());
        context.fill(x,y, width, height, Color.black.getRGB());

    }

    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX , mouseY)) {
            Hibana.LOGGER.info("MOUSE CLICKED AND HOVERED");
        }
        else {
            Hibana.LOGGER.info("MOUSE CLICKED AND NOT HOVERED");
        }
    }
}

