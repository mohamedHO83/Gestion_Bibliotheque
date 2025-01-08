package view;
import controller.*;
import modele.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the main user interface for the library management system.
 * This view provides separate tabs for managing users, books, and loans (emprunts),
 * and integrates with the corresponding controllers for each entity.
 */
public class BiblioView extends JFrame {

    /**
     * Main tabbed pane containing the "Users", "Books", and "Emprunts" sections.
     */
    JTabbedPane mainTabbedPane = new JTabbedPane();

    // user section
    MembreController membreController=new MembreController();
    JButton userAddButton = new JButton("Add");
    JButton userModifyButton = new JButton("Modify");
    JButton userDeleteButton = new JButton("Delete");
    JTable userTable = new JTable();
    JLabel userSearchLabel = new JLabel("Search : ");
    JTextField userSearchField = new JTextField(20);

    // book section
    LivreController bookController=new LivreController();
    JButton bookAddButton = new JButton("Add");
    JButton bookModifyButton = new JButton("Modify");
    JButton bookDeleteButton = new JButton("Delete");
    JButton bookEmpruntButton = new JButton("Borrow");
    JTable bookTable = new JTable();
    JLabel bookSearchLabel = new JLabel("Search : ");
    JTextField bookSearchField = new JTextField(20);

    // Emprunt section
    EmpruntController loanController=new EmpruntController();
    RetourController returnController=new RetourController();
    JLabel     empruntAddNomLabel = new JLabel("User Id");
    JTextField empruntAddUserNomField = new JTextField(20);
    JLabel     empruntAddBookLabel = new JLabel("Book Id");
    JTextField empruntAddBookNomField = new JTextField(20);
    JButton    empruntAddButton = new JButton("New Loan");
    JButton empruntRetourButton = new JButton("return");
    JTable empruntTable = new JTable();
    JLabel empruntSearchLabel = new JLabel("Search : ");
    JTextField empruntSearchField = new JTextField(20);
    JTable returnTable = new JTable();

    // statistics section
    DefaultTableModel mostBorrowedBooksModel = new DefaultTableModel(
            new String[]{"Title", "Count"}, 0);
    DefaultTableModel mostBorrowedCategoryModel = new DefaultTableModel(
            new String[]{"Genre", "Count"}, 0);
    DefaultTableModel mostActiveUsersModel = new DefaultTableModel(
            new String[]{"Member", "Count"}, 0);

    Map<String, Integer> borrowCounts = new HashMap<>();


