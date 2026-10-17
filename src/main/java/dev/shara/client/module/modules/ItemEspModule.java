package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;

import java.util.List;

public class ItemEspModule extends BaseModule {
    private final HudElement itemsElement = new HudElement("item_esp", 10, 96, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null || client.textRenderer == null) return;
            try {
                List<ItemEntity> items = client.world.getEntitiesByClass(ItemEntity.class, client.player.getBoundingBox().expand(64), e -> true);
                client.textRenderer.drawWithShadow(Text.literal("Nearby items: " + items.size()), getX(), getY(), 0xFFFFFFAA);
            } catch (Throwable t) {
                // ignore
            }
        }
    };

    public ItemEspModule() {
        super("item_esp", "Item ESP", "Displays nearby dropped items count.", ModuleCategory.ITEMS);
    }

    @Override
    protected void onEnable() { SharaClient.get().getHudManager().register(itemsElement); }
    @Override
    protected void onDisable() { SharaClient.get().getHudManager().unregister(itemsElement); }
}
