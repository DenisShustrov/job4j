package ru.job4j.bomberman2;
/**
 * Class Characters.
 *
 * @author dshustrov
 * @version 1
 * @since 05.02.2019
 */
public abstract class Characters {

    /**
     * starting position.
     */
    private Cell position;

    /**
     * Constructor Characters.
     *
     * @param position this position.
     */
    public Characters(Cell position) {
        this.position = position;
    }

    public Cell getCell() {
        return position;
    }

    public void setCell(Cell cell) {
        position = cell;
    }
}
