package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

//        boolean result = false;
//        int horizontX = 0;
//        int diagOneX = 0;
//        int diagTwoX = 0;
//        int verticalX = 0;
//        for (int i = 0; i < table[i].length; i++) {
//            for (int j = 0; j < table[i].length; j++) {
//                if (!table[i][j].hasMarkO()) {
//                    horizontX++;
//                }
//                if (!table[j][i].hasMarkO()) {
//                    verticalX++;
//                }
//                if (!table[i][0 + i].hasMarkO()) {
//                    diagOneX++;
//                }
//                if (!table[i][table[i].length - 1 - i].hasMarkO()) {
//                    diagTwoX++;
//                }
//            }
//            if (horizontX == table[i].length) {
//                result = true;
//                break;
//            } else {
//                horizontX = 0;
//            }
//            if (verticalX == table[i].length) {
//                result = true;
//                break;
//            } else {
//                verticalX = 0;
//            }
//            if (diagOneX == table[i].length) {
//                result = true;
//                break;
//            }
//            if (diagTwoX == table[i].length) {
//                result = true;
//                break;
//            }
//        }
//        return result;

    public boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1)
                || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0);
    }

    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1)
                || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0);
//        boolean result = false;
//        int horizontO = 0;
//        int diagOneO = 0;
//        int diagTwoO = 0;
//        int verticalO = 0;
//        for (int i = 0; i < table[i].length; i++) {
//            for (int j = 0; j < table[i].length; j++) {
//                if (!table[i][j].hasMarkO()) {
//                    horizontO++;
//                }
//                if (!table[j][i].hasMarkO()) {
//                    verticalO++;
//                }
//                if (!table[i][0 + i].hasMarkO()) {
//                    diagOneO++;
//                }
//                if (!table[i][table[i].length - 1 - i].hasMarkO()) {
//                    diagTwoO++;
//                }
//            }
//            if (horizontO == table[i].length) {
//                result = true;
//                break;
//            } else {
//                horizontO = 0;
//            }
//            if (verticalO == table[i].length) {
//                result = true;
//                break;
//            } else {
//                verticalO = 0;
//            }
//            if (diagOneO == table[i].length) {
//                result = true;
//                break;
//            }
//            if (diagTwoO == table[i].length) {
//                result = true;
//                break;
//            }
//        }
//        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (!table[i][j].hasMarkX() & !table[i][j].hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
