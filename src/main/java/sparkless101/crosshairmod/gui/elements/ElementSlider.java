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

public class ElementSlider
extends ElementBase {
    private int minValue;
    private int maxValue;
    private int value;
    private boolean mouseDown;
    private int offset;
    private int boxPosition;
    private int boxWidth;
    private RGBA boxColour = null;

    public ElementSlider(GuiScreen screen, String displayText, int posX, int posY, int width, int height, int minValue, int maxValue) {
        super(screen, displayText, posX, posY, width, height);
        this.setMinMaxValue(minValue, maxValue);
        this.boxWidth = 15;
        this.boxPosition = 1;
        this.offset = 0;
        this.mouseDown = false;
        this.value = 0;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOverBox(mouseX, mouseY)) {
            this.mouseDown = true;
            this.offset = mouseX - (this.getPosX() + this.boxPosition);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.mouseDown = false;
        this.setValue(this.value);
    }

    @Override
    public void updateElement() {
        this.onValueChanged();
    }

    public void onValueChanged() {
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RenderManager.drawBorderedRectangle(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 2.0f, GuiTheme.PRIMARY, GuiTheme.THEME_L5, true);
        RGBA boxColour = this.boxColour != null ? this.boxColour : (this.isMouseOverBox(mouseX, mouseY) || this.mouseDown ? GuiTheme.THEME_L1 : GuiTheme.PRIMARY);
        int x = this.getPosX() + this.boxPosition;
        if (this.mouseDown) {
            x = mouseX - this.offset;
            if (x < this.getPosX() + 2) {
                x = this.getPosX() + 1;
            }
            if (x > this.getPosX() + this.getWidth() - this.boxWidth - 1) {
                x = this.getPosX() + this.getWidth() - this.boxWidth - 1;
            }
            this.setCurrentPosition(x - this.getPosX());
        }
        RenderManager.drawFilledRectangle(x + 1, this.getPosY() + 2, x + this.boxWidth - 1, this.getPosY() + this.getHeight() - 2, boxColour, true);
        RenderManager.drawString(this.getDisplayText() + ": " + this.getValue(), this.getPosX() + this.getWidth() + 3, this.getPosY() + this.getHeight() / 2 - 3, new RGBA(0, 0, 0, 255));
    }

    private boolean isMouseOverBox(int mouseX, int mouseY) {
        return mouseX >= this.getPosX() + this.boxPosition && mouseX <= this.getPosX() + this.boxPosition + this.boxWidth && mouseY >= this.getPosY() + 1 && mouseY <= this.getPosY() + this.getHeight() - 1;
    }

    private void setCurrentPosition(int x) {
        this.boxPosition = x;
        this.value = (int)((float)this.getMinValue() + (float)(this.boxPosition - 1) / (float)(this.getWidth() - this.boxWidth - 2) * (float)(this.maxValue - this.minValue));
    }

    public void setValue(int newValue) {
        this.value = newValue;
        this.boxPosition = (this.getWidth() - this.boxWidth - 2) * (this.getValue() - this.minValue) / (this.maxValue - this.minValue) + 1;
    }

    public void setMinMaxValue(int min, int max) {
        this.minValue = min;
        this.maxValue = max;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public int getValue() {
        return this.value;
    }

    public void setBoxColour(RGBA newColour) {
        this.boxColour = newColour;
    }
}

