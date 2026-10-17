package dev.shara.client.util;

import com.mojang.blaze3d.systems.RenderSystem;

public final class RenderUtils {
    private RenderUtils() {}

    /**
     * Call after custom rendering to restore safe GL state.
     */
    public static void safeRestoreState() {
        try {
            RenderSystem.disableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.color4f(1f,1f,1f,1f);
        } catch (Throwable ignored) {}
    }

    public static int argb(int a, int r, int g, int b) {
        return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }
}
