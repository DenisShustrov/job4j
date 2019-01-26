package ru.job4j.nonblockingalgoritm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class NonBlockingCache.
 *
 * @author dshustrov
 * @version 1
 * @since 26.01.2019
 */
public class NonBlockingCache {
    /**
     * cache model storage.
     */
    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Constructor default.
     */
    public NonBlockingCache() {
    }

    /**
     * Method add models.
     *
     * @param model add.
     */
    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    /**
     * Method delete models.
     *
     * @param model delete.
     */
    public void delete(Base model) {
        cache.remove(model.getId());
    }

    /**
     * Method update models.
     *
     * @param model update.
     */
    public void update(Base model) {
        if (cache.containsKey(model.getId())) {
            if (cache.get(model.getId()).getVersion() != model.getVersion()) {
                throw new OptimisticException("This is OptimisticException");
            } else {
                model.versionIncrement();
                cache.computeIfPresent(model.getId(), (k, v) -> v = model);

            }
        }
    }

    /**
     * Method get version models.
     *
     * @param id models.
     */
    public int getVersion(int id) {
        int result = 0;
        if (cache.containsKey(id)) {
            result = cache.get(id).getVersion();
        }
        return result;
    }
}
