package OnlineShop;

public class NoSuchItemException extends RuntimeException {
    public NoSuchItemException() {
    }

    public NoSuchItemException(String message) {
        super(message);
    }
}
