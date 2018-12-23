package sample;

public enum Equasion {
    ADD("+"), SUBTRACT("-"), MULTIPLY("X"), DIVIDE("/"), NULL("");

    private String symbol;

    Equasion(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
