package exceptions;

import javax.swing.*;
import java.awt.*;

public class FieldNotFilledException extends RuntimeException {
    public FieldNotFilledException(String message) {
    super(message);
  }
    public FieldNotFilledException(){ }
    public Throwable message(Component x) {
      JOptionPane.showMessageDialog(x, "Field " + this.getMessage() + " is not filled properly, please try again");
      return null;
    }
}