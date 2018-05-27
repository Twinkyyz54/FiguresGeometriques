package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.DessinModele;

// Classe controleur pour la fabrication des figures
public class FabricantFigures implements MouseListener {

	// Le modele de dessin pour fabriquer les figures
	private DessinModele model;

	/**
	 * Constructeur d'une fabriquant de figures a partir d'un modele de dessin
	 * 
	 * @param model
	 *            Le modele de dessin pour fabriquer les figures
	 */
	public FabricantFigures(DessinModele model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// On verifie que le modele n'est pas null et qu'il est bien en mode creation de figure
		if(this.model != null && this.model.getType() == 0) {
			// On ajoute le point cliquer pour la creation de figure en cours
			this.model.ajouterPoint(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
