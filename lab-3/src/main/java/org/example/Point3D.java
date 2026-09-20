package org.example;
public class Point3D {
    private Complex x;
    private Complex y;
    private Complex z;

    public Point3D(Complex x, Complex y, Complex z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Расстояние до начала координат (используем модули комплексных координат как проекции в пространстве)
    public double distanceToOrigin() {
        double mx = x.module();
        double my = y.module();
        double mz = z.module();
        return Math.sqrt(mx * mx + my * my + mz * mz);
    }

    // Расстояние между двумя точками в пространстве
    public double distanceTo(Point3D other) {
        double dx = x.module() - other.x.module();
        double dy = y.module() - other.y.module();
        double dz = z.module() - other.z.module();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}