package module;

import controller.EmpruntController;

import java.sql.Date;


/**
 * Represents a class Emprunt in the library management system.
 *
 * @author Monsef
 * @author Amine
 * @author Ismail
 * @author Mohammed
 */
public class Emprunt {
    /**
     * Represents the Emprunt’s ID.
     */
    private int idE;
    /**
     * Represents the book that was borrowed.
     */
    private Livre livreEmprunte;
    /**
     * Represents the preson who borrows the book.
     */
    private Membre emprunteur;
    /**
     * Represents the date of borrowing.
     */
    private Date dateEmprunt;
    /**
     * Represents the expected return date.
     */
    private Date dateRetourTheo;
    private boolean returned=false;

    /**
     * Default constructor for class Emprunt
     */
    public Emprunt(){idE= EmpruntController.EMPRUNT_ID_CPT++;}
    public Emprunt(Livre livreEmprunte,Membre emprunteur,Date dateEmprunt,Date dateRetourTheo){
        this.idE=EmpruntController.EMPRUNT_ID_CPT++;
        this.livreEmprunte=livreEmprunte;
        this.emprunteur=emprunteur;
        this.dateEmprunt=dateEmprunt;
        this.dateRetourTheo=dateRetourTheo;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    /**
     * Gets the expected return date.
     *
     * @return The expected return date.
     */
    public Date getDateRetourTheo() {
        return dateRetourTheo;
    }
    /**
     * Sets the expected return date.
     *
     * @param dateRetourTheo The expected return date.
     */
    public void setDateRetourTheo(Date dateRetourTheo) {
        this.dateRetourTheo = dateRetourTheo;
    }

    /**
     * Gets the borrowing date.
     *
     * @return The borrowing date.
     */
    public Date getDateEmprunt() {
        return dateEmprunt;
    }
    /**
     * Sets the borrowing date.
     *
     * @param dateEmprunt The borrowing date.
     */
    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    /**
     * Gets the member who borrowed the book.
     *
     * @return The member who borrowed the book.
     */
    public Membre getEmprunteur() {
        return emprunteur;
    }
    /**
     * Sets the member who borrowed the book.
     *
     * @param emprunteur The member who borrowed the book.
     */
    public void setEmprunteur(Membre emprunteur) {
        this.emprunteur = emprunteur;
    }

    /**
     * Gets the book that was borrowed.
     *
     * @return The book that was borrowed.
     */
    public Livre getLivreEmprunte() {
        return livreEmprunte;
    }
    /**
     * Sets the book that was borrowed.
     *
     * @param livreEmprunte The book that was borrowed.
     */
    public void setLivreEmprunte(Livre livreEmprunte) {
        this.livreEmprunte = livreEmprunte;
    }

    /**
     * Gets the Emprunt's ID.
     *
     * @return The Emprunt's ID.
     */
    public int getIdE() {
        return idE;
    }
    /**
     * Sets the Emprunt's ID.
     *
     * @param idE The Emprunt's ID.
     */
    public void setIdE(int idE) {
        this.idE = idE;
    }

    /**
     * Provides a string representation of the Emprunt instance.
     * @return A string representing the Emprunt.
     */
    @Override
    public String toString() {
        return  idE +"," + livreEmprunte.getidBook() +"," + emprunteur.getUid() +"," + dateEmprunt +"," + dateRetourTheo+","+returned ;
    }
}
