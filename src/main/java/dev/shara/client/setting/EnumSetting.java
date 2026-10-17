package dev.shara.client.setting;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    public EnumSetting(String id, String name, T defaultValue) {
        super(id, name, defaultValue);
    }
}
