/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraftforge.client.GuiIngameForge
 *  org.lwjgl.input.Keyboard
 */
package sparkless101.crosshairmod.main;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import java.io.BufferedReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.GuiIngameForge;
import org.lwjgl.input.Keyboard;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.CrosshairConfig;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.crosshair.properties.types.BooleanProperty;
import sparkless101.crosshairmod.crosshair.properties.types.StringProperty;
import sparkless101.crosshairmod.crosshair.render.CrosshairRenderer;
import sparkless101.crosshairmod.gui.screens.ScreenMain;
import sparkless101.crosshairmod.utils.WebUtils;

@Mod(modid="customcrosshairmod", version="0.8.4")
public class CustomCrosshairMod {
    public static final String NAME = "Custom Crosshair Mod";
    public static final String VERSION = "0.8.4";
    public static final String MCVERSION = "1.7.10-forge";
    public static final String MCFORUM_URL = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2637819/";
    public static final String CURSEFORGE_URL = "https://www.curseforge.com/minecraft/mc-mods/custom-crosshair-mod";
    public static final String LATEST_VERSION_URL = "http://pastebin.com/raw/B2sL8QCh";
    private Crosshair crosshair;
    private CrosshairRenderer renderer;
    private static CustomCrosshairMod crosshairMod = new CustomCrosshairMod();
    public String detectedLatestVersion;
    public static boolean showNewVersionMessage = true;
    public KeyBinding guiEditKeyBind;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        crosshairMod = this;
        this.crosshair = new Crosshair();
        this.renderer = new CrosshairRenderer(this.crosshair, Minecraft.getMinecraft());
        if (!CrosshairConfig.readConfigFile()) {
            CrosshairConfig.writeDefaultConfigFile();
        }
        this.detectedLatestVersion = this.sendLatestVersionGetRequest();
        this.guiEditKeyBind = new KeyBinding("Open GUI", Keyboard.getKeyIndex((String)((String)this.crosshair.properties.keybind_gui.getType())), NAME);
        GuiIngameForge.renderCrosshairs = (Boolean)this.crosshair.properties.mod_enabled.getType() == false;
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        FMLCommonHandler.instance().bus().register((Object)this.renderer);
        FMLCommonHandler.instance().bus().register((Object)this);
        ClientRegistry.registerKeyBinding((KeyBinding)this.guiEditKeyBind);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (this.guiEditKeyBind.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new ScreenMain());
        }
    }

    public void addChatMessage(String message) {
        String pre = "\u00a79[Custom Crosshair Mod] \u00a7r";
        Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentTranslation(pre + message, new Object[0]));
    }

    public String sendLatestVersionGetRequest() {
        BufferedReader reader = WebUtils.get(LATEST_VERSION_URL);
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                if (!lineSplit[0].equals(MCVERSION)) continue;
                reader.close();
                return lineSplit[1];
            }
            reader.close();
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean usingCorrectVersion() {
        return this.detectedLatestVersion == null ? true : this.detectedLatestVersion.equals(VERSION);
    }

    public void displayNewVersionMessage() {
        if (showNewVersionMessage && !this.usingCorrectVersion()) {
            this.addChatMessage("New version available: " + this.detectedLatestVersion + ".");
            showNewVersionMessage = false;
        }
    }

    public static CustomCrosshairMod getCrosshairMod() {
        return crosshairMod;
    }

    public Crosshair getCrosshair() {
        return this.crosshair;
    }

    public CrosshairRenderer getRenderer() {
        return this.renderer;
    }
}

