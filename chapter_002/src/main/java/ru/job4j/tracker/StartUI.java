package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class StartUI.
 *
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для показа всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявок.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявок.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_ITEM_BY_ID = "4";

    /**
     * Константа меню для поиска заявки по name.
     */
    private static final String FIND_ITEMS_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() throws IOException {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD:
                    this.createItem();
                    break;
                case SHOW:
                    this.showItem();
                    break;
                case EDIT:
                    this.editItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FIND_ITEM_BY_ID:
                    this.findByIdItem();
                    break;
                case FIND_ITEMS_BY_NAME:
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Вы ввели не известную команду");
                    break;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() throws IOException {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        String create = this.input.ask("Введите время время создания заявки :");
        Item item = new Item(name, desc, Long.parseLong(create));
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод отображает все заявки.
     */
    private void showItem() throws IOException {
        System.out.println("------------ Все текущие заявки: --------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }

    /**
     * Метод редактирует заявки.
     */
    private void editItem() throws IOException {
        System.out.println("------------ Редактирование заявки: --------------");
        String id = this.input.ask("Введите id заявки :");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        String create = this.input.ask("Введите время время создания заявки :");
        Item item = new Item(name, desc, Long.parseLong(create));
        this.tracker.replace(id, item);
    }

    /**
     * Метод удаляет заявки.
     */
    private void deleteItem() throws IOException {
        System.out.println("------------ Удаление заявки: --------------");
        String id = this.input.ask("Введите id заявки :");
        this.tracker.delete(id);
    }

    /**
     * Метод находит заявку по id.
     */
    private void findByIdItem() throws IOException {
        System.out.println("------------ Найти заявку по id: --------------");
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        System.out.println("------------ Заявка с id: " + item.getId() + " " + item.getName());
    }

    /**
     * Метод находит заявку по name.
     */
    private void findByNameItem() throws IOException {
        System.out.println("------------ Найти заявки по названию: --------------");
        String nameItem = this.input.ask("Введите название заявки :");
        Item[] items = this.tracker.findByName(nameItem);
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item\n"
                +
                "1. Show all items\n"
                +
                "2. Edit item\n"
                +
                "3. Delete item\n"
                +
                "4. Find item by Id\n"
                +
                "5. Find items by name\n"
                +
                "6. Exit Program\n"
                +
                "Select:");
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}


