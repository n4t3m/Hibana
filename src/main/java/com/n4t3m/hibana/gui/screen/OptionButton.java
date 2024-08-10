package com.n4t3m.hibana.gui.screen;

import com.n4t3m.hibana.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class OptionButton {

    protected final Module module;
    protected final ModuleButton parent;

    protected final int offsetIndex;

    protected final MinecraftClient client = MinecraftClient.getInstance();

    public OptionButton(ModuleButton parent, Module module, int offsetIndex) {
        this.module = module;
        this.parent = parent;
        this.offsetIndex = offsetIndex;
    }

    public int getBorderX() {
        return parent.getBorderX()+this.getWidth();
    }

    public int getBorderY() {
        return parent.getBorderY()+(offsetIndex*this.getHeight());
    }

    public int getFillX1() {
        return parent.getBorderX()+ this.getWidth()+1;
    }

    public int getFillY1() {
        return parent.getBorderY()+(this.getHeight() * offsetIndex)+1;
    }

    public int getFillX2() {
        return parent.getBorderX()+this.getWidth()+this.getWidth()-1;
    }

    public int getFillY2() {
        return this.getFillY1()+this.getHeight()-2;
    }

    public int getWidth() {
        return parent.getWidth();
    }

    public int getHeight() {
        return parent.getHeight()*2;
    }

    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        if(parent.getOptionsExtended()) {
            context.drawBorder(
                    this.getBorderX(), this.getBorderY(), this.getWidth(), this.getHeight(), Color.black.getRGB()
            );
            // context.drawText(client.textRenderer, "Option", getBorderX()+((int)(this.getWidth() - client.textRenderer.getWidth(module.module_name))/2), getBorderY()+((int)(this.getHeight() - client.textRenderer.fontHeight)/2)-2, Color.white.getRGB(), true);
            context.fill(this.getFillX1(), this.getFillY1(), this.getFillX2(), this.getFillY2(), Color.blue.getRGB());
            // Hibana.LOGGER.info(String.format("Fill (%d) %d %d %d %d", offsetIndex,this.getFillX1(), this.getFillY1(), this.getFillX2(), this.getFillY2()));
            // Hibana.LOGGER.info(String.format("Border (%d) %d %d %d %d", offsetIndex,this.getBorderX(), this.getBorderY(), this.getBorderX()+this.getWidth(), this.getBorderY()+this.getHeight()));

        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > this.getFillX1() && mouseX < this.getFillX2() && mouseY > this.getFillY1() && mouseY < this.getFillY2();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {

    }

    public void keyPressed(int keyCode) {

    }
}
