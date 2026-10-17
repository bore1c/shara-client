package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

public class PingModule extends BaseModule {
    private final HudElement pingElement = new HudElement("ping", 10, 58, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) return;
            String display = "N/A";
            try {
                if (client.getNetworkHandler() != null && client.player != null) {
                    PlayerListEntry entry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
                    if (entry != null) display = String.valueOf(entry.getLatency());
                }
            } catch (Throwable ignored) {}
            client.textRenderer.drawWithShadow(Text.literal("Ping: " + display), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public PingModule() {
        super("ping", "Ping", "Shows server ping or N/A in singleplayer.", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() {
        SharaClient.get().getHudManager().register(pingElement);
    }

    @Override
    protected void onDisable() {
        SharaClient.get().getHudManager().unregister(pingElement);
    }
}
