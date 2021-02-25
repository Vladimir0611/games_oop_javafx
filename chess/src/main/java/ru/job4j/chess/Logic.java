package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public boolean move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        boolean rsl = false;
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        if(free(steps)) {
            figures[index] = figures[index].copy(dest);
            rsl = true;
        } else {
            figures[index] = figures[index].copy(source);
        }
        return rsl;
    }

    private boolean free(Cell[] steps) throws OccupiedCellException {
        boolean rsl = true;
        for (int i = 0; i < steps.length; i++) {
            Cell c = steps[i];
            for ( int in = 0; in < index; in++) {
                if (c.equals(figures[in].position())) {
                    rsl = false;
                }

            }
        }
        return rsl;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException();
    }
}
