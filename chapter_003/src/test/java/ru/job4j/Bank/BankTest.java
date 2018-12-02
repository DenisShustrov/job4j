package ru.job4j.Bank;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class BankTest {
    @Test
    public void whenAddUserThenUpdateMap() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addUser(new User("Ivan", "12346"));
        int result = bank.getBase().size();
        assertThat(result, is(2));
    }

    @Test
    public void whendeleteUserThenUpdateMap() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addUser(new User("Ivan", "12346"));
        bank.deleteUser(new User("Denis", "12345"));
        int result = bank.getBase().size();
        assertThat(result, is(1));
    }

    @Test
    public void whenAddAccountToUserThenUpdateMap() {
        double result = 0;
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        for (Map.Entry<User, List<Account>> entry : bank.getBase().entrySet()) {
            result = entry.getValue().get(0).getValue();
        }
        assertThat(result, is(100.0));
    }

    @Test
    public void whenDeleteAccountFromUserThenUpdateMap() {
        int result = 0;
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        bank.deleteAccountFromUser("12345", new Account(100, "4654351"));
        for (Map.Entry<User, List<Account>> entry : bank.getBase().entrySet()) {
            result = entry.getValue().size();
        }
        assertThat(result, is(0));
    }

    @Test
    public void whenGetUserAccountsThenAllAccount() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        bank.addAccountToUser("12345", new Account(150, "4641316"));
        int result = bank.getUserAccounts("12345").size();
        assertThat(result, is(2));
    }

    @Test
    public void whenTransferMoneyThenUpdateAccount() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addUser(new User("Ivan", "12346"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        bank.addAccountToUser("12345", new Account(150, "4641316"));
        bank.addAccountToUser("12346", new Account(90, "1238462"));
        bank.transferMoney("12345", "4641316", "12346", "1238462", 100);
        double result = bank.getUserAccounts("12345").get(1).getValue();
        assertThat(result, is(50.0));
    }
    @Test
    public void whenTransferMoneyThenUpdateAccountNumberTwo() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addUser(new User("Ivan", "12346"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        bank.addAccountToUser("12346", new Account(90, "1238462"));
        boolean result = bank.transferMoney("12345", "4654351", "12346", "1238462", 100);
        assertThat(result, is(true));
    }

    @Test
    public void whenGetAccountThenAccount() {
        Bank bank = new Bank();
        bank.addUser(new User("Denis", "12345"));
        bank.addUser(new User("Ivan", "12346"));
        bank.addAccountToUser("12345", new Account(100, "4654351"));
        double result = bank.getAccount("12345", "4654351").getValue();
        assertThat(result, is(100.0));
    }
}

