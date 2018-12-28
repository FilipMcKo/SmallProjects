package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.concurrent.TimeUnit;

public class Controller {

    private AnimationTimer timer;
    private Life life = new Life();
    private long lastUpdate = 0;
    private long speedRate = 100000000;
    public static int rows = 40;
    public static int columns = 60;
    public static int generations = 0;
    private static boolean isGamePlayed = false;
    @FXML
    private GridPane gridPane;
    @FXML
    public Cell[][] cells = new Cell[rows][columns];
    private Cell[][] previousSetup;
    @FXML
    Cell cello;
    @FXML
    Label nrOfGenerations;


    @FXML
    public void initialize() {
        nrOfGenerations.setText("Generations: " + generations);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cello = new Cell(i, j);
                gridPane.add(cello, j, i);
                cells[i][j] = cello;
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
        cells[21][34].changeState();
        cells[21][35].changeState();


        cells[2][29].changeState();
        cells[2][30].changeState();
        cells[31][27].changeState();
        cells[2][31].changeState();
        cells[31][28].changeState();
        cells[31][26].changeState();
        cells[31][25].changeState();
        cells[31][29].changeState();
        cells[31][30].changeState();
        cells[31][31].changeState();
        cells[32][31].changeState();
        cells[33][31].changeState();

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
    public void startKey() {

        if (isGamePlayed)
            return;
        else {
            //if (generations == 0) life.setPreviousSetup(cells);
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
    }

    @FXML
    public void saveSetupKey(){
    life.setPreviousSetup(cells);
    }


    @FXML
    public void loadSetupKey(){
        generations = 0;
        nrOfGenerations.setText("Generations: " + (generations));
        cells=life.getPreviousSetup(cells);
    }

    public void playGame() {

        nrOfGenerations.setText("Generations: " + (++generations));
        life.newLife(cells);

    }


}
