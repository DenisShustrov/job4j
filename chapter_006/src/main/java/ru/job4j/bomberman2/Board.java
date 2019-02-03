package ru.job4j.bomberman2;

import java.util.Random;
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
     * monster counts.
     */
    private final Monster[] monsters;
    /**
     * lockBlocks storage blocks.
     */
    private final ReentrantLock[] lockBlocks;


    /**
     * Constructor Board.
     *
     * @param size          board.
     * @param hero          init hero.
     * @param countMonsters number of monsters.
     * @param countBlocks   number of blocks.
     */
    public Board(int size, Hero hero, int countMonsters, int countBlocks) {
        this.board = new ReentrantLock[size][size];
        this.hero = hero;
        this.size = size;
        this.monsters = new Monster[countMonsters];
        this.lockBlocks = new ReentrantLock[countBlocks];
    }

    /**
     * Method return free position.
     */
    public Cell unLockPosition() {
        Random random = new Random();
        int x = 0;
        int y = 0;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (x == hero.getCell().getX() || y == hero.getCell().getY());
        ReentrantLock lock = board[x][y];
        while (!lock.tryLock()) {
            x = random.nextInt(size);
            y = random.nextInt(size);
            lock = board[x][y];
        }
        return new Cell(x, y);
    }

    /**
     * Method block placement.
     */
    private void initializationBlocs() {
        for (int i = 0; i < lockBlocks.length; i++) {
            Cell cell = unLockPosition();
            lockBlocks[i] = board[cell.getX()][cell.getY()];
            lockBlocks[i].lock();
            System.out.println("Block on position x: " + cell.getX() + " y: " + cell.getY());
        }
    }

    /**
     * Method monsters placement.
     */
    private void initializationMonsters() {
        for (int i = 0; i < monsters.length; i++) {
            Cell cell = unLockPosition();
            monsters[i] = new Monster(cell);
            board[cell.getX()][cell.getY()].lock();
        }
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
    private void moveHero(Cell source, Cell dist) throws InterruptedException {
        ReentrantLock heroPosit = board[source.getX()][source.getY()];
        heroPosit.lock();
        ReentrantLock heroNewPosit = board[dist.getX()][dist.getY()];
        boolean tempHero = heroNewPosit.tryLock(500, TimeUnit.MILLISECONDS);
        if (tempHero) {
            hero.setCell(dist);
            heroPosit.unlock();
            System.out.println("The player is here now: x " + dist.getX() + " y " + dist.getY());
            Thread.sleep(1000);
        }
    }

    private void moveMonsters(Cell source, Cell dist) throws InterruptedException {
        int index = 0;
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].getCell().equals(source)) {
                index = i;
                break;
            }
        }
        ReentrantLock monsterNewPosit = board[dist.getX()][dist.getY()];
        boolean tempMonster = monsterNewPosit.tryLock(500, TimeUnit.MILLISECONDS);
        if (tempMonster) {
            monsters[index].setCell(dist);
            board[dist.getX()][dist.getY()].unlock();
            System.out.println("Monster - " + index + " is here now: x " + dist.getX() + " y " + dist.getY());
            Thread.sleep(1000);
        }
    }

    /**
     * Method method starts the thread.
     */
    public void runThread() throws InterruptedException {

        Thread threadHero = new Thread(
                () -> {
                    while (!isStopped) {
                        try {
                            Cell dist = move(hero.getCell());
                            moveHero(hero.getCell(), dist);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        threadHero.start();
        Thread threadMonster = new Thread(
                () -> {
                    while (!isStopped) {
                        try {
                            for (int i = 0; i < monsters.length; i++) {
                                Cell dist = move(monsters[i].getCell());
                                moveMonsters(monsters[i].getCell(), dist);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        threadMonster.start();
    }


    /**
     * Method returns the participants new position.
     *
     * @param cell current position.
     */
    private Cell move(Cell cell) {
        Cell result = null;
        int newX;
        int newY;
        while (result == null) {
            Random random = new Random();
            int direction = random.nextInt(8);
            switch (direction) {
                case 0:
                    newX = cell.getX();
                    newY = cell.getY() + 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 1:
                    newX = cell.getX() + 1;
                    newY = cell.getY() + 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 2:
                    newX = cell.getX() + 1;
                    newY = cell.getY();
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 3:
                    newX = cell.getX() + 1;
                    newY = cell.getY() - 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 4:
                    newX = cell.getX();
                    newY = cell.getY() - 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 5:
                    newX = cell.getX() - 1;
                    newY = cell.getY() - 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 6:
                    newX = cell.getX() - 1;
                    newY = cell.getY();
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                case 7:
                    newX = cell.getX() - 1;
                    newY = cell.getY() + 1;
                    if (newX < size && newY < size && newX >= 0 && newY >= 0) {
                        result = new Cell(newX, newY);
                        break;
                    }
                    break;
                default:
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
        Board board = new Board(15, new Hero(new Cell(1, 1)), 3, 5);
        board.initialization();
        board.initializationBlocs();
        board.initializationMonsters();
        board.runThread();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stop();
    }
}

