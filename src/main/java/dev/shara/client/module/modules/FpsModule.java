package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class FpsModule extends BaseModule {
    private final HudElement fpsElement = new HudElement("fps", 10, 28, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) {
                return;
            }
            long fpsValue = Math.round(SharaClient.get().getPerformanceMonitor().getFps());
            client.textRenderer.drawWithShadow(
                    Text.literal("FPS: " + fpsValue),
                    getX(),
                    getY(),
                    0xFFFFFFFF
            );
        }
    };

    public FpsModule() {
        super("fps", "FPS Counter", "Shows current FPS.", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() {
        SharaClient.get().getHudManager().register(fpsElement);
    }

    @Override
    protected void onDisable() {
        SharaClient.get().getHudManager().unregister(fpsElement);
    }
}
