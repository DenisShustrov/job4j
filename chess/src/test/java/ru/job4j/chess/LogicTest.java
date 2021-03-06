package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.white.KnightWhite;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test.
 *
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class LogicTest {
    /**
     * * Test move.
     */
    @Test
    public void whenBishopBlackRightDiagonalThenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.B1));
        boolean result = logic.move(Cell.B1, Cell.H7);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenKingBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new KingBlack(Cell.D1));
        boolean result = logic.move(Cell.D1, Cell.D2);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenKnightBlackMoveThenTrue() {
        Logic logic = new Logic();
        logic.add(new KnightWhite(Cell.B1));
        boolean result = logic.move(Cell.B1, Cell.C3);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenPawnBlackMoveThenTrue() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.A7));
        boolean result = logic.move(Cell.A7, Cell.A6);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenPawnWhiteMoveThenTrue() {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.B6));
        boolean result = logic.move(Cell.B6, Cell.B7);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenQeenBlackMoveDiagonalThenTrue() {
        Logic logic = new Logic();
        logic.add(new QeenBlack(Cell.D1));
        boolean result = logic.move(Cell.D1, Cell.H5);
        assertThat(result, is(true));
    }

    /**
     * * Test move.
     */
    @Test
    public void whenRookBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.A3));
        boolean result = logic.move(Cell.A3, Cell.A8);
        assertThat(result, is(true));
    }

    /**
     * * Test way.
     */
    @Test
    public void whenBishopBlackWayThenCellSteps() {
        BishopBlack bishopBlack = new BishopBlack(Cell.B1);
        Cell[] result = bishopBlack.way(Cell.B1, Cell.D3);
        Cell[] expect = {Cell.C2, Cell.D3};
        assertThat(result, is(expect));
    }

    /**
     * * Test way.
     */
    @Test
    public void whenRookBlackWayThenCellSteps() {
        RookBlack rookBlack = new RookBlack(Cell.H1);
        Cell[] result = rookBlack.way(Cell.H1, Cell.H4);
        Cell[] expect = {Cell.H2, Cell.H3, Cell.H4};
        assertThat(result, is(expect));
    }

    /**
     * * Test OccupiedWayException.
     */
    @Test
    public void testToOccupiedWayException() {
        try {
            Logic logic = new Logic();
            logic.add(new BishopBlack(Cell.B1));
            logic.add(new BishopWhite(Cell.C2));
            logic.move(Cell.B1, Cell.C2);
            fail("Expected OccupiedWayException");
        } catch (OccupiedWayException ex) {
            assertThat(ex.getMessage(), containsString("Ход не возможен, стоит другая фигура"));
        }
    }

    /**
     * * Test ImpossibleMoveException.
     */
    @Test
    public void testImpossibleMoveException() {
        try {
            Logic logic = new Logic();
            logic.add(new BishopBlack(Cell.B1));
            logic.move(Cell.B1, Cell.B2);
            fail("Expected ImpossibleMoveException");
        } catch (ImpossibleMoveException ex) {
            assertThat(ex.getMessage(), containsString("So you can not walk!"));
        }
    }

    /**
     * * Test FigureNotFoundException.
     */
    @Test
    public void testFigureNotFoundException() {
        try {
            Logic logic = new Logic();
            logic.move(Cell.B1, Cell.B2);
            fail("Expected FigureNotFoundException");
        } catch (FigureNotFoundException ex) {
            assertThat(ex.getMessage(), containsString("Фигура не найдена"));
        }
    }
}

