/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.client.GuiIngameForge
 */
package sparkless101.crosshairmod.gui.screens;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.GuiIngameForge;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.CrosshairType;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.crosshair.properties.types.BooleanProperty;
import sparkless101.crosshairmod.crosshair.properties.types.CrosshairTypeProperty;
import sparkless101.crosshairmod.crosshair.properties.types.IntegerProperty;
import sparkless101.crosshairmod.crosshair.properties.types.RgbaProperty;
import sparkless101.crosshairmod.gui.elements.ElementBase;
import sparkless101.crosshairmod.gui.elements.ElementColourEdit;
import sparkless101.crosshairmod.gui.elements.ElementHeaderButton;
import sparkless101.crosshairmod.gui.elements.ElementHelpButton;
import sparkless101.crosshairmod.gui.elements.ElementPanel;
import sparkless101.crosshairmod.gui.elements.ElementSlider;
import sparkless101.crosshairmod.gui.elements.ElementTickBox;
import sparkless101.crosshairmod.gui.elements.custom.ElementCrosshairPreview;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.ScreenSettings;
import sparkless101.crosshairmod.main.CustomCrosshairMod;
import sparkless101.crosshairmod.utils.RGBA;

public class ScreenMain
extends Screen {
    private Crosshair crosshair;
    private ElementPanel panel_container;
    private ElementTickBox tickBox_mod_enabled;
    private ElementTickBox tickBox_visible_default;
    private ElementTickBox tickBox_visible_hiddenGui;
    private ElementTickBox tickBox_visible_debug;
    private ElementTickBox tickbox_visible_thirdPerson;
    private ElementTickBox tickBox_outline_enabled;
    private ElementTickBox tickBox_dot_enabled;
    private ElementTickBox tickBox_dynamic_bow_enabled;
    private ElementTickBox tickBox_highlight_hostile_enabled;
    private ElementTickBox tickBox_highlight_passive_enabled;
    private ElementTickBox tickBox_highlight_player_enabled;
    private ElementTickBox tickBox_rainbow_enabled;
    private ElementColourEdit colourEdit_crosshair_base;
    private ElementColourEdit colourEdit_outline;
    private ElementColourEdit colourEdit_dot;
    private ElementColourEdit colourEdit_highlight_hostile;
    private ElementColourEdit colourEdit_highlight_passive;
    private ElementColourEdit colourEdit_highlight_player;
    private ElementSlider slider_crosshair_type;
    private ElementSlider slider_crosshair_width;
    private ElementSlider slider_crosshair_height;
    private ElementSlider slider_crosshair_gap;
    private ElementSlider slider_crosshair_thickness;
    private ElementSlider slider_crosshair_rotation;
    private ElementSlider slider_rainbow_speed;
    private ElementHeaderButton headerButton_settings;
    private ElementHeaderButton headerButton_reset;
    private ElementCrosshairPreview crosshairPreview;

    public void initGui() {
        this.crosshair = CustomCrosshairMod.getCrosshairMod().getCrosshair();
        this.panel_container = new ElementPanel(this, 0, 0, this.width, this.height - 40, true);
        this.panel_container.setPosition(0, 40);
        this.tickBox_mod_enabled = new ElementTickBox(this, "Mod Enabled", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.mod_enabled.setType(this.getChecked());
                GuiIngameForge.renderCrosshairs = !this.getChecked();
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Enables or disables the Custom Crosshair mod.");
            }
        };
        this.tickBox_mod_enabled.setChecked((Boolean)this.crosshair.properties.mod_enabled.getType());
        this.tickBox_visible_default = new ElementTickBox(this, "Visible by Default", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_default.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Shows or hides the crosshair.");
            }
        };
        this.tickBox_visible_default.setChecked((Boolean)this.crosshair.properties.visible_default.getType());
        this.tickBox_visible_hiddenGui = new ElementTickBox(this, "Visible when hidden GUI", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_hiddenGui.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Shows or hides the crosshair when the GUI (F1 Mode) is hidden.");
            }
        };
        this.tickBox_visible_hiddenGui.setChecked((Boolean)this.crosshair.properties.visible_hiddenGui.getType());
        this.tickBox_visible_debug = new ElementTickBox(this, "Visible in Debug UI", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_debug.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Shows or hides the crosshair when in the debug UI (F3 Mode).");
            }
        };
        this.tickBox_visible_debug.setChecked((Boolean)this.crosshair.properties.visible_debug.getType());
        this.tickbox_visible_thirdPerson = new ElementTickBox(this, "Visible in Third Person", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_thirdPerson.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Shows or hides the crosshair when in third person mode.");
            }
        };
        this.tickbox_visible_thirdPerson.setChecked((Boolean)this.crosshair.properties.visible_thirdPerson.getType());
        this.tickBox_outline_enabled = new ElementTickBox(this, "Outline Enabled", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.outline_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Draws an outline around the crosshair.");
            }
        };
        this.tickBox_outline_enabled.setChecked((Boolean)this.crosshair.properties.outline_enabled.getType());
        this.tickBox_dot_enabled = new ElementTickBox(this, "Dot Enabled", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.dot_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Draws a dot at the centre of the screen.");
            }
        };
        this.tickBox_dot_enabled.setChecked((Boolean)this.crosshair.properties.dot_enabled.getType());
        this.tickBox_dynamic_bow_enabled = new ElementTickBox(this, "Dynamic Bow Enabled", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.dynamic_bow_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("When using a bow, indicates the duration of the pull animation.");
            }
        };
        this.tickBox_dynamic_bow_enabled.setChecked((Boolean)this.crosshair.properties.dynamic_bow_enabled.getType());
        this.tickBox_highlight_hostile_enabled = new ElementTickBox(this, "Highlight Hostiles", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_hostile_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Highlights hostile mobs within reaching distance.");
            }
        };
        this.tickBox_highlight_hostile_enabled.setChecked((Boolean)this.crosshair.properties.highlight_hostile_enabled.getType());
        this.tickBox_highlight_passive_enabled = new ElementTickBox(this, "Highlight Passives", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_passive_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Highlights passive mobs within reaching distance.");
            }
        };
        this.tickBox_highlight_passive_enabled.setChecked((Boolean)this.crosshair.properties.highlight_passive_enabled.getType());
        this.tickBox_highlight_player_enabled = new ElementTickBox(this, "Highlight Players", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_player_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Highlights players within reaching distance.");
            }
        };
        this.tickBox_highlight_player_enabled.setChecked((Boolean)this.crosshair.properties.highlight_player_enabled.getType());
        this.tickBox_rainbow_enabled = new ElementTickBox(this, "Rainbow Crosshair Enabled", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.rainbow_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Crosshair changes colour to the rainbow!");
            }
        };
        this.tickBox_rainbow_enabled.setChecked((Boolean)this.crosshair.properties.rainbow_enabled.getType());
        this.colourEdit_crosshair_base = new ElementColourEdit(this, "Crosshair Base Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.crosshair_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the base colour of the crosshair.");
            }
        };
        this.colourEdit_outline = new ElementColourEdit(this, "Outline Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.outline_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the outline colour of the crosshair.");
            }
        };
        this.colourEdit_dot = new ElementColourEdit(this, "Dot Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.dot_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the dot colour of the crosshair.");
            }
        };
        this.colourEdit_highlight_hostile = new ElementColourEdit(this, "Highlight Hostiles Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_hostile_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the highlight colour for hostile mobs.");
            }
        };
        this.colourEdit_highlight_passive = new ElementColourEdit(this, "Highlight Passives Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_passive_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the highlight colour for passive mobs.");
            }
        };
        this.colourEdit_highlight_player = new ElementColourEdit(this, "Highlight Players Colour", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_player_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the highlight colour for players.");
            }
        };
        this.slider_crosshair_type = new ElementSlider(this, "Type", 0, 0, 120, 11, 0, 5){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_type.setType(CrosshairType.getTypeFromByte((byte)this.getValue()));
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the crosshair type", "[0 = Cross]", "[1 = Circle]", "[2 = Square]", "[3 = Default]", "[4 = Arrow]");
            }
        };
        this.slider_crosshair_type.setValue(((CrosshairType)((Object)this.crosshair.properties.crosshair_type.getType())).getValue());
        this.slider_crosshair_width = new ElementSlider(this, "Width", 0, 0, 150, 11, 1, 100){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_width.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the horizontal width of the crosshair.");
            }
        };
        this.slider_crosshair_width.setValue((Integer)this.crosshair.properties.crosshair_width.getType());
        this.slider_crosshair_height = new ElementSlider(this, "Height", 0, 0, 150, 11, 1, 100){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_height.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the vertical height of the crosshair.");
            }
        };
        this.slider_crosshair_height.setValue((Integer)this.crosshair.properties.crosshair_height.getType());
        this.slider_crosshair_gap = new ElementSlider(this, "Gap", 0, 0, 150, 11, 0, 50){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_gap.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the gap of the crosshair.");
            }
        };
        this.slider_crosshair_gap.setValue((Integer)this.crosshair.properties.crosshair_gap.getType());
        this.slider_crosshair_thickness = new ElementSlider(this, "Thickness", 0, 0, 150, 11, 1, 10){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_thickness.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the thickness of the crosshair.");
            }
        };
        this.slider_crosshair_thickness.setValue((Integer)this.crosshair.properties.crosshair_thickness.getType());
        this.slider_crosshair_rotation = new ElementSlider(this, "Rotation", 0, 0, 180, 11, 0, 360){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_rotation.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the rotation of the crosshair.");
            }
        };
        this.slider_crosshair_rotation.setValue((Integer)this.crosshair.properties.crosshair_rotation.getType());
        this.slider_rainbow_speed = new ElementSlider(this, "Rainbow Speed", 0, 0, 150, 11, 1, 1000){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.rainbow_speed.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("Changes the speed of the colour change for the Rainbow crosshair.");
            }
        };
        this.slider_rainbow_speed.setValue((Integer)this.crosshair.properties.rainbow_speed.getType());
        this.elementList.clear();
        this.elementList.add(this.panel_container);
        this.panel_container.elementList.clear();
        this.panel_container.elementList.add(this.tickBox_mod_enabled);
        this.panel_container.elementList.add(this.colourEdit_crosshair_base);
        this.panel_container.elementList.add(this.slider_crosshair_type);
        this.panel_container.elementList.add(this.tickBox_visible_default);
        this.panel_container.elementList.add(this.tickBox_visible_hiddenGui);
        this.panel_container.elementList.add(this.tickBox_visible_debug);
        this.panel_container.elementList.add(this.tickbox_visible_thirdPerson);
        this.panel_container.elementList.add(this.tickBox_outline_enabled);
        this.panel_container.elementList.add(this.colourEdit_outline);
        this.panel_container.elementList.add(this.tickBox_dot_enabled);
        this.panel_container.elementList.add(this.colourEdit_dot);
        this.panel_container.elementList.add(this.slider_crosshair_width);
        this.panel_container.elementList.add(this.slider_crosshair_height);
        this.panel_container.elementList.add(this.slider_crosshair_gap);
        this.panel_container.elementList.add(this.slider_crosshair_thickness);
        this.panel_container.elementList.add(this.slider_crosshair_rotation);
        this.panel_container.elementList.add(this.tickBox_dynamic_bow_enabled);
        this.panel_container.elementList.add(this.tickBox_highlight_hostile_enabled);
        this.panel_container.elementList.add(this.colourEdit_highlight_hostile);
        this.panel_container.elementList.add(this.tickBox_highlight_passive_enabled);
        this.panel_container.elementList.add(this.colourEdit_highlight_passive);
        this.panel_container.elementList.add(this.tickBox_highlight_player_enabled);
        this.panel_container.elementList.add(this.colourEdit_highlight_player);
        this.panel_container.elementList.add(this.tickBox_rainbow_enabled);
        this.panel_container.elementList.add(this.slider_rainbow_speed);
        this.panel_container.helpButtonList.clear();
        int elementY = this.panel_container.getPosY() + 4;
        int contentHeight = 0;
        for (ElementBase element : this.panel_container.elementList) {
            element.setPosition(19, elementY);
            ElementHelpButton helpButton = new ElementHelpButton(this, 4, elementY);
            helpButton.setHelpText(element.getHelpText());
            this.panel_container.helpButtonList.add(helpButton);
            elementY += element.getHeight() + 4;
            contentHeight += element.getHeight() + 4;
        }
        this.panel_container.setContentHeight(contentHeight + 8);
        this.headerButton_settings = new ElementHeaderButton(this, "Settings", 0, 0, 15){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new ScreenSettings());
            }
        };
        this.headerButton_reset = new ElementHeaderButton(this, "Reset", 0, 0, 15){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                CustomCrosshairMod.getCrosshairMod().getCrosshair().reset();
                ScreenMain.this.initGui();
            }
        };
        this.headerButtonList.clear();
        this.headerButtonList.add(this.headerButton_settings);
        this.headerButtonList.add(this.headerButton_reset);
        int headerButtonX = this.width - 4;
        for (ElementHeaderButton headerButton : this.headerButtonList) {
            headerButton.setPosition(headerButtonX - headerButton.getWidth(), 10);
            headerButtonX -= headerButton.getWidth() + 2;
        }
        this.crosshairPreview = new ElementCrosshairPreview(this, this.width - 17 - 100, 44, 100, 100);
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
        this.crosshairPreview.drawElement(mouseX, mouseY);
        this.drawToolTip(mouseX, mouseY);
    }

    static /* synthetic */ Crosshair access$000(ScreenMain x0) {
        return x0.crosshair;
    }

}

