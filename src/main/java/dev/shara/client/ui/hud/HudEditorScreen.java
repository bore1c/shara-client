package dev.shara.client.ui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class HudEditorScreen extends Screen {
    public HudEditorScreen() {
        super(Text.literal("HUD Editor"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0x88090B12);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("HUD Editor"), width / 2, 20, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
