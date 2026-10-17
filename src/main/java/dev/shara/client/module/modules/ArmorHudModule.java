package dev.shara.client.module.modules;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ArmorHudModule extends BaseModule {
    private final HudElement armor = new HudElement("armor_hud", 10, 440, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.player == null || client.textRenderer == null) return;
            ItemStack head = client.player.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chest = client.player.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legs = client.player.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feet = client.player.getEquippedStack(EquipmentSlot.FEET);
            client.textRenderer.drawWithShadow(Text.literal("Armor:"), getX(), getY(), 0xFFFFFFFF);
            client.textRenderer.drawWithShadow(Text.literal("H:" + (head.isEmpty()?"-":head.getName().getString())), getX(), getY()+10, 0xFFFFFFFF);
            client.textRenderer.drawWithShadow(Text.literal("C:" + (chest.isEmpty()?"-":chest.getName().getString())), getX(), getY()+20, 0xFFFFFFFF);
            client.textRenderer.drawWithShadow(Text.literal("L:" + (legs.isEmpty()?"-":legs.getName().getString())), getX(), getY()+30, 0xFFFFFFFF);
            client.textRenderer.drawWithShadow(Text.literal("F:" + (feet.isEmpty()?"-":feet.getName().getString())), getX(), getY()+40, 0xFFFFFFFF);
        }
    };

    public ArmorHudModule() { super("armor_hud", "Armor HUD", "Shows equipped armor items.", ModuleCategory.HUD); }
    @Override protected void onEnable() { SharaClient.get().getHudManager().register(armor); }
    @Override protected void onDisable() { SharaClient.get().getHudManager().unregister(armor); }
}
