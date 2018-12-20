package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.function.Predicate;

/**
 * //TODO add comments.
 *'
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            for (Cell st : steps) {
                if (findBy(st) != -1) {
                    throw new OccupiedWayException("Ход не возможен, стоит другая фигура");
                }
            }

            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        } else {
            throw new FigureNotFoundException("Фигура не найдена");
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        Predicate<Figure> predicate = t -> t != null && t.position().equals(cell);
        for (int index = 0; index != this.figures.length; index++) {
            if (predicate.test(this.figures[index])) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
