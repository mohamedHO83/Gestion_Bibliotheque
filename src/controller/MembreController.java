package controller;

import exceptions.FieldNotFilledException;
import modele.Membre;
import view.AjouterMembreDialog;
import view.BiblioView;
import view.ModifierMembreDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controls operations related to members in the library system.
 * This class manages the list of members, provides search functionality,
 * and manages reading from and writing to a file.
 */
public class MembreController {
    public MembreController(){ }

    /**
     * Displays a dialog for adding a new member to the system. Prompts the user
     * for member details such as last name, first name, password, age, and address.
     *
     * @param x The BiblioView instance for updating the UI.
     */

    public void ajouterMembre(BiblioView x){
        Membre nouveauMembre = new Membre();

        AjouterMembreDialog dialog= new AjouterMembreDialog(x);
        dialog.getSubmit().addActionListener(e-> {
            if(dialog.getLastName().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Title'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getFirstName().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Author'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getPassword().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Year of Publication'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getAge().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Genre'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getAddress().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Number of Copies'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            nouveauMembre.setLastName(dialog.getLastName().getText());
            nouveauMembre.setFirstName(dialog.getFirstName().getText());
            nouveauMembre.setPassword(dialog.getPassword().getText());
            nouveauMembre.setAdresse(dialog.getAddress().getText());
            nouveauMembre.setAge(Integer.parseInt(dialog.getAge().getText()));
            x.getUserTableModel().addRow(new Object[]{
                    nouveauMembre.getUid(),
                    nouveauMembre.getLastName(),
                    nouveauMembre.getFirstName(),
                    nouveauMembre.getPassword(),
                    nouveauMembre.getAdresse()
            });
            Membre.membersList.add(nouveauMembre);
            Membre.WriteMemberFile();
        });
    }
    /**
     * Displays a dialog for modifying the details of an existing member. Allows the user
     * to update fields such as last name, first name, password, age, and address.
     *
     * @param x     The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public void modifierMembre(BiblioView x,int index){
        if(index<0){
            JOptionPane.showMessageDialog(x,"Please select a member");return;}
        Membre m = Membre.membersList.get(index);
        ModifierMembreDialog dialog=new ModifierMembreDialog(x,m);
        dialog.getSubmit().addActionListener(e -> {
            if(dialog.getLastName().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Last Name'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getFirstName().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'First Name'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getPassword().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Password'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getAge().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Age'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            if(dialog.getAddress().getText().isEmpty()){
                try {
                    throw new FieldNotFilledException("'Adresse'").message(dialog);
                } catch (Throwable err) {
                    throw new RuntimeException(err);
                }
            }
            m.setLastName(dialog.getLastName().getText());
            m.setFirstName(dialog.getFirstName().getText());
            m.setAge(Integer.parseInt(dialog.getAge().getText()));
            m.setPassword(dialog.getPassword().getText());
            m.setAdresse(dialog.getAddress().getText());
            x.getUserTableModel().setValueAt(m.getUid(), index, 0);
            x.getUserTableModel().setValueAt(m.getLastName(), index, 1);
            x.getUserTableModel().setValueAt(m.getFirstName(), index, 2);
            x.getUserTableModel().setValueAt(m.getPassword(), index, 3);
            x.getUserTableModel().setValueAt(m.getAge(), index, 4);
            x.getUserTableModel().setValueAt(m.getAdresse(), index, 5);
            Membre.WriteMemberFile();
        });
    }
    /**
     * Deletes a selected member from the system after user confirmation.
     *
     * @param x The BiblioView instance for updating the UI.
     * @param index the index of the selected book in the data model
     */
    public void supprimerMembre(BiblioView x,int index){
        if(index<0){JOptionPane.showMessageDialog(x,"Please select a member","Delete Member",JOptionPane.ERROR_MESSAGE);return;}
        int choice=JOptionPane.showConfirmDialog(x,"Are you sure you want to delete this member?","Delete Member",JOptionPane.YES_NO_CANCEL_OPTION);
        if(choice==JOptionPane.YES_OPTION){
            Membre.membersList.remove(index);
            x.getUserTableModel().removeRow(index);
            Membre.WriteMemberFile();
        }
    }
    public void addSearchFieldSorter(BiblioView x){
        x.getUserSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String typed = x.getUserSearchField().getText()+e.getKeyChar();
                typed = typed.trim();
                TableRowSorter<TableModel> sorter=new TableRowSorter<>(x.getUserTableModel());
                x.getUserTable().setRowSorter(sorter);
                if(typed.isEmpty()){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)"+typed));
                }
            }
        });
    }
    public void addUserTableModel(JTable userTable){
        DefaultTableModel userTableModel = new DefaultTableModel(
                new String[]{"Member Id", "Last Name", "First Name","Password", "Age", "Adress","Penalty End"}, 0
        );
        for (Membre m : Membre.membersList) {
            if(!m.isPenalized())
                userTableModel.addRow(new Object[]{
                        m.getUid(),
                        m.getLastName(),
                        m.getFirstName(),
                        m.getPassword(),
                        m.getAge(),
                        m.getAdresse()
                });
            else
                userTableModel.addRow(new Object[]{
                        m.getUid(),
                        m.getLastName(),
                        m.getFirstName(),
                        m.getPassword(),
                        m.getAge(),
                        m.getAdresse(),
                        m.getFinPenalite()
                });
        }
        userTable.setModel(userTableModel);
    }
}
