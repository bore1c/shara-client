package dev.shara.client.ui.clickgui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SharaClickGuiScreen extends Screen {
    public SharaClickGuiScreen() {
        super(Text.literal("SHARA Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0xDD090B12);
        context.fill(20, 20, 190, height - 20, 0xCC111522);
        context.fill(210, 20, width - 240, height - 20, 0xCC191E2D);
        context.fill(width - 220, 20, width - 20, height - 20, 0xCC111522);

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.literal("SHARA Client"),
                width / 2,
                24,
                0xFFFFFFFF
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
