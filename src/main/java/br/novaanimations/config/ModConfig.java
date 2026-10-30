
package br.novaanimations.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public final class ModConfig {

    private static final Properties VALUES = new Properties();
    private static File configFile;

    private ModConfig() {
    }

    public static void init(File configDirectory) {

        File directory = new File(
            configDirectory,
            "NovaAnimations"
        );

        if (!directory.exists()) {
            directory.mkdirs();
        }

        configFile = new File(
            directory,
            "settings.properties"
        );

        if (configFile.exists()) {
            try (FileInputStream input =
                    new FileInputStream(configFile)) {

                VALUES.load(input);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean get(String key) {
        return Boolean.parseBoolean(
            VALUES.getProperty(key, "false")
        );
    }

    public static void set(String key, boolean value) {

        VALUES.setProperty(
            key,
            String.valueOf(value)
        );

        save();
    }

    private static void save() {

        if (configFile == null) return;

        try (FileOutputStream output =
                new FileOutputStream(configFile)) {

            VALUES.store(
                output,
                "NovaAnimations Settings"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
