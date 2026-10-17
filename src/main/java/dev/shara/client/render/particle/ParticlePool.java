package dev.shara.client.render.particle;

import java.util.ArrayList;
import java.util.List;

public class ParticlePool {
    private final List<Particle> particles = new ArrayList<>();
    private int maxSize = 500;

    public ParticlePool(int maxSize) {
        this.maxSize = Math.max(0, maxSize);
    }

    public synchronized void spawn(Particle p) {
        if (particles.size() >= maxSize) return;
        particles.add(p);
    }

    public synchronized void tick(double delta) {
        var it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            boolean dead = p.update(delta);
            if (dead) it.remove();
        }
    }

    public synchronized List<Particle> snapshot() {
        return new ArrayList<>(particles);
    }

    public synchronized int count() { return particles.size(); }

    public synchronized void setMaxSize(int s) { this.maxSize = Math.max(0, s); }
}
