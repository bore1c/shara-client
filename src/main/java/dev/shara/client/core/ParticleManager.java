package dev.shara.client.core;

public class ParticleManager {
    private int particleCount;
    private int maxParticles = 500;

    public int getParticleCount() {
        return particleCount;
    }

    public int getMaxParticles() {
        return maxParticles;
    }

    public void setMaxParticles(int count) {
        this.maxParticles = Math.max(0, count);
    }

    public void setParticleCount(int count) {
        this.particleCount = Math.max(0, Math.min(count, maxParticles));
    }
}
