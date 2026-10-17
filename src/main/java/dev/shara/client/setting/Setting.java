package dev.shara.client.setting;

public abstract class Setting<T> {
    private final String id;
    private final String name;
    private T value;
    private final T defaultValue;

    protected Setting(String id, String name, T defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public T getValue() { return value; }
    public T getDefaultValue() { return defaultValue; }

    public void setValue(T value) {
        this.value = value;
    }

    public void reset() {
        this.value = defaultValue;
    }
}
