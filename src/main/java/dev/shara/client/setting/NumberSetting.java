package dev.shara.client.setting;

public class NumberSetting extends Setting<Double> {
    private final double min;
    private final double max;

    public NumberSetting(String id, String name, double defaultValue, double min, double max) {
        super(id, name, defaultValue);
        this.min = min;
        this.max = max;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }

    @Override
    public void setValue(Double value) {
        double clamped = Math.max(min, Math.min(max, value));
        super.setValue(clamped);
    }
}
