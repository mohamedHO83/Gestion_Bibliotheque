package model;

public interface EtudiantModelInterface {
	
	public void ajouterEtudiant(Etudiant e);
	public void supprimerEtudiant(int id);
	public void modifierEtudiant(int id, String nouveauNom, float nouvelleNote);
	public void listerEtudiants();
	public void trierEtudiants();
	public void sauvegraderCSV();
	public Etudiant rechercherParID(int id);
	public void lireCSV(); //comma separated value

}
