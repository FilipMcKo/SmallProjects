package sample;

import java.util.logging.Logger;

public class Life {

    Logger logger = Logger.getLogger("JakaNazwa");

    private Cell[][] previousSetup;

    int[][] neighbors = new int[][] {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    Life() {
        previousSetup = new Cell[Controller.rows][Controller.columns];
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                previousSetup[i][j] = new Cell();
            }
        }
    }

    void setPreviousSetup(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                this.previousSetup[i][j].setAlive(cell[i][j].isAlive());
            }
        }
    }

    void getPreviousSetup(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].setAlive(previousSetup[i][j].isAlive());
            }
        }
    }

     void setState(int mx, int my, Cell[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (mx > j * 15 && mx < (j * 15 + 15) && my > i * 15 && my < (i * 15 + 15))
                    cell[i][j].changeState();
            }
        }
     }

    private void countNeighbors(Cell[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].resetAliveNeighbors();
                for (int k = 0; k < neighbors.length ; k++) {
                    try {
                        if (cell[i + neighbors[k][0]][j + neighbors[k][1]].isAlive()) cell[i][j].addAliveNeighbor();
                    } catch (IndexOutOfBoundsException e) {
                        //TODO: zawinac plansze jak wystepuje wyjatek
                    }
                }
            }
        }
    }

    Cell[][] newLife(Cell[][] cell) {
        countNeighbors(cell);

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (!cell[i][j].isAlive() && (cell[i][j].getAliveNeighbors() == 3)) {
                    cell[i][j].changeState();
                } else if (cell[i][j].isAlive() && (cell[i][j].getAliveNeighbors() < 2 || cell[i][j].getAliveNeighbors() > 3)) {
                    cell[i][j].changeState();
                }
            }
        }
        return cell;
    }


    void killAll(Cell[][] cell) {
        for (Cell[] cells : cell) {
            for (int j = 0; j < cell[0].length; j++) {
                cells[j].killCell();
            }
        }
    }


    boolean isTheSame(Cell[][] cell) {
        boolean theSame = true;
        Cell[][] nextLife = newLife(cell);
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (cell[i][j].isAlive() != nextLife[i][j].isAlive()) theSame = false;
            }
        }
        return theSame;
    }
}


