/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public abstract class ElementBase {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private String displayText;
    private GuiScreen currentScreen;
    protected List<String> helpText;

    public ElementBase(GuiScreen screen) {
        this(screen, "no-name", 0, 0, 10, 10);
    }

    public ElementBase(GuiScreen screen, String displayText, int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.displayText = displayText;
        this.setCurrentScreen(screen);
        this.helpText = new ArrayList<String>();
    }

    public void updateElement() {
    }

    public void keyTyped(char keyChar, int keyCode) {
    }

    public void mouseClicked(int mouseX, int mouseY) {
    }

    public void mouseReleased(int mouseX, int mouseY) {
    }

    public void drawElement(int mouseX, int mouseY) {
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.getPosX() && mouseX <= this.getPosX() + this.getWidth() && mouseY >= this.getPosY() && mouseY <= this.getPosY() + this.getHeight();
    }

    public void setCurrentScreen(GuiScreen screen) {
        this.currentScreen = screen;
    }

    public GuiScreen getCurrentScreen() {
        return this.currentScreen;
    }

    public void setPosition(int newPosX, int newPosY) {
        this.setPosX(newPosX);
        this.setPosY(newPosY);
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int newPosX) {
        this.posX = newPosX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int newPosY) {
        this.posY = newPosY;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public void setDisplayText(String newDisplayText) {
        this.displayText = newDisplayText;
    }

    public void setHelpText(List<String> newHelpText) {
        this.helpText = newHelpText;
    }

    public List<String> getHelpText() {
        return this.helpText;
    }
}

