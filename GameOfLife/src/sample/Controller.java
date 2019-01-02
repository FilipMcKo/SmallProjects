package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private AnimationTimer timer;
    private Life life = new Life();
    private long lastUpdate = 0;
    private long speedRate = 100000000;
    public static int rows = 40;
    public static int columns = 60;
    public static int generations = 0;
    private static boolean isGamePlayed = false;
    private static int mx = -100;
    private static int my = -100;
    private static boolean[][] deadOrAlive1 = new boolean[rows][columns];
    private static boolean[][] deadOrAlive2 = new boolean[rows][columns];
    private static boolean[][] deadOrAlive3 = new boolean[rows][columns];
    boolean lastTwoGenerationsTheSame;
    boolean thisAndSecondToLastGenerationTheSame;

    @FXML
    private GridPane gridPane;
    @FXML
    public Cell[][] cells = new Cell[rows][columns];
    private Cell[][] previousSetup = new Cell[rows][columns];
    @FXML
    Cell cello;
    @FXML
    Label nrOfGenerations;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nrOfGenerations.setText("Generations: " + generations);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cello = new Cell(i, j);
                gridPane.add(cello, j, i);
                cells[i][j] = cello;
                deadOrAlive1[i][j] = true;
                deadOrAlive2[i][j] = false;
                if (j % 2 == 0)
                    deadOrAlive3[i][j] = true;
                else
                    deadOrAlive3[i][j] = false;
            }
        }

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= speedRate) {
                    playGame();
                    lastUpdate = now;
                }
            }
        };
    }

    @FXML
    private void changeClickedCell(MouseEvent event) {
        mx = (int) event.getX();
        my = (int) event.getY();
        life.setState(mx, my, cells);
    }

    @FXML
    public void startKey() {
        if (isGamePlayed)
            return;
        else {
            timer.start();
            isGamePlayed = true;
        }
    }

    public void pauseKey() {
        if (isGamePlayed) {
            isGamePlayed = false;
            timer.stop();
        } else
            return;
    }

    @FXML
    public void speedUpKey() {
        speedRate /= 2;
    }

    @FXML
    public void slowDownKey() {
        speedRate *= 2;
    }

    @FXML
    public void resetKey() {
        life.killAll(cells);
        generations = 0;
        nrOfGenerations.setText("Generations: " + (generations));
       /* for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                deadOrAlive1[i][j] = true;
                deadOrAlive2[i][j] = false;
                if (columns % 2 == 0)
                    deadOrAlive3[i][j] = true;
                else
                    deadOrAlive3[i][j] = false;
            }
        }*/
    }

    @FXML
    public void saveSetupKey() {
        life.setPreviousSetup(cells);
    }


    @FXML
    public void loadSetupKey() {
        generations = 0;
        nrOfGenerations.setText("Generations: " + (generations));
        life.getPreviousSetup(cells);
    }

    public void playGame() {
        nrOfGenerations.setText("Generations: " + (++generations));
        life.newLife(cells);


        if (generations % 3 == 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (cells[i][j].isAlive()) deadOrAlive1[i][j] = true;
                    else deadOrAlive1[i][j] = false;
                }
            }
        } else if (generations % 3 == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (cells[i][j].isAlive()) deadOrAlive2[i][j] = true;
                    else deadOrAlive2[i][j] = false;
                }
            }
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (cells[i][j].isAlive()) deadOrAlive3[i][j] = true;
                    else deadOrAlive3[i][j] = false;
                }
            }
        }

        lastTwoGenerationsTheSame = true;
        thisAndSecondToLastGenerationTheSame = true;
        for (int i = 0; i < rows; i++) {
            if (!Arrays.equals(deadOrAlive1[i], deadOrAlive2[i])) lastTwoGenerationsTheSame = false;
            if (!Arrays.equals(deadOrAlive1[i], deadOrAlive3[i])) thisAndSecondToLastGenerationTheSame = false;
        }

        if (lastTwoGenerationsTheSame || thisAndSecondToLastGenerationTheSame) timer.stop();


    }


}
