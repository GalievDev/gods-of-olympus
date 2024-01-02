package dev.galiev.gofo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.galiev.gofo.GodsOfOlympus;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public final class ConfigManager {
    private static final File configDir = Paths.get("", "config", MOD_ID).toFile();
    private static final File configFile = new File(configDir, "config.json");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final Config defaultConfig = new Config(true, true);

    public static void init() {
        if (!configDir.exists() && !configDir.mkdirs()) {
            GodsOfOlympus.LOGGER.warn("Can't create config dirs");
        }

        if (!configFile.exists()) {
            try {
                if (!configFile.createNewFile()) {
                    GodsOfOlympus.LOGGER.warn("Can't create config file");
                    return;
                }
                Files.writeString(configFile.toPath(), gson.toJson(defaultConfig));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Config read() {
        try {
            return gson.fromJson(Files.readString(configFile.toPath()), Config.class);
        } catch (Exception e) {
            GodsOfOlympus.LOGGER.error("Can't read config or it don't exist");
            try {
                GodsOfOlympus.LOGGER.info("Backup config...");
                Files.copy(configFile.toPath(), new File(configDir, "backup_config.json").toPath());
                Files.writeString(configFile.toPath(), gson.toJson(defaultConfig));
                return gson.fromJson(Files.readString(configFile.toPath()), Config.class);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
