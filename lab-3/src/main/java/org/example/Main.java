package org.example;
public class Main {
    public static void main(String[] args) {
        // Создаем комплексные координаты для точек
        Complex c1 = new Complex(3.0, 4.0); // модуль = 5.0
        Complex c2 = new Complex(0.0, 0.0); // модуль = 0.0
        Complex c3 = new Complex(1.0, 1.0); // модуль ≈ 1.414

        Point3D p1 = new Point3D(c1, c2, c3);
        Point3D p2 = new Point3D(new Complex(1.0, 0.0), new Complex(2.0, 0.0), new Complex(2.0, 0.0));

        System.out.println("Точка 1: " + p1);
        System.out.println("Точка 2: " + p2);

        System.out.println("\nРасстояние от Точки 1 до начала координат: " + p1.distanceToOrigin());
        System.out.println("Расстояние между Точкой 1 и Точкой 2: " + p1.distanceTo(p2));
    }
}