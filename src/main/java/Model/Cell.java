package Model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author fmucko
 */

public class Cell extends StackPane {
    private Rectangle rectangle;
    private int width = 15;
    private int height = 15;
    private boolean alive;
    private int aliveNeighbors;

    void setAlive(boolean alive) {
        this.alive = alive;
        if (alive) {
            this.rectangle.setFill(Color.DODGERBLUE);
        } else {
            this.rectangle.setFill(Color.WHITE);
        }
    }

    int getAliveNeighbors() {
        return aliveNeighbors;
    }

    void addAliveNeighbor() {
        this.aliveNeighbors++;
    }

    void resetAliveNeighbors() {
        this.aliveNeighbors = 0;    }

    public Cell() {
        this.rectangle = new Rectangle(width, height, Color.WHITE);
        this.rectangle.setStroke(Color.LIGHTGRAY);
        this.rectangle.toFront();
        this.alive = false;
        this.aliveNeighbors = 0;
        getChildren().add(rectangle);
    }


    void changeState() {
        alive = !this.alive;
        if (alive) {
            this.rectangle.setFill(Color.DODGERBLUE);
        } else {
            this.rectangle.setFill(Color.WHITE);
        }
    }

    void killCell() {
        if (this.alive) this.changeState();
    }

    public boolean isAlive() {
        return alive;
    }



}
