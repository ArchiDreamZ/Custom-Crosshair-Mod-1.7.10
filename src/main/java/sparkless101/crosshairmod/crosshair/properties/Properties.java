/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties;

import sparkless101.crosshairmod.crosshair.properties.CrosshairType;
import sparkless101.crosshairmod.crosshair.properties.Property;
import sparkless101.crosshairmod.crosshair.properties.types.BooleanProperty;
import sparkless101.crosshairmod.crosshair.properties.types.CrosshairTypeProperty;
import sparkless101.crosshairmod.crosshair.properties.types.IntegerProperty;
import sparkless101.crosshairmod.crosshair.properties.types.RgbaProperty;
import sparkless101.crosshairmod.crosshair.properties.types.StringProperty;
import sparkless101.crosshairmod.utils.RGBA;

public class Properties {
    public final BooleanProperty mod_enabled;
    public final StringProperty keybind_gui;
    public final RgbaProperty crosshair_colour;
    public final CrosshairTypeProperty crosshair_type;
    public final IntegerProperty crosshair_width;
    public final IntegerProperty crosshair_height;
    public final IntegerProperty crosshair_gap;
    public final IntegerProperty crosshair_thickness;
    public final IntegerProperty crosshair_rotation;
    public final BooleanProperty visible_default;
    public final BooleanProperty visible_hiddenGui;
    public final BooleanProperty visible_debug;
    public final BooleanProperty visible_thirdPerson;
    public final BooleanProperty outline_enabled;
    public final RgbaProperty outline_colour;
    public final BooleanProperty dot_enabled;
    public final RgbaProperty dot_colour;
    public final BooleanProperty dynamic_bow_enabled;
    public final BooleanProperty highlight_hostile_enabled;
    public final BooleanProperty highlight_passive_enabled;
    public final BooleanProperty highlight_player_enabled;
    public final RgbaProperty highlight_hostile_colour;
    public final RgbaProperty highlight_passive_colour;
    public final RgbaProperty highlight_player_colour;
    public final BooleanProperty rainbow_enabled;
    public final IntegerProperty rainbow_speed;
    private Property[] properties = new Property[26];

    public Property[] getProperties() {
        return this.properties;
    }

