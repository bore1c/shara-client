package dev.shara.client.render.particle.effects;

import dev.shara.client.SharaClient;
import dev.shara.client.util.ColorUtils;

import java.util.Random;

public class OrbEffect {
    private final Random rand = new Random();

    public void spawn(double x, double y, double z) {
        int color = ColorUtils.hsvToArgb(rand.nextFloat(), 0.7f, 0.9f);
        for (int i = 0; i < 4; i++) {
            double vx = (rand.nextDouble() - 0.5) * 0.06;
            double vy = (rand.nextDouble() - 0.5) * 0.06;
            double vz = (rand.nextDouble() - 0.5) * 0.06;
            SharaClient.get().getParticleManager().spawnParticle(x, y, z, vx, vy, vz, color, 1.8f, 80 + rand.nextInt(40));
        }
    }
}
