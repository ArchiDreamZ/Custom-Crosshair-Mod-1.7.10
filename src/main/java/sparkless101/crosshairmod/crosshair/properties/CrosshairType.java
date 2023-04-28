/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties;

public enum CrosshairType {
    CROSS(0),
    CIRCLE(1),
    SQUARE(2),
    DEFAULT(3),
    ARROW(4),
    TRIANGLE(5);

    private final int value;

    private CrosshairType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static CrosshairType getTypeFromByte(int i) {
        for (CrosshairType cType : CrosshairType.values()) {
            if (cType.getValue() != i) continue;
            return cType;
        }
        return null;
    }

    public String toString() {
        switch (this) {
            case CROSS: {
                return "Cross";
            }
            case CIRCLE: {
                return "Circle";
            }
            case SQUARE: {
                return "Square";
            }
            case DEFAULT: {
                return "Default";
            }
            case ARROW: {
                return "Arrow";
            }
            case TRIANGLE: {
                return "Triangle";
            }
        }
        return "CrosshairType";
    }

}

