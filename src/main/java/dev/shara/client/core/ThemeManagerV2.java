package dev.shara.client.core;

import dev.shara.client.theme.Theme;
import dev.shara.client.theme.ThemePreset;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ThemeManagerV2 {
    private final Map<String, Theme> themes = new HashMap<>();
    private String activeThemeId = ThemePreset.SHARA_VIOLET.getId();

    public ThemeManagerV2() {
        // Register presets
        for (ThemePreset p : ThemePreset.values()) {
            Theme t = p.toTheme();
            themes.put(t.getId(), t);
        }
    }

    public Optional<Theme> getTheme(String id) {
        return Optional.ofNullable(themes.get(id));
    }

    public void registerTheme(Theme theme) {
        themes.put(theme.getId(), theme);
    }

    public Map<String, Theme> getThemes() { return themes; }

    public Theme getActiveTheme() {
        return themes.getOrDefault(activeThemeId, ThemePreset.SHARA_VIOLET.toTheme());
    }

    public void setActiveTheme(String id) {
        if (themes.containsKey(id)) activeThemeId = id;
    }
}
