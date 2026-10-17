package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import dev.shara.client.ui.notifications.NotificationManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NotificationsModule extends BaseModule {
    private final HudElement notifier = new HudElement("notifications", 10, 700, true) {
        @Override
        public void render(double width, double height, float delta) {
            var nm = SharaClient.get().getNotificationManager();
            if (nm == null) return;
            nm.render(net.minecraft.client.MinecraftClient.getInstance().getWindow().getFramebuffer().getColorTextureId() == 0 ? null : null);
        }
    };

    public NotificationsModule() { super("notifications", "Notifications", "Displays client notifications.", ModuleCategory.INTERFACE); }
    @Override protected void onEnable() { SharaClient.get().getHudManager().register(notifier); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(notifier); }
}
