/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.Property;

public class IntegerProperty
extends Property<Integer> {
    public IntegerProperty(String name, String alias, int t) {
        super(name, alias, t);
    }

    @Override
    public void setValue(String s) {
        try {
            this.type = Integer.parseInt(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public String getStringValue() {
        return ((Integer)this.type).toString();
    }
}

