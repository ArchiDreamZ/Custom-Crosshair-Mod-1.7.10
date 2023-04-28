/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.gui.GuiTheme;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementButton;
import sparkless101.crosshairmod.gui.screens.ScreenColourEdit;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementColourEdit
extends ElementBase {
    private RGBA editColour;
    private ElementButton editButton;

    public ElementColourEdit(GuiScreen screen, String text, int x, int y, int width, int height, RGBA colour) {
        super(screen, text, x, y, width, height);
        this.editColour = colour;
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RGBA backgroundColour = this.isMouseOver(mouseX, mouseY) ? GuiTheme.THEME_L3 : GuiTheme.THEME_L5;
        RenderManager.drawBorderedRectangle(this.getPosX(), this.getPosY() + 2, this.getPosX() + this.getWidth() - 2, this.getPosY() + this.getHeight() - 1, 2.0f, GuiTheme.PRIMARY, backgroundColour, true);
        RenderManager.drawFilledRectangle(this.getPosX() + 2, this.getPosY() + 4, this.getPosX() + this.getWidth() - 4, this.getPosY() + this.getHeight() - 3, this.editColour, true);
        RenderManager.drawString(this.getDisplayText(), this.getPosX() + this.getWidth() + 3, this.getPosY() + this.getHeight() - 13, new RGBA(0, 0, 0, 255));
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOver(mouseX, mouseY)) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new ScreenColourEdit(this.editColour, "Edit " + this.getDisplayText() + "..."));
        }
    }
}

