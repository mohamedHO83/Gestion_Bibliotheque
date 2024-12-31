package exceptions;

import javax.swing.*;
import java.awt.*;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){}
    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x, "The specified member does not exist.","Member Not Found",JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
