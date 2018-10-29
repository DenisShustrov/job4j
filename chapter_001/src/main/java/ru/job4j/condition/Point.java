package ru.job4j.condition;

/**
 * Class Point
 * @author dshustrov
 * @version 1
 * @since 28.10.2018
 */
public class Point {
    /**
     * Координата x.
     */
    private int x;

    /**
     * Координата y.
     */
    private int y;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param x - координата x
     * @param y - координата y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method distanceTo.
     * @param that вторая точка для вычисления.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    /**
     * Main.
     * @param args - args.
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        // сделаем вызов метода
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
