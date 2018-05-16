import java.awt.Color;
import java.awt.Graphics;

// Classe abstraite representant une figure coloree
public abstract class FigureColoree {

	// Constante entiere correspondant a la taille d'un carre de selection d'une figure coloree
	public final static int TAILLE_CARRE_SELECTION = 9;
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
}
