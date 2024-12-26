package module;

import java.io.Serializable;

public class Livre implements Serializable {
    private int idBook;
    private String titre;
    private String auteur;
    private int anneepub;
    private String genre;
    private int nbCopies;
    private static int cpt=1;

    public Livre() { this.idBook =cpt++;}

    public Livre(String titre,String auteur, int anneepub,String genre){
        this.idBook =cpt++;
        this.titre=titre;
        this.auteur=auteur;
        this.anneepub=anneepub;
        this.genre=genre;
    }
    public int getISBN(){
        return idBook;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn=" + idBook +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneepub=" + anneepub +
                ", genre='" + genre + '\'' +
                '}';
    }

    //    public void setISBN(int isbn){
//        this.isbn=isbn;
//    }
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneepub() {
        return anneepub;
    }

    public void setAnneepub(int anneepub) {
        this.anneepub = anneepub;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livre livre)) return false;
        return this.idBook ==((Livre) o).idBook;
    }
}
