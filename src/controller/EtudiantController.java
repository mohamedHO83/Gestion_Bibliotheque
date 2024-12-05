package controller;

import javax.swing.JOptionPane;

import model.Etudiant;
import model.EtudiantModel;
import view.EtudiantFrame;

public class EtudiantController {
	private EtudiantModel model=new EtudiantModel();
	private EtudiantFrame view= new EtudiantFrame();
	
	public void clickAjouter() {
		String nom=view.getNomJTextField().getText();
		float note=Float.parseFloat(view.getNoteJTextField().getText());
		Etudiant e=new Etudiant(nom,note);
		model.ajouterEtudiant(e);
		JOptionPane.showMessageDialog(view, e.toString());
		model.listerEtudiants();
		

	}
	public EtudiantController() {
		view.getAjouterButton().addActionListener(e->clickAjouter());
	}

}
