package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class EtudiantFrame extends JFrame{
	private JLabel nomLabel=new JLabel("Nom");
	private JLabel noteLabel=new JLabel("Note");
	
	private JTextField nomJTextField=new JTextField(10);
	private JTextField noteJTextField=new JTextField(10);
	
	private JButton ajouterButton=new JButton("Ajouter");

	public EtudiantFrame() throws HeadlessException {
		super();
		this.ajouterComposantes();
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterComposantes() {
		JPanel p1=new JPanel();
		p1.add(nomLabel); p1.add(nomJTextField);
		
		TitledBorder etchedBorder = BorderFactory.createTitledBorder(null, "my ttile");
		p1.setBorder(etchedBorder);
		
		JPanel p2=new JPanel();
		p2.add(noteLabel); p2.add(noteJTextField);
		
		JPanel p3=new JPanel();
		p3.add(ajouterButton); 
		
		this.add(p1,BorderLayout.NORTH); this.add(p2,BorderLayout.CENTER); this.add(p3,BorderLayout.SOUTH);
	}

	public JLabel getNomLabel() {
		return nomLabel;
	}

	public void setNomLabel(JLabel nomLabel) {
		this.nomLabel = nomLabel;
	}

	public JLabel getNoteLabel() {
		return noteLabel;
	}

	public void setNoteLabel(JLabel noteLabel) {
		this.noteLabel = noteLabel;
	}

	public JTextField getNomJTextField() {
		return nomJTextField;
	}

	public void setNomJTextField(JTextField nomJTextField) {
		this.nomJTextField = nomJTextField;
	}

	public JTextField getNoteJTextField() {
		return noteJTextField;
	}

	public void setNoteJTextField(JTextField noteJTextField) {
		this.noteJTextField = noteJTextField;
	}

	public JButton getAjouterButton() {
		return ajouterButton;
	}

	public void setAjouterButton(JButton ajouterButton) {
		this.ajouterButton = ajouterButton;
	}
	
	
	

}
