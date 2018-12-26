package ru.job4j.generic;

/**
 * Interface tore<T extends Base>
 *
 * @author dshustrov
 * @version 1
 * @since 26.12.2018
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
