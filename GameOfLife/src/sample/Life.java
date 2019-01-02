package sample;

public class Life {

    Cell[][] previousSetup;

    Life() {
        previousSetup = new Cell[Controller.rows][Controller.columns];
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                previousSetup[i][j] = new Cell(i, j);
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

    public Cell[][] getPreviousSetup(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].setAlive(previousSetup[i][j].isAlive());
            }
        }
        return cell;
    }

    static Cell[][] setState(int mx, int my, Cell[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (mx > j * 15 && mx < (j * 15 + 15) && my > i * 15 && my < (i * 15 + 15))
                    cell[i][j].changeState();
            }
        }
        return cell;
    }

    static void countNeighbors(Cell[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].setAliveNeighbors(0);
                try {
                    if (cell[i - 1][j - 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i - 1][j].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i - 1][j + 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i][j - 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i][j + 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i + 1][j - 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i + 1][j].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i + 1][j + 1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
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


    Cell[][] killAll(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].killCell();
            }
        }
        return cell;
    }

    Cell[][] reviveAll(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].reviveCell();
            }
        }
        return cell;
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


