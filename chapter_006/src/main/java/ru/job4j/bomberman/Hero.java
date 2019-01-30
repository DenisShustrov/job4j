package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Hero.
 *
 * @author dshustrov
 * @version 1
 * @since 30.01.2019
 */
public class Hero implements Runnable {
    /**
     * board game.
     */
    private Board board;
    /**
     * starting position.
     */
    private Cell position;
    /**
     * positionLock.
     */
    private ReentrantLock positionLock;
    /**
     * flag stop.
     */
    private volatile boolean isStopped = false;

    /**
     * Constructor Hero.
     *
     * @param x position.
     * @param y position
     * @param board game.
     */
    public Hero(int x, int y, Board board) {
        this.position = new Cell(x, y);
        this.board = board;
        this.positionLock = this.board.getCell(x, y);
        System.out.println(positionLock.getClass().getName());
    }

    @Override
    public void run() {
        this.positionLock.lock();
        while (!isStopped) {
            try {
                Cell newPosition = move(position);
                moveHero(newPosition.getX(), newPosition.getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method method of movement of the hero from the lock to the lock.
     *
     * @param newX new position by x.
     * @param newY new position by y.
     */
    private void moveHero(int newX, int newY) throws InterruptedException {
        ReentrantLock nextCell = this.board.getCell(newX, newY);
        boolean move = nextCell.tryLock(500, TimeUnit.MILLISECONDS);
        if (move) {
            position = new Cell(newX, newY);
            positionLock.unlock();
            positionLock = nextCell;
            System.out.println("Игрок сейчас здесь. x " + position.getX() + " y " + position.getY());
            Thread.sleep(1000);
        }
    }

    /**
     * Method stops the game.
     */
    public void stop() {
        Thread.currentThread().interrupt();
        this.isStopped = true;
    }

    /**
     * Method return new Cell.
     *
     * @param position current.
     */
    private Cell move(Cell position) {
        Cell result;
        while (true) {
            int newX = position.getX() + (-1 + (int) (Math.random() * 3));
            int newY = position.getY() + (-1 + (int) (Math.random() * 3));
            if (newX < board.getSize() && newY < board.getSize() && newX >= 0 && newY >= 0) {
                result = new Cell(newX, newY);
                break;
            }
        }
        return result;
    }

}