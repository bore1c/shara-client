package dev.shara.client.core;

import dev.shara.client.setting.Setting;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SettingManager {
    private final Map<String, Setting<?>> settings = new HashMap<>();

    public void register(Setting<?> setting) {
        settings.put(setting.getId(), setting);
    }

    public Optional<Setting<?>> get(String id) {
        return Optional.ofNullable(settings.get(id));
    }

    public Map<String, Setting<?>> all() {
        return settings;
    }
}
