package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Controller {

    private Life life = new Life();
    public static int rows = 40;
    public static int columns = 60;
    public static int generations =0;
    private boolean isGamePlayed = false;
    @FXML
    private GridPane gridPane;
    @FXML
    private StackPane gridStackPane;
    @FXML
    public  Cell[][] cells = new Cell[rows][columns];
    @FXML
    Cell cello;
    @FXML
    Text nrOfGenerations;


    @FXML
    public void initialize() {
        //nrOfGenerations.setText(String.valueOf(nrOfGenerations));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cello = new Cell(i, j);
                gridPane.add(cello, j, i);
                cells[i][j]=cello;
            }
        }
        cells[20][29].changeState();
        cells[20][30].changeState();
        cells[20][31].changeState();
        cells[21][29].changeState();
        cells[21][30].changeState();
        cells[21][31].changeState();
        cells[21][32].changeState();
        cells[22][32].changeState();
        cells[22][33].changeState();
        cells[22][34].changeState();
        cells[21][33].changeState();


        cells[2][29].changeState();
        cells[2][30].changeState();
        cells[2][31].changeState();
        cells[31][29].changeState();
        cells[31][30].changeState();
        cells[31][31].changeState();
    }

    @FXML
    public void mouseHandler() {
        cells[10][10].changeState();
        System.out.println("mouseHandler1");


    }


    @FXML
    public void startKey(){
        /*if(isGamePlayed)
            return;
        else{*/
            isGamePlayed=true;
            playGame();
       // }
    }

    public void pauseKey(){
        if(isGamePlayed)
            isGamePlayed = false;
        else
            return;
    }

    public void playGame(){
       /* while(isGamePlayed){*/
            //nrOfGenerations.setText(String.valueOf(++generations));
            life.newLife(cells);
            isGamePlayed=false;
       // }
    }



}
