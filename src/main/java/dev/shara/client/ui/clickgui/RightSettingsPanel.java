package dev.shara.client.ui.clickgui;

import dev.shara.client.ui.clickgui.component.SettingsPanel;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

/**
 * RightSettingsPanel is a light wrapper around SettingsPanel adding some helpers for layout.
 */
public class RightSettingsPanel {
    private final SettingsPanel settings;

    public RightSettingsPanel(int x, int y, int w, int h) {
        settings = new SettingsPanel(x, y, w, h);
    }

    public SettingsPanel getSettings() { return settings; }

    public void render(DrawContext ctx, Screen screen, float delta) {
        settings.render(ctx, screen, delta);
    }

    public boolean mouseClicked(double mx, double my, int button) {
        return settings.mouseClicked(mx, my, button);
    }
}
