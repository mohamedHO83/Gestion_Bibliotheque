package exceptions;

import javax.swing.*;
import java.awt.*;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(){}
    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x, "The specified member does not exist.");
        return null;
    }
}
