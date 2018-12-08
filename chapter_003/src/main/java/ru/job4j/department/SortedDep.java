package ru.job4j.department;

import java.util.*;

/**
 * Class SortedDep.
 *
 * @author dshustrov
 * @version 1
 * @since 29.11.2018
 */
public class SortedDep {
    /**
     * directory каталог депортаментов в виде ArrayList.
     */
    private TreeSet<String> directory;

    /**
     * Конструктор.
     *
     * @param directory каталог депортаментов в виде массива.
     */
    public SortedDep(String[] directory) {
        this.directory = new TreeSet<>(Arrays.asList(directory));
    }

    /**
     * Метод позволяет получить доступ к каталогу депортаментов.
     */
    public TreeSet<String> getDirectory() {
        return directory;
    }

    /**
     * Метод добовляет иерархию каталогов в случае отсутствия.
     */
    public void addDep() {
        TreeSet<String> temp = new TreeSet<>();
        for (String str : directory) {
            String[] pars = str.split("\\\\");
            if (pars.length > 1) {
                StringBuilder builder = new StringBuilder(pars[0]);
                for (int j = 1; j < pars.length; j++) {
                    temp.add(builder.toString());
                    builder.append("\\");
                    builder.append(pars[j]);
                }
            }
        }
        directory.addAll(temp);
    }

    /**
     * Метод сортирует депортаменты по убыванию.
     */
    public void sortDescending() {
        TreeSet<String> temp = new TreeSet<>(((o1, o2) -> {
            int result;
            if (o1.length() == o2.length()) {
                result = o2.compareTo(o1);
            } else {
                int min = Math.min(o1.length(), o2.length());
                String one = o1.substring(0, min);
                String two = o2.substring(0, min);
                if (one.compareTo(two) == 0 && o1.length() > o2.length()) {
                    result = 1;
                } else if (one.compareTo(two) == 0 && o1.length() < o2.length()) {
                    result = -1;
                } else {
                    result = o2.compareTo(o1);
                }
            }
            return result;
        }));
        temp.addAll(directory);
        directory = temp;
    }

    /**
     * Метод сортирует депортаменты по возрастанию после использования метода sortDescending.
     */
    public void sortAscendingAfterSortDescending() {
        TreeSet<String> temp = new TreeSet<>();
        temp.addAll(directory);
        directory = temp;
    }
}
