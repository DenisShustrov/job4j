package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Class Tracker.
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод находит заявку по id.
     * @param id заявки.
     */
    public Item findById(String id)  {
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
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }
    /**
     * Метод редактирования заявок.
     * @param id заявки.
     * @param item заявка для замены.
     */
    public void replace(String id, Item item) {
        item.setId(id);
        for (int i = 0; i != this.position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                break;
            }
        }
    }
    /**
     * Метод удаления заявок.
     * @param id заявки.
     */
    public void delete(String id) {
        int count = 0;
        for (int i = 0; i <= this.position; i++) {
            if (items[i].getId().equals(id)) {
                count = i;
                items[i] = null;
                break;
            }
        }
        if (items[0] == null) {
            Item[] result = new Item[items.length];
            System.arraycopy(items, count + 1, result, 0, items.length - count - 1);
            System.arraycopy(result, 0, items, count, result.length);
        } else {
            Item[] result = new Item[items.length - 1];
            System.arraycopy(items, count + 1, result, 0, items.length - count - 1);
            System.arraycopy(result, 0, items, count, result.length);
        }

    }

    /**
     * Метод получает список заявок по ключу.
     * @param key ключ.
     */
    public Item[] findByName(String key) {
        Item[] temp = new Item[position];
        int count = 0;
        for (int i = 0; i != this.position; i++) {
            if (items[i].getName().equals(key)) {
                temp[count] = items[i];
                count++;
            }
        }
        return Arrays.copyOf(temp, count);
    }
}
