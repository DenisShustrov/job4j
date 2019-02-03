package ru.job4j.bomberman2;

import java.util.Objects;

/**
 * Class Monster.
 *
 * @author dshustrov
 * @version 1
 * @since 04.02.2019
 */
public class Monster {

    /**
     * starting position.
     */
    private Cell position;

    /**
     * Constructor Monster.
     *
     * @param position this position.
     */
    public Monster(Cell position) {
        this.position = position;
    }

    public Cell getCell() {
        return position;
    }

    public void setCell(Cell cell) {
        position = cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Monster monster = (Monster) o;
        return Objects.equals(position, monster.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
