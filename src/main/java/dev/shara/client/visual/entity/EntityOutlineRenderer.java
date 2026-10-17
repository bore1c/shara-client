package dev.shara.client.visual.entity;

import dev.shara.client.SharaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

/**
 * EntityOutlineRenderer
 * Lightweight placeholder for entity outline rendering. Currently uses a safe, non-GL text overlay
 * until a full render-pass implementation is added. Registered renderers should be called from a RenderManager.
 */
public class EntityOutlineRenderer {
    public void render(MatrixStack matrices, float tickDelta) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.player == null) return;
            // Placeholder: no-op for now. Real 3D outlines require render pass and buffer management.
        } catch (Throwable t) {
            SharaClient.LOGGER.debug("EntityOutlineRenderer exception", t);
        }
    }
}
