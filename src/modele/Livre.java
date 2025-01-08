package modele;

import controller.LivreController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * List of books in the library system.
     */
    public static List<Livre> livreslist=new ArrayList<>();
    /**
     * Counter used to generate IDs for books.
     */
    public static int livreIdCpt =0;

    /**
     * Searches for a book by its ID in the list of books.
     *
     * @param id The ID of the book to search for.
     * @return The book with the specified ID, or null if the book doesn't exist.
     */
    public static Livre findBook(int id){
        for(Livre l:livreslist){
            if(l.getidBook()==id){
                return l;
            }
        }
        return null;
    }
    public static Livre findBook(String title){
        for(Livre l:livreslist){
            if(l.getTitre().equals(title)){
                return l;
            }
        }
        return null;
    }
    public Livre() { this.idBook = livreIdCpt++;}
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
        this.idBook =livreIdCpt++;
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
     * Reads the list of books from a CSV file and populates {@link #livreslist}.
     * Each line in the file represents a book's details.
     *
     * @throws RuntimeException if there is an I/O error while reading the file.
     */
    public static void readLivreFile() {
        try{
            BufferedReader ois=new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\Livres.csv"));
            String line;
            while((line=ois.readLine())!=null) {
                if(line.isEmpty()){return;}
                Livre l=new Livre();
                String[] livrefields=line.split(",");
                if(livreIdCpt <Integer.parseInt(livrefields[0])){
                    livreIdCpt =Integer.parseInt(livrefields[0])+1;
                }
                l.setIdBook(Integer.parseInt(livrefields[0]));
                l.setTitre(livrefields[1]);
                l.setAuteur(livrefields[2]);
                l.setAnneepub(Integer.parseInt(livrefields[3]));
                l.setGenre(livrefields[4]);
                l.setNbCopies(Integer.parseInt(livrefields[5]));
                livreslist.add(l);
            }
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the list of books to a CSV file.
     * Each line in the file represents a book's details.
     *
     * @throws RuntimeException if there is an I/O error while writing to the file.
     */
    public static void writeLivreFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Livres.csv"));
            for(Livre l:livreslist){
                oos.write(l.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
