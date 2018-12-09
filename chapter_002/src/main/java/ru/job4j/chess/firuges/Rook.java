package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

import java.util.function.BiPredicate;

public abstract class Rook implements Figure {
    public boolean isDiagonal(Cell source, Cell dest) {
        BiPredicate<Integer, Integer> biPredicate = (one, two) -> !(one == two);
        return biPredicate.test(Math.abs(source.x - dest.x), Math.abs(source.y - dest.y));
    }

    public Cell[] rookWay(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        int deltaX = (source.x == dest.x) ? 0 : (source.x > dest.x ? -1 : 1);
        int deltaY = (source.y == dest.y) ? 0 : (source.y > dest.y ? -1 : 1);
        int size = Math.abs((source.x - dest.x) + (source.y - dest.y));
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            int x = source.x + (i + 1) * deltaX;
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.values()[8 * x + y];
        }
        return steps;
    }
}
