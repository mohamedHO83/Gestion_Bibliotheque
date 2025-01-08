package exceptions;

import view.ModifierMembreDialog;

import javax.swing.*;

public class FieldNotFilledException extends RuntimeException {
    public FieldNotFilledException(String message) {
    super(message);
  }

    public Throwable message(JFrame x) {
      JOptionPane.showMessageDialog(x, "Field " + this.getMessage() + " is not filled properly, please try again","Field error",JOptionPane.ERROR_MESSAGE);
      return null;
    }
}