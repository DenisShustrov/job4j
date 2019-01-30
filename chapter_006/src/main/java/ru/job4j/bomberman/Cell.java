package ru.job4j.bomberman;

import java.util.Objects;

/**
 * Class Cell.
 *
 * @author dshustrov
 * @version 1
 * @since 30.01.2019
 */
public class Cell {
    /**
     * x position.
     */
    private int x;
    /**
     * y position.
     */
    private int y;

    /**
     * Constructor Cell.
     *
     * @param x position.
     * @param y position.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

