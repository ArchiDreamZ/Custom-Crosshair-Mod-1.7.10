/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.crosshair.properties.Property;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class CrosshairConfig {
    private static final String SAVE_DIRECTORY = "crosshair_config.ccmcfg";
    private static final String COMMENT = "#";

    public static boolean readConfigFile() {
        try {
            FileReader reader = new FileReader(SAVE_DIRECTORY);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            CustomCrosshairMod ccm = CustomCrosshairMod.getCrosshairMod();
            Properties properties = ccm.getCrosshair().properties;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted;
                if (line.startsWith(COMMENT) || (splitted = line.split(":")).length <= 1) continue;
                String property = splitted[0].trim();
                String value = splitted[1].trim();
                for (int i = 0; i < properties.getProperties().length; ++i) {
                    Property listProperty = properties.getProperties()[i];
                    if (!property.equals(listProperty.getAlias())) continue;
                    listProperty.setValue(value);
                }
            }
            bufferedReader.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean writeConfigFile(Properties properties) {
        try {
            int i;
            FileWriter writer = new FileWriter(SAVE_DIRECTORY);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            ArrayList<String> lines = new ArrayList<String>();
            lines.add("# Custom Crosshair Mod v0.8.4");
            lines.add("# Crosshair Config File");
            lines.add("# Made By Sparkless101");
            lines.add("# ------------------------------------");
            lines.add("# This config file contains the properties for the crosshair and settings for the mod.");
            lines.add("# Feel free to edit the file.");
            lines.add("# Colours should be formatted as such: \"property_name:r/g/b/a\"");
            lines.add(COMMENT);
            lines.add("# Visit the official project pages for more info and preset crosshairs.");
            lines.add("# https://www.curseforge.com/projects/242995/");
            lines.add("# http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2637819/");
            lines.add("# ------------------------------------");
            for (i = 0; i < properties.getProperties().length; ++i) {
                lines.add(properties.getProperties()[i].getAlias() + ":" + properties.getProperties()[i].getStringValue());
            }
            for (i = 0; i < lines.size(); ++i) {
                bufferedWriter.write((String)lines.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeDefaultConfigFile() {
        return CrosshairConfig.writeConfigFile(new Properties());
    }
}

