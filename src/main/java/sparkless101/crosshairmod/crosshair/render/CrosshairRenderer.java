/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package sparkless101.crosshairmod.crosshair.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.CrosshairType;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.crosshair.properties.types.BooleanProperty;
import sparkless101.crosshairmod.crosshair.properties.types.CrosshairTypeProperty;
import sparkless101.crosshairmod.crosshair.properties.types.IntegerProperty;
import sparkless101.crosshairmod.crosshair.properties.types.RgbaProperty;
import sparkless101.crosshairmod.gui.RenderManager;
import sparkless101.crosshairmod.main.CustomCrosshairMod;
import sparkless101.crosshairmod.utils.RGBA;

public class CrosshairRenderer {
    private final Crosshair crosshair;
    private final Minecraft mc;
    private int rainbowTicks = 0;

    public CrosshairRenderer(Crosshair crosshair, Minecraft mc) {
        this.crosshair = crosshair;
        this.mc = mc;
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (this.mc.theWorld != null && this.mc.inGameHasFocus) {
            ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            this.draw(resolution.getScaledWidth() / 2, resolution.getScaledHeight() / 2);
        }
    }

    public void draw(int x, int y) {
        CustomCrosshairMod.getCrosshairMod().displayNewVersionMessage();
        if (((Boolean)this.crosshair.properties.mod_enabled.getType()).booleanValue() && ((Boolean)this.crosshair.properties.visible_default.getType()).booleanValue() && (this.mc.gameSettings.thirdPersonView > 0 && ((Boolean)this.crosshair.properties.visible_thirdPerson.getType()).booleanValue() || this.mc.gameSettings.thirdPersonView <= 0) && (this.mc.gameSettings.hideGUI && ((Boolean)this.crosshair.properties.visible_hiddenGui.getType()).booleanValue() || !this.mc.gameSettings.hideGUI) && this.mc.gameSettings.showDebugInfo && ((Boolean)this.crosshair.properties.visible_debug.getType()).booleanValue() || !this.mc.gameSettings.showDebugInfo) {
            RGBA renderColour = (RGBA)this.crosshair.properties.crosshair_colour.getType();
            int renderGap = (Integer)this.crosshair.properties.crosshair_gap.getType();
            renderColour = this.getRainbowColour(renderColour);
            renderColour = this.getHighlightColour(renderColour);
            renderGap = this.getDynamicGap(renderGap);
            if (this.mc.gameSettings.hideGUI) {
                this.translateHiddenGui();
            }
            this.renderCrosshairRotated(x, y, renderGap, renderColour);
            if (((Boolean)this.crosshair.properties.dot_enabled.getType()).booleanValue()) {
                this.renderCrosshairDot(x, y);
            }
        }
    }

    private RGBA getHighlightColour(RGBA originalColour) {
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            if (this.mc.objectMouseOver.entityHit instanceof EntityPlayer && ((Boolean)this.crosshair.properties.highlight_player_enabled.getType()).booleanValue()) {
                return (RGBA)this.crosshair.properties.highlight_player_colour.getType();
            }
            if (this.mc.objectMouseOver.entityHit instanceof EntityLiving) {
                if (this.isEntityHostile(this.mc.objectMouseOver.entityHit) && ((Boolean)this.crosshair.properties.highlight_hostile_enabled.getType()).booleanValue()) {
                    return (RGBA)this.crosshair.properties.highlight_hostile_colour.getType();
                }
                if (!this.isEntityHostile(this.mc.objectMouseOver.entityHit) && ((Boolean)this.crosshair.properties.highlight_passive_enabled.getType()).booleanValue()) {
                    return (RGBA)this.crosshair.properties.highlight_passive_colour.getType();
                }
            }
        }
        return originalColour;
    }

    private boolean isEntityHostile(Entity entity) {
        return entity instanceof EntityMob;
    }

    private RGBA getRainbowColour(RGBA originalColour) {
        if (((Boolean)this.crosshair.properties.rainbow_enabled.getType()).booleanValue()) {
            ++this.rainbowTicks;
            return new RGBA(this.getColourFromTicks(0.0f), this.getColourFromTicks(2.0f), this.getColourFromTicks(4.0f), ((RGBA)this.crosshair.properties.crosshair_colour.getType()).getOpacity());
        }
        return originalColour;
    }

    private int getColourFromTicks(float offset) {
        return (int)(Math.sin((float)((Integer)this.crosshair.properties.rainbow_speed.getType()).intValue() / 100000.0f * (float)this.rainbowTicks + offset) * 127.0 + 128.0);
    }
