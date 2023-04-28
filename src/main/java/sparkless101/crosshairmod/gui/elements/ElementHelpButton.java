/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.elements;

import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.gui.elements.ElementButton;
import sparkless101.crosshairmod.gui.screens.Screen;

public class ElementHelpButton
extends ElementButton {
    public ElementHelpButton(GuiScreen screen, int posX, int posY) {
        super(screen, "?", posX, posY, 11, 11);
    }

    @Override
    public void drawElement(int mouseX, int mouseY) {
        super.drawElement(mouseX, mouseY);
        if (this.isMouseOver(mouseX, mouseY) && this.getCurrentScreen() instanceof Screen) {
            ((Screen)this.getCurrentScreen()).setToolTip(this.getHelpText());
        }
    }
}

