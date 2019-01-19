package ru.job4j.tree;

import java.util.*;

/**
 * Class Analize.
 *
 * @author dshustrov
 * @version 1
 * @since 16.01.2019
 */
public class Analize {
    /**
     * Method returns collection change statistics.
     *
     * @param previous collection.
     * @param current  collection.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info in = new Info();
//        // we equalize the length of the collections
//        if (previous.size() > current.size()) {
//            for (int i = 0; i <= previous.size() - current.size(); i++) {
//                current.add(null);
//            }
//        } else {
//            for (int i = 0; i <= current.size() - previous.size(); i++) {
//                previous.add(null);
//            }
//        }
//        // main loop
//        for (int i = 0; i < previous.size(); i++) {
//            for (int j = 0; j < previous.size(); j++) {
//                if (previous.get(i) != null && previous.get(i).equals(current.get(j)) && !previous.get(i).name.equals(current.get(j).name)) {
//                    in.changed++;
//                }
//            }
//            if (previous.get(i) != null && !current.contains(previous.get(i))) {
//                in.deleted++;
//            }
//            if (current.get(i) != null && !previous.contains(current.get(i))) {
//                in.added++;
//            }
//        }
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user);
        }

        for (User user : current) {
            if (!map.containsValue(user)) {
                in.added++;
            }
        }
        for (Map.Entry<Integer, User> entry : map.entrySet()) {
            for (User user : current) {
                if (entry.getValue().id == user.id && !entry.getValue().name.equals(user.name)) {
                    in.changed++;
                }
            }
            if (!current.contains(entry.getValue())) {
                in.deleted++;
            }
        }
        return in;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {

        int added;
        int changed;

        int deleted;
    }

    public static void main(String[] args) {
        Analize an = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(123, "Denis"));
        previous.add(new Analize.User(124, "Ivan"));
        previous.add(new Analize.User(125, "Max"));
        previous.add(new Analize.User(126, "Alex"));
        previous.add(new Analize.User(127, "Dima"));
        List<Analize.User> current = new ArrayList<>();
        current.add(new Analize.User(123, "DenisNew"));
        current.add(new Analize.User(124, "Ivan"));
        current.add(new Analize.User(127, "DimaNew"));
        current.add(new Analize.User(128, "New1"));
        Analize.Info in = an.diff(previous, current);
        System.out.println(in.added);
        System.out.println(in.deleted);
        System.out.println(in.changed);
    }
}
