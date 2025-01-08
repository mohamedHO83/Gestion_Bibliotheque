package controller;

import exceptions.BookNotFoundException;
import exceptions.CopiesException;
import exceptions.FieldNotFilledException;
import exceptions.UserNotFoundException;
import modele.Emprunt;
import modele.Livre;
import modele.Membre;
import view.BiblioView;
import view.BorrowBookDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Controls operations related to emprunts in the library system.
 * This class handles the list of loans, provides file read/write functionality
 */
public class EmpruntController  {
    public EmpruntController(){}
    public  void ajouterEmprunt(BiblioView x){
        Emprunt nouvelleEmprunt=new Emprunt();
        String bookIdText = x.getEmpruntAddBookNomField().getText();
        String userIdText = x.getEmpruntAddUserNomField().getText();
        Livre livre ;
        try{
            livre= Livre.findBook(Integer.parseInt(bookIdText));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(x,"Please enter the book id","Loan Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Membre membre ;
        try{
            membre=Membre.findMember(Integer.parseInt(userIdText));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(x,"Please enter the member id","Loan Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (livre == null) {
            try {
                throw new BookNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);

            }
        }
        if (membre == null) {
            try{
                throw new UserNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        if(membre.isPenalized()){
            JOptionPane.showMessageDialog(x,"This member can not loan a book untill "+membre.getFinPenalite(),"Penalized Member",JOptionPane.ERROR_MESSAGE);
        }
        if (livre.getNbCopies() == 0) {
            try{
                throw new CopiesException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        livre.setNbCopies(livre.getNbCopies() - 1);
        //ismail
        nouvelleEmprunt.setLivreEmprunte(Livre.findBook(Integer.parseInt(x.getEmpruntAddBookNomField().getText())));
        nouvelleEmprunt.setEmprunteur(Membre.findMember(Integer.parseInt(x.getEmpruntAddUserNomField().getText())));
        nouvelleEmprunt.setDateEmprunt(Date.valueOf(LocalDate.now()));
        nouvelleEmprunt.setDateRetourTheo(Date.valueOf(LocalDate.now().plusDays(15)));
        Emprunt.empruntList.add(nouvelleEmprunt);

        // update the row
        for (int i = 0; i < x.getBookTableModel().getRowCount(); i++) {
            if ((int) x.getBookTableModel().getValueAt(i, 0) == livre.getidBook()) {
                x.getBookTableModel().setValueAt(livre.getNbCopies(), i, 5);
                break;
            }
        }

        x.getEmpruntTableModel().addRow(new Object[]{
                nouvelleEmprunt.getIdE(),
                nouvelleEmprunt.getLivreEmprunte().getTitre(),
                nouvelleEmprunt.getEmprunteur().getFullName(),
                nouvelleEmprunt.getDateEmprunt(),
                nouvelleEmprunt.getDateRetourTheo()
        });
        Emprunt.writeEmpruntFile();

        x.updateStatistics();
    }
    public  void borrowBook(BiblioView x, int index) {
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a Book","Borrow Book",JOptionPane.ERROR_MESSAGE);return;}
        Emprunt nouvelleEmprunt = new Emprunt();
        Livre livre = Livre.livreslist.get(index);
        if(livre==null){
            try{
                throw new BookNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        BorrowBookDialog dialog=new BorrowBookDialog(x,livre);

        dialog.getSubmit().addActionListener(event -> {
            if (livre.getNbCopies() == 0) {
                try {
                    throw new CopiesException().message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            } else if(dialog.getMemberId().getText().isEmpty()){
                try{
                    throw  new FieldNotFilledException("'Member Id'").message(dialog);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            } else {
                Membre emprunteur=Membre.findMember(Integer.parseInt(dialog.getMemberId().getText()));
                if(emprunteur==null){
                    try{
                        throw new UserNotFoundException().message(dialog);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
                if(emprunteur.isPenalized()){
                    JOptionPane.showMessageDialog(x,"This member can not loan a book untill "+emprunteur.getFinPenalite(),"Penalized Member",JOptionPane.ERROR_MESSAGE);
                }
                nouvelleEmprunt.setLivreEmprunte(livre);
                nouvelleEmprunt.setEmprunteur(emprunteur);
                nouvelleEmprunt.setDateEmprunt(Date.valueOf(LocalDate.now()));
                nouvelleEmprunt.setDateRetourTheo(Date.valueOf(LocalDate.now().plusDays(15)));
                Emprunt.empruntList.add(nouvelleEmprunt);
                livre.setNbCopies(livre.getNbCopies() - 1);
                //ismail
                x.getBookTableModel().setValueAt(livre.getNbCopies(), index, 5);
                x.getEmpruntTableModel().addRow(new Object[]{
                        nouvelleEmprunt.getIdE(),
                        nouvelleEmprunt.getLivreEmprunte().getTitre(),
                        nouvelleEmprunt.getEmprunteur().getFullName(),
                        nouvelleEmprunt.getDateEmprunt(),
                        nouvelleEmprunt.getDateRetourTheo()
                });
                Livre.writeLivreFile();
                Emprunt.writeEmpruntFile();
                dialog.dispose();
                x.updateStatistics();
            }
        });
    }
    public void addSearchFieldSorteur(BiblioView x){
        x.getEmpruntSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String emprunttyped = x.getEmpruntSearchField().getText()+e.getKeyChar();
                emprunttyped = emprunttyped.trim();
                TableRowSorter<TableModel> sorter=new TableRowSorter<>(x.getEmpruntTableModel());
                x.getEmpruntTable().setRowSorter(sorter);
                if(emprunttyped.isEmpty()){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)"+emprunttyped));
                }
            }
        });
    }
    public void addEmpruntTableModel(JTable empruntTable){
        DefaultTableModel empruntTableModel = new DefaultTableModel(new String[]{"Loan Id", "Book Title", "Member Name","Loan Date", "Supposed Return Date"}, 0);
        for (Emprunt em : Emprunt.empruntList) {
            if(!em.isReturned()) {
                empruntTableModel.addRow(new Object[]{
                        em.getIdE(),
                        em.getLivreEmprunte().getTitre(),
                        em.getEmprunteur().getFullName(),
                        em.getDateEmprunt(),
                        em.getDateRetourTheo()
                });
            }
        }
        empruntTable.setModel(empruntTableModel);
    }
}
