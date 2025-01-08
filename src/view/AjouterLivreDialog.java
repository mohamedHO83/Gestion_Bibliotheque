package view;

import javax.swing.*;
import java.awt.*;

public class AjouterLivreDialog extends JFrame {
    JTextField titre = new JTextField(10);
    JTextField autheur = new JTextField(10);
    JTextField annee = new JTextField(10);
    JTextField genre = new JTextField(10);
    JTextField nbCopies = new JTextField(10);
    JButton submit = new JButton("Submit");

    public AjouterLivreDialog(BiblioView x) {
        JDialog dialog = new JDialog(x);
        dialog.setTitle("New Book");
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Title: "));
        inputPanel.add(titre);
        inputPanel.add(new JLabel("Author: "));
        inputPanel.add(autheur);
        inputPanel.add(new JLabel("Year of Publication: "));
        inputPanel.add(annee);
        inputPanel.add(new JLabel("Genre: "));
        inputPanel.add(genre);
        inputPanel.add(new JLabel("Number of Copies: "));
        inputPanel.add(nbCopies);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);

    }

    public JTextField getTitre() {
        return titre;
    }

    public JTextField getAutheur() {
        return autheur;
    }

    public JTextField getAnnee() {
        return annee;
    }

    public JTextField getGenre() {
        return genre;
    }

    public JTextField getNbCopies() {
        return nbCopies;
    }

    public JButton getSubmit() {
        return submit;
    }
}