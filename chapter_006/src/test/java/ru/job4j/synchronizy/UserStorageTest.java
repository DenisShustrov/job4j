package ru.job4j.synchronizy;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private UserStorage storage;

    @Before
    public void beforeTest() {
        storage = new UserStorage();
        storage.add(new User(1, 10));
        storage.add(new User(2, 120));
        storage.add(new User(3, 50));
        storage.add(new User(4, 100));
        storage.add(new User(5, 200));
    }

    @Test
    public void whenDeleteUserWithId5ThenSize4() {
        boolean del = storage.delete(new User(5, 200));
        assertThat(del, is(true));
        assertThat(storage.getSizeStorage(), is(4));
    }

    private class ThreadStorage extends Thread {
        private int fromId;
        private int toId;
        private int amount;

        ThreadStorage(int fromId, int toId, int amount) {
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
        }

        @Override
        public void run() {
            storage.transfer(fromId, toId, amount);
        }
    }

    @Test
    public void whenExecute2ThreadThenDoubleTransfer() throws InterruptedException {
        Thread thread1 = new ThreadStorage(2, 1, 20);
        Thread thread2 = new ThreadStorage(5, 1, 100);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(storage.get(2).getAmount(), is(100));
        assertThat(storage.get(1).getAmount(), is(130));
        assertThat(storage.get(5).getAmount(), is(100));
    }


}
