package ru.job4j.tracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class MenuTracker.
 *
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class MenuTracker {
    /**
     * startUI ссылка на StartUI.
     */
    private StartUI startUI;
    /**
     * input хранит ссылку на объект .
     */
    private Input input;
    /**
     * tracker хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * actions хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     * @param startUI объект типа StartUI
     */
    public MenuTracker(Input input, Tracker tracker, StartUI startUI) {
        this.input = input;
        this.tracker = tracker;
        this.startUI = startUI;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new Item."));
        this.actions.add(new ShowItems(1, "Show all items."));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item."));
        this.actions.add(new FindItemById(4, "Find item by Id."));
        this.actions.add(new FindItemsByName(5, "Find items by name."));
        this.actions.add(new ExitProgram(6, "Exit Program.", this.startUI));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) throws IOException {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
//    public void show() {
//        for (UserAction action : this.actions) {
//            if (action != null) {
//                System.out.println(action.info());
//            }
//        }
//    }
    public void show(Consumer<List<UserAction>> com) {
        com.accept(actions);
    }

    /**
     * Class AddItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    public static class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            String create = input.ask("Please, provide item create:");
            Item item = new Item(name, desc, Long.parseLong(create));
            tracker.add(item);
            System.out.println("------------ New Item with Id : " + item.getId());
            System.out.println("------------ New Item with Name : " + item.getName());
            System.out.println("------------ New Item with Description : " + item.getDiscription());
        }
    }

    /**
     * Class EditItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Editing item: --------------");
            String id = input.ask("Please, provide id item:");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            String create = input.ask("Please, provide item create:");
            Item item = new Item(name, desc, Long.parseLong(create));
            if (tracker.replace(id, item)) {
                System.out.println("Item with id: " + id + " edited!");
            } else {
                System.out.println("There are no item with such id in the database");
            }
        }
    }

    /**
     * Class DeleteItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Deletion item: --------------");
            String id = input.ask("Please, provide id item:");
            if (tracker.delete(id)) {
                System.out.println("Item with id: " + id + " deleted!");
            } else {
                System.out.println("There are no item with such id in the database");
            }
        }
    }

    /**
     * Class FindItemById.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Find item by id: --------------");
            String id = input.ask("Please, provide id item:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("------------ Item with id: " + item.getId() + " " + item.getName());
            } else {
                System.out.println("There are no item with such id in the database");
            }
        }
    }

    /**
     * Class FindItemsByName.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Find item by name:: --------------");
            String nameItem = input.ask("Please, provide item name:");
            ArrayList<Item> items = tracker.findByName(nameItem);
            for (Item item : items) {
                System.out.println(item.getName());
            }
        }
    }

    /**
     * Class ShowItems.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All available items: --------------");
            //Item[] items = tracker.findAll();
            ArrayList<Item> items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item);
            }
        }

    }
}

/**
 * Class ExitProgram.
 *
 * @author dshustrov
 * @version 1
 * @since 14.11.2018
 */
class ExitProgram extends BaseAction {

    StartUI startUI;

    public ExitProgram(int key, String name, StartUI startUI) {
        super(key, name);
        this.startUI = startUI;
    }

    @Override
    public void execute(Input input, Tracker tracker) throws IOException {
        String exit = input.ask("Exit?(y): ");
        //StartUI start = new StartUI(input, tracker);
        if ("y".equals(exit)) {
            startUI.exit();
        }
    }
}