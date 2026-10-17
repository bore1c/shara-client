package dev.shara.client.core;

import dev.shara.client.ui.hud.HudElement;
import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<HudElement> elements = new ArrayList<>();

    public void register(HudElement element) {
        elements.add(element);
    }

    public void unregister(HudElement element) {
        elements.remove(element);
    }

    public List<HudElement> getElements() {
        return elements;
    }

    public void renderAll(double width, double height, float delta) {
        for (HudElement element : elements) {
            if (element.isVisible()) {
                element.render(width, height, delta);
            }
        }
    }
}
