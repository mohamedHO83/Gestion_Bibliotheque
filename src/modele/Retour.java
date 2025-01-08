package modele;

import controller.EmpruntController;
import controller.LivreController;
import controller.MembreController;
import controller.RetourController;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static modele.Livre.findBook;

public class Retour {
    private int idRetour;
    private Emprunt empruntretournee;
    private Livre livreretourne;
    private Membre membreemprunteur;
    private Date dateRetour;

    public static List<Retour> retourList=new ArrayList<>();
    public static int retourIdCpt =0;

    public Retour(){
        idRetour=retourIdCpt++;
    }

    public Retour(Emprunt empruntretournee,Livre livreretourne,Membre membreemprunteur,Date dateRetour){
        idRetour=retourIdCpt++;
        this.empruntretournee=empruntretournee;
        this.livreretourne=livreretourne;
        this.membreemprunteur=membreemprunteur;
        this.dateRetour=dateRetour;
    }
    public boolean setPenalite(){
        if(dateRetour.after(empruntretournee.getDateEmprunt())){
            long diffInMillis = dateRetour.getTime() - empruntretournee.getDateRetourTheo().getTime();
            long daysLate = diffInMillis / (1000 * 60 * 60 * 24);

            this.membreemprunteur.setPenalized(true);
            this.membreemprunteur.setFinPenalite(Date.valueOf(LocalDate.now().plusDays(2L * daysLate)));
            return true;
        }else{
            return false;
        }
    }

    public static void readRetourFile() {
        try {
            BufferedReader ois = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\Retours.csv"));
            String line;
            while((line=ois.readLine()) !=null){
                if(line.isEmpty()){return;}
                Retour re=new Retour();
                String[] returnFields =line.split(",");
                if(retourIdCpt <Integer.parseInt(returnFields[0])){
                    retourIdCpt =Integer.parseInt(returnFields[0])+1;}
                re.setIdRetour(Integer.parseInt(returnFields[0]));
                re.setEmpruntretournee(Emprunt.findEmprunt(Integer.parseInt(returnFields[1])));
                re.setLivreretourne(Livre.findBook(Integer.parseInt(returnFields[2])));
                re.setMembreemprunteur(Membre.findMember(Integer.parseInt(returnFields[3])));
                re.setDateRetour(Date.valueOf(returnFields[4]));
                retourList.add(re);
            }
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeRetourFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Retours.csv"));
            for(Retour re:retourList){
                oos.write(re.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public int getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(int idRetour) {
        this.idRetour = idRetour;
    }

    public Emprunt getEmpruntretournee() {
        return empruntretournee;
    }

    public void setEmpruntretournee(Emprunt empruntretournee) {
        this.empruntretournee = empruntretournee;
    }

    public Livre getLivreretourne() {
        return livreretourne;
    }

    public void setLivreretourne(Livre livreretourne) {
        this.livreretourne = livreretourne;
    }

    public Membre getMembreemprunteur() {
        return membreemprunteur;
    }

    public void setMembreemprunteur(Membre membreemprunteur) {
        this.membreemprunteur = membreemprunteur;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return  idRetour +"," + empruntretournee.getIdE() +"," + livreretourne.getidBook() +"," + membreemprunteur.getUid() +"," + dateRetour ;
    }
}
