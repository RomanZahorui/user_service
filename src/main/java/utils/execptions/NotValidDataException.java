package utils.execptions;

public class NotValidDataException extends RuntimeException {

    public NotValidDataException(String msg) {
        super(msg);
    }

    public NotValidDataException(String msg, Throwable e) {
        super(msg, e);
    }
}
