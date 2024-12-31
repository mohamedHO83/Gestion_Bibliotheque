package exceptions;

import javax.swing.*;
import java.awt.*;

public class CopiesException extends RuntimeException {
    public CopiesException(String message) {
        super(message);
    }
    public CopiesException(){}

    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x, "No copies available for this book.");
        return null;
    }
}
