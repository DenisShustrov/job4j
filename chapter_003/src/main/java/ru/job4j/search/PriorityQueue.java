package ru.job4j.search;

import java.util.LinkedList;
import java.util.stream.Collectors;

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
            //int index = 0;
//            for (int i = 0; i < tasks.size(); i++) {
//                if (tasks.get(i).getPriority() > task.getPriority()) {
//                    index = i;
//                    break;
//                }
//                index++;
//            }
//            tasks.add(index, task);
             var index = tasks.stream().filter(tasks -> tasks.getPriority() < task.getPriority()).collect(Collectors.toList()).size();
             this.tasks.add(index, task);
    }


    public Task take() {
        return this.tasks.poll();
    }
}