//判断手中物品是否有拉弓动作，就例如弩、投枪，吹箭....


    private int getDynamicGap(int originalGap) {
        if (this.mc.thePlayer.getHeldItem() != null && ((Boolean)this.crosshair.properties.dynamic_bow_enabled.getType()).booleanValue()) {
            ItemStack item = this.mc.thePlayer.getHeldItem();
            int useCount = this.mc.thePlayer.getItemInUseCount();
            if (((Boolean)this.crosshair.properties.dynamic_bow_enabled.getType()).booleanValue() && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow) {
                float bowExtension = (float)(item.getItem().getMaxItemUseDuration(item) - useCount) / 20.0f;
                if (useCount == 0 || bowExtension > 1.0f) {
                    bowExtension = 1.0f;
                }
                return (Integer)this.crosshair.properties.crosshair_gap.getType() + (int)((1.0f - bowExtension) * (float)((Integer)this.crosshair.properties.crosshair_gap.getType() + 5) * 2.0f);
            }
        }
        return originalGap;
    }

    private void renderCrosshairRotated(int drawX, int drawY, int renderGap, RGBA renderColour) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)drawX, (float)drawY, (float)0.0f);
        GL11.glRotatef((float)((Integer)this.crosshair.properties.crosshair_rotation.getType()).intValue(), (float)drawX, (float)drawY, (float)8000.0f);
        GL11.glTranslatef((float)(-drawX), (float)(-drawY), (float)0.0f);
        this.renderBaseCrosshair(drawX, drawY, renderGap, renderColour);
        GL11.glPopMatrix();
    }

    private void renderBaseCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        switch ((CrosshairType)((Object)this.crosshair.properties.crosshair_type.getType())) {
            case CROSS: {
                this.renderCrossCrosshair(drawX, drawY, renderGap, renderColour);
                break;
            }
            case CIRCLE: {
                this.renderCircleCrosshair(drawX, drawY, renderGap, renderColour);
                break;
            }
            case SQUARE: {
                this.renderSquareCrosshair(drawX, drawY, renderGap, renderColour);
                break;
            }
            case ARROW: {
                this.renderArrowCrosshair(drawX, drawY, renderGap, renderColour);
                break;
            }
            case TRIANGLE: {
                this.renderTriangleCrosshair(drawX, drawY, renderGap, renderColour);
                break;
            }
            default: {
                this.renderDefaultCrosshair(drawX, drawY, renderGap, renderColour);
            }
        }
    }

    private void renderCrossCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        float thickness = (float)((Integer)this.crosshair.properties.crosshair_thickness.getType()).intValue() / 2.0f;
        RenderManager.drawFilledRectangle((float)drawX - thickness, drawY - renderGap - (Integer)this.crosshair.properties.crosshair_height.getType(), (float)drawX + thickness, drawY - renderGap, renderColour, true);
        RenderManager.drawFilledRectangle((float)drawX - thickness, drawY + renderGap, (float)drawX + thickness, drawY + renderGap + (Integer)this.crosshair.properties.crosshair_height.getType(), renderColour, true);
        RenderManager.drawFilledRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_width.getType(), (float)drawY - thickness, drawX - renderGap, (float)drawY + thickness, renderColour, true);
        RenderManager.drawFilledRectangle(drawX + renderGap, (float)drawY - thickness, drawX + renderGap + (Integer)this.crosshair.properties.crosshair_width.getType(), (float)drawY + thickness, renderColour, true);
        if (((Boolean)this.crosshair.properties.outline_enabled.getType()).booleanValue()) {
            RenderManager.drawRectangle((float)drawX - thickness, drawY - renderGap - (Integer)this.crosshair.properties.crosshair_height.getType(), (float)drawX + thickness, drawY - renderGap, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), false);
            RenderManager.drawRectangle((float)drawX - thickness, drawY + renderGap, (float)drawX + thickness, drawY + renderGap + (Integer)this.crosshair.properties.crosshair_height.getType(), 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), false);
            RenderManager.drawRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_width.getType(), (float)drawY - thickness, drawX - renderGap, (float)drawY + thickness, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), false);
            RenderManager.drawRectangle(drawX + renderGap, (float)drawY - thickness, drawX + renderGap + (Integer)this.crosshair.properties.crosshair_width.getType(), (float)drawY + thickness, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), false);
        }
    }

    private void renderCircleCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        RenderManager.drawCircle(drawX, drawY, renderGap, ((Integer)this.crosshair.properties.crosshair_thickness.getType()).intValue(), renderColour, true);
        if (((Boolean)this.crosshair.properties.outline_enabled.getType()).booleanValue()) {
            RenderManager.drawCircle(drawX, drawY, (float)renderGap + (float)((Integer)this.crosshair.properties.crosshair_thickness.getType()).intValue() / 2.0f - 1.5f, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
            RenderManager.drawCircle(drawX, drawY, (float)renderGap - (float)((Integer)this.crosshair.properties.crosshair_thickness.getType()).intValue() / 2.0f + 1.5f, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
        }
    }

    private void renderSquareCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        RenderManager.drawFilledRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawX + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY - renderGap, renderColour, true);
        RenderManager.drawFilledRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY + renderGap, drawX + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), renderColour, true);
        RenderManager.drawFilledRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY - renderGap, drawX - renderGap, drawY + renderGap, renderColour, true);
        RenderManager.drawFilledRectangle(drawX + renderGap, drawY - renderGap, drawX + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY + renderGap, renderColour, true);
        if (((Boolean)this.crosshair.properties.outline_enabled.getType()).booleanValue()) {
            RenderManager.drawRectangle(drawX - renderGap, drawY - renderGap, drawX + renderGap, drawY + renderGap, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
            RenderManager.drawRectangle(drawX - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY - renderGap - (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawX + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), drawY + renderGap + (Integer)this.crosshair.properties.crosshair_thickness.getType(), 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
        }
    }

    private void renderArrowCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        RenderManager.drawLines(new float[]{drawX - (Integer)this.crosshair.properties.crosshair_width.getType(), drawY + (Integer)this.crosshair.properties.crosshair_height.getType(), drawX, drawY, drawX, drawY, drawX + (Integer)this.crosshair.properties.crosshair_width.getType(), drawY + (Integer)this.crosshair.properties.crosshair_height.getType()}, 2.0f, renderColour, true);
    }

    private void renderDefaultCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)775, (int)769, (int)1, (int)0);
        this.mc.getTextureManager().bindTexture(Gui.icons);
        this.mc.ingameGUI.drawTexturedModalRect(drawX - 7, drawY - 7, 0, 0, 16, 16);
        GL11.glDisable((int)3042);
    }

    private void renderTriangleCrosshair(int drawX, int drawY, int renderGap, RGBA renderColour) {
        RenderManager.drawLines(new float[]{drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f, drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f}, 2.0f, renderColour, true);
        if (((Boolean)this.crosshair.properties.outline_enabled.getType()).booleanValue()) {
            RenderManager.drawLines(new float[]{drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 2.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f - 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 1.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f - 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 1.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f + 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 1.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f + 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 1.0f, drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 2.0f}, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
            RenderManager.drawLines(new float[]{drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 2.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f + 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 1.0f, (float)(drawX - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f + 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 1.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f - 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 1.0f, (float)(drawX + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_width.getType()).intValue() / 2.0f - 2.0f, (float)(drawY + renderGap) + (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f - 1.0f, drawX, (float)(drawY - renderGap) - (float)((Integer)this.crosshair.properties.crosshair_height.getType()).intValue() / 2.0f + 2.0f}, 2.0f, (RGBA)this.crosshair.properties.outline_colour.getType(), true);
        }
    }

    private void renderCrosshairDot(int drawX, int drawY) {
        RenderManager.drawRectangle((float)drawX - 0.5f, (float)drawY - 0.5f, (float)drawX + 0.5f, (float)drawY + 0.5f, 2.0f, (RGBA)this.crosshair.properties.dot_colour.getType(), true);
    }

    private void translateHiddenGui() {
        ScaledResolution screenSizeDouble = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)screenSizeDouble.getScaledWidth_double(), (double)screenSizeDouble.getScaledHeight_double(), (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

}

