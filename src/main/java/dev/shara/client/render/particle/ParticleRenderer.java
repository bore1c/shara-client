package dev.shara.client.render.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.shara.client.SharaClient;
import dev.shara.client.util.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

public class ParticleRenderer {
    private final ParticlePool pool;

    public ParticleRenderer(ParticlePool pool) {
        this.pool = pool;
    }

    public void render(MatrixStack matrices, float tickDelta) {
        var client = MinecraftClient.getInstance();
        if (client == null) return;

        var snapshot = pool.snapshot();

        // Simple sprite rendering using fill calls (cheap)
        for (var p : snapshot) {
            int col = p.color;
            int a = (col >> 24) & 0xFF;
            int r = (col >> 16) & 0xFF;
            int g = (col >> 8) & 0xFF;
            int b = col & 0xFF;

            int screenX = (int) (p.x - client.gameRenderer.getCamera().getPos().x);
            int screenY = (int) (p.y - client.gameRenderer.getCamera().getPos().y);

            // fallback: render via HUD draw - approximate
            client.textRenderer.drawWithShadow(matrices, "*", screenX, screenY, ColorUtils.blend(col, 0x00FFFFFF, 0.0f));
        }
    }
}
