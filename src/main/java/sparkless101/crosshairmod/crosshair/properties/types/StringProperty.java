/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.Property;

public class StringProperty
extends Property<String> {
    public StringProperty(String name, String alias, String t) {
        super(name, alias, t);
    }

    @Override
    public void setValue(String s) {
        this.type = s;
    }

    @Override
    public String getStringValue() {
        return (String)this.type;
    }
}

