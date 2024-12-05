package model;
//POJO=Plain Old Java Object
public class Etudiant{
	private int id;
	private static int compteur;
	private String nom;
	private float note;
	public Etudiant() {
		super();
		compteur++;
		id=compteur;
	}
	public Etudiant(String nom, float note) {
		super();
		this.nom = nom;
		this.note = note;
		compteur++;
		id=compteur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", note=" + note + "]";
	}

	
	
	
	
}
