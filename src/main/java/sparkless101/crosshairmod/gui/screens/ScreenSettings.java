/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Keyboard
 */
package sparkless101.crosshairmod.gui.screens;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.crosshair.properties.types.StringProperty;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementButton;
import sparkless101.crosshairmod.gui.elements.ElementHeaderButton;
import sparkless101.crosshairmod.gui.elements.ElementHelpButton;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.ScreenMain;
import sparkless101.crosshairmod.main.CustomCrosshairMod;
import sparkless101.crosshairmod.utils.RGBA;
import sparkless101.crosshairmod.utils.WebUtils;

public class ScreenSettings
extends Screen {
    private ElementButton button_editKey;
    private ElementButton button_project_mcForums;
    private ElementButton button_project_curseForge;
    private ElementButton button_project_MCbaike;
    private ElementHeaderButton headerButton_return;
    private boolean isEditingKey;
    private String requestedLatestVersion;

    public void initGui() {
        this.isEditingKey = false;
        String editKeyDisplayText = "开启设置菜单的按键: " + (String)CustomCrosshairMod.getCrosshairMod().getCrosshair().properties.keybind_gui.getType();
        this.button_editKey = new ElementButton(this, editKeyDisplayText, 0, 0, RenderManager.getTextWidth(editKeyDisplayText) + 8, 25){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                ScreenSettings.this.isEditingKey = true;
                this.setDisplayText("按下一个按钮...");
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改打开自定义准星菜单的按键。");
            }
        };
        this.button_project_mcForums = new ElementButton(this, "查看 MinecraftForum 帖子", 0, 0, RenderManager.getTextWidth("查看 MinecraftForum 帖子") + 8, 21){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                WebUtils.openInBrowser("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2637819/");
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("链接到 MinecraftForum 帖子.", "(在浏览器中打开).");
            }
        };
        this.button_project_curseForge = new ElementButton(this, "查看 CurseForge 页面", 0, 0, RenderManager.getTextWidth("查看 CurseForge 页面") + 8, 21){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                WebUtils.openInBrowser("https://www.curseforge.com/minecraft/mc-mods/custom-crosshair-mod");
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("链接到 CurseForge 页面.", "(在浏览器中打开).");
            }
        };
        this.button_project_MCbaike = new ElementButton(this, "查看 MC百科 页面", 0, 0, RenderManager.getTextWidth("查看 MC百科 页面") + 8, 21){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                WebUtils.openInBrowser("https://www.mcmod.cn/class/1047.html");
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("链接到 MC百科 页面.", "(在浏览器中打开).");
            }
        };
        this.elementList.clear();
        this.elementList.add(this.button_editKey);
        this.elementList.add(this.button_project_mcForums);
        this.elementList.add(this.button_project_curseForge);
        this.elementList.add(this.button_project_MCbaike);
        int elementY = 44;
        for (Object element : this.elementList) {
            ((ElementBase)element).setPosition(19, elementY);
            ElementHelpButton helpButton = new ElementHelpButton(this, 4, elementY);
            helpButton.setHelpText(((ElementBase)element).getHelpText());
            this.helpButtonList.add(helpButton);
            elementY += ((ElementBase)element).getHeight() + 4;
        }
        this.headerButton_return = new ElementHeaderButton(this, "< 返回", 0, 0, 15){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenSettings.this.mc.displayGuiScreen((GuiScreen)new ScreenMain());
            }
        };
        this.headerButtonList.clear();
        this.headerButtonList.add(this.headerButton_return);
        int headerButtonX = this.width - 4;
        for (ElementHeaderButton headerButton : this.headerButtonList) {
            headerButton.setPosition(headerButtonX - headerButton.getWidth(), 10);
            headerButtonX -= headerButton.getWidth() + 2;
        }
    }

    public void updateScreen() {
        for (ElementBase element : this.elementList) {
            element.updateElement();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int i;
        for (i = 0; i < this.elementList.size(); ++i) {
            ElementBase element = (ElementBase)this.elementList.get(i);
            if (mouseX < element.getPosX() || mouseX > element.getPosX() + element.getWidth() || mouseY < element.getPosY() || mouseY > element.getPosY() + element.getHeight()) continue;
            element.mouseClicked(mouseX, mouseY);
        }
        for (i = 0; i < this.headerButtonList.size(); ++i) {
            ElementHeaderButton headerButton = (ElementHeaderButton)this.headerButtonList.get(i);
            if (mouseX < headerButton.getPosX() || mouseX > headerButton.getPosX() + headerButton.getWidth() || mouseY < headerButton.getPosY() || mouseY > headerButton.getPosY() + headerButton.getHeight()) continue;
            headerButton.mouseClicked(mouseX, mouseY);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY) {
        for (int i = 0; i < this.elementList.size(); ++i) {
            ElementBase item = (ElementBase)this.elementList.get(i);
            item.mouseReleased(mouseX, mouseY);
        }
        super.mouseReleased(mouseX, mouseY);
    }

    protected void keyTyped(char typedChar, int keyCode) {
        for (ElementBase element : this.elementList) {
            element.keyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
        if (this.isEditingKey) {
            CustomCrosshairMod.getCrosshairMod().getCrosshair().properties.keybind_gui.setType(Keyboard.getKeyName((int)keyCode));
            this.initGui();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String message;
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.setToolTip(null);
        if (this.requestedLatestVersion != null && !this.requestedLatestVersion.equals("0.8.4")) {
            message = "检测到新的最新版本: v" + this.requestedLatestVersion + ".";
            RenderManager.drawString(message, this.width - RenderManager.getTextWidth(message) - 5, this.height - 41, new RGBA(255, 180, 0, 255));
        }
        message = "Custom Crosshair Mod v0.8.4";
        RenderManager.drawString(message, this.width - RenderManager.getTextWidth(message) - 5, this.height - 28, new RGBA(0, 0, 0, 255));
        message = "Made by Sparkless101 and 彼梦";
        RenderManager.drawString(message, this.width - RenderManager.getTextWidth(message) - 5, this.height - 15, new RGBA(0, 0, 0, 255));
        for (ElementBase element : this.elementList) {
            element.drawElement(mouseX, mouseY);
        }
        for (ElementHelpButton helpButton : this.helpButtonList) {
            helpButton.drawElement(mouseX, mouseY);
        }
        this.drawHeader();
        for (ElementHeaderButton headerButton : this.headerButtonList) {
            headerButton.drawElement(mouseX, mouseY);
        }
        this.drawToolTip(mouseX, mouseY);
    }

}

