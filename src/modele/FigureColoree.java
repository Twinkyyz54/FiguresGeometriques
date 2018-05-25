package modele;

import java.awt.Color;
import java.awt.Graphics;

// Classe abstraite representant une figure coloree
public abstract class FigureColoree {

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

	/**
	 * Constructeur d'une figure coloree
	 */
	public FigureColoree() {
		tab_mem = new Point[this.nbPoints()];
		couleur = Color.BLACK;
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

	/**
	 * Methode permettant de savoir si une abscisse et une ordonee d'un point sont dans la figure coloree
	 * 
	 * @param x
	 *            Abscisse du point
	 * @param y
	 *            Ordonnee du point
	 * @return Vrai si le point est dans la figure coloree, faux sinon
	 */
	public abstract boolean estDedans(int x, int y);

	/**
	 * Methode permettant d'afficher sur un graphique la figure coloree
	 * 
	 * @param g
	 *            Le graphique sur lequel on va dessiner la figure coloree
	 */
	public void affiche(Graphics g) {
		if(g != null && selected) {
			g.setColor(Color.BLACK);
			for(Point p : tab_mem) {
				g.fillRect(p.rendreX() - TAILLE_CARRE_SELECTION / 2, p.rendreY() - TAILLE_CARRE_SELECTION / 2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
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
		if(tab_mem != null) {
			for(int i = 0; i < tab_mem.length; i++) {
				Point p = tab_mem[i];
				if(p != null) {
					if(Math.abs(p.rendreX() - x) <= PERIPHERIE_CARRE_SELECTION && Math.abs(p.rendreY() - y) <= PERIPHERIE_CARRE_SELECTION) {
						return i;
					}
				}
			}
		}
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
		if(tab_mem != null && idxcarre < tab_mem.length && idxcarre >= 0) {
			Point p = tab_mem[idxcarre];
			if(p != null) {
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
}
