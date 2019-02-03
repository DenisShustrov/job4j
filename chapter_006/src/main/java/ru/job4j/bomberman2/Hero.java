package ru.job4j.bomberman2;

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
     * @param position this position.
     */
    public Hero(Cell position) {
        this.position = position;
    }

    public Cell getCell() {
        return position;
    }

    public void setCell(Cell cell) {
        position = cell;
    }

}
