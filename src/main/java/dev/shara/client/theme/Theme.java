package dev.shara.client.theme;

import java.util.Map;

public class Theme {
    private String id;
    private String displayName;
    private Map<String, Integer> colors; // color name -> ARGB int
    private boolean gradient;

    public Theme(String id, String displayName, Map<String, Integer> colors, boolean gradient) {
        this.id = id;
        this.displayName = displayName;
        this.colors = colors;
        this.gradient = gradient;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public Map<String, Integer> getColors() { return colors; }
    public boolean isGradient() { return gradient; }
}
