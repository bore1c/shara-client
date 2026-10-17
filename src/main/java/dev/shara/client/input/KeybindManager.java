package dev.shara.client.input;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class KeybindManager {
    private final Map<String, KeyBinding> bindings = new HashMap<>();

    public KeybindManager() {}

    public KeyBinding register(String id, String translationKey, int key) {
        KeyBinding kb = KeyBindingHelper.registerKeyBinding(new KeyBinding(translationKey, InputUtil.Type.KEYSYM, key, "key.category.shara"));
        bindings.put(id, kb);
        return kb;
    }

    public KeyBinding get(String id) {
        return bindings.get(id);
    }

    public static int key(String name) {
        return switch (name.toUpperCase()) {
            case "RSHIFT" -> GLFW.GLFW_KEY_RIGHT_SHIFT;
            case "LSHIFT" -> GLFW.GLFW_KEY_LEFT_SHIFT;
            case "RCTRL" -> GLFW.GLFW_KEY_RIGHT_CONTROL;
            case "LCTRL" -> GLFW.GLFW_KEY_LEFT_CONTROL;
            case "M" -> GLFW.GLFW_KEY_M;
            case "G" -> GLFW.GLFW_KEY_G;
            default -> GLFW.GLFW_KEY_UNKNOWN;
        };
    }
}
