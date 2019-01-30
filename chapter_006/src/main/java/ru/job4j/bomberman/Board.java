package ru.job4j.bomberman;

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
     * Constructor Board.
     *
     * @param size board.
     */
    public Board(int size) {
        this.board = new ReentrantLock[size][size];
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
     * Method returns size board.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method returns position lock cell.
     */
    public ReentrantLock getCell(int x, int y) {
        return this.board[x][y];
    }

    public static void main(String[] args) {
        Board board = new Board(10);
        board.initialization();
        Hero hero = new Hero(2, 2, board);
        Thread tread = new Thread(hero);
        tread.start();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hero.stop();
    }
}
