package view;
import controller.*;
import module.*;

import javax.swing.*;
import java.awt.*;

public class BiblioView extends JFrame {

    JTabbedPane mainTabbedPane = new JTabbedPane();

    // user section

    JButton userDetailsButton = new JButton("Details");
    JButton userAddButton = new JButton("Add");
    JButton userModifyButton = new JButton("Modify");
    JButton userDeleteButton = new JButton("Delete");
    JButton userSaveButton = new JButton("Save");
    DefaultListModel<String> userNomModel = new DefaultListModel<>();
    JList<String> userNomListe = new JList<>();
    JLabel userSearchLabel = new JLabel("Rechercher : ");
    JTextField userSearchField = new JTextField(20);

    // book section
    JButton bookDetailsButton = new JButton("Details");
    JButton bookAddButton = new JButton("Add");
    JButton bookModifyButton = new JButton("Modify");
    JButton bookDeleteButton = new JButton("Delete");
    JButton bookEmpruntButton = new JButton("Burrow");
    JButton bookSaveButton = new JButton("Save");
    DefaultListModel<String> bookNomModel = new DefaultListModel<>();
    JList<String> bookNomListe = new JList<>();
    JLabel bookSearchLabel = new JLabel("Rechercher : ");
    JTextField bookSearchField = new JTextField(20);

    // Emprunt section
    JLabel     empruntAddNomLabel = new JLabel("⌕ user");
    JTextField empruntAddUserNomField = new JTextField(20);
    JLabel     empruntAddBookLabel = new JLabel("⌕ book");
    JTextField empruntAddBookNomField = new JTextField(20);
    JButton    empruntAddButton = new JButton("Créer emprunt");

    JLabel empruntDetailsId = new JLabel("ID Emprunt : ");
    JLabel empruntDetailsNomUser = new JLabel("nom propriétaire : ");
    JLabel empruntDetailsNomBook = new JLabel("nom livre : ");
    JLabel empruntDetailsDateEmprunt = new JLabel("date d'emprunt : ");
    JLabel empruntDetailsDateRetourTheorique = new JLabel("date de retour théorique : ");
    JButton empruntRetourButton = new JButton("retourner");

    DefaultListModel<String> empruntNomModel = new DefaultListModel<>();
    JList<String> empruntNomListe = new JList<>();
    JLabel empruntSearchLabel = new JLabel("Rechercher : ");
    JTextField empruntSearchField = new JTextField(20);
    JButton empruntSaveButton = new JButton("Save");


