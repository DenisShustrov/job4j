package ru.job4j.search;

/**
 * Class Person.
 *
 * @author dshustrov
 * @version 1
 * @since 21.11.2018
 */
public class Person {
    /**
     * Имя.
     */
    private String name;
    /**
     * Фамилия.
     */
    private String surname;
    /**
     * Телефон.
     */
    private String phone;
    /**
     * Адрес.
     */
    private String address;

    /**
     * Конструктор.
     *
     * @param name    название.
     * @param surname описание.
     * @param phone   время создания.
     * @param address время создания.
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Метод доступа к имени.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод доступа к фамилии.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Метод доступа к телефону.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Метод доступа к адресу.
     */
    public String getAddress() {
        return address;
    }
}

