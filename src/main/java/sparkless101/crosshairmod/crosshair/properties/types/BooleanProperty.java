/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.Property;

public class BooleanProperty
extends Property<Boolean> {
    public BooleanProperty(String name, String alias, boolean t) {
        super(name, alias, t);
    }

    @Override
    public void setValue(String s) {
        this.type = Boolean.parseBoolean(s);
    }

    @Override
    public String getStringValue() {
        return ((Boolean)this.type).toString();
    }
}

