package sample;

import java.math.BigDecimal;

//the logic happes here
public class Model {

    public BigDecimal calculate(BigDecimal number1, BigDecimal number2, String operator) {
        switch (operator) {
            case "+":
                return number1.add(number2);
            case "-":
                return number1.subtract(number2);
            case "X":
                return number1.multiply(number2);
            case "/":
                if (number2.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                return number1.divide(number2,7,BigDecimal.ROUND_HALF_UP);
            default:
                System.out.println("Unknown operator - " + operator);
        }
        return BigDecimal.ZERO;
    }
}
