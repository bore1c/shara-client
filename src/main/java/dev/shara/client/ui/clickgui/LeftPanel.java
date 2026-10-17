package dev.shara.client.ui.clickgui;

import dev.shara.client.ui.clickgui.component.ModuleButton;
import dev.shara.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * LeftPanel manages module list rendering and simple grouping.
 */
public class LeftPanel {
    private final List<ModuleButton> buttons = new ArrayList<>();

    public LeftPanel() {}

    public void build(List<Module> modules, int startX, int startY) {
        buttons.clear();
        int idx = 0;
        for (Module m : modules) {
            ModuleButton b = new ModuleButton(m, startX, startY + idx * 22, 140, 18, mod -> mod.toggle());
            buttons.add(b);
            idx++;
        }
    }

    public void render(DrawContext ctx, Screen screen, int mouseX, int mouseY, float delta) {
        for (ModuleButton b : buttons) b.render(ctx, screen, mouseX, mouseY, delta);
    }

    public boolean mouseClicked(double mx, double my, int button) {
        for (ModuleButton b : buttons) if (b.mouseClicked(mx, my, button)) return true;
        return false;
    }
}
