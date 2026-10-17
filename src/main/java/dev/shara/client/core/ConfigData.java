package dev.shara.client.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.shara.client.SharaClient;
import dev.shara.client.ui.hud.HudElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ConfigData {
    public static class ModuleState {
        public boolean enabled;
    }

    public static class HudPosition {
        public int x;
        public int y;
    }

    public Map<String, ModuleState> modules = new HashMap<>();
    public Map<String, HudPosition> hudPositions = new HashMap<>();
    public String activeTheme = "shara_violet";

    public static ConfigData fromJson(JsonObject root) {
        ConfigData cfg = new ConfigData();
        if (root == null) return cfg;
        if (root.has("modules")) {
            JsonObject mods = root.getAsJsonObject("modules");
            for (var e : mods.entrySet()) {
                ModuleState s = new ModuleState();
                s.enabled = e.getValue().getAsBoolean();
                cfg.modules.put(e.getKey(), s);
            }
        }
        if (root.has("hudPositions")) {
            JsonObject hud = root.getAsJsonObject("hudPositions");
            for (var e : hud.entrySet()) {
                JsonObject p = e.getValue().getAsJsonObject();
                HudPosition pos = new HudPosition();
                pos.x = p.has("x") ? p.get("x").getAsInt() : 0;
                pos.y = p.has("y") ? p.get("y").getAsInt() : 0;
                cfg.hudPositions.put(e.getKey(), pos);
            }
        }
        if (root.has("activeTheme")) cfg.activeTheme = root.get("activeTheme").getAsString();
        return cfg;
    }

    public JsonObject toJson() {
        JsonObject root = new JsonObject();
        JsonObject mods = new JsonObject();
        for (var e : modules.entrySet()) mods.addProperty(e.getKey(), e.getValue().enabled);
        root.add("modules", mods);
        JsonObject hud = new JsonObject();
        for (var e : hudPositions.entrySet()) {
            JsonObject p = new JsonObject();
            p.addProperty("x", e.getValue().x);
            p.addProperty("y", e.getValue().y);
            hud.add(e.getKey(), p);
        }
        root.add("hudPositions", hud);
        root.addProperty("activeTheme", activeTheme);
        return root;
    }

    // Helper: apply loaded hud positions to HudManager
    public void applyTo(HudManager hudManager) {
        for (HudElement he : hudManager.getElements()) {
            HudPosition pos = hudPositions.get(he.getId());
            if (pos != null) {
                he.setX(pos.x);
                he.setY(pos.y);
            }
        }
    }
}
