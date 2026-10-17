package dev.shara.client.render.particle.effects;

import dev.shara.client.SharaClient;
import dev.shara.client.util.ColorUtils;

import java.util.Random;

public class SparkEffect {
    private final Random rand = new Random();

    public void spawn(double x, double y, double z) {
        int color = ColorUtils.hsvToArgb(rand.nextFloat(), 0.8f, 0.9f);
        for (int i = 0; i < 8; i++) {
            double vx = (rand.nextDouble() - 0.5) * 0.3;
            double vy = (rand.nextDouble() - 0.5) * 0.3 + 0.05;
            double vz = (rand.nextDouble() - 0.5) * 0.3;
            SharaClient.get().getParticleManager().spawnParticle(x, y, z, vx, vy, vz, color, 1.0f, 40 + rand.nextInt(20));
        }
    }
}
