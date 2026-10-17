package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class WatermarkModule extends BaseModule {
    private final HudElement watermarkElement = new HudElement("watermark", 10, 10, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) {
                return;
            }
            client.textRenderer.drawWithShadow(
                    Text.literal("SHARA Client"),
                    getX(),
                    getY(),
                    0xFFA78BFA
            );
        }
    };

    public WatermarkModule() {
        super("watermark", "Watermark", "Shows the SHARA Client watermark.", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() {
        SharaClient.get().getHudManager().register(watermarkElement);
    }

    @Override
    protected void onDisable() {
        SharaClient.get().getHudManager().unregister(watermarkElement);
    }
}
