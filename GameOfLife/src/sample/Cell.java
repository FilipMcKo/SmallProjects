package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    public Rectangle rectangle;
    private int width = 15;
    private int height = 15;
    private int row, column;
    private boolean alive;
    private int aliveNeighbors;

    public void setAlive(boolean alive) {
        this.alive = alive;
        if (alive) {
            this.rectangle.setFill(Color.DODGERBLUE);
        } else {
            this.rectangle.setFill(Color.WHITE);
        }
    }

    public int getAliveNeighbors() {
        return aliveNeighbors;
    }

    public void addAliveNeighbor() {
        this.aliveNeighbors++;
    }

    public void setAliveNeighbors(int aliveNeighbors) {
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


    public void changeState() {
        alive = !this.alive;
        if (alive) {
            this.rectangle.setFill(Color.DODGERBLUE);
        } else {
            this.rectangle.setFill(Color.WHITE);
        }
    }

    public void killCell() {
        if (this.alive) this.changeState();
    }

    public void reviveCell() {
        if (!this.alive) this.changeState();
    }

    public boolean isAlive() {
        if (alive) return true;
        return false;
    }



}
