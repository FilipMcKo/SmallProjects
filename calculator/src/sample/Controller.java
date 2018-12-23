package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.math.BigDecimal;


public class Controller {

    @FXML
    private Text output;
    @FXML
    private GridPane gridPane;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private StackPane stackPane;
    @FXML
    private HBox hBox;
    @FXML
    private Button ceButton;
    private BigDecimal number1 = null;
    private Equasion operator = Equasion.NULL;
    private boolean start = true;
    private boolean turnedOn = true;
    private Model model = new Model();

    private static void setFontSize(Text output) {
        output.setFont(new Font(21));
        if (output.getText().length() > 34) output.setFont(new Font(14));
        if (output.getText().length() > 75) output.setFont(new Font(11));
    }

    private boolean outputIsTooLong() {
        if (this.output.getText().length() > 127) return true;
        return false;
    }

    @FXML
    private void processNumPad(KeyEvent key) {
        if (turnedOn) {
            output.fontProperty();
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
                    operator = Equasion.NULL;
                    value = "";
                    number1 = BigDecimal.ZERO;
                    break;
                case BACK_SPACE:
                    if (!output.getText().isEmpty())
                        output.setText((String) output.getText().subSequence(0, (output.getText().length() - 1)));
                    break;
                case ADD:
                    if (operator != Equasion.NULL) return;
                    number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                    operator = Equasion.ADD;
                    output.setText("");
                    break;
                case SUBTRACT:
                    if (operator != Equasion.NULL) return;
                    number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                    operator = Equasion.SUBTRACT;
                    output.setText("");
                    break;
                case MULTIPLY:
                    if (operator != Equasion.NULL) return;
                    number1 = BigDecimal.valueOf(Double.parseDouble(output.getText()));
                    operator = Equasion.MULTIPLY;
                    output.setText("");
                    break;
                case DIVIDE:
                    if (operator != Equasion.NULL) return;
                    number1 = new BigDecimal(output.getText());
                    operator = Equasion.DIVIDE;
                    output.setText("");
                    break;
                case ENTER:
                    if (operator == Equasion.NULL) return;
                    output.setText(String.valueOf(model.calculate(number1, new BigDecimal(output.getText()), operator)));
                    operator = Equasion.NULL;
                    number1 = null;
            }

            if (value.equals(".") && output.getText().contains(".")) value = "";
            if (this.outputIsTooLong()) return;
            output.setText(output.getText() + value);
            setFontSize(output);
        }
    }

    @FXML
    private void processNumClick(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }

        String value = ((Button) event.getSource()).getText();
        if (this.outputIsTooLong()) return;
        output.setText(output.getText() + value);
        setFontSize(output);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!value.equals("=")) {
            if (operator != Equasion.NULL)
                return;
            if (value.equals("CE")) {
                operator = Equasion.NULL;
                value = "";
                number1 = BigDecimal.ZERO;
            }
            operator = Equasion.getOperator(value);
            number1 = new BigDecimal(output.getText());
            output.setText("");
        } else {
            if (operator == Equasion.NULL)
                return;
            output.setText(String.valueOf(model.calculate(number1, new BigDecimal(output.getText()), operator)));
            operator = Equasion.NULL;
            start = true;
        }
    }

    @FXML
    private void processOnOffButton(ActionEvent event) {
        if (toggleButton.isSelected()) {
            gridPane.setDisable(false);
            output.setDisable(false);
            stackPane.setDisable(false);
            ceButton.setDisable(false);
            turnedOn = true;
            output.setOpacity(1);

        } else {
            gridPane.setDisable(true);
            output.setDisable(true);
            stackPane.setDisable(true);
            ceButton.setDisable(true);
            turnedOn = false;
            output.setOpacity(0.5);
        }
    }


}
