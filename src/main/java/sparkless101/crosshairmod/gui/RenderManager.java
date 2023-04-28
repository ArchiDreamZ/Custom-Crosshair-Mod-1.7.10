/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  org.lwjgl.opengl.GL11
 */
package sparkless101.crosshairmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import sparkless101.crosshairmod.utils.RGBA;

public class RenderManager {
    public static void preRender() {
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
    }

    public static void postRender() {
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void drawLine(float x1, float y1, float x2, float y2, float thickness, RGBA colour, boolean smooth) {
        RenderManager.drawLines(new float[]{x1, y1, x2, y2}, thickness, colour, smooth);
    }

    public static void drawRectangle(float x1, float y1, float x2, float y2, float thickness, RGBA colour, boolean smooth) {
        RenderManager.drawLines(new float[]{x1, y1, x2, y1, x2, y1, x2, y2, x1, y2, x2, y2, x1, y1, x1, y2}, thickness, colour, smooth);
    }

    public static void drawBorderedRectangle(float x1, float y1, float x2, float y2, float borderThickness, RGBA borderColour, RGBA fillColour, boolean smooth) {
        RenderManager.drawFilledRectangle(x1, y1, x2, y2, fillColour, smooth);
        RenderManager.drawRectangle(x1, y1, x2, y2, borderThickness, borderColour, smooth);
    }

    public static void drawLines(float[] points, float thickness, RGBA colour, boolean smooth) {
        RenderManager.preRender();
        if (smooth) {
            GL11.glEnable((int)2848);
        } else {
            GL11.glDisable((int)2848);
        }
        GL11.glLineWidth((float)thickness);
        GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)((float)colour.getOpacity() / 255.0f));
        GL11.glBegin((int)1);
        for (int i = 0; i < points.length; i += 2) {
            GL11.glVertex2f((float)points[i], (float)points[i + 1]);
        }
        GL11.glEnd();
        RenderManager.postRender();
    }

    public static void drawFilledRectangle(float x1, float y1, float x2, float y2, RGBA colour, boolean smooth) {
        RenderManager.drawFilledShape(new float[]{x1, y1, x1, y2, x2, y2, x2, y1}, colour, smooth);
    }

    public static void drawFilledShape(float[] points, RGBA colour, boolean smooth) {
        RenderManager.preRender();
        if (smooth) {
            GL11.glEnable((int)2848);
        } else {
            GL11.glDisable((int)2848);
        }
        GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)((float)colour.getOpacity() / 255.0f));
        GL11.glBegin((int)9);
        for (int i = 0; i < points.length; i += 2) {
            GL11.glVertex2f((float)points[i], (float)points[i + 1]);
        }
        GL11.glEnd();
        RenderManager.postRender();
    }

    public static void drawCircle(float x, float y, float radius, float thickness, RGBA colour, boolean smooth) {
        RenderManager.drawPartialCircle(x, y, radius, 0, 360, thickness, colour, smooth);
    }

    public static void drawPartialCircle(float x, float y, float radius, int startAngle, int endAngle, float thickness, RGBA colour, boolean smooth) {
        RenderManager.preRender();
        if (startAngle > endAngle) {
            int temp = startAngle;
            startAngle = endAngle;
            endAngle = temp;
        }
        if (startAngle < 0) {
            startAngle = 0;
        }
        if (endAngle > 360) {
            endAngle = 360;
        }
        if (smooth) {
            GL11.glEnable((int)2848);
        } else {
            GL11.glDisable((int)2848);
        }
        GL11.glLineWidth((float)thickness);
        GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)((float)colour.getOpacity() / 255.0f));
        GL11.glBegin((int)3);
        float ratio = 0.017453292f;
        for (int i = startAngle; i <= endAngle; ++i) {
            float radians = (float)(i - 90) * ratio;
            GL11.glVertex2f((float)(x + (float)Math.cos(radians) * radius), (float)(y + (float)Math.sin(radians) * radius));
        }
        GL11.glEnd();
        RenderManager.postRender();
    }

    public static void drawString(String text, int x, int y, RGBA colour) {
        GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)((float)colour.getOpacity() / 255.0f));
        Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, 0);
    }

    public static int getTextWidth(String text) {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
    }
}

