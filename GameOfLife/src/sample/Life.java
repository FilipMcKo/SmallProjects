package sample;

public class Life {

    public static void countNeighbors(Cell[][] cell) {

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].setAliveNeighbors(0);
                try {
                    if (cell[i-1][j-1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i-1][j].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i-1][j+1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i][j-1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i][j+1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i+1][j-1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i+1][j].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    if (cell[i+1][j+1].isAlive()) cell[i][j].addAliveNeighbor();
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    public Cell[][] newLife(Cell[][] cell) {
        countNeighbors(cell);
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (!cell[i][j].isAlive() && (cell[i][j].getAliveNeighbors() == 3)) {
                    cell[i][j].changeState();
                }
                else if (cell[i][j].isAlive() && (cell[i][j].getAliveNeighbors() < 2 || cell[i][j].getAliveNeighbors() > 3)) {
                    cell[i][j].changeState();
                }
            }
        }
        return cell;
    }
}


