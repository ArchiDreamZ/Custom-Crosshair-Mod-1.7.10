/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.utils;

public class RGBA {
    private int RED;
    private int GREEN;
    private int BLUE;
    private int OPACITY;

    public RGBA(int red, int green, int blue, int opacity) {
        this.RED = red;
        this.GREEN = green;
        this.BLUE = blue;
        this.OPACITY = opacity;
    }

    public String toString() {
        return "rgba(" + this.RED + ", " + this.GREEN + ", " + this.BLUE + ", " + this.OPACITY + ")";
    }

    public int getRed() {
        return this.RED;
    }

    public void setRed(int r) {
        this.RED = r;
    }

    public int getGreen() {
        return this.GREEN;
    }

    public void setGreen(int g) {
        this.GREEN = g;
    }

    public int getBlue() {
        return this.BLUE;
    }

    public void setBlue(int b) {
        this.BLUE = b;
    }

    public int getOpacity() {
        return this.OPACITY;
    }

    public void setOpacity(int o) {
        this.OPACITY = o;
    }
}

