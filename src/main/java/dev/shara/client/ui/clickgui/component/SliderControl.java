package dev.shara.client.ui.clickgui.component;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SliderControl {
    public int x, y, w, h;
    public String label;
    public double min, max, value;

    public SliderControl(int x, int y, int w, int h, String label, double min, double max, double defaultValue) {
        this.x = x; this.y = y; this.w = w; this.h = h; this.label = label; this.min = min; this.max = max; this.value = defaultValue;
    }

    public void render(DrawContext ctx, Screen screen) {
        ctx.drawText(screen.textRenderer, Text.literal(label + ": " + String.format("%.2f", value)), x, y - 10, 0xFFFFFFFF, false);
        int barY = y + h/2 - 4;
        ctx.fill(x, barY, x + w, barY + 8, 0xFF2B2F3A);
        float t = (float)((value - min) / (max - min));
        int px = x + Math.round(t * (w - 8));
        ctx.fill(px, barY - 4, px + 8, barY + 12, 0xFF7C3AED);
    }

    public boolean mouseClicked(double mx, double my) {
        if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
            double t = (mx - x) / (double) w;
            value = Math.max(min, Math.min(max, min + t * (max - min)));
            return true;
        }
        return false;
    }
}
