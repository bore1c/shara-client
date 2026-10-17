package dev.shara.client.visual.world;

import dev.shara.client.SharaClient;
import dev.shara.client.module.BaseModule;
import dev.shara.client.module.ModuleCategory;
import dev.shara.client.ui.hud.HudElement;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class BlockHighlightModule extends BaseModule {
    private final HudElement highlightElement = new HudElement("block_highlight", 10, 200, true) {
        @Override
        public void render(double width, double height, float delta) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.crosshairTarget == null || client.textRenderer == null) return;
            try {
                var hit = client.crosshairTarget;
                if (hit.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
                    var pos = ((net.minecraft.util.hit.BlockHitResult) hit).getBlockPos();
                    BlockState state = client.world.getBlockState(pos);
                    String name = state.getBlock().getName().getString();
                    client.textRenderer.drawWithShadow(Text.literal("Looking at: " + name), getX(), getY(), 0xFFFFFFFF);
                }
            } catch (Throwable ignored) {}
        }
    };

    public BlockHighlightModule() {
        super("block_highlight", "Block Highlight", "Shows the block you are looking at (client-only).", ModuleCategory.WORLD);
    }

    @Override
    protected void onEnable() { SharaClient.get().getHudManager().register(highlightElement); }
    @Override
    protected void onDisable() { SharaClient.get().getHudManager().unregister(highlightElement); }
}
