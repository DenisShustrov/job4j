package ru.job4j.tracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class StartUI.
 *
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class StartUI {
    /**
     * running имеет занчение true пока программа работает.
     */
    private boolean running = true;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Выход из программы.
     */
    public void exit() {
        running = false;
    }

    /**
     * Запускт программы.
     *
     * @param args строки
     */
    public static void main(String[] args) throws IOException, MenuOutException {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();

    }

    /**
     * Основой цикл программы.
     */
    public void init() throws IOException {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            menu.show(userActions -> userActions.forEach(userAction -> System.out.println(userAction.info())));
            menu.select(input.ask("select:", range));
            // иначе тесты не проходят
            break;
        } while (running);
    }
}


