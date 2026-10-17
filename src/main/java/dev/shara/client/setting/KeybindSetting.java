package dev.shara.client.setting;

public class KeybindSetting extends Setting<Integer> {
    public KeybindSetting(String id, String name, int defaultKey) {
        super(id, name, defaultKey);
    }

    public int getKey() {
        return getValue();
    }

    public void setKey(int key) {
        setValue(key);
    }
}
