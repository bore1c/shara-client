package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.stream.Collectors;

public class EntityListModule extends BaseModule {
    private final HudElement listElement = new HudElement("entity_list", 10, 120, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null || client.player == null || client.textRenderer == null) return;
            List<LivingEntity> ents = client.world.getEntitiesByClass(LivingEntity.class, client.player.getBoundingBox().expand(64), e -> e != client.player).stream().collect(Collectors.toList());
            int yy = getY();
            client.textRenderer.drawWithShadow(Text.literal("Entities (nearby): " + ents.size()), getX(), yy, 0xFFFFFFAA);
            yy += 10;
            int limit = 12;
            for (int i = 0; i < Math.min(ents.size(), limit); i++) {
                LivingEntity e = ents.get(i);
                double dx = e.getX() - client.player.getX();
                double dy = e.getY() - client.player.getY();
                double dz = e.getZ() - client.player.getZ();
                double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
                String line = String.format("%s (%.1fm)", e.getName().getString(), dist);
                client.textRenderer.drawWithShadow(Text.literal(line), getX(), yy, 0xFFDDDDFF);
                yy += 10;
            }
        }
    };

    public EntityListModule() {
        super("entity_list", "Entity List", "Shows nearby entities and distance (client-only).", ModuleCategory.VISUALS);
    }

    @Override
    protected void onEnable() { SharaClient.get().getHudManager().register(listElement); }
    @Override
    protected void onDisable() { SharaClient.get().getHudManager().unregister(listElement); }
}
