package ru.job4j.tracker;

/**
 * Class BaseAction.
 *
 * @author dshustrov
 * @version 1
 * @since 18.11.2018
 */
public abstract class BaseAction implements UserAction {

    /**
     * Значение ключа.
     */
    private final int key;

    /**
     * Название действия.
     */
    private final String name;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param key ключ.
     * @param name название действия.
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }
}
