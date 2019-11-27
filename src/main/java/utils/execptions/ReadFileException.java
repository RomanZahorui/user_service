package utils.execptions;

import java.io.IOException;

public class ReadFileException extends IOException {
    public ReadFileException(String msg) {
        super(msg);
    }

    public ReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
