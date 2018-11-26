package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

public abstract class Pawn implements Figure {

    public Cell[] pawnWay(Cell source, Cell dest) {
        Cell[] steps;
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[]{dest};
        } else {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        return steps;
    }

    public Cell[] pawnWayWite(Cell source, Cell dest) {
        Cell[] steps;
        if (source.y == dest.y - 1 && source.x == dest.x) {
            steps = new Cell[]{dest};
        } else {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        return steps;
    }
}
