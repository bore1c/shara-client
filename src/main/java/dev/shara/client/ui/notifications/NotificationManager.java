package dev.shara.client.ui.notifications;

import dev.shara.client.SharaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayDeque;
import java.util.Deque;

public class NotificationManager {
    private final Deque<String> queue = new ArrayDeque<>();

    public void push(String text) {
        synchronized (queue) {
            queue.addFirst(text);
            while (queue.size() > 5) queue.removeLast();
        }
    }

    public void render(DrawContext ctx) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.textRenderer == null) return;
        int y = 30;
        synchronized (queue) {
            for (String t : queue) {
                client.textRenderer.drawWithShadow(Text.literal(t), 10, y, 0xFFFFFFAA);
                y += 12;
            }
        }
    }

    public void clear() { synchronized (queue) { queue.clear(); } }
}
