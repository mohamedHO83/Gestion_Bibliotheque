package controller;

import module.Emprunt;
import java.io.*;
import java.sql.Date;
import java.util.*;

/**
 * Controls operations related to emprunts in the library system.
 * This class handles the list of loans, provides file read/write functionality
 */
public class EmpruntController  {
    /**
     * List of emprunts in the library system.
     */
    public static List<Emprunt> empruntList=new ArrayList<>();
    /**
     * Counter used to generate IDs for emrprunts.
     */
    public static int EMPRUNT_ID_CPT=0;

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
                if(EMPRUNT_ID_CPT<Integer.parseInt(empruntFields[0])){EMPRUNT_ID_CPT=Integer.parseInt(empruntFields[0])+1;}
                e.setIdE(Integer.parseInt(empruntFields[0]));
                e.setLivreEmprunte(LivreController.findBook(Integer.parseInt(empruntFields[1])));
                e.setEmprunteur(MembreController.findMember(Integer.parseInt(empruntFields[2])));
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
}
