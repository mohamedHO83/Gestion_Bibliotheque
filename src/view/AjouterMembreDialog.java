package view;

import javax.swing.*;
import java.awt.*;

public class AjouterMembreDialog extends JFrame{
    JTextField lastName = new JTextField(10);
    JTextField firstName = new JTextField(10);
    JTextField password = new JTextField(10);
    JTextField age = new JTextField(10);
    JTextField address = new JTextField(10);
    JButton submit = new JButton("Submit");

    public AjouterMembreDialog(BiblioView x) {
        JDialog dialog = new JDialog(x);
        dialog.setTitle("New Member");
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Last Name: "));
        inputPanel.add(lastName);
        inputPanel.add(new JLabel("First Name: "));
        inputPanel.add(firstName);
        inputPanel.add(new JLabel("Password: "));
        inputPanel.add(password);
        inputPanel.add(new JLabel("Age: "));
        inputPanel.add(age);
        inputPanel.add(new JLabel("Address: "));
        inputPanel.add(address);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getAge() {
        return age;
    }

    public JTextField getAddress() {
        return address;
    }

    public JButton getSubmit() {
        return submit;
    }
}
