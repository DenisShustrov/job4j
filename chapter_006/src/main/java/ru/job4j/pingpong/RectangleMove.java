package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        int x = 1;
        int y = 1;
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + x);
            this.rect.setY(this.rect.getY() + y);
            if (rect.getX() >= limitX || rect.getX() <= 0) {
                x = -x;
            }
            if (rect.getY() >= limitY || rect.getY() <= 0) {
                y = -y;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
