package controller;

import exceptions.FieldNotFilledException;
import modele.Livre;
import view.AjouterLivreDialog;
import view.BiblioView;
import view.ModifierLivreDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controls operations related to books in the library system.
 * This class manages the list of books, allows searching, and provides
 * methods for reading from and writing to a file.
 */
public class LivreController {

    public LivreController(){}
    /**
     * Displays a dialog for adding a new book to the system. Prompts the user
     * for book details such as title, author, year of publication, genre, and
     * number of copies.
     *
     * @param x The BiblioView instance for updating the UI.
     */
    public void ajouterLivre(BiblioView x){
        Livre nouveauLivre = new Livre();
        AjouterLivreDialog dialog=new AjouterLivreDialog(x);
        dialog.getSubmit().addActionListener(event->{
            if(dialog.getTitre().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Title'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            if(dialog.getAutheur().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Author'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            if(dialog.getAnnee().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Year of Publication'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            if(dialog.getGenre().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Genre'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            if(dialog.getNbCopies().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Number of Copies'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            nouveauLivre.setTitre(dialog.getTitre().getText());
            nouveauLivre.setAuteur(dialog.getAutheur().getText());
            nouveauLivre.setAnneepub(Integer.parseInt(dialog.getAnnee().getText()));
            nouveauLivre.setGenre(dialog.getGenre().getText());
            nouveauLivre.setNbCopies(Integer.parseInt(dialog.getNbCopies().getText()));
            Livre.livreslist.add(nouveauLivre);
            x.getBookTableModel().addRow(new Object[]{
                    nouveauLivre.getidBook(),
                    nouveauLivre.getTitre(),
                    nouveauLivre.getAuteur(),
                    nouveauLivre.getAnneepub(),
                    nouveauLivre.getGenre(),
                    nouveauLivre.getNbCopies()
            });
            Livre.writeLivreFile();
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
    public void modifierLivre(BiblioView x,int index){
        if(index<0){
            JOptionPane.showMessageDialog(x,"Please select a book");return;}
        Livre l = Livre.livreslist.get(index);
        ModifierLivreDialog dialog=new ModifierLivreDialog(x,l);
        dialog.getSubmit().addActionListener(e -> {
            if(dialog.getTitre().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Title'").message(dialog);
                } catch (Throwable er) {
                    throw new RuntimeException(er);
                }
            }
            if(dialog.getAutheur().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Author'").message(dialog);
                } catch (Throwable er) {
                    throw new RuntimeException(er);
                }
            }
            if(dialog.getAnnee().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Year of Publication'").message(dialog);
                } catch (Throwable er) {
                    throw new RuntimeException(er);
                }
            }
            if(dialog.getGenre().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Genre'").message(dialog);
                } catch (Throwable er) {
                    throw new RuntimeException(er);
                }
            }
            if(dialog.getNbCopies().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Number of Copies'").message(dialog);
                } catch (Throwable er) {
                    throw new RuntimeException(er);
                }
            }
            l.setTitre(dialog.getTitre().getText());
            l.setAuteur(dialog.getAutheur().getText());
            l.setAnneepub(Integer.parseInt(dialog.getAnnee().getText()));
            l.setGenre(dialog.getGenre().getText());
            l.setNbCopies(Integer.parseInt(dialog.getNbCopies().getText()));
            x.getBookTableModel().setValueAt(l.getTitre(), index, 1);
            x.getBookTableModel().setValueAt(l.getAuteur(), index, 2);
            x.getBookTableModel().setValueAt(l.getAnneepub(), index, 3);
            x.getBookTableModel().setValueAt(l.getGenre(), index, 4);
            x.getBookTableModel().setValueAt(l.getNbCopies(), index, 5);
            Livre.writeLivreFile();
            dialog.dispose();
        });
    }
    /**
     * Deletes a selected book from the system after user confirmation.
     *
     * @param x The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public void supprimerLivre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a book");return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this book?","Delete Book",JOptionPane.YES_NO_CANCEL_OPTION);
        if(choice==JOptionPane.YES_OPTION){
            Livre.livreslist.remove(index);
            x.getBookTableModel().removeRow(index);
            Livre.writeLivreFile();
        }
    }
    public void addSearchFieldSorter(BiblioView x){
        x.getBookSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String typed = x.getBookSearchField().getText()+e.getKeyChar();
                typed = typed.trim();
                TableRowSorter<TableModel> sorter=new TableRowSorter<>(x.getBookTableModel());
                x.getBookTable().setRowSorter(sorter);
                if(typed.isEmpty()){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)"+typed));
                }
            }
        });
    }
    public void addBookTableModel(JTable bookTable){
        DefaultTableModel bookTableModel = new DefaultTableModel(new String[]{"Book Id", "Title", "Author", "Year", "Genre", "Copies"}, 0);
        for (Livre l : Livre.livreslist) {
            bookTableModel.addRow(new Object[]{
                    l.getidBook(),
                    l.getTitre(),
                    l.getAuteur(),
                    l.getAnneepub(),
                    l.getGenre(),
                    l.getNbCopies()
            });
        }
        bookTable.setModel(bookTableModel);
    }
}
