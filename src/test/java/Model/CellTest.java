package Model;

import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fmucko
 */
class CellTest {

    Cell cell = new Cell();

    @Test
    void isAliveTest() {
        assertFalse(cell.isAlive());
        assertEquals(Color.WHITE, cell.getRectangle().getFill());
    }

    @Test
    void setAliveTest() {
        cell.setAlive(true);
        assertTrue(cell.isAlive());
        assertEquals(Color.DODGERBLUE, cell.getRectangle().getFill());
        cell.setAlive(false);
        assertFalse(cell.isAlive());
        assertEquals(Color.WHITE, cell.getRectangle().getFill());
    }

    @Test
    void getAliveNeighborsTest() {
        assertEquals(0, cell.getAliveNeighbors());
    }

    @Test
    void addAliveNeighborTest() {
        cell.addAliveNeighbor();
        cell.addAliveNeighbor();
        assertEquals(2, cell.getAliveNeighbors());
        cell.addAliveNeighbor();
        assertEquals(3, cell.getAliveNeighbors());
    }

    @Test
    void resetAliveNeighborsTest() {
        cell.resetAliveNeighbors();
        assertEquals(0, cell.getAliveNeighbors());
    }

    @Test
    void changeState() {
        cell.changeState();
        assertTrue(cell.isAlive());
        assertEquals(Color.DODGERBLUE, cell.getRectangle().getFill());
    }

    @Test
    void killCell() {
        cell.killCell();
        assertFalse(cell.isAlive());
        assertEquals(Color.WHITE, cell.getRectangle().getFill());
    }


}