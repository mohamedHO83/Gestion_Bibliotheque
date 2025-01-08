package modele;


import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
     * List of emprunts in the library system.
     */
    public static List<Emprunt> empruntList=new ArrayList<>();
    /**
     * Counter used to generate IDs for emrprunts.
     */
    public static int empruntIdCpt =0;

    /**
     * Default constructor for class Emprunt
     */
    public Emprunt(){idE=empruntIdCpt++;}
    public Emprunt(Livre livreEmprunte,Membre emprunteur,Date dateEmprunt,Date dateRetourTheo){
        this.idE=empruntIdCpt++;
        this.livreEmprunte=livreEmprunte;
        this.emprunteur=emprunteur;
        this.dateEmprunt=dateEmprunt;
        this.dateRetourTheo=dateRetourTheo;
    }

    public static Emprunt findEmprunt(int id){
        for(Emprunt e:empruntList){
            if(e.getIdE()==id){
                return e;
            }
        }
        return null;
    }
    /**
     * Reads the list of loans from a CSV file and populates {@link #empruntList}.
     * Each line in the file represents a loan's details.
     *
     * @throws RuntimeException if an I/O error or a parsing error occurs while reading the file.
     */
    public static void readEmpruntFile() {
        try {
            BufferedReader ois = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\Emprunts.csv"));
            String line;
            while((line=ois.readLine()) !=null){
                if(line.isEmpty()){return;}
                Emprunt e=new Emprunt();
                String[] empruntFields=line.split(",");
                if(empruntIdCpt <Integer.parseInt(empruntFields[0])){
                    empruntIdCpt =Integer.parseInt(empruntFields[0])+1;}
                e.setIdE(Integer.parseInt(empruntFields[0]));
                e.setLivreEmprunte(Livre.findBook(Integer.parseInt(empruntFields[1])));
                e.setEmprunteur(Membre.findMember(Integer.parseInt(empruntFields[2])));
                e.setDateEmprunt(Date.valueOf(empruntFields[3]));
                e.setDateRetourTheo(Date.valueOf(empruntFields[4]));
                if(empruntFields[5].equals("true")) {
                    e.setReturned(true);
                }else{
                    e.setReturned(false);
                }
                empruntList.add(e);
            }
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the list of loans to a CSV file.
     * Each line in the file represents a loan's details.
     *
     * @throws RuntimeException if an I/O error occurs while writing to the file.
     */
    public static void writeEmpruntFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Emprunts.csv"));
            for(Emprunt e:empruntList){
                oos.write(e.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
