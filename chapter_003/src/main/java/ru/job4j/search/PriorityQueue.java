package ru.job4j.search;

import java.util.LinkedList;

/**
 * Class PriorityQueue.
 *
 * @author dshustrov
 * @version 1
 * @since 26.11.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.size() == 0) {
            tasks.addFirst(task);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDesc().equals(task.getDesc())) {
                    tasks.remove(i);
                    break;
                }
            }
            for (int y = 0; y < tasks.size(); y++) {
                if (tasks.get(y).getPriority() > task.getPriority()) {
                    tasks.add(y, task);
                    break;
                } else {
                    tasks.addLast(task);
                    break;
                }
            }
        }
    }
    public Task take() {
        return this.tasks.poll();
    }
}
