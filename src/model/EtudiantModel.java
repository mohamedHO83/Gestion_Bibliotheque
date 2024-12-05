package model;

import java.util.ArrayList;
import java.util.Optional;

public class EtudiantModel implements EtudiantModelInterface{
	private ArrayList<Etudiant> liste=new ArrayList<>();
	private String csvFileName;
	
	@Override
	public void ajouterEtudiant(Etudiant e) {
		liste.add(e);
		
	}

	@Override
	public void supprimerEtudiant(int id) {
		Etudiant e=rechercherParID(id);
		if(e!=null)
			liste.remove(e);
		else
			System.out.println("l'étudiant n'existe pas!");
		
	}
	
	

	@Override
	public void modifierEtudiant(int id, String nouveauNom, float nouvelleNote) {
		// TODO Auto-generated method stub
		Etudiant e= rechercherParID(id);
		if (e!=null) {
			e.setNom(nouveauNom);
			e.setNote(nouvelleNote);
	
		}
		else 
			System.out.println("l etudiant n existe pas");
	}

	@Override
	public void listerEtudiants() {
		System.out.println(liste);
		
	}

	@Override
	public void trierEtudiants() {
		liste.stream().sorted((o1,o2)->o1.getNom().compareTo(o2.getNom())).forEach(t->System.out.println(t));
		
	}

	@Override
	public void sauvegraderCSV() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lireCSV() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Etudiant rechercherParID(int id) {
//		for(Etudiant e:liste)
//			if(e.getId()==id)
//				return e;
//		return null;
		Optional<Etudiant> p=
				liste.
				stream().
				filter(e->e.getId()==id).
				findFirst();
		if(p.isPresent())
			return p.get();
		return null;
		
	}

}
