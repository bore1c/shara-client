package dev.shara.client.visual.world;

import dev.shara.client.SharaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

/**
 * BlockOutlineRenderer
 * Placeholder for block outline rendering. For safety we do not perform direct GL operations here.
 */
public class BlockOutlineRenderer {
    public void render(MatrixStack matrices, float tickDelta) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.crosshairTarget == null) return;
            // placeholder: show nothing currently
        } catch (Throwable t) {
            SharaClient.LOGGER.debug("BlockOutlineRenderer exception", t);
        }
    }
}
