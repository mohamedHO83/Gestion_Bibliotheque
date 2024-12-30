package module;

import controller.LivreController;

import java.io.Serializable;

/**
 * Represents a book in the library system.
 *
 * @author Monsef
 * @author Amine
 * @author Ismail
 * @author Mohammed
 */
public class Livre  {
    /**
     * Represents the ID of the book.
     */
    private int idBook;
    /**
     * Represents the title of the book.
     */
    private String titre;
    /**
     * Represents the author of the book.
     */
    private String auteur;
    /**
     * Represents the year the book was published.
     */
    private int anneepub;
    /**
     * Represents the genre of the book.
     */
    private String genre;
    /**
     * Represents the number of copies available in the library.
     */
    private int nbCopies;

    /**
     * Default constructor for the Livre class.
     */
    public Livre() { this.idBook = LivreController.LIVRE_ID_CPT++;}
    /**
     * Constructor for the Livre class.
     *
     * @param titre The title of the book.
     * @param auteur The author of the book.
     * @param anneepub The year the book was published.
     * @param genre The genre of the book.
     * @param nbcopies The number of copies available in the library.
     */
    public Livre(String titre, String auteur, int anneepub, String genre, int nbcopies){
        this.idBook =LivreController.LIVRE_ID_CPT++;
        this.titre=titre;
        this.auteur=auteur;
        this.anneepub=anneepub;
        this.genre=genre;
        this.nbCopies=nbcopies;
    }

    /**
     * Gets the ID of the book.
     *
     * @return The ID of the book.
     */
    public int getidBook(){
        return idBook;
    }

    /**
     * Gets the number of copies available in the library.
     *
     * @return The number of copies available.
     */
    public int getNbCopies() {
        return nbCopies;
    }
    /**
     * Sets the number of copies available in the library.
     *
     * @param nbCopies The new number of available copies.
     */
    public void setNbCopies(int nbCopies) {
        this.nbCopies = nbCopies;
    }

    /**
     * Sets the ID of the book.
     *
     * @param idbook The new ID for the book.
     */
    public void setIdBook(int idbook){
        this.idBook=idbook;
    }
    public String getAuteur() {
        return auteur;
    }
    /**
     * Sets the author of the book.
     *
     * @param auteur The new author of the book.
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * Gets the year the book was published.
     *
     * @return The publication year of the book.
     */
    public int getAnneepub() {
        return anneepub;
    }
    /**
     * Sets the year the book was published.
     *
     * @param anneepub The new publication year of the book.
     */
    public void setAnneepub(int anneepub) {
        this.anneepub = anneepub;
    }

    /**
     * Gets the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Sets the genre of the book.
     *
     * @param genre The new genre of the book.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitre() {
        return titre;
    }
    /**
     * Sets the title of the book.
     *
     * @param titre The new title of the book.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Checks if this book is equal to another book based on the book ID.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livre livre)) return false;
        return this.idBook ==((Livre) o).idBook;
    }

    /**
     * Provides a string representation of the book instance.
     * @return A string representing the book.
     */
    @Override
    public String toString() {
        return idBook +"," + titre +"," + auteur +"," + anneepub +"," + genre +","+ nbCopies;
    }
}
