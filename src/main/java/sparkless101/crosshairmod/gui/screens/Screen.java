/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.opengl.GL11
 */
package sparkless101.crosshairmod.gui.screens;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.CrosshairConfig;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.gui.GuiTheme;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementHeaderButton;
import sparkless101.crosshairmod.gui.elements.ElementHelpButton;
import sparkless101.crosshairmod.main.CustomCrosshairMod;
import sparkless101.crosshairmod.utils.RGBA;

public abstract class Screen
extends GuiScreen {
    protected List<ElementBase> elementList = new ArrayList<ElementBase>();
    private List<String> toolTip = new ArrayList<String>();
    protected List<ElementHeaderButton> headerButtonList = new ArrayList<ElementHeaderButton>();
    protected List<ElementHelpButton> helpButtonList = new ArrayList<ElementHelpButton>();

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (ElementHeaderButton headerButton : this.headerButtonList) {
            CrosshairConfig.writeConfigFile(CustomCrosshairMod.getCrosshairMod().getCrosshair().properties);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void mouseMovedOrUp(int mouseX, int mouseY, int actionType) {
        super.mouseMovedOrUp(mouseX, mouseY, actionType);
        this.mouseReleased(mouseX, mouseY);
    }

    protected void mouseReleased(int mouseX, int mouseY) {
    }

    public void onGuiClosed() {
        CrosshairConfig.writeConfigFile(CustomCrosshairMod.getCrosshairMod().getCrosshair().properties);
        super.onGuiClosed();
    }

    private int getToolTipMaxWidth() {
        int max = 0;
        for (int i = 0; i < this.getToolTip().size(); ++i) {
            int currentWidth = RenderManager.getTextWidth(this.getToolTip().get(i));
            if (currentWidth <= max) continue;
            max = currentWidth;
        }
        return max;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.drawBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawBackground() {
        RenderManager.drawFilledRectangle(0.0f, 0.0f, this.width, this.height, GuiTheme.WHITE, true);
    }

    public void drawHeader() {
        RenderManager.drawFilledRectangle(0.0f, 0.0f, this.width, 35.0f, GuiTheme.THEME_L5, true);
        RenderManager.drawFilledRectangle(0.0f, 35.0f, this.width, 40.0f, GuiTheme.PRIMARY, true);
        RenderManager.drawString("Custom Crosshair Mod", 4, 15, new RGBA(0, 0, 0, 255));
        GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
        RenderManager.drawString("v0.8.3", (4 + RenderManager.getTextWidth("Custom Crosshair Mod") + 4) * 2, 36, new RGBA(0, 0, 0, 255));
        GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
    }

    public void drawToolTip(int mouseX, int mouseY) {
        if (this.getToolTip() != null) {
            RenderManager.drawBorderedRectangle(mouseX + 5, mouseY + 5, mouseX + this.getToolTipMaxWidth() + 9, mouseY + this.getToolTip().size() * 11 + 8, 2.0f, GuiTheme.PRIMARY, GuiTheme.THEME_L2, true);
            for (int i = 0; i < this.getToolTip().size(); ++i) {
                RenderManager.drawString(this.getToolTip().get(i), mouseX + 8, mouseY + i * 11 + 9, new RGBA(255, 255, 255, 255));
            }
        }
    }

    public List<String> getToolTip() {
        return this.toolTip;
    }

    public void setToolTip(List<String> toolTip) {
        this.toolTip = toolTip;
    }
}

