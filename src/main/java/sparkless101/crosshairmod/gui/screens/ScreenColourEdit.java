/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package sparkless101.crosshairmod.gui.screens;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementHeaderButton;
import sparkless101.crosshairmod.gui.elements.ElementHelpButton;
import sparkless101.crosshairmod.gui.elements.ElementLabel;
import sparkless101.crosshairmod.gui.elements.ElementSlider;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.ScreenMain;
import sparkless101.crosshairmod.utils.RGBA;

public class ScreenColourEdit
extends Screen {
    private ElementLabel label_title;
    private ElementSlider slider_red;
    private ElementSlider slider_green;
    private ElementSlider slider_blue;
    private ElementSlider slider_opacity;
    private ElementHeaderButton headerButton_return;
    private RGBA colour;
    private String title;

    public ScreenColourEdit(RGBA colour) {
        this(colour, "Edit Colour...");
    }

    public ScreenColourEdit(RGBA colour, String text) {
        this.colour = colour;
        this.title = text;
    }

    public void initGui() {
        this.label_title = new ElementLabel(this, this.title, 0, 0);
        this.slider_red = new ElementSlider(this, "Red", 0, 0, 255, 11, 0, 255){

            @Override
            public void onValueChanged() {
                ScreenColourEdit.this.colour.setRed(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes how red the colour is.");
            }
        };
        this.slider_red.setValue(this.colour.getRed());
        this.slider_red.setBoxColour(new RGBA(255, 0, 0, 255));
        this.slider_green = new ElementSlider(this, "Green", 0, 0, 255, 11, 0, 255){

            @Override
            public void onValueChanged() {
                ScreenColourEdit.this.colour.setGreen(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes how green the colour is.");
            }
        };
        this.slider_green.setValue(this.colour.getGreen());
        this.slider_green.setBoxColour(new RGBA(0, 255, 0, 255));
        this.slider_blue = new ElementSlider(this, "Blue", 0, 0, 255, 11, 0, 255){

            @Override
            public void onValueChanged() {
                ScreenColourEdit.this.colour.setBlue(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes how blue the colour is.");
            }
        };
        this.slider_blue.setValue(this.colour.getBlue());
        this.slider_blue.setBoxColour(new RGBA(0, 0, 255, 255));
        this.slider_opacity = new ElementSlider(this, "Opacity", 0, 0, 255, 11, 0, 255){

            @Override
            public void onValueChanged() {
                ScreenColourEdit.this.colour.setOpacity(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the opacity of the colour.");
            }
        };
        this.slider_opacity.setValue(this.colour.getOpacity());
        this.elementList.clear();
        this.elementList.add(this.label_title);
        this.elementList.add(this.slider_red);
        this.elementList.add(this.slider_green);
        this.elementList.add(this.slider_blue);
        this.elementList.add(this.slider_opacity);
        this.helpButtonList.clear();
        int elementY = 44;
        int contentHeight = 0;
        for (Object element : this.elementList) {
            ((ElementBase)element).setPosition(19, elementY);
            ElementHelpButton helpButton = new ElementHelpButton(this, 4, elementY);
            helpButton.setHelpText(((ElementBase)element).getHelpText());
            this.helpButtonList.add(helpButton);
            elementY += ((ElementBase)element).getHeight() + 4;
            contentHeight += ((ElementBase)element).getHeight() + 4;
        }
        this.headerButton_return = new ElementHeaderButton(this, "< Return", 0, 0, 15){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenColourEdit.this.mc.displayGuiScreen((GuiScreen)new ScreenMain());
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
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.setToolTip(null);
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

