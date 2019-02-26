package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author fmucko
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlGameOfLife = new FXMLLoader(getClass().getResource("gameOfLifeMain.fxml"));
        Parent root = fxmlGameOfLife.load();
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root, 900, 650));
        Controller controller = fxmlGameOfLife.<Controller>getController();
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}