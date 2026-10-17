package dev.shara.client.ui.clickgui;

import dev.shara.client.SharaClient;
import dev.shara.client.module.Module;
import dev.shara.client.ui.clickgui.component.ModuleButton;
import dev.shara.client.ui.clickgui.component.SettingsPanel;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SharaClickGuiScreen extends Screen {
    private final List<ModuleButton> buttons = new ArrayList<>();
    private final SettingsPanel settingsPanel = new SettingsPanel( width - 200, 40, 180, height - 80);

    public SharaClickGuiScreen() {
        super(Text.literal("SHARA Client"));
    }

    @Override
    protected void init() {
        super.init();
        buttons.clear();
        int startY = 40;
        int idx = 0;
        for (Module m : SharaClient.get().getModuleManager().getModules()) {
            int bx = 30;
            int by = startY + idx * 22;
            ModuleButton b = new ModuleButton(m, bx, by, 140, 18, mod -> {
                mod.toggle();
                settingsPanel.setModule(mod);
            });
            buttons.add(b);
            idx++;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0xDD090B12);
        context.fill(20, 20, 190, height - 20, 0xCC111522);
        context.fill(210, 20, width - 240, height - 20, 0xCC191E2D);
        context.fill(width - 220, 20, width - 20, height - 20, 0xCC111522);

        context.drawCenteredTextWithShadow(textRenderer, Text.literal("SHARA Client"), width / 2, 24, 0xFFFFFFFF);

        for (ModuleButton b : buttons) b.render(context, this, mouseX, mouseY, delta);

        settingsPanel.render(context, this, delta);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ModuleButton b : buttons) if (b.mouseClicked(mouseX, mouseY, button)) return true;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() { return false; }
}
