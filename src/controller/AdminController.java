package controller;
import module.*;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.sql.Date;

/**
 * AdminController is responsible for managing the administrative actions
 * related to borrowing (Emprunt) operations. It provides functionality to
 * modify loans and synchronize the UI with the application's data model.
 */
public class AdminController extends JFrame   {
    /**
     * Displays a dialog for adding a new book to the system. Prompts the user
     * for book details such as title, author, year of publication, genre, and
     * number of copies.
     *
     * @param x The BiblioView instance for updating the UI.
     */
    public static void ajouterLivre(BiblioView x){
        Livre nouveauLivre = new Livre();
        JTextField titre = new JTextField(10);
        JTextField autheur = new JTextField(10);
        JTextField annee = new JTextField(10);
        JTextField genre = new JTextField(10);
        JTextField nbCopies = new JTextField(10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Titre: "));
        inputPanel.add(titre);
        inputPanel.add(new JLabel("Autheur: "));
        inputPanel.add(autheur);
        inputPanel.add(new JLabel("Année de publication: "));
        inputPanel.add(annee);
        inputPanel.add(new JLabel("Genre: "));
        inputPanel.add(genre);
        inputPanel.add(new JLabel("Nombre de copies: "));
        inputPanel.add(nbCopies);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);
        submit.addActionListener(event->{
            nouveauLivre.setTitre(titre.getText());
            nouveauLivre.setAuteur(autheur.getText());
            nouveauLivre.setAnneepub(Integer.parseInt(annee.getText()));
            nouveauLivre.setGenre(genre.getText());
            nouveauLivre.setNbCopies(Integer.parseInt(nbCopies.getText()));
            LivreController.livreslist.add(nouveauLivre);
            x.getBookTableModel().addRow(new Object[]{
                    nouveauLivre.getidBook(),
                    nouveauLivre.getTitre(),
                    nouveauLivre.getAuteur(),
                    nouveauLivre.getAnneepub(),
                    nouveauLivre.getGenre(),
                    nouveauLivre.getNbCopies()
            });
            LivreController.WriteLivreFile();
            dialog.dispose();
        });
    }
    /**
     * Displays a dialog for modifying the details of an existing book. Allows the user
     * to update fields such as title, author, year of publication, and genre.
     *
     * @param x     The BiblioView instance for updating the UI.
     * @param index The index of the book in the list model.
     */
    public static void modifierLivre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a book");return;}
        Livre l = LivreController.livreslist.get(index);
        JTextField titre = new JTextField(l.getTitre(), 10);
        JTextField autheur = new JTextField(l.getAuteur(), 10);
        JTextField annee = new JTextField(Integer.toString(l.getAnneepub()), 10);
        JTextField genre = new JTextField(l.getGenre(), 10);
        JTextField nbCopies = new JTextField(Integer.toString(l.getNbCopies()), 10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Titre: "));
        inputPanel.add(titre);
        inputPanel.add(new JLabel("Autheur: "));
        inputPanel.add(autheur);
        inputPanel.add(new JLabel("Année de publication: "));
        inputPanel.add(annee);
        inputPanel.add(new JLabel("Genre: "));
        inputPanel.add(genre);
        inputPanel.add(new JLabel("Nombre de copies: "));
        inputPanel.add(nbCopies);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);
        submit.addActionListener(e -> {
            l.setTitre(titre.getText());
            l.setAuteur(autheur.getText());
            l.setAnneepub(Integer.parseInt(annee.getText()));
            l.setGenre(genre.getText());
            l.setNbCopies(Integer.parseInt(nbCopies.getText()));
            String s = l.getTitre();
            x.getBookTableModel().setValueAt(l.getTitre(), index, 1);
            x.getBookTableModel().setValueAt(l.getAuteur(), index, 2);
            x.getBookTableModel().setValueAt(l.getAnneepub(), index, 3);
            x.getBookTableModel().setValueAt(l.getGenre(), index, 4);
            x.getBookTableModel().setValueAt(l.getNbCopies(), index, 5);
            LivreController.WriteLivreFile();
            dialog.dispose();
        });
    }
    /**
     * Deletes a selected book from the system after user confirmation.
     *
     * @param x The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public static void supprimerLivre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a book");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this book?");
        if(choice==JOptionPane.YES_OPTION){
            LivreController.livreslist.remove(index);
            x.getBookTableModel().removeRow(index);
            LivreController.WriteLivreFile();
        }
    }

    /**
     * Displays a dialog for adding a new member to the system. Prompts the user
     * for member details such as last name, first name, password, age, and address.
     *
     * @param x The BiblioView instance for updating the UI.
     */
    public static void ajouterMembre(BiblioView x){
        Membre nouveauMembre = new Membre();
        JTextField lastName = new JTextField(10);
        JTextField firstName = new JTextField(10);
        JTextField password = new JTextField(10);
        JTextField age = new JTextField(10);
        JTextField address = new JTextField(10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
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
        submit.addActionListener(e-> {
            nouveauMembre.setLastName(lastName.getText());
            nouveauMembre.setFirstName(firstName.getText());
            nouveauMembre.setPassword(password.getText());
            nouveauMembre.setAdresse(address.getText());
            nouveauMembre.setAge(Integer.parseInt(age.getText()));
            x.getUserTableModel().addRow(new Object[]{
                    nouveauMembre.getUid(),
                    nouveauMembre.getLastName(),
                    nouveauMembre.getFirstName(),
                    nouveauMembre.getPassword(),
                    nouveauMembre.getAdresse()
            });
            dialog.setVisible(false);
            MembreController.membersList.add(nouveauMembre);
            MembreController.WriteMemberFile();
        });
    }
    /**
     * Displays a dialog for modifying the details of an existing member. Allows the user
     * to update fields such as last name, first name, password, age, and address.
     *
     * @param x     The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public static void modifierMembre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a member");return;}
        Membre m = MembreController.membersList.get(index);
        JTextField lastName = new JTextField(m.getLastName(), 10);
        JTextField firstName = new JTextField(m.getFirstName(), 10);
        JTextField password = new JTextField(m.getPassword(), 10);
        JTextField age = new JTextField(Integer.toString(m.getAge()), 10);
        JTextField address = new JTextField(m.getAdresse(), 10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
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
        submit.addActionListener(e -> {
            m.setLastName(lastName.getText());
            m.setFirstName(firstName.getText());
            m.setAge(Integer.parseInt(age.getText()));
            m.setPassword(password.getText());
            m.setAdresse(address.getText());
            x.getUserTableModel().setValueAt(m.getUid(), index, 0);
            x.getUserTableModel().setValueAt(m.getLastName(), index, 1);
            x.getUserTableModel().setValueAt(m.getFirstName(), index, 2);
            x.getUserTableModel().setValueAt(m.getPassword(), index, 3);
            x.getUserTableModel().setValueAt(m.getAge(), index, 4);
            x.getUserTableModel().setValueAt(m.getAdresse(), index, 5);
            MembreController.WriteMemberFile();
            dialog.dispose();
        });
    }
    /**
     * Deletes a selected member from the system after user confirmation.
     *
     * @param x The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public static void supprimerMembre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a member");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this member?");
        if(choice==JOptionPane.YES_OPTION){
            MembreController.membersList.remove(index);
            x.getUserTableModel().removeRow(index);
            MembreController.WriteMemberFile();
        }
    }
    public static void ajouterEmprunt(BiblioView x){
        Emprunt nouvelleEmprunt=new Emprunt();
        String bookIdText = x.getEmpruntAddBookNomField().getText();
        String userIdText = x.getEmpruntAddUserNomField().getText();
        Livre livre = LivreController.findBook(Integer.parseInt(bookIdText));
        Membre membre = MembreController.findMember(Integer.parseInt(userIdText));

        if (livre == null) {
            JOptionPane.showMessageDialog(x, "The specified book does not exist.");
            return;
        }
        if (membre == null) {
            JOptionPane.showMessageDialog(x, "The specified user does not exist.");
            return;
        }

        if (livre.getNbCopies() == 0) {
            JOptionPane.showMessageDialog(x, "No copies available for this book.");
            return;
        }
        livre.setNbCopies(livre.getNbCopies() - 1);
        //ismail
        nouvelleEmprunt.setLivreEmprunte(LivreController.findBook(Integer.parseInt(x.getEmpruntAddBookNomField().getText())));
        nouvelleEmprunt.setEmprunteur(MembreController.findMember(Integer.parseInt(x.getEmpruntAddUserNomField().getText())));
        nouvelleEmprunt.setDateEmprunt(Date.valueOf(LocalDate.now()));
        nouvelleEmprunt.setDateRetourTheo(Date.valueOf(LocalDate.now().plusDays(15)));
        EmpruntController.empruntList.add(nouvelleEmprunt);

        // update the row
        for (int i = 0; i < x.getBookTableModel().getRowCount(); i++) {
            if ((int) x.getBookTableModel().getValueAt(i, 0) == livre.getidBook()) {
                x.getBookTableModel().setValueAt(livre.getNbCopies(), i, 5);
                break;
            }
        }

        x.getEmpruntTableModel().addRow(new Object[]{
        nouvelleEmprunt.getIdE(),
        nouvelleEmprunt.getLivreEmprunte().getidBook(),
        nouvelleEmprunt.getEmprunteur().getUid(),
        nouvelleEmprunt.getDateEmprunt(),
        nouvelleEmprunt.getDateRetourTheo()
        });
        EmpruntController.writeEmpruntFile();

        x.updateStatistics();
    }
    public static void borrowBook(BiblioView x, int index) {
        Emprunt nouvelleEmprunt = new Emprunt();
        Livre livre = LivreController.livreslist.get(index);

        JTextField memberId = new JTextField(10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Book Title: "));
        inputPanel.add(new JLabel(livre.getTitre()));
        inputPanel.add(new JLabel("Member ID: "));
        inputPanel.add(memberId);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);

        submit.addActionListener(event -> {
            if (livre.getNbCopies() == 0) {
                JOptionPane.showMessageDialog(dialog, "No copies available for this book.");
            } else {
                nouvelleEmprunt.setLivreEmprunte(livre);
                nouvelleEmprunt.setEmprunteur(MembreController.findMember(Integer.parseInt(memberId.getText())));
                nouvelleEmprunt.setDateEmprunt(Date.valueOf(LocalDate.now()));
                nouvelleEmprunt.setDateRetourTheo(Date.valueOf(LocalDate.now().plusDays(15)));
                EmpruntController.empruntList.add(nouvelleEmprunt);
                livre.setNbCopies(livre.getNbCopies() - 1);
                //ismail
                x.getBookTableModel().setValueAt(livre.getNbCopies(), index, 5);
                x.getEmpruntTableModel().addRow(new Object[]{
                        nouvelleEmprunt.getIdE(),
                        livre.getidBook(),
                        nouvelleEmprunt.getEmprunteur().getUid(),
                        nouvelleEmprunt.getDateEmprunt(),
                        nouvelleEmprunt.getDateRetourTheo()
                });
                EmpruntController.writeEmpruntFile();
                dialog.dispose();
            }
        x.updateStatistics();
        });

        dialog.setVisible(true);
    }
    public static void modifierEmprunt(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a loan");return;}
        Emprunt em = EmpruntController.empruntList.get(index);
        JTextField bookId = new JTextField(Integer.toString(em.getLivreEmprunte().getidBook()), 10);
        JTextField memberId = new JTextField(Integer.toString(em.getEmprunteur().getUid()), 10);
        JTextField dateEmprunt = new JTextField(em.getDateEmprunt().toString(), 10);
        JTextField dateRetourTheo = new JTextField(em.getDateRetourTheo().toString(), 10);
        JButton submit = new JButton("Submit");

        JDialog dialog = new JDialog(x);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("Book ID: "));
        inputPanel.add(bookId);
        inputPanel.add(new JLabel("Member ID: "));
        inputPanel.add(memberId);
        inputPanel.add(new JLabel("Loan Date: "));
        inputPanel.add(dateEmprunt);
        inputPanel.add(new JLabel("Return Date: "));
        inputPanel.add(dateRetourTheo);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);
        dialog.setVisible(true);
        submit.addActionListener(e -> {
            em.setLivreEmprunte(LivreController.findBook(Integer.parseInt(bookId.getText())));
            em.setEmprunteur(MembreController.findMember(Integer.parseInt(memberId.getText())));
            em.setDateEmprunt(Date.valueOf(LocalDate.now()));
            em.setDateRetourTheo(Date.valueOf(LocalDate.now().plusDays(15)));
            x.getEmpruntTableModel().setValueAt(em.getLivreEmprunte().getidBook(), index, 1);
            x.getEmpruntTableModel().setValueAt(em.getEmprunteur().getUid(), index, 2);
            x.getEmpruntTableModel().setValueAt(em.getDateEmprunt(), index, 3);
            x.getBookTableModel().setValueAt(em.getDateRetourTheo(), index, 4);
            EmpruntController.writeEmpruntFile();
            dialog.dispose();
        });
    }

    public static void supprimerEmprunt(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a loan");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this loan?");
        if(choice==JOptionPane.YES_OPTION){
            EmpruntController.empruntList.remove(index);
            x.getEmpruntTableModel().removeRow(index);
            EmpruntController.writeEmpruntFile();
        }
    }
    public static void returnBook(BiblioView x, int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a loan");return;}
        Retour returned=new Retour();
        returned.setEmpruntretournee(EmpruntController.findEmprunt((int)x.getEmpruntTableModel().getValueAt(index,0)));
        returned.setLivreretourne(LivreController.findBook((int)x.getEmpruntTableModel().getValueAt(index,1)));
        returned.setMembreemprunteur(MembreController.findMember((int) x.getEmpruntTableModel().getValueAt(index,2)));
        returned.setDateRetour(Date.valueOf(LocalDate.now()));
        if(returned.setPenalite()){
            JOptionPane.showMessageDialog(x,"This book has been returne late, a penalty has been applied");
        }
        RetourController.retourList.add(returned);
        EmpruntController.findEmprunt((int)x.getEmpruntTableModel().getValueAt(index,0)).setReturned(true);
        RetourController.writeRetourFile();
        EmpruntController.writeEmpruntFile();
        returned.getLivreretourne().setNbCopies(returned.getLivreretourne().getNbCopies()+1);
        x.getReturnTableModel().addRow(new Object[]{
                returned.getIdRetour(),
                returned.getEmpruntretournee().getIdE(),
                returned.getLivreretourne().getidBook(),
                returned.getMembreemprunteur().getUid(),
                returned.getDateRetour()
        });
        x.getEmpruntTableModel().removeRow(index);
    }
}
