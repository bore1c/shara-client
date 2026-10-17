package dev.shara.client.util;

public final class ColorUtils {
    private ColorUtils() {}

    public static int hexToArgb(String hex) {
        if (hex == null) return 0xFFFFFFFF;
        String h = hex.replace("#", "");
        if (h.length() == 6) h = "FF" + h;
        long val = Long.parseLong(h, 16) & 0xFFFFFFFFL;
        return (int) val;
    }

    public static int blend(int a, int b, float t) {
        float ta = 1 - t;
        int aa = (a >> 24) & 0xFF;
        int ar = (a >> 16) & 0xFF;
        int ag = (a >> 8) & 0xFF;
        int ab = a & 0xFF;

        int ba = (b >> 24) & 0xFF;
        int br = (b >> 16) & 0xFF;
        int bg = (b >> 8) & 0xFF;
        int bb = b & 0xFF;

        int ra = Math.min(255, (int) (aa * ta + ba * t));
        int rr = Math.min(255, (int) (ar * ta + br * t));
        int rg = Math.min(255, (int) (ag * ta + bg * t));
        int rb = Math.min(255, (int) (ab * ta + bb * t));

        return (ra << 24) | (rr << 16) | (rg << 8) | rb;
    }

    public static int rainbow(float hueOffset) {
        float h = (System.currentTimeMillis() % 10000L) / 10000f + hueOffset;
        return hsvToArgb(h - (float)Math.floor(h), 0.8f, 0.9f);
    }

    public static int hsvToArgb(float h, float s, float v) {
        int rgb = java.awt.Color.HSBtoRGB(h, s, v);
        // HSBtoRGB returns packed RGB int, add full alpha
        return (0xFF << 24) | (rgb & 0xFFFFFF);
    }
}
