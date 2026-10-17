package dev.shara.client.ui.clickgui.component;

import dev.shara.client.util.ColorUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ColorPickerControl {
    public int x, y, w, h;
    public String label;
    public int color;

    public ColorPickerControl(int x, int y, int w, int h, String label, int defaultColor) {
        this.x = x; this.y = y; this.w = w; this.h = h; this.label = label; this.color = defaultColor;
    }

    public void render(DrawContext ctx, Screen screen) {
        ctx.drawText(screen.textRenderer, Text.literal(label), x, y - 10, 0xFFFFFFFF, false);
        ctx.fill(x, y, x + w, y + h, color);
        // overlay border
        ctx.fill(x, y, x + w, y + 1, 0x99000000);
        ctx.fill(x, y + h - 1, x + w, y + h, 0x99000000);
    }

    public boolean mouseClicked(double mx, double my) {
        if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
            // simple hue pick based on x position
            float t = (float)((mx - x) / (double) w);
            color = ColorUtils.hsvToArgb(t, 0.8f, 0.9f);
            return true;
        }
        return false;
    }
}
