package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

import java.util.function.BiPredicate;

public abstract class King implements Figure {

    public boolean isDiagonal(Cell source, Cell dest) {
        BiPredicate<Integer, Integer> biPredicate = (one, two) -> one == 1 || two == 1;
        return biPredicate.test(Math.abs(source.x - dest.x), Math.abs(source.y - dest.y));
    }

    public Cell[] kingWay(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        return new Cell[]{dest};
    }
}
