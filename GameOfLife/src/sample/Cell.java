package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    public int getAliveNeighbors() {
        return aliveNeighbors;
    }

    public void addAliveNeighbor() {
        this.aliveNeighbors++;
    }

    public void setAliveNeighbors(int aliveNeighbors) {
        this.aliveNeighbors = aliveNeighbors;
    }

    public void subtractAliveNeighbot(){
        this.aliveNeighbors--;
    }

    Cell(int row, int column) {
        this.rectangle = new Rectangle(width, height, Color.WHITE);
        this.rectangle.setStroke(Color.LIGHTGRAY);
        this.alive = false;
        this.row = row;
        this.column = column;
        this.aliveNeighbors=0;
        getChildren().add(rectangle);
        setOnMouseClicked(event -> changeState());
    }


    public void changeState() {
        alive = !this.alive;
        if (alive) {
            this.rectangle.setFill(Color.DODGERBLUE);
        } else {
            this.rectangle.setFill(Color.WHITE);
        }
        //System.out.println("state changed");
    }

    public boolean isAlive(){
        if(alive) return true;
        return false;
    }
}
