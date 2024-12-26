package module;

import java.io.Serializable;
import java.util.Date;

public class Emprunt implements Serializable {
    private int idE;
    private Livre livreEmprunte;
    private Membre emprunteur;
    private Date dateEmprunt;
    private Date dateRetourTheo;
    private Date dateRetourReel;
    private static int cpt=1;

    public Emprunt(){idE=cpt++;}
    public Emprunt(Livre livreEmprunte,Membre emprunteur,Date dateEmprunt,Date dateRetourTheo,Date dateRetourReel){
        this.idE=cpt++;
        this.livreEmprunte=livreEmprunte;
        this.emprunteur=emprunteur;
        this.dateEmprunt=dateEmprunt;
        this.dateRetourTheo=dateRetourTheo;
        this.dateRetourReel=dateRetourReel;
    }

    public Date getDateRetourReel() {
        return dateRetourReel;
    }

    public void setDateRetourReel(Date dateRetourReel) {
        this.dateRetourReel = dateRetourReel;
    }

    public Date getDateRetourTheo() {
        return dateRetourTheo;
    }

    public void setDateRetourTheo(Date dateRetourTheo) {
        this.dateRetourTheo = dateRetourTheo;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Membre getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Membre emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Livre getLivreEmprunte() {
        return livreEmprunte;
    }

    public void setLivreEmprunte(Livre livreEmprunte) {
        this.livreEmprunte = livreEmprunte;
    }

    public int getIdE() {
        return idE;
    }

//    public void setIdE(int idE) {
//        this.idE = idE;
//    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "idE=" + idE +
                ", livre Emprunte=" + livreEmprunte.getISBN() +
                ", emprunteur=" + emprunteur.getUid() +
                ", date Emprunt=" + dateEmprunt +
                ", date Retour Theorique =" + dateRetourTheo +
                ", date Retour Réelle=" + dateRetourReel +
                '}';
    }
}
