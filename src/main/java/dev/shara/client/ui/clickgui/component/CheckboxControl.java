package dev.shara.client.ui.clickgui.component;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CheckboxControl {
    public int x, y, w, h;
    public String label;
    public boolean value;

    public CheckboxControl(int x, int y, int w, int h, String label, boolean defaultValue) {
        this.x = x; this.y = y; this.w = w; this.h = h; this.label = label; this.value = defaultValue;
    }

    public void render(DrawContext ctx, Screen screen) {
        int bg = value ? 0xFF2ECC71 : 0xFF444B5A;
        ctx.fill(x, y, x + h, y + h, bg);
        ctx.drawText(screen.textRenderer, Text.literal(label), x + h + 6, y + 2, 0xFFFFFFFF, false);
    }

    public boolean mouseClicked(double mx, double my) {
        if (mx >= x && mx <= x + h && my >= y && my <= y + h) {
            value = !value;
            return true;
        }
        return false;
    }
}
