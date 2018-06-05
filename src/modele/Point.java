package modele;

import java.io.Serializable;

// Classe representant un point dans un espace a deux dimensions
public class Point implements Serializable {

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

	/**
	 * Methode permettant de faire une roation du point d'un certain angle autour d'un point d'origine avec un distance finale differente
	 * 
	 * @param origine
	 *            Le point autour du quel on doit faire la rotation
	 * @param angle
	 *            L'angle en radian de la rotation
	 * @param distanceFinale
	 *            La distance finale qui devra separer l'origine du point
	 */
	public void rotation(Point origine, double angle, double distanceFinale) {
		if(origine != null) {
			double angleInitial = this.recupererAngle(origine);
			double angleFinal = angleInitial + angle;
			this.x = (int) Math.round(origine.rendreX() + Math.cos(angleFinal) * distanceFinale);
			this.y = (int) Math.round(origine.rendreY() - Math.sin(angleFinal) * distanceFinale);
		}
	}

	/**
	 * Methode permettant de recuperer l'angle du point par rapport a un point d'origine en radian
	 * 
	 * @param origine
	 *            Le point d'origine pour calculer l'angle
	 * @return L'angle du point par rapport a l'origine
	 */
	public double recupererAngle(Point origine) {
		if(origine != null) {
			double dist = this.distance(origine);
			if(dist > 0) {
				double diffX = this.rendreX() - origine.rendreX();
				double diffY = this.rendreY() - origine.rendreY();
				double angle = diffY <= 0 ? Math.acos(diffX / dist) : -Math.acos(diffX / dist);
				return angle;
			}
		}
		return 0;
	}
}
