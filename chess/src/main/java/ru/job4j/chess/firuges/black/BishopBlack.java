package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;


    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }



    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
            int size = Math.abs(dest.getX() - position.getX());
            Cell[] steps = new Cell[size];
            int deltaX = (dest.getX() - position.getX()) / size;
            int deltaY = (dest.getY() - position.getY()) / size;
            for (int index = 0; index < size; index++) {
                int x = position.getX() + deltaX * (index+1);
                int y = position.getY() + deltaY * (index+1);
                steps[index] = Cell.findBy(x, y);
            }
            return steps;
        }


    public boolean isDiagonal(Cell dest) {
       return  (Math.abs(dest.getX() - position.getX()) == Math.abs(position.getY() - dest.getY()));

    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
