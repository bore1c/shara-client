package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.stream.Collectors;

public class InventoryHudModule extends BaseModule {
    private final HudElement inv = new HudElement("inventory_hud", 10, 500, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.player == null || client.textRenderer == null) return;
            var items = client.player.inventory.main.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
            client.textRenderer.drawWithShadow(Text.literal("Inv: " + items.size()), getX(), getY(), 0xFFFFFFFF);
            int yy = getY() + 10;
            for (ItemStack s : items) {
                client.textRenderer.drawWithShadow(Text.literal(s.getName().getString() + " x" + s.getCount()), getX(), yy, 0xFFCCCCCC);
                yy += 10; if (yy > getY() + 100) break;
            }
        }
    };

    public InventoryHudModule() { super("inventory_hud", "Inventory HUD", "Shows a compact inventory summary.", ModuleCategory.HUD); }
    @Override protected void onEnable() { SharaClient.get().getHudManager().register(inv); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(inv); }
}
