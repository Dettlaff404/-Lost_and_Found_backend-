package lk.ijse.cmjd108.LostandFoundSys_2025.exception;

public class RequestNotFoundException extends RuntimeException{

    public RequestNotFoundException() {
        super();
    }

    public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
