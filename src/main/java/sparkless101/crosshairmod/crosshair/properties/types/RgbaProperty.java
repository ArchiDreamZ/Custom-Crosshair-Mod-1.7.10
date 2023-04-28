/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.Property;
import sparkless101.crosshairmod.utils.RGBA;

public class RgbaProperty
extends Property<RGBA> {
    public RgbaProperty(String name, String alias, RGBA t) {
        super(name, alias, t);
    }

    @Override
    public void setValue(String s) {
        RGBA rgba = new RGBA(255, 255, 255, 255);
        String[] split = s.split("/");
        if (split.length > 3) {
            rgba.setRed(Integer.parseInt(split[0]));
            rgba.setGreen(Integer.parseInt(split[1]));
            rgba.setBlue(Integer.parseInt(split[2]));
            rgba.setOpacity(Integer.parseInt(split[3]));
        }
        this.type = rgba;
    }

    @Override
    public String getStringValue() {
        return ((RGBA)this.type).getRed() + "/" + ((RGBA)this.type).getGreen() + "/" + ((RGBA)this.type).getBlue() + "/" + ((RGBA)this.type).getOpacity();
    }
}

