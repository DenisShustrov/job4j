package ru.job4j.pseudo;

/**
 * Class Paint.
 *
 * @author dshustrov
 * @version 1
 * @since 13.11.2018
 */
public class Paint {

    public void draw(Shape shape) {
        /**
         * Метод метод выводит на консоль фигуры.
         * @return строка в виде треугольника.
         */
        System.out.println(shape.draw());
    }
}
