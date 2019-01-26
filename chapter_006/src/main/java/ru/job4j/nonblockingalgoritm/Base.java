package ru.job4j.nonblockingalgoritm;

/**
 * Class Base.
 *
 * @author dshustrov
 * @version 1
 * @since 26.01.2019
 */
public class Base {
    /**
     * id of models.
     */
    private int id;
    /**
     * version of models.
     */
    private int version = 0;

    /**
     * Constructor.
     */
    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void versionIncrement() {
        version++;
    }
}
