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
    private ArrayList<String> directory;

    /**
     * Конструктор.
     *
     * @param directory каталог депортаментов в виде массива.
     */
    public SortedDep(String[] directory) {
        this.directory = new ArrayList<>(Arrays.asList(directory));
    }

    /**
     * Метод позволяет получить доступ к каталогу депортаментов.
     */
    public ArrayList<String> getDirectory() {
        return directory;
    }

    /**
     * Метод добовляет иерархию каталогов в случае отсутствия.
     */
    public void addDep() {
        for (int i = 0; i < directory.size(); i++) {
            String[] pars = directory.get(i).split("\\\\");
            if (pars.length > 1) {
                StringBuilder builder = new StringBuilder(pars[0]);
                for (int j = 1; j < pars.length; j++) {
                    if (!directory.contains(builder.toString())) {
                        directory.add(builder.toString());
                    }
                    builder.append("\\");
                    builder.append(pars[j]);
                }
            }
        }
    }

    /**
     * Метод сортирует депортаменты по возрастанию.
     */
    public ArrayList<String> sortAscending() {
        TreeSet<String> set = new TreeSet<>(directory);
        return new ArrayList<>(set);
    }

    /**
     * Метод сортирует депортаменты по убыванию.
     */
    public ArrayList<String> sortDescending() {
        directory.sort(((o1, o2) -> {
            int result;
            if (o1.length() == o2.length()) result = o2.compareTo(o1);
            else {
                int min = Math.min(o1.length(), o2.length());
                String one = o1.substring(0, min);
                String two = o2.substring(0, min);
                if (one.compareTo(two) == 0 && o1.length() > o2.length()) result = 1;
                else if (one.compareTo(two) == 0 && o1.length() < o2.length()) result = -1;
                else result = o2.compareTo(o1);
            }
            return result;
        }));
        return directory;

    }

    public static void main(String[] args) {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        SortedDep sort = new SortedDep(dep);

        System.out.println(Arrays.asList(dep));

        sort.addDep();
        System.out.println(sort.getDirectory());
        System.out.println(sort.sortDescending());

    }
}
