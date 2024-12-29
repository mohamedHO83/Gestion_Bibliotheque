package controller;
import module.*;
import view.*;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class AdminController extends JFrame   {
    public static void ajouterLivre(BiblioView x){
//        DateFormatter dateFormatter=new DateFormatter();
//        dateFormatter.setFormat(new );
        Livre nouveauLivre=new Livre();
        JTextField titre=new JTextField(10);
        JTextField autheur=new JTextField(10);
        JTextField annee=new JTextField(10);
        JTextField genre=new JTextField(10);
        JTextField nbCopies = new JTextField(10);
        JButton submit=new JButton("Submit");
        JDialog dialog=new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000,800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Titre: "));
        dialog.add(titre);
        dialog.add(new JLabel("Autheur: "));
        dialog.add(autheur);
        dialog.add(new JLabel("Année de publication: "));
        dialog.add(annee);
        dialog.add(new JLabel("Genre: "));
        dialog.add(genre);
        dialog.add(new JLabel("Nombre de copies"));
        dialog.add(nbCopies);
        dialog.add(submit);
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

    public static void modifierLivre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a book");return;}
        Livre l=LivreController.livreslist.get(index);
        JTextField ISBN = new JTextField(Integer.toString(l.getidBook()), 10);
        ISBN.setEditable(false);
        JTextField titre = new JTextField(l.getTitre(), 10);
        JTextField autheur = new JTextField(l.getAuteur(), 10);
        JTextField annee = new JTextField(Integer.toString(l.getAnneepub()), 10);
        JTextField genre = new JTextField(l.getGenre(), 10);
        JTextField nbCopies = new JTextField(Integer.toString(l.getNbCopies()), 10);
        JButton submit = new JButton("Submit");
        JDialog dialog = new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Book id: "));
        dialog.add(ISBN);
        dialog.add(new JLabel("Titre: "));
        dialog.add(titre);
        dialog.add(new JLabel("Autheur: "));
        dialog.add(autheur);
        dialog.add(new JLabel("Année de publication: "));
        dialog.add(annee);
        dialog.add(new JLabel("Genre: "));
        dialog.add(genre);
        dialog.add(new JLabel("Nombre de copies"));
        dialog.add(nbCopies);
        dialog.add(submit);
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
        return;
    }

    public static void supprimerLivre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a book");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this book?");
        if(choice==JOptionPane.YES_OPTION){
            LivreController.livreslist.remove(index);
            x.getBookTableModel().removeRow(index);
            LivreController.WriteLivreFile();
        }
        return;
    }


    public static void ajouterMembre(BiblioView x){
        Membre nouveauMembre=new Membre();
        JTextField lastName=new JTextField(10);
        JTextField firstName=new JTextField(10);
        JTextField password=new JTextField(10);
        JTextField age=new JTextField(10);
        JTextField adress=new JTextField(10);
        JButton submit=new JButton("Submit");
        JDialog dialog=new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000,800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Last Name: "));
        dialog.add(lastName);
        dialog.add(new JLabel("First Name: "));
        dialog.add(firstName);
        dialog.add(new JLabel("Password: "));
        dialog.add(password);
        dialog.add(new JLabel("Age: "));
        dialog.add(age);
        dialog.add(new JLabel("Adress: "));
        dialog.add(adress);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(e-> {
            nouveauMembre.setLastName(lastName.getText());
            nouveauMembre.setFirstName(firstName.getText());
            nouveauMembre.setPassword(password.getText());
            nouveauMembre.setAdresse(adress.getText());
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
    public static void modifierMembre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a member");return;}
        Membre m=MembreController.membersList.get(index);
        JTextField UID = new JTextField(Integer.toString(m.getUid()), 10);
        UID.setEditable(false);
        JTextField lastName = new JTextField(m.getLastName(), 10);
        JTextField firstName = new JTextField(m.getFirstName(), 10);
        JTextField password = new JTextField(m.getPassword(), 10);
        JTextField age = new JTextField(Integer.toString(m.getAge()), 10);
        JTextField adresse = new JTextField(m.getAdresse(), 10);
        JButton submit = new JButton("Submit");
        JDialog dialog = new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Member Id: "));
        dialog.add(UID);
        dialog.add(new JLabel("Last Name: "));
        dialog.add(lastName);
        dialog.add(new JLabel("First Name: "));
        dialog.add(firstName);
        dialog.add(new JLabel("Password: "));
        dialog.add(password);
        dialog.add(new JLabel("Age: "));
        dialog.add(age);
        dialog.add(new JLabel("Adresse"));
        dialog.add(adresse);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(e -> {
            m.setLastName(lastName.getText());
            m.setFirstName(firstName.getText());
            m.setAge(Integer.parseInt(age.getText()));
            m.setPassword(password.getText());
            m.setAdresse(adresse.getText());
            x.getBookTableModel().setValueAt(m.getUid(), index, 1);
            x.getBookTableModel().setValueAt(m.getLastName(), index, 2);
            x.getBookTableModel().setValueAt(m.getFirstName(), index, 3);
            x.getBookTableModel().setValueAt(m.getPassword(), index, 4);
            x.getBookTableModel().setValueAt(m.getAge(), index, 5);
            x.getBookTableModel().setValueAt(m.getAdresse(), index, 5);
            MembreController.WriteMemberFile();
            dialog.dispose();
        });
        return;
    }
    public static void supprimerMembre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a member");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this member?");
        if(choice==JOptionPane.YES_OPTION){
            MembreController.membersList.remove(index);
            x.getUserTableModel().removeRow(index);
            MembreController.WriteMemberFile();
        }
        return;
    }
    public static void ajouterEmprunt(BiblioView x){
        Emprunt nouvelleEmprunt=new Emprunt();
        JTextField bookId=new JTextField(10);
        JTextField memberId=new JTextField(10);
        JTextField dateEmprunt=new JTextField(10);
        JTextField dateRetourTheo=new JTextField(10);
        JTextField dateRetourRelle = new JTextField(10);
        JButton submit=new JButton("Submit");
        JDialog dialog=new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000,800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Book Id: "));
        dialog.add(bookId);
        dialog.add(new JLabel("Member Id: "));
        dialog.add(memberId);
        dialog.add(new JLabel("Loan Date: "));
        dialog.add(dateEmprunt);
        dialog.add(new JLabel("Supposed Return Date: "));
        dialog.add(dateRetourTheo);
        dialog.add(new JLabel("Actual Return Date"));
        dialog.add(dateRetourRelle);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(event->{
            nouvelleEmprunt.setLivreEmprunte(LivreController.findBook(Integer.parseInt(bookId.getText())));
            nouvelleEmprunt.setEmprunteur(MembreController.findMember(Integer.parseInt(memberId.getText())));
            try{
                nouvelleEmprunt.setDateEmprunt((DateFormat.getDateInstance().parse(dateEmprunt.getText())));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(dialog,"Loan Date incorrect, please try again ");
            }
            try{
                nouvelleEmprunt.setDateRetourTheo((DateFormat.getDateInstance().parse(dateRetourTheo.getText())));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(dialog,"Supposed Return Date incorrect, please try again ");
            }
            try{
                nouvelleEmprunt.setDateRetourReel((DateFormat.getDateInstance().parse(dateRetourRelle.getText())));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(dialog,"Actual return Date, please try again ");
            }
            EmpruntController.empruntList.add(nouvelleEmprunt);
            x.getEmpruntTableModel().addRow(new Object[]{
                    nouvelleEmprunt.getIdE(),
                    nouvelleEmprunt.getLivreEmprunte().getidBook(),
                    nouvelleEmprunt.getEmprunteur().getUid(),
                    nouvelleEmprunt.getDateEmprunt(),
                    nouvelleEmprunt.getDateRetourTheo(),
                    nouvelleEmprunt.getDateRetourReel()
            });
            EmpruntController.writeEmpruntFile();
            dialog.dispose();
        });
    }

    public static void modifierEmprunt(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a loan");return;}
        Emprunt em=EmpruntController.empruntList.get(index);
        JTextField empruntID = new JTextField(Integer.toString(em.getIdE()), 10);
        empruntID.setEditable(false);
        JTextField bookId = new JTextField(Integer.toString(em.getLivreEmprunte().getidBook()), 10);
        JTextField memberId = new JTextField(Integer.toString(em.getEmprunteur().getUid()), 10);
        JTextField dateEmprunt = new JTextField(em.getDateEmprunt().toString(), 10);
        JTextField dateRetourTheo = new JTextField(em.getDateRetourTheo().toString(), 10);
        JTextField dateRetourReel = new JTextField(em.getDateRetourReel().toString(), 10);
        JButton submit = new JButton("Submit");
        JDialog dialog = new JDialog(x);
        dialog.setLayout(new GridLayout());
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(null);
        dialog.add(new JLabel("Loan id: "));
        dialog.add(empruntID);
        dialog.add(new JLabel("Book id: "));
        dialog.add(bookId);
        dialog.add(new JLabel("Member id: "));
        dialog.add(memberId);
        dialog.add(new JLabel("Loan Date: "));
        dialog.add(dateEmprunt);
        dialog.add(new JLabel("Supposed Return Date: "));
        dialog.add(dateRetourTheo);
        dialog.add(new JLabel("Actual Return Date"));
        dialog.add(dateRetourReel);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(e -> {
            em.setLivreEmprunte(LivreController.findBook(Integer.parseInt(bookId.getText())));
            em.setEmprunteur(MembreController.findMember(Integer.parseInt(memberId.getText())));
            try{
                em.setDateEmprunt((DateFormat.getDateInstance().parse(dateEmprunt.getText())));
            } catch (ParseException er) {
                JOptionPane.showMessageDialog(dialog,"Loan Date incorrect, please try again ");
            }
            try{
                em.setDateRetourTheo((DateFormat.getDateInstance().parse(dateRetourTheo.getText())));
            } catch (ParseException er) {
                JOptionPane.showMessageDialog(dialog,"Supposed Return Date incorrect, please try again ");
            }
            try{
                em.setDateRetourReel((DateFormat.getDateInstance().parse(dateRetourReel.getText())));
            } catch (ParseException er) {
                JOptionPane.showMessageDialog(dialog,"Actual return Date, please try again ");
            }
            x.getEmpruntTableModel().setValueAt(em.getLivreEmprunte().getidBook(), index, 1);
            x.getEmpruntTableModel().setValueAt(em.getEmprunteur().getUid(), index, 2);
            x.getEmpruntTableModel().setValueAt(em.getDateEmprunt(), index, 3);
            x.getBookTableModel().setValueAt(em.getDateRetourTheo(), index, 4);
            x.getUserTableModel().setValueAt(em.getDateRetourReel(), index, 5);
            EmpruntController.writeEmpruntFile();
            dialog.dispose();
        });
        return;
    }

    public static void supprimerEmprunt(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a loan");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this loan?");
        if(choice==JOptionPane.YES_OPTION){
            EmpruntController.empruntList.remove(index);
            x.getEmpruntTableModel().removeRow(index);
            EmpruntController.writeEmpruntFile();
        }
        return;
    }
}

