package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

public abstract class Knight implements Figure {

    public boolean isDiagonal(Cell source, Cell dest) {
        return (Math.abs(source.x - dest.x) == 1 && Math.abs(source.y - dest.y) == 2
                || Math.abs(source.x - dest.x) == 2 && Math.abs(source.y - dest.y) == 1);
    }

    public Cell[] knightWay(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        return new Cell[]{dest};
    }
}
