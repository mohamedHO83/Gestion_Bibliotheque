package controller;
import exceptions.BookNotFoundException;
import exceptions.LoanNotFoundException;
import exceptions.UserNotFoundException;
import modele.Emprunt;
import modele.Livre;
import modele.Membre;
import modele.Retour;
import view.BiblioView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RetourController {
    public RetourController(){}
    public void returnBook(BiblioView x, int index){
        if(index<0){
            JOptionPane.showMessageDialog(x,"Please select a loan","Return Error",JOptionPane.ERROR_MESSAGE);return;}
        Retour returned=new Retour();
        Emprunt emprunt=Emprunt.findEmprunt((int)x.getEmpruntTableModel().getValueAt(index,0));
        if(emprunt==null){
            try{
                throw new LoanNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        returned.setEmpruntretournee(emprunt);
        Livre empruntee=Livre.findBook((String)x.getEmpruntTableModel().getValueAt(index,1));
        if(empruntee==null){
            try{
                throw new BookNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        returned.setLivreretourne(empruntee);
        Membre emprunteur =Membre.findMember((String) x.getEmpruntTableModel().getValueAt(index,2));
        if(emprunteur==null){
            try{
                throw new UserNotFoundException().message(x);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        returned.setMembreemprunteur(emprunteur);
        returned.setDateRetour(Date.valueOf(LocalDate.now()));
        if(returned.setPenalite()){
            JOptionPane.showMessageDialog(x,"This book has been returne late, a penalty has been applied","Penalty applied",JOptionPane.WARNING_MESSAGE);
        }
        Retour.retourList.add(returned);
        Objects.requireNonNull(Emprunt.findEmprunt((int) x.getEmpruntTableModel().getValueAt(index, 0))).setReturned(true);
        Retour.writeRetourFile();
        Emprunt.writeEmpruntFile();
        Membre.WriteMemberFile();
        Livre.writeLivreFile();
        returned.getLivreretourne().setNbCopies(returned.getLivreretourne().getNbCopies()+1);
        x.getReturnTableModel().addRow(new Object[]{
                returned.getIdRetour(),
                returned.getEmpruntretournee().getIdE(),
                returned.getLivreretourne().getTitre(),
                returned.getMembreemprunteur().getFullName(),
                returned.getDateRetour()
        });
        x.getEmpruntTableModel().removeRow(index);
        x.getUserTableModel().setRowCount(0);
        for (Membre m : Membre.membersList) {
            if(!m.isPenalized())
                x.getUserTableModel().addRow(new Object[]{
                        m.getUid(),
                        m.getLastName(),
                        m.getFirstName(),
                        m.getPassword(),
                        m.getAge(),
                        m.getAdresse()
                });
            else
                x.getUserTableModel().addRow(new Object[]{
                        m.getUid(),
                        m.getLastName(),
                        m.getFirstName(),
                        m.getPassword(),
                        m.getAge(),
                        m.getAdresse(),
                        m.getFinPenalite()
                });
        }
    }
    public void addRetourTableModel(JTable retourTable){
        DefaultTableModel returnTableModel = new DefaultTableModel(new String[]{"return Id","Loan Id","Book Title", "Member Name","Actual Return Date"}, 0);
        for(Retour re: Retour.retourList){
            returnTableModel.addRow(new Object[]{
                    re.getIdRetour(),
                    re.getEmpruntretournee().getIdE(),
                    re.getLivreretourne().getTitre(),
                    re.getMembreemprunteur().getFullName(),
                    re.getDateRetour()
            });
        }
        retourTable.setModel(returnTableModel);
    }
}
