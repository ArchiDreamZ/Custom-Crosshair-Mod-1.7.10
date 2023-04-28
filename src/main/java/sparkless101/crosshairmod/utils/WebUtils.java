/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class WebUtils {
    public static void openInBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static BufferedReader get(String requestURL) {
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch (Exception e) {
            return null;
        }
    }
}