    /**
     * Constructs the main library management view.
     * Initializes components, populates data from controllers, and sets up action listeners.
     */
    public BiblioView(){
        Membre.readMemberFile();
        Livre.readLivreFile();
        Emprunt.readEmpruntFile();
        Retour.readRetourFile();
        addComponentsUser();
        addComponentsBooks();
        addComponentsEmprunt();
        addComponentsStatistics();
        add(mainTabbedPane);
        pack();
        setTitle("Library MAMI");
        setSize(1080,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        bookAddButton.addActionListener(e-> bookController.ajouterLivre(this));
        bookModifyButton.addActionListener(e->bookController.modifierLivre(this,bookTable.getSelectedRow()));
        bookDeleteButton.addActionListener(e->bookController.supprimerLivre(this,bookTable.getSelectedRow()));

        userAddButton.addActionListener(e->membreController.ajouterMembre(this));
        userModifyButton.addActionListener(e->membreController.modifierMembre(this,userTable.getSelectedRow()));
        userDeleteButton.addActionListener(e->membreController.supprimerMembre(this,userTable.getSelectedRow()));

        bookEmpruntButton.addActionListener(e->loanController.borrowBook(this,bookTable.getSelectedRow()));
        empruntAddButton.addActionListener(e->loanController.ajouterEmprunt(this));
        empruntRetourButton.addActionListener(e->returnController.returnBook(this,empruntTable.getSelectedRow()));
        bookController.addSearchFieldSorter(this);
        membreController.addSearchFieldSorter(this);
        loanController.addSearchFieldSorteur(this);
    }
    public JButton getUserAddButton() {
        return userAddButton;
    }

    public JTextField getBookSearchField() {
        return bookSearchField;
    }

    public JTextField getUserSearchField() {
        return userSearchField;
    }

    public JTextField getEmpruntSearchField() {
        return empruntSearchField;
    }

    public DefaultTableModel getEmpruntTableModel() {
        return (DefaultTableModel) empruntTable.getModel();
    }


    public JTable getEmpruntTable() {
        return empruntTable;
    }

    public void setEmpruntTable(JTable empruntTable) {
        this.empruntTable = empruntTable;
    }

    /**
     * Adds components for the "Users" section of the library system.
     * Populates user data and sets up the user tab layout.
     */
    public void addComponentsUser() {
        membreController.addUserTableModel(userTable);
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for user buttons
        JPanel userP1 = new JPanel();
        userP1.setLayout(new GridLayout(4, 1, 10, 10));
        userP1.add(userAddButton);
        userP1.add(userModifyButton);
        userP1.add(userDeleteButton);

        // Panel for search field at the top
        JPanel userP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userP2.add(userSearchLabel);
        userP2.add(userSearchField);
        JPanel userP3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel userP4 = new JPanel();
        userP4.add(userP2);
        userP4.add(userP3);

        // User list setup
        JScrollPane userListScrollPane = new JScrollPane(userTable);

        // Add to the main panel
        mainPanel.add(userP4, BorderLayout.NORTH);
        mainPanel.add(userListScrollPane, BorderLayout.CENTER);
        mainPanel.add(userP1, BorderLayout.EAST);
        mainTabbedPane.addTab("Members", mainPanel);
    }

    /**
     * Adds components for the "Books" section of the library system.
     * Populates book data and sets up the book tab layout.
     */
    public void addComponentsBooks(){
        bookController.addBookTableModel(bookTable);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bookP1 = new JPanel();
        bookP1.setLayout(new GridLayout(5,1,10,10));
        bookP1.add(bookAddButton);
        bookP1.add(bookModifyButton);
        bookP1.add(bookDeleteButton);
        bookP1.add(bookEmpruntButton);

        JPanel bookP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bookP2.add(bookSearchLabel);
        bookP2.add(bookSearchField);
        JPanel bookP3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel bookP4 = new JPanel();
        bookP4.add(bookP2);
        bookP4.add(bookP3);

        JScrollPane bookListScrollPane = new JScrollPane(bookTable);

        mainPanel.add(bookP4,BorderLayout.NORTH);
        mainPanel.add(bookListScrollPane,BorderLayout.CENTER);
        mainPanel.add(bookP1,BorderLayout.EAST);

        mainTabbedPane.addTab("Books",mainPanel);

    }
    public DefaultTableModel getUserTableModel(){
        return (DefaultTableModel) userTable.getModel();
    }

    /**
     * Adds components for the "Emprunts" section of the library system.
     * Populates loan data and sets up the loan tab layout.
     */
    public void addComponentsEmprunt(){
        loanController.addEmpruntTableModel(empruntTable);
        returnController.addRetourTableModel(returnTable);

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
        empruntP2.add(empruntRetourButton);

        JPanel empruntP3 = new JPanel(new GridLayout(2,1,10,10));
        empruntP3.add(empruntP1);
        empruntP3.add(empruntP2);

        JPanel empruntP4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        empruntP4.add(empruntSearchLabel);
        empruntP4.add(empruntSearchField);

        JPanel empruntP5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel empruntP6 = new JPanel();
        empruntP6.add(empruntP4);
        empruntP6.add(empruntP5);


        JScrollPane empruntListScrollPane = new JScrollPane(empruntTable);
        JScrollPane returnTableScrollPane=new JScrollPane(returnTable);
        JPanel tablesPanel=new JPanel(new GridLayout(2,1));
        tablesPanel.add(empruntListScrollPane);
        tablesPanel.add(returnTableScrollPane);
        mainPanel.add(empruntP6,BorderLayout.NORTH);
        mainPanel.add(tablesPanel,BorderLayout.CENTER);
        mainPanel.add(empruntP3,BorderLayout.EAST);

        mainTabbedPane.addTab("Loans",mainPanel);
    }
    private void addComponentsStatistics() {
// Create table models for statistics

        for (Emprunt em : Emprunt.empruntList) {
            String bookTitle = em.getLivreEmprunte().getTitre();
            borrowCounts.put(bookTitle, borrowCounts.getOrDefault(bookTitle, 0) + 1);
        }
        borrowCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10) // Limit to top 10 books
                .forEach(entry -> mostBorrowedBooksModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));