    public Properties(BooleanProperty mod_enabled, StringProperty keybind_gui, RgbaProperty crosshair_colour, CrosshairTypeProperty crosshair_type, IntegerProperty crosshair_width, IntegerProperty crosshair_height, IntegerProperty crosshair_gap, IntegerProperty crosshair_thickness, IntegerProperty crosshair_rotation, BooleanProperty visible_default, BooleanProperty visible_hiddenGui, BooleanProperty visible_debug, BooleanProperty visible_thirdPerson, BooleanProperty outline_enabled, RgbaProperty outline_colour, BooleanProperty dot_enabled, RgbaProperty dot_colour, BooleanProperty dynamic_bow_enabled, BooleanProperty highlight_hostile_enabled, RgbaProperty highlight_hostile_colour, BooleanProperty highlight_passive_enabled, RgbaProperty highlight_passive_colour, BooleanProperty highlight_player_enabled, RgbaProperty highlight_player_colour, BooleanProperty rainbow_enabled, IntegerProperty rainbow_speed) {
        this.mod_enabled = mod_enabled;
        this.properties[0] = this.mod_enabled;
        this.keybind_gui = keybind_gui;
        this.properties[1] = this.keybind_gui;
        this.crosshair_colour = crosshair_colour;
        this.properties[2] = this.crosshair_colour;
        this.crosshair_type = crosshair_type;
        this.properties[3] = this.crosshair_type;
        this.crosshair_width = crosshair_width;
        this.properties[4] = this.crosshair_width;
        this.crosshair_height = crosshair_height;
        this.properties[5] = this.crosshair_height;
        this.crosshair_gap = crosshair_gap;
        this.properties[6] = this.crosshair_gap;
        this.crosshair_thickness = crosshair_thickness;
        this.properties[7] = this.crosshair_thickness;
        this.crosshair_rotation = crosshair_rotation;
        this.properties[8] = this.crosshair_rotation;
        this.visible_default = visible_default;
        this.properties[9] = this.visible_default;
        this.visible_hiddenGui = visible_hiddenGui;
        this.properties[10] = this.visible_hiddenGui;
        this.visible_debug = visible_debug;
        this.properties[11] = this.visible_debug;
        this.visible_thirdPerson = visible_thirdPerson;
        this.properties[12] = this.visible_thirdPerson;
        this.outline_enabled = outline_enabled;
        this.properties[13] = this.outline_enabled;
        this.outline_colour = outline_colour;
        this.properties[14] = this.outline_colour;
        this.dot_enabled = dot_enabled;
        this.properties[15] = this.dot_enabled;
        this.dot_colour = dot_colour;
        this.properties[16] = this.dot_colour;
        this.dynamic_bow_enabled = dynamic_bow_enabled;
        this.properties[17] = this.dynamic_bow_enabled;
        this.highlight_hostile_enabled = highlight_hostile_enabled;
        this.properties[18] = this.highlight_hostile_enabled;
        this.highlight_hostile_colour = highlight_hostile_colour;
        this.properties[19] = this.highlight_hostile_colour;
        this.highlight_passive_enabled = highlight_passive_enabled;
        this.properties[20] = this.highlight_passive_enabled;
        this.highlight_passive_colour = highlight_passive_colour;
        this.properties[21] = this.highlight_passive_colour;
        this.highlight_player_enabled = highlight_player_enabled;
        this.properties[22] = this.highlight_player_enabled;
        this.highlight_player_colour = highlight_player_colour;
        this.properties[23] = this.highlight_player_colour;
        this.rainbow_enabled = rainbow_enabled;
        this.properties[24] = this.rainbow_enabled;
        this.rainbow_speed = rainbow_speed;
        this.properties[25] = this.rainbow_speed;
    }

    public Properties() {
        this(new BooleanProperty("Mod Enabled", "mod_enabled", true), new StringProperty("Edit Crosshair Keybind", "keybind_gui", "C"), new RgbaProperty("Base Colour", "crosshair_colour", new RGBA(255, 255, 255, 255)), new CrosshairTypeProperty("Crosshair Type", "crosshair_type", CrosshairType.CROSS), new IntegerProperty("Width", "crosshair_width", 5), new IntegerProperty("Height", "crosshair_height", 5), new IntegerProperty("Gap", "crosshair_gap", 3), new IntegerProperty("Thickness", "crosshair_thickness", 2), new IntegerProperty("Rotation", "crosshair_rotation", 0), new BooleanProperty("Visible Default", "visible_default", true), new BooleanProperty("Visible Hidden GUI", "visible_hiddenGui", true), new BooleanProperty("Visible Debug", "visible_debug", true), new BooleanProperty("Visible Third Person", "visible_thirdperson", false), new BooleanProperty("Outline Enabled", "outline_enabled", true), new RgbaProperty("Outline Colour", "outline_colour", new RGBA(0, 0, 0, 255)), new BooleanProperty("Dot Enabled", "dot_enabled", false), new RgbaProperty("Dot Colour", "dot_colour", new RGBA(0, 0, 0, 255)), new BooleanProperty("Dynamic Bow Enabled", "dynamic_bow_enabled", true), new BooleanProperty("Highlight Hostiles", "highlight_hostile_enabled", true), new RgbaProperty("Highlight Hostiles Colour", "highlight_hostile_colour", new RGBA(255, 0, 0, 255)), new BooleanProperty("Highlight Passives", "highlight_passive_enabled", true), new RgbaProperty("Highlight Passives Colour", "highlight_passive_colour", new RGBA(0, 255, 0, 255)), new BooleanProperty("Highlight Players", "highlight_players_enabled", true), new RgbaProperty("Highlight Players", "highlight_players_colour", new RGBA(0, 0, 255, 255)), new BooleanProperty("Rainbow Crosshair Enabled", "rainbow_enabled", false), new IntegerProperty("Rainbow Speed", "rainbow_speed", 500));
    }
}

