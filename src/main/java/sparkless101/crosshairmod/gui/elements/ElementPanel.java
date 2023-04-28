/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Mouse
 */
package sparkless101.crosshairmod.gui.elements;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import sparkless101.crosshairmod.gui.GuiTheme;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementHelpButton;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementPanel
extends ElementBase {
    private boolean scrollbarEnabled;
    private boolean mouseDown;
    private int boxPosition;
    private int boxHeight;
    private int boxWidth;
    private int offset;
    private int currentValue;
    private int minValue;
    private int maxValue;
    private int elementOffset;
    public int contentHeight;
    public List<ElementBase> elementList = new ArrayList<ElementBase>();
    public List<ElementHelpButton> helpButtonList = new ArrayList<ElementHelpButton>();

    public ElementPanel(GuiScreen screen, int posX, int posY, int width, int height) {
        this(screen, posX, posY, width, height, false);
    }

    public ElementPanel(GuiScreen screen, int posX, int posY, int width, int height, boolean scroll) {
        super(screen, "", posX, posY, width, height);
        this.setScrollbarEnabled(scroll);
        this.contentHeight = 0;
        this.boxPosition = 0;
        this.boxWidth = 10;
        this.boxHeight = 31;
        this.minValue = 0;
        this.maxValue = Math.abs(this.contentHeight - this.getHeight());
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        for (ElementBase element : this.elementList) {
            element.drawElement(mouseX, mouseY);
        }
        for (ElementHelpButton helpButton : this.helpButtonList) {
            helpButton.drawElement(mouseX, mouseY);
        }
        if (this.isScrollbarEnabled()) {
            RenderManager.drawLine(this.getPosX() + this.getWidth() - this.boxWidth - 2, this.getPosY(), this.getPosX() + this.getWidth() - this.boxWidth - 2, this.getPosY() + this.getHeight(), 2.0f, GuiTheme.THEME_L3, true);
            if (this.contentHeight > this.getHeight()) {
                if (this.mouseDown) {
                    int y = this.getPosY() + this.boxPosition;
                    y = mouseY - this.offset;
                    if (y < this.getPosY()) {
                        y = this.getPosY();
                    }
                    if (y > this.getPosY() + this.getHeight() - this.boxHeight) {
                        y = this.getPosY() + this.getHeight() - this.boxHeight;
                    }
                    this.setCurrentPosition(y - this.getPosY());
                } else {
                    int wheel = Mouse.getDWheel();
                    int increment = (int)Math.ceil(this.maxValue / 10);
                    if (wheel > 0) {
                        this.currentValue -= increment;
                    }
                    if (wheel < 0) {
                        this.currentValue += increment;
                    }
                }
            }
            this.setValue(this.currentValue);
            RGBA boxColour = this.isMouseOverBox(mouseX, mouseY) ? GuiTheme.THEME_L1 : GuiTheme.PRIMARY;
            RenderManager.drawFilledRectangle(this.getPosX() + this.getWidth() - this.boxWidth, this.getPosY() + this.boxPosition + 1, this.getPosX() + this.getWidth() - 2, this.getPosY() + this.boxPosition + this.boxHeight - 1, boxColour, true);
        }
    }

    @Override
    public void updateElement() {
        for (ElementBase element : this.elementList) {
            element.updateElement();
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        for (ElementBase element : this.elementList) {
            if (mouseX < element.getPosX() || mouseX > element.getPosX() + element.getWidth() || mouseY < element.getPosY() || mouseY > element.getPosY() + element.getHeight()) continue;
            element.mouseClicked(mouseX, mouseY);
        }
        if (this.isMouseOverBox(mouseX, mouseY)) {
            this.mouseDown = true;
            this.offset = mouseY - (this.getPosY() + this.boxPosition);
        }
        super.mouseClicked(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        for (ElementBase element : this.elementList) {
            element.mouseReleased(mouseX, mouseY);
        }
        this.mouseDown = false;
    }

    @Override
    public void keyTyped(char keyChar, int keyCode) {
        for (ElementBase element : this.elementList) {
            element.keyTyped(keyChar, keyCode);
        }
        switch (keyCode) {
            case 200: {
                this.setValue(this.currentValue - 5);
                break;
            }
            case 208: {
                this.setValue(this.currentValue + 5);
            }
        }
        super.keyTyped(keyChar, keyCode);
    }

    public boolean isScrollbarEnabled() {
        return this.scrollbarEnabled;
    }

    private boolean isMouseOverBox(int mouseX, int mouseY) {
        return mouseX >= this.getPosX() + this.getWidth() - this.boxWidth && mouseX <= this.getPosX() + this.getWidth() - 1 && mouseY >= this.getPosY() + this.boxPosition && mouseY <= this.getPosY() + this.boxPosition + this.boxHeight;
    }

    private void setCurrentPosition(int x) {
        if (this.contentHeight <= this.getHeight()) {
            return;
        }
        this.boxPosition = x;
        this.currentValue = (int)((float)this.minValue + (float)this.boxPosition / (float)(this.getHeight() - this.boxHeight) * (float)(this.maxValue - this.minValue));
    }

    public void setValue(int newValue) {
        if (this.contentHeight <= this.getHeight()) {
            return;
        }
        this.currentValue = newValue;
        if (this.currentValue < this.minValue) {
            this.currentValue = this.minValue;
        }
        if (this.currentValue > this.maxValue) {
            this.currentValue = this.maxValue;
        }
        this.boxPosition = (this.getHeight() - this.boxHeight) * (this.currentValue - this.minValue) / (this.maxValue - this.minValue);
        int elementY = this.getPosY() + 4 - this.currentValue;
        for (int i = 0; i < this.elementList.size(); ++i) {
            this.elementList.get(i).setPosition(19, elementY);
            this.helpButtonList.get(i).setPosition(4, elementY);
            elementY += this.elementList.get(i).getHeight() + 4;
        }
    }

    public void setScrollbarEnabled(boolean newEnabled) {
        this.scrollbarEnabled = newEnabled;
    }

    public void setContentHeight(int newHeight) {
        this.contentHeight = newHeight;
        this.maxValue = Math.abs(this.contentHeight - this.getHeight());
    }
}

