package ru.job4j.testing;

import java.util.Random;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(runner::firstThread);
        Thread thread2 = new Thread(runner::secondThread);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10_000; i++) {
            synchronized (account1) {
                synchronized (account2) {
                    Account.transfer(account1, account2, random.nextInt(100));
                }
            }

        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10_000; i++) {
            synchronized (account2) {
                synchronized (account1) {
                    Account.transfer(account2, account1, random.nextInt(100));
                }
            }
        }
    }
}

class Account {
    private int balance = 10_000;

    private void deposit(int amount) {
        balance += amount;
    }

    private void withdraw(int amount) {
        balance -= amount;
    }
    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.deposit(amount);
        acc2.withdraw(amount);
    }
}
