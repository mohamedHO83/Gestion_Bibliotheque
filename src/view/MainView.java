package view;

import controller.AdminController;
import controller.LivreController;
import module.Livre;
import controller.MembreController;
import module.Membre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame {
    private JTabbedPane tabs=new JTabbedPane();
    private JLabel rechercherLabel=new JLabel("Rechercher");
    private JTextField rechercherTextField=new JTextField(10);
    private JButton detailsBtn=new JButton("Details");
    private JButton userAddBtn=new JButton("Add");
    private JButton userModifyBtn=new JButton("Modify");
    private JButton userDeleteBtn=new JButton("Delete");
    private JButton bookAjouterBtn=new JButton("Add");
    private JButton bookModifierBtn=new JButton("Modify");
    private JButton bookSupprimerBtn=new JButton("Delete");
    private DefaultListModel<Membre> usersModel=(DefaultListModel<Membre>)MembreController.membersList;
    DefaultListModel<Livre>bookModel=(DefaultListModel<Livre>) LivreController.livreslist;
    private JList<Livre> bookTable=new JList<>();
    private JList<Membre> usersTable=new JList<>(usersModel);
    private DefaultTableModel livresModel=new DefaultTableModel(
            new Object[][]{LivreController.livreslist.toArray()},
            new Object[]{"ISBN","Titre","Autheur","Année de publication","genre"}
    );
    private JTable livresTable=new JTable(livresModel);

    public MainView(){
        this.setTitle("Bibliothèque MADI");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        bookAjouterBtn.addActionListener(e-> AdminController.ajouterLivre(this));
        bookModifierBtn.addActionListener(e->AdminController.modifierLivre(this, bookTable.getSelectedValue()));
        userAddBtn.addActionListener(e->AdminController.ajouterMembre(this));
    }

    public DefaultListModel<Livre> getBookModel() {
        return bookModel;
    }


}
