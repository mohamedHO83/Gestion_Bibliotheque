package view;

import modele.Livre;

import javax.swing.*;
import java.awt.*;

public class BorrowBookDialog extends JFrame {
    JTextField memberId = new JTextField(10);
    JButton submit = new JButton("Submit");

    public BorrowBookDialog(BiblioView x, Livre livre){
        JDialog dialog = new JDialog(x);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Book Title: "));
        inputPanel.add(new JLabel(livre.getTitre()));
        inputPanel.add(new JLabel("Member ID: "));
        inputPanel.add(memberId);

        dialog.setTitle("New Loan");
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getMemberId() {
        return memberId;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
}
