package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;


public class Controller {

    @FXML
    private Text output;

    private double number1 = 0;
    private String operator = "";   //later change to enum
    private boolean start = true;

    private Model model = new Model();


    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!"=".equals(value)) {
            if (!operator.isEmpty())
                return;
            if ("CE".equals(value)) {
                operator = "";
                value = "";
                //output.setText("");
                number1 = 0;
            }
            operator = value;
            number1 = Double.parseDouble(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty())
                return;
            output.setText(String.valueOf(model.calculate(number1, Double.parseDouble(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }


}
