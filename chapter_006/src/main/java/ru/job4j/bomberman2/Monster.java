package ru.job4j.bomberman2;

/**
 * Class Monster.
 *
 * @author dshustrov
 * @version 1
 * @since 04.02.2019
 */
public class Monster extends Characters {

    /**
     * Constructor Monster.
     *
     * @param position this position.
     */
    public Monster(Cell position) {
        super(position);
    }

    @Override
    public String toString() {
        return "The Monster is here now: x ";
    }
}
