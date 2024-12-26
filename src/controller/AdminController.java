package controller;
import interfaces.AdminFile;
import module.Admin;
import module.Livre;
import module.Membre;
import view.MainView;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class AdminController extends JFrame implements AdminFile  {
    private static ArrayList<Admin> adminList =new ArrayList<>();
    @Override
    public void readAdminFile() {
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Admins.csv"));
            Admin a=new Admin();
            while((a=(Admin) ois.readObject())!=null) {
                adminList.add(a);
            }
            ois.close();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteAdminFile() {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Admins.csv"));
            for(Admin a: adminList){
                oos.writeObject(a);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void ajouterLivre(MainView x){
        Livre nouveauLivre=new Livre();
        JTextField titre=new JTextField(10);
        JTextField autheur=new JTextField(10);
        JTextField annee=new JTextField(10);
        JTextField genre=new JTextField(10);
        JButton submit=new JButton("Submit");
        JDialog dialog=new JDialog(x);
        dialog.add(new JLabel("Titre: "));
        dialog.add(titre);
        dialog.add(new JLabel("Autheur: "));
        dialog.add(autheur);
        dialog.add(new JLabel("Année de publication: "));
        dialog.add(annee);
        dialog.add(new JLabel("Genre: "));
        dialog.add(genre);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(e->{
            nouveauLivre.setTitre(titre.getText());
            nouveauLivre.setAuteur(autheur.getText());
            nouveauLivre.setAnneepub(Integer.parseInt(annee.getText()));
            nouveauLivre.setGenre(genre.getText());
            //dialog.setVisible(false);
        });
        LivreController.livreslist.add(nouveauLivre);
        x.getBookModel().addElement(nouveauLivre);
    }
    public static void modifierLivre(JFrame x, Object o){
        if(o==null){JOptionPane.showMessageDialog(x,"Please select a book");}
        for(Livre l:LivreController.livreslist){
            if(l.getISBN()==((Livre)o).getISBN()){
                JTextField ISBN=new JTextField(Integer.toString(l.getISBN()),10);
                ISBN.setEditable(false);
                JTextField titre=new JTextField(l.getTitre(),10);
                JTextField autheur=new JTextField(l.getAuteur(),10);
                JTextField annee=new JTextField(Integer.toString(l.getAnneepub()),10);
                JTextField genre=new JTextField(l.getGenre(),10);
                JButton submit=new JButton("Submit");
                JDialog dialog=new JDialog(x);
                dialog.add(new JLabel("Titre: "));
                dialog.add(titre);
                dialog.add(new JLabel("Autheur: "));
                dialog.add(autheur);
                dialog.add(new JLabel("Année de publication: "));
                dialog.add(annee);
                dialog.add(new JLabel("Genre: "));
                dialog.add(genre);
                dialog.add(submit);
                dialog.setVisible(true);
                submit.addActionListener(e->{
                    l.setTitre(titre.getText());
                    l.setAuteur(autheur.getText());
                    l.setAnneepub(Integer.parseInt(annee.getText()));
                    l.setGenre(genre.getText());
                });
            }
        }
    }
    public static void ajouterMembre(MainView x){
        Membre nouveauMembre=new Membre();
        JTextField lastName=new JTextField(10);
        JTextField firstName=new JTextField(10);
        JTextField password=new JTextField(10);
        JButton submit=new JButton("Submit");
        JDialog dialog=new JDialog(x);
        dialog.add(new JLabel("Last Name: "));
        dialog.add(lastName);
        dialog.add(new JLabel("First Name: "));
        dialog.add(firstName);
        dialog.add(new JLabel("Password: "));
        dialog.add(password);
        dialog.add(submit);
        dialog.setVisible(true);
        submit.addActionListener(e->{
            nouveauMembre.setLastName(lastName.getText());
            nouveauMembre.setFirstName(firstName.getText());
            nouveauMembre.setPassword(password.getText());
            dialog.setVisible(false);
        });
        MembreController.membersList.add(nouveauMembre);
    }


}
