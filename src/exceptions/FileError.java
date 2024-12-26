package exceptions;

import java.io.IOException;

public class FileError extends IOException {
    public FileError(String message) {
        super(message);
    }
}
