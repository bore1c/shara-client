package dev.shara.client.ui.clickgui.component;

import dev.shara.client.module.Module;
import dev.shara.client.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class SettingsPanel {
    private Module module;
    private final int x, y, w, h;

    public SettingsPanel(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.w = w; this.h = h;
    }

    public void setModule(Module m) { this.module = m; }

    public void render(DrawContext ctx, Screen screen, float delta) {
        ctx.fill(x, y, x + w, y + h, 0xCC111522);
        if (module == null) {
            ctx.drawText(screen.textRenderer, Text.literal("Select a module"), x + 8, y + 8, 0xFFAAAAAA, false);
            return;
        }
        ctx.drawText(screen.textRenderer, Text.literal(module.getName()), x + 8, y + 8, 0xFFFFFFFF, false);

        int yy = y + 24;
        // very basic: render known settings via toString
        List<Setting<?>> settings = module.getSettings().stream().map(s -> (Setting<?>) s).toList();
        for (Setting<?> s : settings) {
            ctx.drawText(screen.textRenderer, Text.literal(s.getName() + ": " + String.valueOf(s.getValue())), x + 8, yy, 0xFFCCCCCC, false);
            yy += 12;
            if (yy > y + h - 20) break;
        }
    }
}
