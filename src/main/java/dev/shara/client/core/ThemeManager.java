package dev.shara.client.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ThemeManager {
    private final Map<String, Object> themes = new HashMap<>();

    public void registerTheme(String id, Object theme) {
        themes.put(id, theme);
    }

    public Optional<Object> getTheme(String id) {
        return Optional.ofNullable(themes.get(id));
    }

    public Map<String, Object> getThemes() {
        return themes;
    }
}
