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
    private final Characters hero;
    /**
     * flag stop.
     */
    private volatile boolean isStopped = false;
    /**
     * monster one.
     */
    private final Characters monster;

    /**
     * monster two.
     */
    private final Characters monsterTwo;
    /**
     * lockBlocks storage blocks.
     */
    private final ReentrantLock[] lockBlocks;


    /**
     * Constructor Board.
     *
     * @param size        board.
     * @param hero        init hero.
     * @param monster     one.
     * @param monsterTwo  number of monsters.
     * @param countBlocks number of blocks.
     */
    public Board(int size, Characters hero, Characters monster, Characters monsterTwo, int countBlocks) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.hero = hero;
        this.monster = monster;
        this.monsterTwo = monsterTwo;
        this.lockBlocks = new ReentrantLock[countBlocks];
    }

    /**
     * Method return free position.
     */
    public Cell unLockPosition() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (x == hero.getCell().getX() || y == hero.getCell().getY()
                || x == monster.getCell().getX() || y == monster.getCell().getY()
                || x == monsterTwo.getCell().getX() || y == monsterTwo.getCell().getY());
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
     * Method characters movement.
     *
     * @param source     current position.
     * @param dist       next position.
     * @param characters character.
     */
    private void move(Cell source, Cell dist, Characters characters) throws InterruptedException {
        ReentrantLock currentPosit = board[source.getX()][source.getY()];
        ReentrantLock nextPosit = board[dist.getX()][dist.getY()];
        currentPosit.lock();
        boolean strokeCheck = nextPosit.tryLock(500, TimeUnit.MILLISECONDS);
        if (strokeCheck) {
            characters.setCell(dist);
            currentPosit.unlock();
            System.out.println(characters.toString() + dist.getX() + " y " + dist.getY());
            Thread.sleep(1000);
            if (characters.getClass() == this.hero.getClass()) {
                if (nextPosit.hasQueuedThreads()) {
                    Thread.currentThread().interrupt();
                    System.out.println("Game over!");
                }
            }
        }
    }

    /**
     * Method working threads.
     */
    private void doing(Characters character) {
        try {
            Cell dist = moving(character.getCell());
            move(character.getCell(), dist, character);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method method starts the thread.
     */
    public void runThread() throws InterruptedException {

        Thread threadHero = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread threadMonster = new Thread(
                                () -> {
                                    while (!isStopped) {
                                        doing(monster);
                                    }
                                });
                        threadMonster.setDaemon(true);
                        Thread threadMonsterTwo = new Thread(
                                () -> {
                                    while (!isStopped) {
                                        doing(monsterTwo);
                                    }
                                });
                        threadMonsterTwo.setDaemon(true);
                        threadMonster.start();
                        threadMonsterTwo.start();
                        doing(hero);
                    }
                }
        );
        threadHero.start();
        threadHero.join();
    }

    /**
     * Method returns the participants new position.
     *
     * @param cell current position.
     */
    private Cell moving(Cell cell) {
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

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(5, new Hero(new Cell(1, 1)), new Monster(new Cell(3, 3)), new MonsterTwo(new Cell(4, 4)), 1);
        board.initialization();
        board.initializationBlocs();
        board.runThread();
    }
}

