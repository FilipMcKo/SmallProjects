package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private AnimationTimer timer;
    private Life life = new Life();
    private long lastUpdate = 0;
    private long speedRate = 100000000;
    static int rows = 40;
    static int columns = 60;
    private static int generations = 0;
    private static boolean isGamePlayed = false;
    private static int mx = -100;
    private static int my = -100;
    private static boolean[][] lifeHistory1 = new boolean[rows][columns];
    private static boolean[][] lifeHistory2 = new boolean[rows][columns];
    private static boolean[][] lifeHistory3 = new boolean[rows][columns];

    @FXML
    private GridPane gridPane;
    @FXML
    private Cell[][] cells = new Cell[rows][columns];
    @FXML
    private Cell cello;
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
                lifeHistory1[i][j] = true;
                lifeHistory2[i][j] = false;
                lifeHistory3[i][j] = j % 2 == 0;
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
        if (isGamePlayed){}
        else {
            timer.start();
            isGamePlayed = true;
        }
    }

    public void pauseKey() {
        if (isGamePlayed) {
            isGamePlayed = false;
            timer.stop();
        }
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
        lifeHistory1[0][0] = !lifeHistory1[0][0];
        lifeHistory2[0][1] = !lifeHistory2[0][1];
        lifeHistory3[0][2] = !lifeHistory3[0][2];
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

    private void playGame() {
        nrOfGenerations.setText("Generations: " + (++generations));
        life.newLife(cells);


        if (generations % 3 == 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    lifeHistory1[i][j] = cells[i][j].isAlive();
                }
            }
        } else if (generations % 3 == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    lifeHistory2[i][j] = cells[i][j].isAlive();
                }
            }
        } else if (generations % 3 == 2) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    lifeHistory3[i][j] = cells[i][j].isAlive();
                }
            }
        }

        boolean lifeHistory1And2TheSame = true;
        boolean lifeHistory2And3TheSame = true;
        boolean lifeHistory1And3TheSame = true;
        for (int i = 0; i < rows; i++) {
            if (!Arrays.equals(lifeHistory1[i], lifeHistory2[i])) lifeHistory1And2TheSame = false;
            if (!Arrays.equals(lifeHistory2[i], lifeHistory3[i])) lifeHistory2And3TheSame = false;
            if (!Arrays.equals(lifeHistory1[i], lifeHistory3[i])) lifeHistory1And3TheSame = false;
        }

        if ((lifeHistory1And2TheSame || lifeHistory2And3TheSame) || lifeHistory1And3TheSame) {
            timer.stop();
            isGamePlayed = false;
        }

    }


}
