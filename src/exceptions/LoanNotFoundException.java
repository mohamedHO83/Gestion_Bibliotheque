package exceptions;

import javax.swing.*;
import java.awt.*;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
    public LoanNotFoundException(){}

    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x,"The specifid loan does not exist");
        return null;
    }
}
