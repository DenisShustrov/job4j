package ru.job4j.Bank;

        import java.util.*;

/**
 * Class Bank.
 *
 * @author dshustrov
 * @version 1
 * @since 01.12.2018
 */
public class Bank {
    /**
     * base база данных банка.
     */
    private Map<User, List<Account>> base = new HashMap<>();

    /**
     * Метод добавления пользователя.
     *
     * @param user пользователь.
     */
    public void addUser(User user) {
        base.put(user, new ArrayList<>());
    }

    /**
     * Метод для доступа к базе.
     */
    public Map<User, List<Account>> getBase() {
        return base;
    }

    /**
     * Метод удаляет пользователя.
     *
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        base.remove(user);
    }

    /**
     * Метод добавляет счёт пользователю.
     *
     * @param passport паспорт.
     * @param account  счет.
     */
    public void addAccountToUser(String passport, Account account) {
        if (!base.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : base.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    entry.getValue().add(account);
                    break;
                }

            }
        }
    }

    /**
     * Метод удаляет один счёт пользователя.
     *
     * @param passport паспорт.
     * @param account  счет.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (!base.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : base.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    entry.getValue().remove(account);
                    break;
                }
            }
        }
    }

    /**
     * Метод получает список счетов для пользователя.
     *
     * @param passport паспорт.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accountList = new ArrayList<>();
        if (!base.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : base.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    accountList = entry.getValue();
                    break;
                }
            }
        }
        return accountList;
    }

    /**
     * Метод возвращает аккаунт пользователя.
     *
     * @param passport  паспорт.
     * @param requisite счет.
     */
    public Account getAccount(String passport, String requisite) {
        Account result = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.getRequisites().equals(requisite)) {
                result = account;
            }
        }
        return result;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт.
     *
     * @param srcPassport  паспорт.
     * @param srcRequisite счет с которого перечисляют.
     * @param destPassport паспорт пользователя которому перечисляют.
     * @param dstRequisite счет на который перечисляют.
     * @param amount       сумма перечисления.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account src = getAccount(srcPassport, srcRequisite);
        Account dest = getAccount(destPassport, dstRequisite);
        if (src != null && src.getValue() >= amount && dest != null) {
            src.setValue(src.getValue() - amount);
            dest.setValue(dest.getValue() + amount);
            result = true;
        }
        return result;
    }
}

