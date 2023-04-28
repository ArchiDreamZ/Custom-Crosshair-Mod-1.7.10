/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements;

import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.utils.RGBA;

public class ElementLabel
extends ElementBase {
    private int displayTextColour;

    public ElementLabel(GuiScreen screen, String displayText, int posX, int posY) {
        this(screen, displayText, posX, posY, 0);
    }

    public ElementLabel(GuiScreen screen, String displayText, int posX, int posY, int textColour) {
        super(screen, displayText, posX, posY, RenderManager.getTextWidth(displayText), 10);
        this.displayTextColour = textColour;
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        RenderManager.drawString(this.getDisplayText(), this.getPosX(), this.getPosY(), new RGBA(0, 0, 0, 255));
    }
}

