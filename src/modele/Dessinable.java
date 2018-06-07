package modele;

import java.awt.Graphics;

public interface Dessinable {

	/**
	 * Methode permettant d'afficher sur un graphique la figure dessinable
	 * 
	 * @param g
	 *            Le graphique sur lequel on va dessiner la figure
	 */
	public void affiche(Graphics g);

	/**
	 * Methode permettant de savoir si une abscisse et une ordonee d'un point sont dans la figure dessinable ou non
	 * 
	 * @param x
	 *            Abscisse du point
	 * @param y
	 *            Ordonnee du point
	 * @return Vrai si le point est dans la figure dessinable, faux sinon
	 */
	public boolean estDedans(int x, int y);
}
