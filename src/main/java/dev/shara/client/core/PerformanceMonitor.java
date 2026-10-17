package dev.shara.client.core;

public class PerformanceMonitor {
    private long lastFrameNanos = System.nanoTime();
    private double fps = 0.0;

    public void tick() {
        long current = System.nanoTime();
        double delta = (current - lastFrameNanos) / 1_000_000_000.0;
        if (delta > 0.0) {
            fps = 1.0 / delta;
        }
        lastFrameNanos = current;
    }

    public double getFps() {
        return fps;
    }
}
