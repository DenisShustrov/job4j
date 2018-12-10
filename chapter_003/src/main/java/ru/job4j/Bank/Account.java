package ru.job4j.bank;

import java.util.Objects;

/**
 * Class Account.
 *
 * @author dshustrov
 * @version 1
 * @since 01.12.2018
 */
public class Account {
    /**
     * value количество денег на счете.
     */
    private double value;
    /**
     * requisites реквизиты счета.
     */
    private String requisites;

    /**
     * Конструктор.
     *
     * @param value      количество денег на счете.
     * @param requisites реквизиты счета.
     */
    Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    void setValue(double value) {
        this.value = value;
    }

    double getValue() {
        return value;
    }

    String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    @Override
    public String toString() {
        return "Account{"
                +
                "value=" + value
                +
                ", requisites='"
                + requisites
                + '\''
                +
                '}';
    }
}
