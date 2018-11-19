package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y))) {
            throw new ImpossibleMoveException("So you can not walk!");
        }
        int deltaX = 0;
        int deltaY = 0;
        if (source.x > dest.x) {
            deltaX = -1;
        } else {
            deltaX = 1;
        }
        if (source.y > dest.y) {
            deltaY = -1;
        } else {
            deltaY = 1;
        }
        int size = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            int x = source.x + (i + 1) * deltaX;
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.values()[8 * x + y];
        }
        return steps;

    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
