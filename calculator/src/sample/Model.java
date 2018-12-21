package sample;

//the logic happes here
public class Model {

    public double calculate(double number1, double number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "X":
                return number1 * number2;
            case "/":
                if (number2 == 0) return 0;
                return number1 / number2;
            default:
                System.out.println("Unknown operator - " + operator);
        }
        return 0;
    }
}
