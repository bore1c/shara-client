package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CompassModule extends BaseModule {
    private final HudElement comp = new HudElement("compass", 10, 640, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.player == null || client.textRenderer == null) return;
            float yaw = client.player.getYaw();
            String dir = "S";
            yaw = (yaw % 360 + 360) % 360;
            if (yaw >= 315 || yaw < 45) dir = "S";
            else if (yaw >= 45 && yaw < 135) dir = "W";
            else if (yaw >= 135 && yaw < 225) dir = "N";
            else dir = "E";
            client.textRenderer.drawWithShadow(Text.literal("Dir: " + dir + " (Yaw:" + Math.round(yaw) + ")"), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public CompassModule() { super("compass", "Compass", "Shows rough facing direction.", ModuleCategory.HUD); }
    @Override protected void onEnable() { SharaClient.get().getHudManager().register(comp); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(comp); }
}
