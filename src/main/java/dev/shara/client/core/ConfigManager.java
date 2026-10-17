package dev.shara.client.core;

import com.google.gson.JsonObject;
import dev.shara.client.SharaClient;
import dev.shara.client.module.Module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private final Path configDir;
    private final Path configFile;

    public ConfigManager() {
        this.configDir = Path.of("config", SharaClient.MOD_ID);
        this.configFile = configDir.resolve("config.json");
    }

    public void load() throws IOException {
        if (!Files.exists(configFile)) {
            save();
            return;
        }

        String content = Files.readString(configFile);
        if (content == null || content.isBlank()) {
            save();
            return;
        }

        JsonObject root = SharaClient.GSON.fromJson(content, JsonObject.class);
        if (root == null || !root.has("modules")) {
            return;
        }

        JsonObject modules = root.getAsJsonObject("modules");
        for (Module module : SharaClient.get().getModuleManager().getModules()) {
            if (modules.has(module.getId())) {
                boolean enabled = modules.get(module.getId()).getAsBoolean();
                if (enabled) {
                    module.enable();
                } else {
                    module.disable();
                }
            }
        }
    }

    public void save() throws IOException {
        Files.createDirectories(configDir);

        JsonObject root = new JsonObject();
        JsonObject modules = new JsonObject();

        for (Module module : SharaClient.get().getModuleManager().getModules()) {
            modules.addProperty(module.getId(), module.isEnabled());
        }

        root.add("modules", modules);
        Files.writeString(configFile, SharaClient.GSON.toJson(root));
    }
}
