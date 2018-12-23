package sample;

import java.math.BigDecimal;

public class Model {

    public BigDecimal calculate(BigDecimal number1, BigDecimal number2, Equasion operator) {
        switch (operator) {
            case ADD:
                return number1.add(number2);
            case SUBTRACT:
                return number1.subtract(number2);
            case MULTIPLY:
                return number1.multiply(number2);
            case DIVIDE:
                if (number2.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                return number1.divide(number2,7,BigDecimal.ROUND_HALF_UP);
            default:
                System.out.println("Unknown operator - " + operator);
        }
        return BigDecimal.ZERO;
    }
}
