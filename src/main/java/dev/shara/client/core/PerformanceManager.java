package dev.shara.client.core;

public class PerformanceManager {
    private PerformancePreset current = PerformancePreset.BALANCED;

    public void applyPreset(PerformancePreset preset) {
        this.current = preset;
        switch (preset) {
            case LOW_END -> {
                // reduce particles
                SharaClient.get().getParticleManager().setMaxParticles(100);
                // disable fancy render passes
                SharaClient.get().getRenderManager().shouldSkipRenderPass();
            }
            case BALANCED -> {
                SharaClient.get().getParticleManager().setMaxParticles(400);
            }
            case HIGH_QUALITY -> {
                SharaClient.get().getParticleManager().setMaxParticles(1000);
            }
        }
    }

    public PerformancePreset getCurrent() { return current; }
}
