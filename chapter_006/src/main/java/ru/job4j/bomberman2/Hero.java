package ru.job4j.bomberman2;

/**
 * Class Hero.
 *
 * @author dshustrov
 * @version 1
 * @since 30.01.2019
 */
public class Hero extends Characters {

    /**
     * Constructor Hero.
     *
     * @param position this position.
     */
    public Hero(Cell position) {
        super(position);
    }

    @Override
    public String toString() {
        return "The Hero is here now: x ";
    }
}
