package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class KeystrokesModule extends BaseModule {
    private final HudElement ks = new HudElement("keystrokes", 10, 260, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.textRenderer == null) return;
            long window = client.getWindow().getHandle();
            boolean w = InputUtil.isKeyPressed(window, InputUtil.fromTranslationKey("key.keyboard.w").getCode());
            boolean a = InputUtil.isKeyPressed(window, InputUtil.fromTranslationKey("key.keyboard.a").getCode());
            boolean s = InputUtil.isKeyPressed(window, InputUtil.fromTranslationKey("key.keyboard.s").getCode());
            boolean d = InputUtil.isKeyPressed(window, InputUtil.fromTranslationKey("key.keyboard.d").getCode());
            client.textRenderer.drawWithShadow(Text.literal("W:" + (w?"■":"□") + " A:" + (a?"■":"□") + " S:" + (s?"■":"□") + " D:" + (d?"■":"□")), getX(), getY(), 0xFFFFFFFF);
        }
    };

    public KeystrokesModule() {
        super("keystrokes", "Keystrokes", "Displays WASD key states.", ModuleCategory.HUD);
    }

    @Override protected void onEnable() { SharaClient.get().getHudManager().register(ks); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(ks); }
}
