package dev.shara.client.theme;

import dev.shara.client.util.ColorUtils;

import java.util.HashMap;
import java.util.Map;

public enum ThemePreset {
    SHARA_VIOLET("shara_violet"),
    MIDNIGHT_BLUE("midnight_blue"),
    AURORA("aurora"),
    EMERALD_GLASS("emerald_glass"),
    ROSE_QUARTZ("rose_quartz"),
    CRIMSON("crimson"),
    MONOCHROME("monochrome"),
    PURE_BLACK("pure_black"),
    FROSTED_WHITE("frosted_white"),
    CUSTOM("custom");

    private final String id;

    ThemePreset(String id) { this.id = id; }

    public String getId() { return id; }

    public Theme toTheme() {
        Map<String, Integer> colors = new HashMap<>();
        // Base palette from TЗ
        colors.put("background", ColorUtils.hexToArgb("#090B12"));
        colors.put("surface", ColorUtils.hexToArgb("#111522"));
        colors.put("elevated", ColorUtils.hexToArgb("#191E2D"));
        colors.put("primary", ColorUtils.hexToArgb("#A78BFA"));
        colors.put("secondary", ColorUtils.hexToArgb("#67E8F9"));
        colors.put("success", ColorUtils.hexToArgb("#65E6A7"));
        colors.put("warning", ColorUtils.hexToArgb("#FFC777"));
        colors.put("error", ColorUtils.hexToArgb("#FF6B81"));
        colors.put("text", ColorUtils.hexToArgb("#F4F5FF"));
        colors.put("secondaryText", ColorUtils.hexToArgb("#A4AAC0"));
        colors.put("mutedText", ColorUtils.hexToArgb("#6D748C"));

        switch (this) {
            case SHARA_VIOLET -> colors.put("accent", ColorUtils.hexToArgb("#A78BFA"));
            case MIDNIGHT_BLUE -> colors.put("accent", ColorUtils.hexToArgb("#67E8F9"));
            case AURORA -> colors.put("accent", ColorUtils.hexToArgb("#7C3AED"));
            case EMERALD_GLASS -> colors.put("accent", ColorUtils.hexToArgb("#10B981"));
            case ROSE_QUARTZ -> colors.put("accent", ColorUtils.hexToArgb("#F472B6"));
            case CRIMSON -> colors.put("accent", ColorUtils.hexToArgb("#EF4444"));
            case MONOCHROME -> colors.put("accent", ColorUtils.hexToArgb("#FFFFFF"));
            case PURE_BLACK -> colors.put("accent", ColorUtils.hexToArgb("#000000"));
            case FROSTED_WHITE -> colors.put("accent", ColorUtils.hexToArgb("#F8FAFC"));
            case CUSTOM -> colors.put("accent", ColorUtils.hexToArgb("#A78BFA"));
        }
        return new Theme(getId(), name().replace('_', ' '), colors, true);
    }
}
