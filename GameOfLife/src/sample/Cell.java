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
    public static int count = 0;

    Cell(int row, int column) {
        this.rectangle = new Rectangle(width, height, Color.WHITE);
        this.rectangle.setStroke(Color.LIGHTGRAY);
        this.alive = false;
        this.row = row;
        this.column = column;
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
        System.out.println("state changed");
    }
}
