package dev.shara.client.ui.hud;

public abstract class HudElement {
    private final String id;
    private int x;
    private int y;
    private boolean visible;

    protected HudElement(String id, int x, int y, boolean visible) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visible = visible;
    }

    public String getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isVisible() { return visible; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public abstract void render(double width, double height, float delta);
}
