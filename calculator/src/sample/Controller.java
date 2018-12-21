package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

import java.math.BigDecimal;


public class Controller {

    @FXML
    private Text output;

    private BigDecimal number1 = null;
    private String operator = "";   //later change to enum
    private boolean start = true;

    private Model model = new Model();


    @FXML
    private void processNumpadNr(KeyEvent key) {
        String value = "";
        switch (key.getCode()) {
            case NUMPAD0:
                value = "0";
                break;
            case NUMPAD1:
                value = "1";
                break;
            case NUMPAD2:
                value = "2";
                break;
            case NUMPAD3:
                value = "3";
                break;
            case NUMPAD4:
                value = "4";
                break;
            case NUMPAD5:
                value = "5";
                break;
            case NUMPAD6:
                value = "6";
                break;
            case NUMPAD7:
                value = "7";
                break;
            case NUMPAD8:
                value = "8";
                break;
            case NUMPAD9:
                value = "9";
                break;
            case COMMA:
            case PERIOD:
            case DECIMAL:
                value = ".";
                break;
            case DELETE:
                output.setText("");
                operator = "";
                value = "";
                number1 = BigDecimal.ZERO;
                break;
            case BACK_SPACE:
                if (!output.getText().isEmpty())
                    output.setText((String) output.getText().subSequence(0, (output.getText().length() - 1)));
                break;
            case ADD:
                if (!operator.isEmpty()) return;
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                operator = "+";
                output.setText("");
                break;
            case SUBTRACT:
                if (!operator.isEmpty()) return;
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                operator = "-";
                output.setText("");
                break;
            case MULTIPLY:
                if (!operator.isEmpty()) return;
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                operator = "X";
                output.setText("");
                break;
            case DIVIDE:
                if (!operator.isEmpty()) return;
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                operator = "/";
                output.setText("");
                break;
            case ENTER:
                if(operator.isEmpty()) return;
                output.setText(String.valueOf(model.calculate(number1, BigDecimal.valueOf(Double.parseDouble(output.getText())), operator)));
                operator="";
                number1=null;

        }

        if (value.equals(".") && output.getText().contains(".")) value = "";
        output.setText(output.getText() + value);

    }

    @FXML
    private void processNumclick(ActionEvent event) {
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
                number1 = BigDecimal.ZERO;
            }
            operator = value;
            number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
            output.setText("");
        } else {
            if (operator.isEmpty())
                return;
            output.setText(String.valueOf(model.calculate(number1, BigDecimal.valueOf(Double.parseDouble(output.getText())), operator)));
            operator = "";
            start = true;
        }
    }


}
