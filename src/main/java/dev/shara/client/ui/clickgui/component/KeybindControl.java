package dev.shara.client.ui.clickgui.component;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class KeybindControl {
    public int x, y, w, h;
    public String label;
    public int key;
    private boolean listening = false;

    public KeybindControl(int x, int y, int w, int h, String label, int defaultKey) {
        this.x = x; this.y = y; this.w = w; this.h = h; this.label = label; this.key = defaultKey;
    }

    public void render(DrawContext ctx, Screen screen) {
        ctx.drawText(screen.textRenderer, Text.literal(label + ": " + (listening ? "..." : java.lang.Integer.toString(key))), x, y - 10, 0xFFFFFFFF, false);
        ctx.fill(x, y, x + w, y + h, 0xFF22262C);
        ctx.drawText(screen.textRenderer, Text.literal(listening ? "Press any key" : "Bind"), x + 6, y + 6, 0xFFCCCCCC, false);
    }

    public boolean mouseClicked(double mx, double my) {
        if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
            listening = !listening;
            return true;
        }
        return false;
    }

    public void keyPressed(int keyCode) {
        if (!listening) return;
        this.key = keyCode;
        this.listening = false;
    }
}
