package ru.job4j.bomberman2;

/**
 * Class MonsterTwo.
 *
 * @author dshustrov
 * @version 1
 * @since 05.02.2019
 */
public class MonsterTwo extends Characters {

    /**
     * Constructor MonsterTwo.
     *
     * @param position this position.
     */
    public MonsterTwo(Cell position) {
        super(position);
    }

    @Override
    public String toString() {
        return "The MonsterTwo is here now: x ";
    }
}
