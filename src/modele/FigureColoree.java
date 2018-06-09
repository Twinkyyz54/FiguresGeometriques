package modele;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

// Classe abstraite representant une figure coloree
public abstract class FigureColoree implements Dessinable, Serializable {

	// Constante statique entiere correspondant a la taille d'un carre de selection d'une figure coloree
	private final static int TAILLE_CARRE_SELECTION = 7;
	// Constante statique entiere correspondant a la peripherie du carre de selection d'une figure coloree
	private final static int PERIPHERIE_CARRE_SELECTION = 5;
	// Tableau de point correspondants au points de memorisation de la figure coloree
	protected Point[] tab_mem;
	// Booleen indiquant si la figure coloree est selectionnee ou non
	private boolean selected;
	// Couleur correspondant a la couleur de la figure coloree
	protected Color couleur;
	// Booleen indiquant si la figure coloree est plein ou non
	protected boolean pleine;
	// Entier correspondant a l'epaisseur des contours de la figure
	protected int epaisseur;

	/**
	 * Constructeur d'une figure coloree
	 */
	public FigureColoree() {
		this.tab_mem = new Point[this.nbPoints()];
		this.couleur = Color.BLACK;
		this.epaisseur = 2;
	}

	/**
	 * Methode permettant de recuperer le nombre de points de memorisation de la figure
	 * 
	 * @return Le nombre de points de memorisation de la figure
	 */
	public abstract int nbPoints();

	/**
	 * Methode permettant de recuperer le nombre de clics necessaires pour creer la figure
	 * 
	 * @return Le nombre de clics necessaires pour creer la figure
	 */
	public abstract int nbClics();

	/**
	 * Methode permettant de modifier le tableau de points de memorisation de la figure a partir d'un autre
	 * 
	 * @param points
	 *            Le nouveau tableau de points de memorisation de la figure
	 */
	public abstract void modifierPoints(Point[] points);

	@Override
	public void affiche(Graphics g) {
		// Si le graphique fournis n'est pas null
		if(g != null) {
			// On recupere le nombre de point ajoutes a la figure
			int nbpoints = 0;
			while(nbpoints < this.tab_mem.length && this.tab_mem[nbpoints] != null) {
				nbpoints++;
			}
			// Si il y en a au moins 1 point et que la figure n'est pas completee
			if(nbpoints > 1 && nbpoints < this.nbPoints()) {
				// On affiche les carres de selection des points et on les relies par des traits
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(this.epaisseur));
				Point precedent = this.tab_mem[0];
				for(int i = 1; i < nbpoints; i++) {
					Point actuel = this.tab_mem[i];
					g2d.drawLine(precedent.rendreX(), precedent.rendreY(), actuel.rendreX(), actuel.rendreY());
					precedent = actuel;
				}
				// Sinon si la figure est completee et quelle est selectionnee
			} else if(selected) {
				// On affiche les carres de selection de la figure
				g.setColor(Color.BLACK);
				for(Point p : tab_mem) {
					g.fillRect(p.rendreX() - TAILLE_CARRE_SELECTION / 2, p.rendreY() - TAILLE_CARRE_SELECTION / 2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
				}
			}
		}
	}

	/**
	 * Methode permettant de selectionner la figure
	 */
	public void selectionne() {
		this.selected = true;
	}

	/**
	 * Methode permettant de deselecitonner la figure
	 */
	public void deSelectionne() {
		this.selected = false;
	}

	/**
	 * Methode permettant de changer la couleur de la figure a partir d'une autre
	 * 
	 * @param couleur
	 *            La nouvelle couleur de la figure
	 */
	public void changeCouleur(Color couleur) {
		// Si la couleur n'est pas null on change la couleur de la figure
		if(couleur != null) {
			this.couleur = couleur;
		}
	}

	/**
	 * Methode permettant de savoir si un des carre de selection de la figure est selectionne
	 * 
	 * @param x
	 *            L'abscisse du point clique pour selectionner un carre de selection
	 * @param y
	 *            L'ordonnee du point clique pour selecitonner un carre de selection
	 * @return L'indice du carre de selection selectionne ou -1 sinon il n'y en a pas
	 */
	public int carreDeSelection(int x, int y) {
		// On parcourt tous les points de la figure et on recupere l'indice du premier selectionne
		for(int i = 0; i < tab_mem.length; i++) {
			Point p = tab_mem[i];
			if(p != null) {
				if(Math.abs(p.rendreX() - x) <= PERIPHERIE_CARRE_SELECTION && Math.abs(p.rendreY() - y) <= PERIPHERIE_CARRE_SELECTION) {
					return i;
				}
			}
		}
		// Sinon on retourne -1
		return -1;
	}

	/**
	 * Methode permettant de transformer une figure (agrandissement...) a partir d'un carre de selection
	 * 
	 * @param dx
	 *            Le deplacement en abscisse du carre de selection
	 * @param dy
	 *            Le deplacement en ordonnee du carre de selection
	 * @param idxcarre
	 *            L'indice du carre de selection deplacer
	 */
	public void transformation(int dx, int dy, int idxcarre) {
		// On verifie que l'indice pour la transformation est bien dans le tableau de points
		if(idxcarre < tab_mem.length && idxcarre >= 0) {
			Point p = tab_mem[idxcarre];
			// On verifie que point selectionne n'est pas null
			if(p != null) {
				// On translate le point et on reconstruit la figure en consequence
				p.translation(dx, dy);
				this.modifierPoints(this.tab_mem);
			}
		}
	}

	/**
	 * Methode accesseur permettant de recuperer la couleur de la figure coloree
	 * 
	 * @return La couleur de la figure coloree
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * Methode accesseur permettant de recuperer le tableau des points de memorisation de la figure
	 * 
	 * @return Le tableau des points de memorisation de la figure coloree
	 */
	public Point[] getTabMem() {
		return this.tab_mem;
	}

	/**
	 * Methode permettant de redefinir le fait que la figure soit pleine ou non
	 * 
	 * @param pleine
	 *            Booleen indiquant si la figure devient pleine ou non
	 */
	public void mettrePleine(boolean pleine) {
		this.pleine = pleine;
	}

	/**
	 * Methode accesseur permettant de savoir si la figure est pleine ou non
	 * 
	 * @return Vrai si la figure est pleine, faux sinon
	 */
	public boolean estPleine() {
		return this.pleine;
	}

	/**
	 * Methode accesseur permettant de recuperer l'epaisseur du contour de la figure
	 * 
	 * @return L'epaisseur du contour de la figure
	 */
	public int getEpaisseur() {
		return this.epaisseur;
	}

	/**
	 * Methode permettant de changer l'epaisseur du contour de la figure
	 * 
	 * @param epaisseur
	 *            La nouvelle epaisseur du contour de la figure
	 */
	public void changerEpaisseur(int epaisseur) {
		// On verifie que l'epaisseur est strictement superieur a 0
		if(epaisseur > 0) {
			this.epaisseur = epaisseur;
		}
	}
}
