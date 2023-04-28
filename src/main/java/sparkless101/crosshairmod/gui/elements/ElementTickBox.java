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

public class ElementTickBox
extends ElementBase {
    private boolean checked;

    public ElementTickBox(GuiScreen screen, String text, int x, int y) {
        this(screen, text, x, y, false);
    }

    public ElementTickBox(GuiScreen screen, String text, int x, int y, boolean startChecked) {
        super(screen, text, x, y, 11, 11);
        this.setChecked(startChecked);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        this.toggleChecked();
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RGBA backgroundColour = this.isMouseOver(mouseX, mouseY) ? GuiTheme.THEME_L3 : GuiTheme.THEME_L5;
        RenderManager.drawBorderedRectangle(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 2.0f, GuiTheme.PRIMARY, backgroundColour, true);
        RenderManager.drawString(this.getDisplayText(), this.getPosX() + this.getWidth() + 5, this.getPosY() + this.getHeight() / 2 - 3, new RGBA(0, 0, 0, 255));
        if (this.getChecked()) {
            RenderManager.drawFilledRectangle(this.getPosX() + 2, this.getPosY() + 2, this.getPosX() + this.getWidth() - 2, this.getPosY() + this.getHeight() - 2, new RGBA(50, 255, 50, 255), true);
        }
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setChecked(boolean newChecked) {
        this.checked = newChecked;
    }

    public void toggleChecked() {
        this.checked = !this.checked;
    }
}

