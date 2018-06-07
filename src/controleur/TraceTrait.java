package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import modele.DessinModele;
import modele.Trace;
import modele.Trait;

// Classe controleur permettant de tracer les traits sur le dessin
public class TraceTrait implements MouseListener, MouseMotionListener {

	// Le modele de dessin avec lequel on va construire les traits
	private DessinModele model;
	// L'abscisse de la derniere position de la souris
	private int lastX;
	// L'ordonne de la derniere position de la souris
	private int lastY;
	// Trace qui est en cours
	private Trace traceEnCours;

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
		// Si il y a un modele pour la construction du trace et que le bouton de la souris clique est le clic gauche et que le trace en cours n'est pas null
		if(this.model != null && SwingUtilities.isLeftMouseButton(e) && this.traceEnCours != null) {
			// Alors on ajoute un trait au trace entre la derniere position de la souris et l'actuelle avec la couleur de la figure en cours
			this.model.ajouterTrait(this.traceEnCours, new Trait(this.lastX, this.lastY, e.getX(), e.getY()));
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
		// Si il y a un modele pour la construction du trace
		if(this.model != null) {
			int button = e.getButton();
			// Si le bouton clique est le clic gauche
			if(button == MouseEvent.BUTTON1) {
				// On redefini la position precedente de la souris avec l'actuelle
				this.lastX = e.getX();
				this.lastY = e.getY();
				// On change le trace en cours et on l'ajoute au dessin
				this.traceEnCours = new Trace(this.model.getFigureEnCours().getCouleur(), this.model.getFigureEnCours().getEpaisseur());
				this.model.ajouter(this.traceEnCours);
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
