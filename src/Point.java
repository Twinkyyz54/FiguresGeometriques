// Classe representant un point dans un espace a deux dimensions
public class Point {

	// Abscisse du point
	private int x;
	// Ordonnee du point
	private int y;

	/**
	 * Constructeur permettant de creer un point a partir de ces coordonnees
	 * 
	 * @param x
	 *            Coordonnee de l'abscisse du point
	 * @param y
	 *            Coordonnee de l'ordonnee du point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Methode permetant de calculer la distance du point par rapport a un autre
	 * 
	 * @param p
	 *            Autre point dont on veut trouver la distance le separant
	 * @return La distance du point par rapport a l'autre
	 */
	public double distance(Point p) {
		if(p == null)
			return -1;
		return Math.sqrt((this.x - p.rendreX()) * (this.x - p.rendreX()) + (this.y - p.rendreY()) * (this.y - p.rendreY()));
	}

	/**
	 * Methode accesseur permettant de recuperer l'abscisse du point
	 * 
	 * @return L'abscisse du point
	 */
	public int rendreX() {
		return this.x;
	}

	/**
	 * Methode accesseur permettant de recuperer l'ordonnee du point
	 * 
	 * @return L'ordonnee du point
	 */
	public int rendreY() {
		return this.y;
	}

	/**
	 * Methode permettant d'ajouter une valeur de deplacement a l'abscisse du point
	 * 
	 * @param dx
	 *            La valeur de deplacement a ajouter a l'abscisse
	 */
	public void incrementerX(int dx) {
		this.x += dx;
	}

	/**
	 * Methode permettant d'ajouter un valeur de deplacement a l'ordonnee du point
	 * 
	 * @param dy
	 *            La valeur de deplacement a ajouter a l'ordonnee
	 */
	public void incrementerY(int dy) {
		this.y += dy;
	}

	/**
	 * Methode permettant de modifier l'abscisse du point
	 * 
	 * @param x
	 *            La nouvelle abscisse du point
	 */
	public void modifierX(int x) {
		this.x = x;
	}

	/**
	 * Methode permettant de modifier l'ordonnee du point
	 * 
	 * @param y
	 *            La nouvelle ordonnee du point
	 */
	public void modifierY(int y) {
		this.y = y;
	}

	/**
	 * Methode de translation permettant d'ajouter une valeur de deplacement a l'abscisse et a l'ordonne du point
	 * 
	 * @param dx
	 *            Valeur de deplacement a ajouter a l'abscisse du point
	 * @param dy
	 *            Valeur de deplacement a ajouter a l'ordonne du point
	 */
	public void translation(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
}
