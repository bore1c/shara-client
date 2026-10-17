package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockModule extends BaseModule {
    private final HudElement clock = new HudElement("clock", 10, 620, true) {
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) return;
            String time = LocalTime.now().format(fmt);
            client.textRenderer.drawWithShadow(Text.literal("Time: " + time), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public ClockModule() { super("clock", "Clock", "Shows local system time.", ModuleCategory.HUD); }
    @Override protected void onEnable() { SharaClient.get().getHudManager().register(clock); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(clock); }
}
