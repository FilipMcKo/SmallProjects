package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public  int rows = 40;
    public  int columns = 60;
    @FXML
    private GridPane gridPane;
    @FXML
    private StackPane gridStackPane;
    @FXML
    private StackPane stack;
    @FXML
    public  Cell[][] cells = new Cell[rows][columns];
    @FXML
    Cell cello;


    @FXML
    public void initialize() {
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cello = new Cell(i, j);
                gridPane.add(cello, j, i);
                cells[i][j]=cello;
            }
        }
    }

    @FXML
    public void mouseHandler() {
        cells[10][10].changeState();
    }


}
