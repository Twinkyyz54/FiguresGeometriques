package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

// Classe representant un trait
public class Trait implements Serializable {

	// Couleur du trait
	private Color couleur;
	// Abscisse du premier point du trait
	private int x1;
	// Ordonnee du premier point du trait
	private int y1;
	// Abscisse du second point du trait
	private int x2;
	// Ordonnee du second point du trait
	private int y2;

	/**
	 * Methode accesseur permettant de recuperer la couleur du trait
	 * 
	 * @return La couleur du trait
	 */
	public Color getColor() {
		return couleur;
	}

	/**
	 * Methode accesseur permettant de recuperer l'abscisse du premier point du trait
	 * 
	 * @return L'abscisse du premier point du trait
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Methode accesseur permettant de recuperer l'ordonnee du premier point du trait
	 * 
	 * @return L'ordonnee du premier point du trait
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Methode accesseur permettant de recuperer l'abscisse du second point du trait
	 * 
	 * @return L'abscisse du second point du trait
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Methode accesseur permettant de recuperer l'ordonnee du second point du trait
	 * 
	 * @return L'ordonnee du second point du trait
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Constructeur du trait à partir de deux point et de sa couleur
	 * 
	 * @param couleur
	 *            Couleur du trait
	 * @param x1
	 *            Abscisse du premier point du trait
	 * @param y1
	 *            Ordonnee du premier point du trait
	 * @param x2
	 *            Abscisse du second point du trait
	 * @param y2
	 *            Ordonnee du second point du trait
	 */
	public Trait(Color couleur, int x1, int y1, int x2, int y2) {
		this.couleur = couleur;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Methode permettant d'afficher le point sur un graphique
	 * 
	 * @param g
	 *            Le graphique sur lequel on veut afficher le point
	 */
	public void affiche(Graphics g) {
		if(g != null) {
			g.setColor(this.couleur);
			g.drawLine(this.x1, this.y1, this.x2, this.y2);
		}
	}
}
