package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Board.
 *
 * @author dshustrov
 * @version 1
 * @since 30.01.2019
 */
public class Board {
    /**
     * board game.
     */
    private final ReentrantLock[][] board;
    /**
     * size board.
     */
    private final int size;
    /**
     * Hero.
     */
    private final Hero hero;
    /**
     * flag stop.
     */
    private volatile boolean isStopped = false;

    /**
     * Constructor Board.
     *
     * @param size board.
     * @param hero Hero.
     */
    public Board(int size, Hero hero) {
        this.board = new ReentrantLock[size][size];
        this.hero = hero;
        this.size = size;
    }

    /**
     * Method initialization board.
     */
    private void initialization() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Method player movement.
     *
     * @param source current position.
     * @param dist   next position.
     */
    private void move(Cell source, Cell dist) throws InterruptedException {
        ReentrantLock heroPosit = board[source.getX()][source.getY()];
        heroPosit.lock();
        ReentrantLock posit = board[dist.getX()][dist.getY()];
        boolean temp = posit.tryLock(500, TimeUnit.MILLISECONDS);
        if (temp) {
            hero.setCell(dist);
            heroPosit.unlock();
            System.out.println("Игрок сейчас здесь. x " + dist.getX() + " y " + dist.getY());
            Thread.sleep(1000);
        }
    }

    /**
     * Method method starts the thread.
     */
    public void runThread() throws InterruptedException {
        Thread thread1 = new Thread(
                () -> {
                    while (!isStopped) {
                        try {
                            Cell dist = moveHero(hero.getCell());
                            move(hero.getCell(), dist);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread1.start();
    }

    /**
     * Method returns the hero's new position.
     *
     * @param hero current position.
     */
    private Cell moveHero(Cell hero) {
        Cell result;
        while (true) {
            int newX = hero.getX() + (-1 + (int) (Math.random() * 4));
            int newY = hero.getY() + (-1 + (int) (Math.random() * 4));
            if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                result = new Cell(newX, newY);
                break;
            }
        }
        return result;
    }

    /**
     * Method stops thread.
     */
    public void stop() {
        Thread.currentThread().interrupt();
        this.isStopped = true;

    }

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(10, new Hero(2, 2));
        board.initialization();
        board.runThread();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stop();
    }
}
