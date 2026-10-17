package dev.shara.client.render.particle.effects;

import dev.shara.client.SharaClient;
import dev.shara.client.util.ColorUtils;

import java.util.Random;

public class PetalEffect {
    private final Random rand = new Random();

    public void spawn(double x, double y, double z) {
        int base = ColorUtils.hsvToArgb(rand.nextFloat(), 0.6f, 0.95f);
        for (int i = 0; i < 6; i++) {
            double vx = (rand.nextDouble() - 0.5) * 0.12;
            double vy = (rand.nextDouble() * 0.08) + 0.02;
            double vz = (rand.nextDouble() - 0.5) * 0.12;
            SharaClient.get().getParticleManager().spawnParticle(x + (rand.nextDouble()-0.5)*0.3, y + rand.nextDouble()*0.3, z + (rand.nextDouble()-0.5)*0.3, vx, vy, vz, base, 1.2f, 50 + rand.nextInt(30));
        }
    }
}
