package dev.shara.client.core;

import dev.shara.client.theme.Theme;

import java.util.Map;
import java.util.Optional;

public class ThemeManager {
    private final ThemeManagerV2 delegate = new ThemeManagerV2();

    public void registerTheme(Theme theme) {
        delegate.registerTheme(theme);
    }

    public Optional<Theme> getTheme(String id) {
        return delegate.getTheme(id);
    }

    public Map<String, Theme> getThemes() {
        return delegate.getThemes();
    }

    public Theme getActiveTheme() {
        return delegate.getActiveTheme();
    }

    public void setActiveTheme(String id) {
        delegate.setActiveTheme(id);
    }
}
