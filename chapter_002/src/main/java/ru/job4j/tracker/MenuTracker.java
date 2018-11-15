package ru.job4j.tracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class MenuTracker.
 *
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class MenuTracker {
    /**
     * input хранит ссылку на объект .
     */
    private Input input;
    /**
     * tracker хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     *  actions хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
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
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new MenuTracker.EditItem());
        this.actions.add(new MenuTracker.DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new ExitProgram());
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
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Class AddItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    static class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return "0. Add new Item.";
        }
    }

    /**
     * Class EditItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return "2. Edit item.";
        }


    }

    /**
     * Class DeleteItem.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return "3. Delete item.";
        }
    }

    /**
     * Class FindItemById.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    private class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return "4. Find item by Id.";
        }
    }

    /**
     * Class FindItemsByName.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    class FindItemsByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) throws IOException {
            System.out.println("------------ Find item by name:: --------------");
            String nameItem = input.ask("Please, provide item name:");
            Item[] items = tracker.findByName(nameItem);
            for (Item item : items) {
                System.out.println(item.getName());
            }
        }

        @Override
        public String info() {
            return "5. Find items by name.";
        }
    }

    /**
     * Class ExitProgram.
     *
     * @author dshustrov
     * @version 1
     * @since 14.11.2018
     */
    class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return "6. Exit Program.";
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
class ShowItems implements UserAction {
    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ All available items: --------------");
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Override
    public String info() {
        return "1. Show all items.";
    }
}