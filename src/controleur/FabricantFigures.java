package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import modele.DessinModele;

// Classe controleur pour la fabrication des figures
public class FabricantFigures implements MouseListener, MouseMotionListener {

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
		// On verifie que le modele n'est pas null
		if(this.model != null && e.getButton() == MouseEvent.BUTTON1) {
			// On ajoute le point clique pour la creation de figure en cours
			this.model.ajouterPoint(e.getX(), e.getY(), false);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// On verfie que le modele n'est pas null
		if(this.model != null) {
			// On ajoute le point pour la previsualisation a la figure en cours
			this.model.ajouterPoint(e.getX(), e.getY(), true);
		}
	}
}
