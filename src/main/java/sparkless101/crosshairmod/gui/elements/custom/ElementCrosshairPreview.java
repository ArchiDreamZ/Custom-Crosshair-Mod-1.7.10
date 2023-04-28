/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements.custom;

import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.crosshair.render.CrosshairRenderer;
import sparkless101.crosshairmod.gui.GuiTheme;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.main.CustomCrosshairMod;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementCrosshairPreview
extends ElementBase {
    public ElementCrosshairPreview(GuiScreen screen, int posX, int posY, int width, int height) {
        super(screen, "", posX, posY, width, height);
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RenderManager.drawRectangle(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 2.0f, GuiTheme.PRIMARY, true);
        CustomCrosshairMod.getCrosshairMod().getRenderer().draw(this.getPosX() + this.getWidth() / 2, this.getPosY() + this.getHeight() / 2);
    }
}

