package controller;
import module.*;
import view.*;
import javax.swing.*;
import java.awt.*;
public class AdminController extends JFrame   {
    //public static ArrayList<Admin> adminList =new ArrayList<>();

//    public void readAdminFile() {
//        try{
//            BufferedReader ois=new BufferedReader(new FileReader("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Bibliothecaire.csv"));
//            Admin a=new Admin();
//            String line;
//            while((line= ois.readLine())!=null) {
//
//                adminList.add(a);
//            }
//            ois.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void WriteAdminFile() {
//        try{
//            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Bibliothecaireb.csv"));
//            for(Admin a: adminList){
//                oos.writeObject(a);
//            }
//            oos.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void ajouterLivre(BiblioView x){
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
            x.getBookNomModel().addElement(nouveauLivre.getTitre());
            LivreController.WriteLivreFile();
            dialog.dispose();
        });
    }

    public static void modifierLivre(BiblioView x, Object o,int index){
        if(o==null){JOptionPane.showMessageDialog(x,"Please select a book");}
        else{
            for(Livre l:LivreController.livreslist){
                if(l.getTitre().equals(o)){
                    JTextField ISBN=new JTextField(Integer.toString(l.getidBook()),10);
                    ISBN.setEditable(false);
                    JTextField titre=new JTextField(l.getTitre(),10);
                    JTextField autheur=new JTextField(l.getAuteur(),10);
                    JTextField annee=new JTextField(Integer.toString(l.getAnneepub()),10);
                    JTextField genre=new JTextField(l.getGenre(),10);
                    JTextField nbCopies=new JTextField(Integer.toString(l.getNbCopies()),10);
                    JButton submit=new JButton("Submit");
                    JDialog dialog=new JDialog(x);
                    dialog.setLayout(new GridLayout());
                    dialog.setSize(1000,800);
                    dialog.setLocationRelativeTo(null);
                    dialog.add(new JLabel("Titre: "));
                    dialog.add(titre);
                    dialog.add(new JLabel("Book id: "));
                    dialog.add(ISBN);
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
                    submit.addActionListener(e->{
                        l.setTitre(titre.getText());
                        l.setAuteur(autheur.getText());
                        l.setAnneepub(Integer.parseInt(annee.getText()));
                        l.setGenre(genre.getText());
                        String s=l.getTitre();
                        x.getBookNomModel().set(index,s);
                        LivreController.WriteLivreFile();
                        dialog.dispose();
                    });
                    return;
                }
            }
        }
    }
    public static void supprimerLivre(BiblioView x,Object o){
        if(o==null){JOptionPane.showMessageDialog(x,"Please select a book");}
        else{
            for(Livre l:LivreController.livreslist){
                if(l.getTitre().equals(o)){
                    int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this book?");
                    if(choice==JOptionPane.YES_OPTION){
                        LivreController.livreslist.remove(l);
                        x.getBookNomModel().removeElement(o);
                        LivreController.WriteLivreFile();
                    }
                    return;
                }
            }
        }
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
        submit.addActionListener(e->{
            nouveauMembre.setLastName(lastName.getText());
            nouveauMembre.setFirstName(firstName.getText());
            nouveauMembre.setPassword(password.getText());
            nouveauMembre.setAge(Integer.parseInt(age.getText()));
            nouveauMembre.setAdresse(adress.getText());
            dialog.setVisible(false);
            MembreController.membersList.add(nouveauMembre);
            x.getUserNomModel().addElement(nouveauMembre.getLastName()+" "+nouveauMembre.getFirstName());
            MembreController.WriteMemberFile();
            dialog.dispose();
        });
    }
    public static void modifierMembre(BiblioView x,Object o,int index){
        if(o==null){JOptionPane.showMessageDialog(x,"Please select a book");}
        else{
            for (Membre m : MembreController.membersList) {
                if ((m.getLastName() + " " + m.getFirstName()).equals(o)) {
                    JTextField lastName = new JTextField(m.getLastName(), 10);
                    JTextField firstName = new JTextField(m.getFirstName(), 10);
                    JTextField password = new JTextField(m.getPassword(), 10);
                    JTextField age = new JTextField(Integer.toString(m.getAge()), 10);
                    JTextField adress = new JTextField(m.getAdresse(), 10);
                    JButton submit = new JButton("Submit");
                    JDialog dialog = new JDialog(x);
                    dialog.setLayout(new GridLayout());
                    dialog.setSize(1000, 800);
                    dialog.setLocationRelativeTo(null);
                    dialog.add(new JLabel("Last Name: "));
                    dialog.add(lastName);
                    dialog.add(new JLabel("First Name: "));
                    dialog.add(firstName);
                    dialog.add(new JLabel("Age: "));
                    dialog.add(age);
                    dialog.add(new JLabel("Adress: "));
                    dialog.add(adress);
                    dialog.add(submit);
                    dialog.setVisible(true);
                    submit.addActionListener(e -> {
                        m.setLastName(lastName.getText());
                        m.setFirstName(firstName.getText());
                        m.setPassword(password.getText());
                        m.setAge(Integer.parseInt(age.getText()));
                        m.setAdresse(adress.getText());
                        String s=m.getLastName()+" "+m.getFirstName();
                        x.getUserNomModel().set(index,s);
                        MembreController.WriteMemberFile();
                        dialog.dispose();
                    });
                }
            }
        }
    }
    public static void supprimerMembre(BiblioView x,Object o){
        if(o==null){JOptionPane.showMessageDialog(x,"Please select a book");}
        else{
            for(Membre m:MembreController.membersList){
                if((m.getLastName()+" "+m.getFirstName()).equals(o)){
                    int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this member?");
                    if(choice==JOptionPane.YES_OPTION){
                        MembreController.membersList.remove(m);
                        x.getUserNomModel().removeElement(o);
                        MembreController.WriteMemberFile();
                    }
                    return;
                }
            }
        }
    }

}
