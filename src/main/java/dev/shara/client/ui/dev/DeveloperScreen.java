package dev.shara.client.ui.dev;

import dev.shara.client.SharaClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DeveloperScreen extends Screen {
    public DeveloperScreen() { super(Text.literal("SHARA Developer")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0,0,width,height,0xDD090B12);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Developer Mode"), width/2, 16, 0xFFFFFFFF);

        int y = 40;
        String[] lines = ("" + dev.shara.client.diagnostics.DeveloperDiagnostics.getSummary()).split("\\n");
        for (String l : lines) {
            context.drawText(textRenderer, Text.literal(l), 30, y, 0xFFCCCCCC, false);
            y += 12;
        }

        // simple clickable actions as text (not full widgets)
        context.drawText(textRenderer, Text.literal("[Reload Fonts]"), 30, y + 8, 0xFF88CCFF, false);
        context.drawText(textRenderer, Text.literal("[Reload Theme]"), 150, y + 8, 0xFF88CCFF, false);
        context.drawText(textRenderer, Text.literal("[Reload Config]"), 30, y + 28, 0xFF88CCFF, false);
        context.drawText(textRenderer, Text.literal("[Reset Layout]"), 150, y + 28, 0xFF88CCFF, false);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override public boolean shouldPause() { return false; }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Very basic hit test for actions
        if (mouseX >= 30 && mouseX <= 120 && mouseY >= 40+8 && mouseY <= 40+20) {
            // Reload Fonts
            SharaClient.LOGGER.info("Developer: Reload Fonts (placeholder)");
            return true;
        }
        if (mouseX >= 150 && mouseX <= 260 && mouseY >= 40+8 && mouseY <= 40+20) {
            SharaClient.LOGGER.info("Developer: Reload Theme (placeholder)");
            return true;
        }
        if (mouseX >= 30 && mouseX <= 140 && mouseY >= 40+28 && mouseY <= 40+40) {
            try { SharaClient.get().getConfigManager().load(); SharaClient.LOGGER.info("Config reloaded"); } catch (Exception e) { SharaClient.LOGGER.warn("Config reload failed", e); }
            return true;
        }
        if (mouseX >= 150 && mouseX <= 260 && mouseY >= 40+28 && mouseY <= 40+40) {
            try { SharaClient.get().getConfigManager().save(); SharaClient.LOGGER.info("Layout reset (config saved)"); } catch (Exception e) { SharaClient.LOGGER.warn("Reset failed", e); }
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
