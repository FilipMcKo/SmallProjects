package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.concurrent.TimeUnit;

public class Life {

    Cell[][] previousSetup = new Cell[Controller.rows][Controller.columns];

    public void setPreviousSetup(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                this.previousSetup[i][j] = cell[i][j];
            }
        }
    }

    public Cell[][] getPreviousSetup(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = previousSetup[i][j];
            }
        }
        return cell;
    }

    public static void countNeighbors(Cell[][] cell) {

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

    public Cell[][] newLife(Cell[][] cell) {
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

    public Cell[][] killAll(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].killCell();
            }
        }
        return cell;
    }

    public Cell[][] reviveAll(Cell[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].reviveCell();
            }
        }
        return cell;
    }


}


