package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CpsModule extends BaseModule {
    private int leftClicks = 0;
    private int rightClicks = 0;
    private long lastReset = System.currentTimeMillis();

    private final HudElement cps = new HudElement("cps", 10, 276, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) return;
            long now = System.currentTimeMillis();
            if (now - lastReset >= 1000) {
                leftClicks = 0; rightClicks = 0; lastReset = now;
            }
            client.textRenderer.drawWithShadow(Text.literal("CPS L:" + leftClicks + " R:" + rightClicks), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public CpsModule() {
        super("cps", "CPS Counter", "Counts clicks per second (approx).", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() {
        SharaClient.get().getHudManager().register(cps);
        // Note: accurate click counting requires mouse callbacks. This is a lightweight approximation — external listeners should increment counters.
    }

    @Override
    protected void onDisable() { SharaClient.get().getHudManager().unregister(cps); }

    // Exposed from input handler (if wired) to increment
    public void incrementLeft() { leftClicks++; }
    public void incrementRight() { rightClicks++; }
}
