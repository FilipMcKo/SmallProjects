package Model;

import Controller.Controller;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fmucko
 */
class LifeTest {
    private static Cell[][] cells;
    private static Life life;

    @BeforeAll
    static void setUp() {
        cells = new Cell[Controller.rows][Controller.columns];
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                cells[i][j] = new Cell();
            }
        }
        cells[0][0].changeState();
        cells[Controller.rows - 1][0].changeState();
        cells[Controller.rows - 1][Controller.columns - 1].changeState();
        cells[0][Controller.columns - 1].changeState();
        life = new Life();

    }

    @Test
    void setPreviousSetupTest() {
        life.setPreviousSetup(cells);
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                assertEquals(cells[i][j].isAlive(), life.getPreviousSetup()[i][j].isAlive());
            }
        }
    }

    @Test
    void restorePreviousSetupTest() {
        cells[0][0].changeState();
        cells[1][0].changeState();
        life.restorePreviousSetup(cells);
        for (int i = 0; i < Controller.rows; i++) {
            for (int j = 0; j < Controller.columns; j++) {
                assertEquals(cells[i][j].isAlive(), life.getPreviousSetup()[i][j].isAlive());
            }
        }
    }

    @Test
    void setStateTest() {
       // life.setState();
    }

    @Test
    void newLifeTest() {
        //TODO
    }

    @Test
    void killAllTest() {
        //TODO
    }

    @Test
    void isTheSameTest() {
        //TODO
    }
}