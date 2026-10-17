package dev.shara.client.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String id;
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private boolean enabled;
    private final List<SettingEntry> settings = new ArrayList<>();

    protected Module(String id, String name, String description, ModuleCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ModuleCategory getCategory() { return category; }
    public boolean isEnabled() { return enabled; }

    public void registerSetting(SettingEntry setting) {
        settings.add(setting);
    }

    public List<SettingEntry> getSettings() {
        return settings;
    }

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    public void enable() {
        if (enabled) {
            return;
        }
        enabled = true;
        onEnable();
    }

    public void disable() {
        if (!enabled) {
            return;
        }
        enabled = false;
        onDisable();
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    public static class SettingEntry {
        private final String key;
        private final Object value;

        public SettingEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() { return key; }
        public Object getValue() { return value; }
    }
}
