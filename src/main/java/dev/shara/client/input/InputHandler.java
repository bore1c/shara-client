package dev.shara.client.input;

import dev.shara.client.SharaClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class InputHandler {
    private static KeyBinding openGuiKey;
    private static KeyBinding openHudEditorKey;

    public static void init(KeybindManager km) {
        // register keybinds
        openGuiKey = km.register("open_gui", "key.shara.open_gui", KeybindManager.key("M"));
        openHudEditorKey = km.register("open_hud_editor", "key.shara.open_hud_editor", KeybindManager.key("G"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                if (openGuiKey.wasPressed()) {
                    MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new net.minecraft.client.gui.screen.Screen(net.minecraft.text.Text.literal("")) {
                        @Override
                        public void render(net.minecraft.client.gui.DrawContext drawContext, int mouseX, int mouseY, float delta) {
                            super.render(drawContext, mouseX, mouseY, delta);
                        }

                        @Override
                        public boolean shouldPause() { return false; }
                    }));
                }
                if (openHudEditorKey.wasPressed()) {
                    MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new dev.shara.client.ui.hud.HudEditorScreen()));
                }
            } catch (Throwable t) {
                SharaClient.LOGGER.debug("Input tick error", t);
            }
        });
    }
}
