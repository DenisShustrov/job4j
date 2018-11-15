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
     * Запускт программы.
     *
     * @param args строки
     */
    public static void main(String[] args) throws IOException {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

    /**
     * Основой цикл программы.
     */
    public void init() throws IOException {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            menu.select(Integer.parseInt(input.ask("select:")));
            break;
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }
}


