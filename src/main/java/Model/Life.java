package Model;

import Controller.Controller;

/**
 * @author fmucko
 */

public class Life {

    private Cell[][] previousSetup;

    private int[][] neighbors = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    public Life() {
        previousSetup = new Cell[Controller.rows][Controller.columns];
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                previousSetup[i][j] = new Cell();
            }
        }
    }

    public void setPreviousSetup(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                this.previousSetup[i][j].setAlive(cells[i][j].isAlive());
            }
        }
    }

    public void restorePreviousSetup(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setAlive(previousSetup[i][j].isAlive());
            }
        }
    }

    public Cell[][] getPreviousSetup() {
        return previousSetup;
    }

    public void setState(int mx, int my, Cell[][] cells) {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (mx > j * 15 && mx < (j * 15 + 15) && my > i * 15 && my < (i * 15 + 15)) //TODO: replace magic numbers with variables
                    cells[i][j].changeState();
            }
        }
    }

    private void countNeighbors(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].resetAliveNeighbors();
                for (int[] neighbor : neighbors) {
                    try {
                        if (cells[i + neighbor[0]][j + neighbor[1]].isAlive()) cells[i][j].addAliveNeighbor();
                    } catch (IndexOutOfBoundsException e) {
                        int neighborRow = i + neighbor[0];
                        int neighborColumn = j + neighbor[1];

                        if (neighborRow < 0 || neighborRow == cells.length) {
                            neighborRow = i - neighbor[0] * (cells.length - 1);
                        }
                        if (neighborColumn < 0 || neighborColumn == cells[0].length) {
                            neighborColumn = j - neighbor[1] * (cells[0].length - 1);
                        }

                        if (cells[neighborRow][neighborColumn].isAlive()) {
                            cells[i][j].addAliveNeighbor();
                        }
                    }
                }
            }
        }
    }

    public Cell[][] newLife(Cell[][] cells) {
        countNeighbors(cells);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (!cells[i][j].isAlive() && (cells[i][j].getAliveNeighbors() == 3)) {
                    cells[i][j].changeState();
                } else if (cells[i][j].isAlive() && (cells[i][j].getAliveNeighbors() < 2 || cells[i][j].getAliveNeighbors() > 3)) {
                    cells[i][j].changeState();
                }
            }
        }
        return cells;
    }


    public void killAll(Cell[][] cells) {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                cell[j].killCell();
            }
        }
    }


    public boolean isTheSame(Cell[][] cells) {
        boolean theSame = true;
        Cell[][] nextLife = newLife(cells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isAlive() != nextLife[i][j].isAlive()) theSame = false;
            }
        }
        return theSame;
    }
}



