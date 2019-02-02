package ru.job4j.bomberman;


/**
 * Class Hero.
 *
 * @author dshustrov
 * @version 1
 * @since 30.01.2019
 */
public class Hero {
    /**
     * starting position.
     */
    private Cell position;

    /**
     * Constructor Hero.
     *
     * @param x position.
     * @param y position
     */
    public Hero(int x, int y) {
        this.position = new Cell(x, y);
    }

    public Cell getCell() {
        return position;
    }

    public void setCell(Cell cell) {
        position = cell;
    }

}