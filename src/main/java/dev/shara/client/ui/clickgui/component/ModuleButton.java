package dev.shara.client.ui.clickgui.component;

import dev.shara.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ModuleButton {
    private final Module module;
    private final int x, y, width, height;
    private final Consumer<Module> onClick;

    public ModuleButton(Module module, int x, int y, int width, int height, Consumer<Module> onClick) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = onClick;
    }

    public void render(DrawContext ctx, Screen screen, int mouseX, int mouseY, float delta) {
        int bg = module.isEnabled() ? 0xFF1E2838 : 0xFF111520;
        ctx.fill(x, y, x + width, y + height, bg);
        ctx.drawText(screen.textRenderer, Text.literal(module.getName()), x + 6, y + 6, 0xFFFFFFFF, false);
    }

    public boolean mouseClicked(double mx, double my, int button) {
        if (mx >= x && mx <= x + width && my >= y && my <= y + height) {
            onClick.accept(module);
            return true;
        }
        return false;
    }
}
