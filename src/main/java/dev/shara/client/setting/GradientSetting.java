package dev.shara.client.setting;

public class GradientSetting extends Setting<Integer[]> {
    public GradientSetting(String id, String name, Integer[] defaultStops) {
        super(id, name, defaultStops);
    }

    public Integer[] getStops() { return getValue(); }

    public void setStops(Integer[] stops) { setValue(stops); }
}
