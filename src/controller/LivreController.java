package controller;

import interfaces.LivreFile;
import module.Emprunt;
import module.Livre;
import java.util.*;
import java.io.*;

/**
 * Controls operations related to books in the library system.
 * This class manages the list of books, allows searching, and provides
 * methods for reading from and writing to a file.
 */
public class LivreController {
    /**
     * List of books in the library system.
     */
    public static List<Livre> livreslist=new ArrayList<>();
    /**
     * Counter used to generate IDs for books.
     */
    public static int LIVRE_ID_CPT=0;
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
    /**
     * Reads the list of books from a CSV file and populates {@link #livreslist}.
     * Each line in the file represents a book's details.
     *
     * @throws RuntimeException if there is an I/O error while reading the file.
     */
    public static void readLivreFile() {
        try{
            BufferedReader ois=new BufferedReader(new FileReader("C:\\Users\\masto\\OneDrive\\Documents\\Projects\\Gestion_Bibliotheque\\src\\Livres.csv"));
            Livre l=new Livre();
            String line;
            while((line=ois.readLine())!=null) {
                String[] livrefields=line.split(",");
                if(LIVRE_ID_CPT<Integer.parseInt(livrefields[0])){LIVRE_ID_CPT=Integer.parseInt(livrefields[0])+1;}
                l.setIdBook(LIVRE_ID_CPT++);
                l.setTitre(livrefields[1]);
                l.setAuteur(livrefields[2]);
                l.setAnneepub(Integer.parseInt(livrefields[3]));
                l.setGenre(livrefields[4]);
                l.setNbCopies(Integer.parseInt(livrefields[5]));
                System.out.println(l);
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
    public static void WriteLivreFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Livres.csv"));
            for(Livre l:livreslist){
                oos.write(l.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
