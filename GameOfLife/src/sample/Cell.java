package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

class Cell extends StackPane {
    private Rectangle rectangle;
    private int width = 15;
    private int height = 15;
    private int row, column;
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

    void setAliveNeighbors(int aliveNeighbors) {
        this.aliveNeighbors = aliveNeighbors;
    }

    Cell(int row, int column) {
        this.rectangle = new Rectangle(width, height, Color.WHITE);
        this.rectangle.setStroke(Color.LIGHTGRAY);
        this.rectangle.toFront();
        this.alive = false;
        this.row = row;
        this.column = column;
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

    void reviveCell() {
        if (!this.alive) this.changeState();
    }

    boolean isAlive() {
        return alive;
    }



}
