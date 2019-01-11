package sample;

import java.math.BigDecimal;

public class Model {





    public BigDecimal calculate(BigDecimal nr1, BigDecimal nr2, Equasion op) {
        switch (op) {
            case ADD:
                return nr1.add(nr2);
            case SUBTRACT:
                return nr1.subtract(nr2);
            case MULTIPLY:
                return nr1.multiply(nr2);
            case DIVIDE:
                if (nr2.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                return nr1.divide(nr2,7,BigDecimal.ROUND_HALF_UP);
            default:
                System.out.println("Unknown operator - " + op);
        }
        return BigDecimal.ZERO;
    }
}