    public BiblioView(){
        MembreController.readMemberFile();
        LivreController.readLivreFile();
        EmpruntController.readEmpruntFile();
        addComponentsUser();
        addComponentsBooks();
        addComponentsEmprunt();
        add(mainTabbedPane);
        pack();
        setTitle("Bibliothèque MAMI");
        setSize(1080,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        bookAddButton.addActionListener(e-> AdminController.ajouterLivre(this));
        bookModifyButton.addActionListener(e->AdminController.modifierLivre(this,bookNomListe.getSelectedValue(),bookNomListe.getSelectedIndex()));
        bookDeleteButton.addActionListener(e->AdminController.supprimerLivre(this,bookNomListe.getSelectedValue()));
        userAddButton.addActionListener(e->AdminController.ajouterMembre(this));
        userModifyButton.addActionListener(e->AdminController.modifierMembre(this,userNomListe.getSelectedValue(),userNomListe.getSelectedIndex()));
        userDeleteButton.addActionListener(e->AdminController.supprimerMembre(this,userNomListe.getSelectedValue()));
    }
    public DefaultListModel<String> getBookNomModel() {
        return bookNomModel;

    }
    public DefaultListModel<String> getUserNomModel() {
        return userNomModel;
    }

    public DefaultListModel<String> getEmpruntNomModel() {
        return empruntNomModel;
    }

    public void addComponentsUser() {
        for(Membre m:MembreController.membersList){
            userNomModel.addElement(m.getLastName()+" "+m.getFirstName());
        }
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for user buttons
        JPanel userP1 = new JPanel();
        userP1.setLayout(new GridLayout(4, 1, 10, 10));
        userP1.add(userDetailsButton);
        userP1.add(userAddButton);
        userP1.add(userModifyButton);
        userP1.add(userDeleteButton);

        // Panel for search field at the top
        JPanel userP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userP2.add(userSearchLabel);
        userP2.add(userSearchField);
        JPanel userP3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userP3.add(userSaveButton);
        JPanel userP4 = new JPanel();
        userP4.add(userP2);
        userP4.add(userP3);

        // User list setup
        userNomListe.setModel(userNomModel);
        JScrollPane userListScrollPane = new JScrollPane(userNomListe);

        // Add to the main panel
        mainPanel.add(userP4, BorderLayout.NORTH);
        mainPanel.add(userListScrollPane, BorderLayout.CENTER);
        mainPanel.add(userP1, BorderLayout.EAST);
        mainTabbedPane.addTab("Utilisateurs", mainPanel);
    }
    public void addComponentsBooks(){
        for(Livre l:LivreController.livreslist){
            bookNomModel.addElement(l.getTitre());
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bookP1 = new JPanel();
        bookP1.setLayout(new GridLayout(5,1,10,10));
        bookP1.add(bookDetailsButton);
        bookP1.add(bookAddButton);
        bookP1.add(bookModifyButton);
        bookP1.add(bookDeleteButton);
        bookP1.add(bookEmpruntButton);

        JPanel bookP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bookP2.add(bookSearchLabel);
        bookP2.add(bookSearchField);
        JPanel bookP3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookP3.add(bookSaveButton);
        JPanel bookP4 = new JPanel();
        bookP4.add(bookP2);
        bookP4.add(bookP3);

        bookNomListe.setModel(bookNomModel);
        JScrollPane bookListScrollPane = new JScrollPane(bookNomListe);

        mainPanel.add(bookP4,BorderLayout.NORTH);
        mainPanel.add(bookListScrollPane,BorderLayout.CENTER);
        mainPanel.add(bookP1,BorderLayout.EAST);

        mainTabbedPane.addTab("Livres",mainPanel);

    }
    public void addComponentsEmprunt(){
        for(Emprunt e: EmpruntController.empruntList){
            empruntNomModel.addElement(Integer.toString(e.getIdE()));
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel empruntP1 = new JPanel(new GridLayout(3,1,10,10));
        JPanel empruntsubP1 = new JPanel();
        empruntsubP1.add(empruntAddNomLabel);
        empruntsubP1.add(empruntAddUserNomField);
        JPanel empruntsubP2 = new JPanel();
        empruntsubP2.add(empruntAddBookLabel);
        empruntsubP2.add(empruntAddBookNomField);
        empruntP1.add(empruntsubP1);
        empruntP1.add(empruntsubP2);
        empruntP1.add(empruntAddButton);

        JPanel empruntP2 = new JPanel(new GridLayout(6,1,10,10));
        empruntP2.add(empruntDetailsId);
        empruntP2.add(empruntDetailsNomUser);
        empruntP2.add(empruntDetailsNomBook);
        empruntP2.add(empruntDetailsDateEmprunt);
        empruntP2.add(empruntDetailsDateRetourTheorique);
        empruntP2.add(empruntRetourButton);

        JPanel empruntP3 = new JPanel(new GridLayout(2,1,10,10));
        empruntP3.add(empruntP1);
        empruntP3.add(empruntP2);

        JPanel empruntP4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        empruntP4.add(empruntSearchLabel);
        empruntP4.add(empruntSearchField);

        JPanel empruntP5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        empruntP5.add(empruntSaveButton);
        JPanel empruntP6 = new JPanel();
        empruntP6.add(empruntP4);
        empruntP6.add(empruntP5);


        empruntNomListe.setModel(empruntNomModel);
        JScrollPane empruntListScrollPane = new JScrollPane(empruntNomListe);

        mainPanel.add(empruntP6,BorderLayout.NORTH);
        mainPanel.add(empruntListScrollPane,BorderLayout.CENTER);
        mainPanel.add(empruntP3,BorderLayout.EAST);

        mainTabbedPane.addTab("Emprunts",mainPanel);
    }

}
