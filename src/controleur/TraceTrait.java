package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import modele.DessinModele;
import modele.Trait;

// Classe controleur permettant de tracer les traits sur le dessin
public class TraceTrait implements MouseListener, MouseMotionListener {

	// Le modele de dessin avec lequel on va construire les traits
	private DessinModele model;
	// L'abscisse de la derniere position de la souris
	private int lastX;
	// L'ordonne de la derniere position de la souris
	private int lastY;

	/**
	 * Constructeur de controleur de tracage de traits
	 * 
	 * @param model
	 *            Le modele pour construrie les traits
	 */
	public TraceTrait(DessinModele model) {
		this.model = model;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Si il y a un modele pour la construction du trait et que le bouton de la souris clique est le clic gauche
		if(this.model != null && SwingUtilities.isLeftMouseButton(e)) {
			// Alors on ajoute un trait au modele entre la derniere position de la souris et l'actuelle avec la couleur de la figure en cours
			this.model.ajouterTrait(new Trait(this.model.getFigureEnCours().getCouleur(), this.lastX, this.lastY, e.getX(), e.getY(), this.model.getFigureEnCours().getEpaisseur()));
			// On redefini la position precedente de la souris avec l'actuelle
			this.lastX = e.getX();
			this.lastY = e.getY();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Si il y a un modele pour la construction du trait
		if(this.model != null) {
			int button = e.getButton();
			// Si le bouton clique est le clic gauche
			if(button == MouseEvent.BUTTON1) {
				// On redefini la position precedente de la souris avec l'actuelle
				this.lastX = e.getX();
				this.lastY = e.getY();
			}
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
