package com.natem135.hibana.gui.screen;

import com.natem135.hibana.modules.Module;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModuleButton {
    private final Module module;
    private final CategoryFrame parent;

    private final int offsetIndex;

    public ModuleButton(CategoryFrame parent, Module module, int offsetIndex) {
        this.module = module;
        this.parent = parent;
        this.offsetIndex = offsetIndex;
    }

    private int getBorderX() {
        return parent.x;
    }

    private int getBorderY() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex);
    }

    private int getFillX1() {
        return parent.x+1;
    }

    private int getFillY1() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex)+1;
    }

    private int getFillX2() {
        return parent.x+parent.width-1;
    }

    private int getFillY2() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex-1)+(3*parent.height/4)-1;
    }


    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawBorder(
                this.getBorderX(), this.getBorderY(), parent.width, 3*parent.height/4, Color.green.getRGB()
        );
        context.fill(getFillX1(), getFillY1(), getFillX2(), getFillY2(), Color.black.getRGB());
        // Hibana.LOGGER.info(String.format("x1 %d y1 %d x2 %d y2 %d", parent.x, parent.y+parent.height+((int)(parent.height/2)*offsetIndex), parent.x+parent.width, (int)parent.y+parent.height+((int)(parent.height/2)*offsetIndex)+(int)(parent.height/2), Color.black.getRGB()));
    }


}