        Map<String, Integer> userActivityCounts = new HashMap<>();
        for (Emprunt em : Emprunt.empruntList) {
            String userName = em.getEmprunteur().getLastName() + " " + em.getEmprunteur().getFirstName();
            userActivityCounts.put(userName, userActivityCounts.getOrDefault(userName, 0) + 1);
        }
        userActivityCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10) // Limit to top 10 users
                .forEach(entry -> mostActiveUsersModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));

        Map<String, Integer> genreCount = new HashMap<>();
        for (Emprunt em : Emprunt.empruntList) {
            String bookGenre = em.getLivreEmprunte().getGenre();
            genreCount.put(bookGenre, genreCount.getOrDefault(bookGenre, 0) + 1); // Use genreCount here
        }
        genreCount.entrySet().stream() // Use genreCount instead of borrowCounts
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10) // Limit to top 10 categories
                .forEach(entry -> mostBorrowedCategoryModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));

        JTable mostBorrowedBooksTable = new JTable(mostBorrowedBooksModel);
        JTable mostBorrowedCategoryTable = new JTable(mostBorrowedCategoryModel);
        JTable mostActiveUsersTable = new JTable(mostActiveUsersModel);

        // Create panels for each table
        JPanel booksPanel = new JPanel(new BorderLayout());
        booksPanel.add(new JLabel("Most Loaned Books"), BorderLayout.NORTH);
        booksPanel.add(new JScrollPane(mostBorrowedBooksTable), BorderLayout.CENTER);

        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.add(new JLabel("Most Loaned Genres"), BorderLayout.NORTH);
        genrePanel.add(new JScrollPane(mostBorrowedCategoryTable), BorderLayout.CENTER);

        JPanel usersPanel = new JPanel(new BorderLayout());
        usersPanel.add(new JLabel("Most Active Users"), BorderLayout.NORTH);
        usersPanel.add(new JScrollPane(mostActiveUsersTable), BorderLayout.CENTER);

        // Combine both panels into one main panel
        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        statsPanel.add(booksPanel);
        statsPanel.add(usersPanel);
        statsPanel.add(genrePanel);

        // Add the panel to the tabbed pane
        mainTabbedPane.addTab("Statistics", statsPanel);
    }

    public void updateStatistics() {
        // Clear the table
        mostBorrowedBooksModel.setRowCount(0);
        mostBorrowedCategoryModel.setRowCount(0);
        mostActiveUsersModel.setRowCount(0);

        Map<String, Integer> bookBorrowCounts = new HashMap<>();
        Map<String, Integer> userBorrowCounts = new HashMap<>();
        Map<String, Integer> genreBorrowCounts = new HashMap<>();

        for (Emprunt em : Emprunt.empruntList) {

            String bookTitle = em.getLivreEmprunte().getTitre();
            bookBorrowCounts.put(bookTitle, bookBorrowCounts.getOrDefault(bookTitle, 0) + 1);


            String userName = em.getEmprunteur().getLastName() + " " + em.getEmprunteur().getFirstName();
            userBorrowCounts.put(userName, userBorrowCounts.getOrDefault(userName, 0) + 1);


            String genre = em.getLivreEmprunte().getGenre();
            genreBorrowCounts.put(genre, genreBorrowCounts.getOrDefault(genre, 0) + 1);
        }


        bookBorrowCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .forEach(entry -> mostBorrowedBooksModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));

        userBorrowCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .forEach(entry -> mostActiveUsersModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));

        genreBorrowCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .forEach(entry -> mostBorrowedCategoryModel.addRow(new Object[]{entry.getKey(), entry.getValue()}));
    }


    public JTable getUserTable() {
        return userTable;
    }

    public DefaultTableModel getBookTableModel() {
        return (DefaultTableModel) bookTable.getModel();
    }

    public JTable getBookTable() {
        return bookTable;
    }


    public DefaultTableModel getReturnTableModel() {
        return (DefaultTableModel) returnTable.getModel();
    }

    public JTable getReturnTable() {
        return returnTable;
    }
    public JTextField getEmpruntAddBookNomField() {
        return empruntAddBookNomField;
    }

    public JTextField getEmpruntAddUserNomField() {
        return empruntAddUserNomField;
    }

    public DefaultTableModel getMostActiveUsersModel() {
        return mostActiveUsersModel;
    }

    public DefaultTableModel getMostBorrowedCategoryModel() {
        return mostBorrowedCategoryModel;
    }

    public DefaultTableModel getMostBorrowedBooksModel() {
        return mostBorrowedBooksModel;
    }
}