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
        this.tickBox_mod_enabled = new ElementTickBox(this, "原版准心", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.mod_enabled.setType(this.getChecked());
                GuiIngameForge.renderCrosshairs = !this.getChecked();
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("启用或禁用原版准心。");
            }
        };
        this.tickBox_mod_enabled.setChecked((Boolean)this.crosshair.properties.mod_enabled.getType());
        this.tickBox_visible_default = new ElementTickBox(this, "默认可见", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_default.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("显示或隐藏准心");
            }
        };
        this.tickBox_visible_default.setChecked((Boolean)this.crosshair.properties.visible_default.getType());
        this.tickBox_visible_hiddenGui = new ElementTickBox(this, "隐藏GUI时可见", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_hiddenGui.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("隐藏 GUI（F1 模式）时显示或隐藏准心。");
            }
        };
        this.tickBox_visible_hiddenGui.setChecked((Boolean)this.crosshair.properties.visible_hiddenGui.getType());
        this.tickBox_visible_debug = new ElementTickBox(this, "在调试 UI 中可见", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_debug.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("在调试 UI（F3 模式）中显示或隐藏准心。");
            }
        };
        this.tickBox_visible_debug.setChecked((Boolean)this.crosshair.properties.visible_debug.getType());
        this.tickbox_visible_thirdPerson = new ElementTickBox(this, "第三人称可见", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.visible_thirdPerson.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("在第三人称模式下显示或隐藏准心。");
            }
        };
        this.tickbox_visible_thirdPerson.setChecked((Boolean)this.crosshair.properties.visible_thirdPerson.getType());
        this.tickBox_outline_enabled = new ElementTickBox(this, "启用轮廓线", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.outline_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("在准心周围绘制轮廓线。");
            }
        };
        this.tickBox_outline_enabled.setChecked((Boolean)this.crosshair.properties.outline_enabled.getType());
        this.tickBox_dot_enabled = new ElementTickBox(this, "启用中心点", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.dot_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("在屏幕中心绘制一个点。");
            }
        };
        this.tickBox_dot_enabled.setChecked((Boolean)this.crosshair.properties.dot_enabled.getType());
        this.tickBox_dynamic_bow_enabled = new ElementTickBox(this, "启用动态拉弓准心", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.dynamic_bow_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("当使用弓时，准心会动态表示蓄力时间");
            }
        };
        this.tickBox_dynamic_bow_enabled.setChecked((Boolean)this.crosshair.properties.dynamic_bow_enabled.getType());
        this.tickBox_highlight_hostile_enabled = new ElementTickBox(this, "高亮敌对生物", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_hostile_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("高亮显示可及范围内的敌对生物。");
            }
        };
        this.tickBox_highlight_hostile_enabled.setChecked((Boolean)this.crosshair.properties.highlight_hostile_enabled.getType());
        this.tickBox_highlight_passive_enabled = new ElementTickBox(this, "高亮被动生物", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_passive_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("高亮显示可及范围内的被动生物。");
            }
        };
        this.tickBox_highlight_passive_enabled.setChecked((Boolean)this.crosshair.properties.highlight_passive_enabled.getType());
        this.tickBox_highlight_player_enabled = new ElementTickBox(this, "高亮玩家", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.highlight_player_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("高亮显示可及范围内的玩家。");
            }
        };
        this.tickBox_highlight_player_enabled.setChecked((Boolean)this.crosshair.properties.highlight_player_enabled.getType());
        this.tickBox_rainbow_enabled = new ElementTickBox(this, "启用彩虹准心", 0, 0){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.rainbow_enabled.setType(this.getChecked());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("准心颜色将变为彩虹！");
            }
        };
        this.tickBox_rainbow_enabled.setChecked((Boolean)this.crosshair.properties.rainbow_enabled.getType());
        this.colourEdit_crosshair_base = new ElementColourEdit(this, "准心底色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.crosshair_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的基础颜色。");
            }
        };
        this.colourEdit_outline = new ElementColourEdit(this, "轮廓线颜色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.outline_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的轮廓线颜色。");
            }
        };
        this.colourEdit_dot = new ElementColourEdit(this, "中心点颜色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.dot_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的中心点颜色。");
            }
        };
        this.colourEdit_highlight_hostile = new ElementColourEdit(this, "高亮敌对生物颜色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_hostile_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改敌对生物的高亮显示颜色。");
            }
        };
        this.colourEdit_highlight_passive = new ElementColourEdit(this, "高亮被动生物颜色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_passive_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改被动生物的高亮显示颜色。");
            }
        };
        this.colourEdit_highlight_player = new ElementColourEdit(this, "高亮玩家颜色", 0, 0, 21, 21, (RGBA)this.crosshair.properties.highlight_player_colour.getType()){

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改玩家的高亮显示颜色。");
            }
        };
        this.slider_crosshair_type = new ElementSlider(this, "Type", 0, 0, 120, 11, 0, 5){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_type.setType(CrosshairType.getTypeFromByte((byte)this.getValue()));
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心类型", "[0 = 十字]", "[1 = 圆]", "[2 = 方]", "[3 = 原版]", "[4 = 箭头]");
            }
        };
        this.slider_crosshair_type.setValue(((CrosshairType)((Object)this.crosshair.properties.crosshair_type.getType())).getValue());
        this.slider_crosshair_width = new ElementSlider(this, "宽", 0, 0, 150, 11, 1, 100){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_width.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的水平宽度。");
            }
        };
        this.slider_crosshair_width.setValue((Integer)this.crosshair.properties.crosshair_width.getType());
        this.slider_crosshair_height = new ElementSlider(this, "高", 0, 0, 150, 11, 1, 100){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_height.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的垂直高度。");
            }
        };
        this.slider_crosshair_height.setValue((Integer)this.crosshair.properties.crosshair_height.getType());
        this.slider_crosshair_gap = new ElementSlider(this, "间距", 0, 0, 150, 11, 0, 50){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_gap.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的间距。");
            }
        };
        this.slider_crosshair_gap.setValue((Integer)this.crosshair.properties.crosshair_gap.getType());
        this.slider_crosshair_thickness = new ElementSlider(this, "厚度", 0, 0, 150, 11, 1, 10){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_thickness.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的厚度。");
            }
        };
        this.slider_crosshair_thickness.setValue((Integer)this.crosshair.properties.crosshair_thickness.getType());
        this.slider_crosshair_rotation = new ElementSlider(this, "旋转", 0, 0, 180, 11, 0, 360){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.crosshair_rotation.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改准心的旋转角度。");
            }
        };
        this.slider_crosshair_rotation.setValue((Integer)this.crosshair.properties.crosshair_rotation.getType());
        this.slider_rainbow_speed = new ElementSlider(this, "彩虹速度", 0, 0, 150, 11, 1, 1000){

            @Override
            public void onValueChanged() {
                ScreenMain.access$000((ScreenMain)ScreenMain.this).properties.rainbow_speed.setType(this.getValue());
            }

            @Override
            public List<String> getHelpText() {
                return Arrays.asList("更改彩虹准心的颜色变化速度。");
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
        this.headerButton_settings = new ElementHeaderButton(this, "设置", 0, 0, 15){

            @Override
            public void mouseClicked(int mouseX, int mouseY) {
                super.mouseClicked(mouseX, mouseY);
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new ScreenSettings());
            }
        };
        this.headerButton_reset = new ElementHeaderButton(this, "重置", 0, 0, 15){

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

