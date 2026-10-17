package dev.shara.client.render.particle;

public class Particle {
    public double x, y, z;
    public double vx, vy, vz;
    public int color;
    public float size;
    public int life;
    public int age = 0;

    public Particle(double x, double y, double z, double vx, double vy, double vz, int color, float size, int life) {
        this.x = x; this.y = y; this.z = z;
        this.vx = vx; this.vy = vy; this.vz = vz;
        this.color = color; this.size = size; this.life = life;
    }

    public boolean update(double delta) {
        x += vx * delta;
        y += vy * delta;
        z += vz * delta;
        age++;
        return age >= life;
    }
}
