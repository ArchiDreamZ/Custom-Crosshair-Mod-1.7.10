/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.Property;

public class FloatProperty
extends Property<Float> {
    public FloatProperty(String name, String alias, float t) {
        super(name, alias, Float.valueOf(t));
    }

    @Override
    public void setValue(String s) {
        try {
            this.type = Float.valueOf(Float.parseFloat(s));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public String getStringValue() {
        return ((Float)this.type).toString();
    }
}

