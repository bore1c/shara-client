package dev.shara.client.render.animation;

import dev.shara.client.core.AnimationManager;

public class Animator {
    private double value;
    private double target;
    private double speed = 6.0; // units per second
    private AnimationManager.Easing easing = AnimationManager.Easing.SMOOTHSTEP;

    public Animator(double start) {
        this.value = start;
        this.target = start;
    }

    public void setTarget(double t) { this.target = t; }
    public double getValue() { return value; }
    public void setSpeed(double s) { this.speed = s; }
    public void setEasing(AnimationManager.Easing e) { this.easing = e; }

    public void update(double delta) {
        if (Math.abs(target - value) < 0.0001) return;
        double diff = target - value;
        double step = Math.signum(diff) * speed * delta;
        if (Math.abs(step) >= Math.abs(diff)) value = target; else value += step;
    }
}
