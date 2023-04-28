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
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementButton
extends ElementBase {
    public ElementButton(GuiScreen screen) {
        super(screen);
    }

    public ElementButton(GuiScreen screen, String text, int x, int y, int width, int height) {
        super(screen, text, x, y, width, height);
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RGBA backgroundColour = this.isMouseOver(mouseX, mouseY) ? GuiTheme.THEME_L4 : GuiTheme.THEME_L5;
        RenderManager.drawBorderedRectangle(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 2.0f, GuiTheme.PRIMARY, backgroundColour, true);
        RenderManager.drawString(this.getDisplayText(), this.getPosX() + this.getWidth() / 2 - RenderManager.getTextWidth(this.getDisplayText()) / 2 + 1, this.getPosY() + this.getHeight() / 2 - 3, new RGBA(0, 0, 0, 255));
    }
}

