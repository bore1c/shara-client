package dev.shara.client.setting;

import java.util.HashSet;
import java.util.Set;

public class MultiSelectSetting extends Setting<Set<String>> {
    public MultiSelectSetting(String id, String name) {
        super(id, name, new HashSet<>());
    }

    public void select(String value) {
        getValue().add(value);
    }

    public void unselect(String value) {
        getValue().remove(value);
    }

    public boolean isSelected(String value) {
        return getValue().contains(value);
    }
}
