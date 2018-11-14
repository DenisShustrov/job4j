package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Class Item.
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public class Item {

    /**
     * id заявки.
     */
    private String id;
    /**
     * Название заявки.
     */
    private String name;
    /**
     * Описание заявки.
     */
    private String discription;
    /**
     * Время создание заявки.
     */
    private Long create;
    /**
     * Комментарии к заявке.
     */
    private String[] comments;
    /**
     * Конструктор.
     * @param name название.
     * @param discription описание.
     * @param create время создания.
     */
    public Item(String name, String discription, Long create) {
        this.name = name;
        this.discription = discription;
        this.create = create;
    }
    /**
     * Метод доступа к имени заявки.
     */
    public String getName() {
        return name;
    }
    /**
     * Метод доступа к описанию заявки.
     */
    public String getDiscription() {
        return discription;
    }
    /**
     * Метод доступа ко времени создания заявки.
     */
    public Long getCreate() {
        return create;
    }
    /**
     * Метод доступа к комментариям к заявке.
     */
    public String[] getComments() {
        return comments;
    }
    /**
     * Метод доступа к id заявке.
     */
    public String getId() {
        return id;
    }
    /**
     * Метод задания id заявки.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Метод задания комментария.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Item{"
                +
                "id='" + id + '\''
                +
                ", name='" + name + '\''
                +
                ", discription='" + discription + '\''
                +
                ", create=" + create
                +
                '}';
    }
}
