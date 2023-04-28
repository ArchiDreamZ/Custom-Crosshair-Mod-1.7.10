/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements;

import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.gui.GuiTheme;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementButton;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementHeaderButton
extends ElementButton {
    public ElementHeaderButton(GuiScreen screen, String text, int x, int y, int height) {
        super(screen, text, x, y, 0, height);
        this.setWidth(RenderManager.getTextWidth(text) + 8);
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RGBA backgroundColour = this.isMouseOver(mouseX, mouseY) ? GuiTheme.THEME_L4 : GuiTheme.THEME_L5;
        RenderManager.drawFilledRectangle(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), backgroundColour, true);
        RenderManager.drawString(this.getDisplayText(), this.getPosX() + (this.getWidth() / 2 - RenderManager.getTextWidth(this.getDisplayText()) / 2), this.getPosY() + (this.getHeight() / 2 - 3), new RGBA(0, 0, 0, 255));
    }
}

