package ru.job4j.generic;

/**
 * Class AbstractStore.
 *
 * @author dshustrov
 * @version 1
 * @since 26.12.2018
 */
public abstract class AbstractStore implements Store {
    /**
     * simpleArray collection.
     */
    private SimpleArray<Base> simpleArray;

    /**
     * Constructor.
     *
     * @param simpleArray collection initialization.
     */
    public AbstractStore(SimpleArray simpleArray) {
        this.simpleArray = simpleArray;
    }

    @Override
    public void add(Base model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        for (int i = 0; i < simpleArray.size(); i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                simpleArray.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < simpleArray.size(); i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                simpleArray.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        Base result = null;
        for (int i = 0; i < simpleArray.size(); i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                result = simpleArray.get(i);
                break;
            }
        }
        return result;
    }
}
