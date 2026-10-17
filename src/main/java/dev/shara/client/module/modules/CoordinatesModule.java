package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CoordinatesModule extends BaseModule {
    private final HudElement coordsElement = new HudElement("coords", 10, 44, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.player == null || client.textRenderer == null) return;
            var p = client.player;
            int x = (int) Math.floor(p.getX());
            int y = (int) Math.floor(p.getY());
            int z = (int) Math.floor(p.getZ());
            client.textRenderer.drawWithShadow(Text.literal("XYZ: " + x + ", " + y + ", " + z), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public CoordinatesModule() {
        super("coordinates", "Coordinates", "Shows player coordinates.", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() {
        SharaClient.get().getHudManager().register(coordsElement);
    }

    @Override
    protected void onDisable() {
        SharaClient.get().getHudManager().unregister(coordsElement);
    }
}
