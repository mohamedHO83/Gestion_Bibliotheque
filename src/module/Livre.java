package module;

import java.util.Objects;

public class Livre {
    private int isbn;
    private String titre;
    private String auteur;
    private int anneepub;
    private String genre;
    private static int cpt=1;
    public Livre() { this.isbn=cpt++;}

    public Livre(String titre,String auteur, int anneepub,String genre){
        this.isbn=cpt++;
        this.titre=titre;
        this.auteur=auteur;
        this.anneepub=anneepub;
        this.genre=genre;
    }
    public int getISBN(){
        return isbn;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn=" + isbn +
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
        return this.isbn==((Livre) o).isbn;
    }
}
