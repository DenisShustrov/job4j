package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Tracker.
 *
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    //private final Item[] items = new Item[100];
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
    //private int position = 0;

    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        //this.items[this.position++] = item;
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод находит заявку по id.
     *
     * @param id заявки.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод получение списка всех заявок.
     */
    public ArrayList<Item> findAll() {
        //return Arrays.copyOf(this.items, this.position);
        return items;
    }

    /**
     * Метод редактирования заявок.
     *
     * @param id   заявки.
     * @param item заявка для замены.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int count = 0;
        item.setId(id);
//        for (int i = 0; i != this.position; i++) {
//            if (items[i].getId().equals(id)) {
//                items[i] = item;
//                count++;
//            }
//        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                this.items.set(i, item);
                count++;
            }
        }
        if (count != 0) {
            result = true;
        }
        return result;
    }

    /**
     * Метод удаления заявок.
     *
     * @param id заявки.
     */
    public boolean delete(String id) {
        boolean result = false;
//        for (int i = 0; i <= this.position; i++) {
//            if (items[i].getId().equals(id)) {
//                result = true;
//                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
//                position--;
//                break;
//            }
//        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод получает список заявок по ключу.
     *
     * @param key ключ.
     */
    public ArrayList<Item> findByName(String key) {
//        Item[] temp = new Item[position];
//        int count = 0;
//        for (int i = 0; i != this.position; i++) {
//            if (items[i].getName().equals(key)) {
//                temp[count] = items[i];
//                count++;
//            }
//        }
        ArrayList<Item> temp = new ArrayList<>();
        for (Item it : items) {
            if (key.equals(it.getName())) {
                temp.add(it);
            }
        }
        return temp;
    }
}
