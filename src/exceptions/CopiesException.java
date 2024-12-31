package exceptions;

import javax.swing.*;
import java.awt.*;

public class CopiesException extends RuntimeException {
    public CopiesException(){}

    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x, "No copies available for this book.","Unavailable Book",JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
