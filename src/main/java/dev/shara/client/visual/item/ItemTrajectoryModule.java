package dev.shara.client.visual.item;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.text.Text;

import java.util.List;

/**
 * ItemTrajectoryModule: client-only approximate display of projectiles in world.
 * This module only observes existing projectile entities and prints an approximate count.
 */
public class ItemTrajectoryModule extends BaseModule {
    private final HudElement element = new HudElement("projectile_info", 10, 220, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null || client.textRenderer == null) return;
            try {
                List<PersistentProjectileEntity> list = client.world.getEntitiesByClass(PersistentProjectileEntity.class, client.player.getBoundingBox().expand(64), e -> true);
                client.textRenderer.drawWithShadow(Text.literal("Projectiles nearby: " + list.size()), getX(), getY(), 0xFFFFFFAA);
            } catch (Throwable ignored) {}
        }
    };

    public ItemTrajectoryModule() {
        super("projectile_info", "Projectile Info", "Shows approximate projectile count (client-only).", ModuleCategory.ITEMS);
    }

    @Override protected void onEnable() { SharaClient.get().getHudManager().register(element); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(element); }
}
