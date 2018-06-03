package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;

public class ManipulateurFormes implements MouseListener, MouseMotionListener {

	// Le modele de dessin pour effectuer les changement lors de la manipulation des figures
	private DessinModele model;
	// Abscisse de la derniere position de la souris lors d'une manipulation
	private int lastX;
	// Ordonnee de la derniere position de la souris lors d'une manipulation
	private int lastY;
	// Indice de carre de selection qui est selectionne ou -1 si il n'y en a pas
	private int selection;

	/**
	 * Constructeur d'un manipulation de formes geometriques
	 * 
	 * @param model
	 *            Le modele de dessin dont on veut manipuler les figures
	 */
	public ManipulateurFormes(DessinModele model) {
		this.model = model;
		this.selection = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// On verifie que le modele n'est pas null et qu'il y a une figure selectionne
		if(this.model != null && this.model.getFigureSelectionnee() != null) {
			// Si le bouton est le bouton gauche de la souris
			if(SwingUtilities.isLeftMouseButton(e)) {
				// On recupere la figure selectionnee et on deplace tous les points de la figure par la difference entre la position precedente de la souris et l'actuelle
				FigureColoree fc = this.model.getFigureSelectionnee();
				int x = e.getX();
				int y = e.getY();
				Point[] tab_mem = fc.getTabMem();
				if(tab_mem != null && tab_mem.length > 0) {
					int dx = x - this.lastX;
					int dy = y - this.lastY;
					Point[] nouveaux = new Point[tab_mem.length];
					for(int i = 0; i < nouveaux.length; i++) {
						Point ancien = tab_mem[i];
						nouveaux[i] = new Point(dx + ancien.rendreX(), dy + ancien.rendreY());
					}
					this.model.changerPoints(fc, nouveaux);
				}
				// On oublie pas de definir la position precedente de la souris avec l'actuelle
				this.lastX = x;
				this.lastY = y;
				// Si le bouton clique est le bouton droit de la souris et qu'un carre de selectio est selectionne
			} else if(SwingUtilities.isRightMouseButton(e) && this.selection != -1) {
				// On deplacement le carre de selection selectionne de la difference entre le position precedente de la souris et l'actuelle
				int x = e.getX();
				int y = e.getY();
				int dx = x - lastX;
				int dy = y - lastY;
				this.model.transformerFigureSelectionnee(dx, dy, this.selection);
				// On oublie pas de definir la position precedente de la souris avec l'actuelle
				this.lastX = x;
				this.lastY = y;
			}
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
		// On verifie que le modele n'est pas null
		if(this.model != null) {
			int button = e.getButton();
			// Si le bouton clique est le bouton gauche
			if(button == MouseEvent.BUTTON1) {
				// On selectionne la figure se trouvant au coordonnees de la souris
				int x = e.getX();
				int y = e.getY();
				this.model.selectionnerFigure(x, y);
				// Si il y en a une de selectionnee
				if(this.model.getFigureSelectionnee() != null) {
					// On defini la position precedente de la souris avec l'actuelle
					FigureColoree selection = this.model.getFigureSelectionnee();
					this.lastX = x;
					this.lastY = y;
					// On supprime la figure selectionnee de la liste des figures puis on la rajoute de nouveau pour qu'elle repasse en premier plan
					ArrayList<FigureColoree> lfg = this.model.getLfg();
					lfg.remove(selection);
					lfg.add(selection);
					this.model.setLfg(lfg);
				}
				// Si le bouton de la souris clique est le bouton droite et qu'il y a une figure selectionnee
			} else if(button == MouseEvent.BUTTON3 && this.model.getFigureSelectionnee() != null) {
				// On voit si un carre de selection est selectionne au coordonnees de la souris
				FigureColoree selection = this.model.getFigureSelectionnee();
				int x = e.getX();
				int y = e.getY();
				this.selection = selection.carreDeSelection(x, y);
				// Si un carre de selection est selectionne
				if(this.selection != -1) {
					// On defini la position precedente de la souris avec l'actuelle
					this.lastX = x;
					this.lastY = y;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// On redefini le carre de selection selectionne a -1 pour dire qu'il n'y en a pas ou plus
		this.selection = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
