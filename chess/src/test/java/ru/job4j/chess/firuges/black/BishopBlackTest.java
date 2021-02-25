package ru.job4j.chess.firuges.black;


import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void testPosition() {
        BishopBlack black = new BishopBlack(Cell.C1);
        assertThat(black.position(), is(Cell.findBy(2, 7)));
    }

    @Test
    public void testCopy() {
        BishopBlack black = new BishopBlack(Cell.D1);
        Figure remove = black.copy(Cell.D2);

        assertThat(black.copy(Cell.D2).position(), is(remove.position()));
    }

    @Test
    public void testWayUpRight() throws ImpossibleMoveException {
        BishopBlack black = new BishopBlack(Cell.C1);

        assertThat(Arrays.toString(black.way(Cell.G5)), is("[D2, E3, F4, G5]"));

    }

    @Test
    public void testWayUpLeft() throws ImpossibleMoveException {
        BishopBlack black = new BishopBlack(Cell.G1);

        assertThat(Arrays.toString(black.way(Cell.A7)), is("[F2, E3, D4, C5, B6, A7]"));

    }

    @Test
    public void testMoveAble() throws ImpossibleMoveException {
        BishopBlack black = new BishopBlack(Cell.C1);

        assertThat(black.isDiagonal(Cell.H6), is(true));
    }

    @Test
    public void testMoveDisAble() throws ImpossibleMoveException {
        BishopBlack black = new BishopBlack(Cell.C1);

        assertThat(black.isDiagonal(Cell.A6), is(false));
    }

    @Test
    public void testException() throws ImpossibleMoveException {
        BishopBlack black = new BishopBlack(Cell.C1);
        assertThat(black.way(Cell.A6), is("Could not move by diagonal from C1 to A6"));
    }

    @Test
    public void testNotMove() throws OccupiedCellException, FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack black = new BishopBlack(Cell.C1);
        logic.add(black);
        PawnBlack pawn = new PawnBlack(Cell.D2);
        logic.add(pawn);

        assertThat(logic.move(Cell.C1, Cell.E3), is(false));
    }

    @Test
    public void testMove() throws OccupiedCellException, FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack black = new BishopBlack(Cell.C1);
        logic.add(black);
        PawnBlack pawn = new PawnBlack(Cell.D4);
        logic.add(pawn);

        assertThat(logic.move(Cell.C1, Cell.F4), is(true));


    }
}

