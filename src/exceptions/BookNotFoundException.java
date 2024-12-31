package exceptions;

import javax.swing.*;
import java.awt.*;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(){}
    public Throwable message(Component x) {
        JOptionPane.showMessageDialog(x, "The specified book does not exist.","Book Not Found",JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
