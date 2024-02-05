package com.natem135.hibana.gui.screen;

import com.natem135.hibana.modules.Module;
import com.natem135.hibana.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton {
    private final Module module;
    public final CategoryFrame parent;

    public MinecraftClient client = MinecraftClient.getInstance();

    public ArrayList<OptionButton> options = new ArrayList<>();

    private boolean optionsExpanded;

    private final int offsetIndex;

    public ModuleButton(CategoryFrame parent, Module module, int offsetIndex) {
        this.module = module;
        this.parent = parent;
        this.offsetIndex = offsetIndex;
        this.optionsExpanded = false;

        options.add(new KeybindOptionButton(this, this.module, 0));
        // options.add(new KeybindOptionButton(this, this.module, 1));
    }

    public int getBorderX() {
        return parent.x;
    }

    public int getBorderY() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex);
    }

    public int getFillX1() {
        return parent.x+1;
    }

    public int getFillY1() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex)+1;
    }

    public int getFillX2() {
        return parent.x+parent.width-1;
    }

    public int getFillY2() {
        return parent.y+parent.height+((3*parent.height/4)*offsetIndex-1)+(3*parent.height/4);
    }

    public int getWidth() {
        return parent.width;
    }

    public int getHeight() {
        return 3*parent.height/4;
    }


    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawBorder(
                this.getBorderX(), this.getBorderY(), parent.width, 3*parent.height/4, Color.green.getRGB()
        );
        context.drawText(client.textRenderer, module.module_name, getBorderX()+((int)(parent.width - client.textRenderer.getWidth(module.module_name))/2), getBorderY()+((int)(parent.height - client.textRenderer.fontHeight)/2)-2, Color.white.getRGB(), true);
        context.fill(getFillX1(), getFillY1(), getFillX2(), getFillY2(), module.module_enabled ? Color.lightGray.getRGB() : Color.black.getRGB());
        // Hibana.LOGGER.info(String.format("x1 %d y1 %d x2 %d y2 %d", parent.x, parent.y+parent.height+((int)(parent.height/2)*offsetIndex), parent.x+parent.width, (int)parent.y+parent.height+((int)(parent.height/2)*offsetIndex)+(int)(parent.height/2), Color.black.getRGB()));
        for(OptionButton option : options) {
            option.onRender(context, mouseX, mouseY, delta);
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > getBorderX() && mouseX < getBorderX() + parent.width && mouseY > getBorderY() && mouseY < getBorderY() + 3*parent.height/4.0;
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(button==0 && parent.extend && isHovered(mouseX, mouseY)) {
            this.module.toggle();
        } else if (button==1 && isHovered(mouseX, mouseY)) {
            this.optionsExpanded = !this.optionsExpanded;
        }
        for(OptionButton option : options) {
            option.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for(OptionButton option : options) {
            option.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean getOptionsExtended() {
        return this.optionsExpanded;
    }

}
