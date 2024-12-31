package exceptions;

import javax.swing.*;
import java.awt.*;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(){}

    public Throwable message(Component x){
        JOptionPane.showMessageDialog(x,"The specifid loan does not exist","Loan Not Found",JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
