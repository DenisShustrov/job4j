package ru.job4j.generic;

/**
 * Class Base.
 *
 * @author dshustrov
 * @version 1
 * @since 26.12.2018
 */
public abstract class Base {
    /**
     * id unique id.
     */
    private final String id;

    /**
     * Constructor.
     *
     * @param id unique id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Method get id.
     */
    public String getId() {
        return id;
    }
}
