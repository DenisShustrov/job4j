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
                    exit = true;
                    break;
                case SHOW:
                    this.showItem();
                    exit = true;
                    break;
                case EDIT:
                    this.editItem();
                    exit = true;
                    break;
                case DELETE:
                    this.deleteItem();
                    exit = true;
                    break;
                case FIND_ITEM_BY_ID:
                    this.findByIdItem();
                    exit = true;
                    break;
                case FIND_ITEMS_BY_NAME:
                    findByNameItem();
                    exit = true;
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
        System.lineSeparator();
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
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
        if (this.tracker.replace(id, item)) {
            System.out.println("Заявки с id: " +  id + " отредактирована!");
        } else {
            System.out.println("Заявки с таким id в базе нет");
        }
    }

    /**
     * Метод удаляет заявки.
     */
    private void deleteItem() throws IOException {
        System.out.println("------------ Удаление заявки: --------------");
        String id = this.input.ask("Введите id заявки :");
        this.tracker.delete(id);
        if (this.tracker.delete(id)) {
            System.out.println("Заявки с id: " +  id + " удалена!");
        } else {
            System.out.println("Заявки с таким id в базе нет");
        }
    }

    /**
     * Метод находит заявку по id.
     */
    private void findByIdItem() throws IOException {
        System.out.println("------------ Найти заявку по id: --------------");
        System.lineSeparator();
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("------------ Заявка с id: " + item.getId() + " " + item.getName());
            System.lineSeparator();
        } else {
            System.out.println("Заявки с таким id в базе нет");
            System.lineSeparator();
        }
    }

    /**
     * Метод находит заявку по name.
     */
    private void findByNameItem() throws IOException {
        System.out.println("------------ Найти заявки по названию: --------------");
        System.lineSeparator();
        String nameItem = this.input.ask("Введите название заявки :");
        Item[] items = this.tracker.findByName(nameItem);
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }

    private void showMenu() {
        StringBuilder str = new StringBuilder();
        str.append("Меню.");
        str.append(System.lineSeparator());
        str.append("0. Add new Item");
        str.append(System.lineSeparator());
        str.append("1. Show all items");
        str.append(System.lineSeparator());
        str.append("2. Edit item");
        str.append(System.lineSeparator());
        str.append("3. Delete item");
        str.append(System.lineSeparator());
        str.append("4. Find item by Id");
        str.append(System.lineSeparator());
        str.append("5. Find items by name");
        str.append(System.lineSeparator());
        str.append("6. Exit Program");
        str.append(System.lineSeparator());
        str.append("Select:");
        str.append(System.lineSeparator());
        System.out.println(str.toString());
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


