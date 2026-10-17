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
 * Tracer module (textual): shows count of tracers that would be drawn to entities.
 * Actual tracer lines require world-space to screen-space transforms and render passes.
 */
public class EntityTracerModule extends BaseModule {
    private final HudElement tracerInfo = new HudElement("entity_tracer", 10, 400, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null || client.player == null || client.textRenderer == null) return;
            List<LivingEntity> ents = client.world.getEntitiesByClass(LivingEntity.class, client.player.getBoundingBox().expand(128), e -> e != client.player).stream().collect(Collectors.toList());
            client.textRenderer.drawWithShadow(Text.literal("Tracers (approx): " + ents.size()), getX(), getY(), 0xFFFFFFAA);
        }
    };

    public EntityTracerModule() {
        super("entity_tracer", "Entity Tracers", "Shows approximate tracer counts (client-only).", ModuleCategory.VISUALS);
    }

    @Override protected void onEnable() { SharaClient.get().getHudManager().register(tracerInfo); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(tracerInfo); }
}
