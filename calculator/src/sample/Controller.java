package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.math.BigDecimal;


public class Controller {

    @FXML
    private Text output;
    @FXML
    private Text operatorBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button ceButton;
    @FXML
    private Rectangle operatorRec;
    
    private BigDecimal number1 = null;
    private BigDecimal number2 = null;
    private Equasion operator = Equasion.NULL;
    private boolean turnedOn = true;
    private boolean beginNewNumber = false;
    private String value = "";
    private String previousValue = "";
    private Model model = new Model();

    private static void setFontSize(Text output) {
        output.setFont(new Font(21));
        if (output.getText().length() > 34) output.setFont(new Font(14));
        if (output.getText().length() > 75) output.setFont(new Font(11));
    }

    private boolean outputIsTooLong() {
        return this.output.getText().length() > 127;
    }

    private boolean numberWasPressed(KeyCode key) {
        return (key == KeyCode.NUMPAD0 || key == KeyCode.NUMPAD1
                || key == KeyCode.NUMPAD2 || key == KeyCode.NUMPAD3
                || key == KeyCode.NUMPAD4 || key == KeyCode.NUMPAD5
                || key == KeyCode.NUMPAD6 || key == KeyCode.NUMPAD7
                || key == KeyCode.NUMPAD8 || key == KeyCode.NUMPAD9);
    }

    private void setOperatorSettings(Equasion op) {
        operator = op;
        operatorBox.setText(op.symbol);
        value = "op";
    }

    private void deleteData() {
        output.setText("");
        operatorBox.setText("");
        operator = Equasion.NULL;
        value = "";
        number1 = null;
        number2 = null;
    }

    private void backSpace() {
        output.setText((String) output.getText().subSequence(0, (output.getText().length() - 1)));
    }

    private void calculate() {
        number2 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
        output.setText(String.valueOf(model.calculate(number1, number2, operator)));
        number2 = null;
    }

    private void updateOutput() {
        output.setText(output.getText() + (value.equals("op") ? "" : value));
        setFontSize(output);
    }

    private void updateValue() {
        previousValue = String.valueOf(value);
        value = "";
    }

    private void respondToUserAction(KeyCode key) {
        if (beginNewNumber && numberWasPressed(key)) {
            output.setText("");
            beginNewNumber = false;
        }
        switch (key) {
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
                deleteData();
                break;
            case BACK_SPACE:
                if (!output.getText().isEmpty())
                    backSpace();
                break;
            case ADD:
                if(output.getText().equals("")) break;
                if (previousValue.equals("op")) {
                    setOperatorSettings(Equasion.ADD);
                    break;
                }
                if (operator != Equasion.NULL && number1 != null && !output.getText().equals("")) {
                    calculate();
                }
                setOperatorSettings(Equasion.ADD);
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                beginNewNumber = true;
                break;
            case SUBTRACT:
                if(output.getText().equals("")) break;
                if (previousValue.equals("op")) {
                    setOperatorSettings(Equasion.SUBTRACT);
                    break;
                }
                if (operator != Equasion.NULL && number1 != null && !output.getText().equals("")) {
                    calculate();
                }
                setOperatorSettings(Equasion.SUBTRACT);
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                beginNewNumber = true;
                break;
            case MULTIPLY:
                if(output.getText().equals("")) break;
                if (previousValue.equals("op")) {
                    setOperatorSettings(Equasion.MULTIPLY);
                    break;
                }
                if (operator != Equasion.NULL && number1 != null && !output.getText().equals("")) {
                    calculate();
                }
                setOperatorSettings(Equasion.MULTIPLY);
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                beginNewNumber = true;
                break;
            case DIVIDE:
                if(output.getText().equals("")) break;
                if (previousValue.equals("op")) {
                    setOperatorSettings(Equasion.DIVIDE);
                    break;
                }
                if (operator != Equasion.NULL && number1 != null && !output.getText().equals("")) {
                    calculate();
                }
                setOperatorSettings(Equasion.DIVIDE);
                number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                beginNewNumber = true;

                break;
            case ENTER:
                if (number1 != null && operator != Equasion.NULL && !output.getText().equals("")) {
                    calculate();
                    operatorBox.setText("");
                    operator = Equasion.NULL;
                    beginNewNumber = true;

                }
        }
        if (value.equals(".") && output.getText().contains(".")) value = "";
        if (this.outputIsTooLong()) return;
        updateOutput();
        updateValue();

    }

    @FXML
    private void processNumPad(KeyEvent key) {
        if (turnedOn) {
            respondToUserAction(key.getCode());
        }
    }

    @FXML
    private void processNumClick(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        KeyCode keyCode = KeyCode.END;
        switch(value){
            case "+":
                keyCode = KeyCode.ADD;
                break;
            case "-":
                keyCode = KeyCode.SUBTRACT;
                break;
            case "/":
                keyCode = KeyCode.DIVIDE;
                break;
            case "X":
                keyCode = KeyCode.MULTIPLY;
                break;
            case "=":
                keyCode = KeyCode.ENTER;
                break;
            case "CE":
                keyCode = KeyCode.DELETE;
                break;
            case "1":
                keyCode = KeyCode.NUMPAD1;
                break;
            case "2":
                keyCode = KeyCode.NUMPAD2;
                break;
            case "3":
                keyCode = KeyCode.NUMPAD3;
                break;
            case "4":
                keyCode = KeyCode.NUMPAD4;
                break;
            case "5":
                keyCode = KeyCode.NUMPAD5;
                break;
            case "6":
                keyCode = KeyCode.NUMPAD6;
                break;
            case "7":
                keyCode = KeyCode.NUMPAD7;
                break;
            case "8":
                keyCode = KeyCode.NUMPAD8;
                break;
            case "9":
                keyCode = KeyCode.NUMPAD9;
                break;
            case "0":
                keyCode = KeyCode.NUMPAD0;
                break;
        }
        respondToUserAction(keyCode);
    }


    @FXML
    private void processOnOffButton() {
        if (toggleButton.isSelected()) {
            gridPane.setDisable(false);
            output.setDisable(false);
            stackPane.setDisable(false);
            ceButton.setDisable(false);
            turnedOn = true;
            output.setOpacity(1);
            operatorRec.setOpacity(1);
            operatorBox.setOpacity(1);

        } else {
            gridPane.setDisable(true);
            output.setDisable(true);
            stackPane.setDisable(true);
            ceButton.setDisable(true);
            turnedOn = false;
            output.setOpacity(0.5);
            operatorRec.setOpacity(0.5);
            operatorBox.setOpacity(0.5);
        }
    }


}
