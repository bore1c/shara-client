package dev.shara.client.visual.entity;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple Entity Box HUD: lists nearby living entities with distance and health.
 * This is intentionally a HUD (text) fallback. Proper 3D box rendering requires careful render state handling
 * and is planned as an advanced feature (would use render layers and buffers).
 */
public class EntityBoxModule extends BaseModule {
    private final HudElement listElement = new HudElement("entity_box", 10, 320, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null || client.player == null || client.textRenderer == null) return;
            List<LivingEntity> ents = client.world.getEntitiesByClass(LivingEntity.class, client.player.getBoundingBox().expand(64), e -> e != client.player).stream().collect(Collectors.toList());
            int yy = getY();
            client.textRenderer.drawWithShadow(Text.literal("Entity Boxes (count: " + ents.size() + ")"), getX(), yy, 0xFFFFFFAA);
            yy += 10;
            int limit = 8;
            for (int i = 0; i < Math.min(ents.size(), limit); i++) {
                LivingEntity e = ents.get(i);
                double dx = e.getX() - client.player.getX();
                double dy = e.getY() - client.player.getY();
                double dz = e.getZ() - client.player.getZ();
                double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
                float health = (float) (e.getHealth());
                String line = String.format("%s HP:%.1f Dist:%.1fm", e.getName().getString(), health, dist);
                client.textRenderer.drawWithShadow(Text.literal(line), getX(), yy, 0xFFDDEEFF);
                yy += 10;
            }
        }
    };

    public EntityBoxModule() {
        super("entity_box", "Entity Boxes", "Displays a textual list of nearby entities (client-only).", ModuleCategory.VISUALS);
    }

    @Override protected void onEnable() { SharaClient.get().getHudManager().register(listElement); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(listElement); }
}
