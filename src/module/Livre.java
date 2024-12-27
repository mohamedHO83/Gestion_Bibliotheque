package module;

import controller.LivreController;

import java.io.Serializable;

public class Livre implements Serializable {
    private int idBook;
    private String titre;
    private String auteur;
    private int anneepub;
    private String genre;
    private int nbCopies;

    public Livre() { this.idBook = LivreController.LIVRE_ID_CPT++;}


    public Livre(String titre, String auteur, int anneepub, String genre, int nbcopies){
        this.idBook =LivreController.LIVRE_ID_CPT++;
        this.titre=titre;
        this.auteur=auteur;
        this.anneepub=anneepub;
        this.genre=genre;
        this.nbCopies=nbcopies;
    }
    public int getidBook(){
        return idBook;
    }

    public int getNbCopies() {
        return nbCopies;
    }

    public void setNbCopies(int nbCopies) {
        this.nbCopies = nbCopies;
    }


        public void setIdBook(int idbook){
        this.idBook=idbook;
    }
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
    @Override
    public String toString() {
        return idBook +"," + titre +"," + auteur +"," + anneepub +"," + genre +","+ nbCopies;
    }
}
