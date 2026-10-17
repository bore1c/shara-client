package dev.shara.client.core;

public class AnimationManager {
    public enum Easing {
        LINEAR,
        EASE_IN_OUT,
        CUBIC,
        BACK,
        ELASTIC,
        SMOOTHSTEP
    }

    public double ease(double x, Easing easing) {
        return switch (easing) {
            case LINEAR -> x;
            case EASE_IN_OUT -> (1.0 - Math.cos(Math.PI * x)) / 2.0;
            case CUBIC -> x * x * (3.0 - 2.0 * x);
            case BACK -> x * x * (2.75 * x - 2.125);
            case ELASTIC -> 1.0 - Math.pow(2.0, -10.0 * x) * Math.cos((x * 10.0 - 0.75) * Math.PI / 2.0);
            case SMOOTHSTEP -> x * x * (3.0 - 2.0 * x);
        };
    }
}
