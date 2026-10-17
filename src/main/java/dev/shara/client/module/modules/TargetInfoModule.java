package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

public class TargetInfoModule extends BaseModule {
    private final HudElement targetElement = new HudElement("target_info", 10, 80, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.crosshairTarget == null) return;
            try {
                var hit = client.crosshairTarget;
                if (hit.getType() != net.minecraft.util.hit.HitResult.Type.ENTITY) return;
                var ent = ((net.minecraft.client.world.ClientWorld)client.world).getEntityById(((net.minecraft.util.hit.EntityHitResult)hit).getEntity().getId());
                if (ent instanceof LivingEntity le) {
                    String name = ent.getName().getString();
                    float health = (float) (le.getHealth());
                    client.textRenderer.drawWithShadow(Text.literal("Target: " + name), getX(), getY(), 0xFFFFFFFF);
                    client.textRenderer.drawWithShadow(Text.literal("HP: " + Math.round(health)), getX(), getY()+10, 0xFFAAFFAA);
                }
            } catch (Throwable t) {
                // safe guard: do not crash
            }
        }
    };

    public TargetInfoModule() {
        super("target_info", "Target Info", "Displays info about the entity you are looking at.", ModuleCategory.HUD);
    }

    @Override
    protected void onEnable() { SharaClient.get().getHudManager().register(targetElement); }
    @Override
    protected void onDisable() { SharaClient.get().getHudManager().unregister(targetElement); }
}
