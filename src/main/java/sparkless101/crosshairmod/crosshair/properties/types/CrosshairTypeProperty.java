/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties.types;

import sparkless101.crosshairmod.crosshair.properties.CrosshairType;
import sparkless101.crosshairmod.crosshair.properties.Property;

public class CrosshairTypeProperty
extends Property<CrosshairType> {
    public CrosshairTypeProperty(String name, String alias, CrosshairType t) {
        super(name, alias, t);
    }

    @Override
    public void setValue(String s) {
        try {
            this.type = CrosshairType.getTypeFromByte(Byte.parseByte(s));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public String getStringValue() {
        return "" + ((CrosshairType)((Object)this.type)).getValue();
    }
}

