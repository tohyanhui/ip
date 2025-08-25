package momo.exception;

public class MomoException extends Exception {
    public MomoException(String message) {
        super("Nani?!? " + message);
    }
}
