package modele;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

// Classe representant un trace
public class Trace implements Dessinable, Serializable {

	// Liste des traits du trace
	private ArrayList<Trait> traits;
	// Couleur du tarce
	private Color couleur;
	// Epaisseur du trace
	private int epaisseur;

	/**
	 * Constructeur d'un trace a partir de sa couleur et son epaisseur
	 * 
	 * @param couleur
	 *            Couleur du trace
	 * @param epaisseur
	 *            Epaisseur du trace
	 */
	public Trace(Color couleur, int epaisseur) {
		this.traits = new ArrayList<Trait>();
		this.couleur = couleur;
		if(epaisseur < 1)
			epaisseur = 1;
		this.epaisseur = epaisseur;
	}

	/**
	 * Methode permettant d'ajouter un trait a la liste des traits du trace
	 * 
	 * @param t
	 *            Le trait a ajouter
	 */
	public void ajouterTrait(Trait t) {
		if(t != null) {
			this.traits.add(t);
		}
	}

	@Override
	public void affiche(Graphics g) {
		if(g != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(this.couleur);
			g2d.setStroke(new BasicStroke(this.epaisseur));
			for(Trait t : this.traits) {
				g2d.drawLine(t.getX1(), t.getY1(), t.getX2(), t.getY2());
			}
		}
	}

	@Override
	public boolean estDedans(int x, int y) {
		for(Trait t : this.traits) {
			if(t.getX1() != t.getX2()) {
				if((x >= t.getX1() && x <= t.getX2()) || (y >= t.getX2() && y <= t.getX1())) {
					double coeffDir = (t.getY2() - t.getY1()) / (t.getX2() - t.getX1());
					double ordOrigin = t.getY1() - (coeffDir * t.getX1());
					if(Math.abs((coeffDir * x + ordOrigin) - y) <= epaisseur + 3) {
						return true;
					}
				}
			} else {
				if(Math.abs(t.getX1() - x) <= epaisseur + 3) {
					if((y >= t.getY1() && y <= t.getY2()) || (y <= t.getY1() && y >= t.getY2())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Methode accesseur permettant de recuperer l'epaisseur du trace
	 * 
	 * @return L'epaisseur du trace
	 */
	public int getEpaisseur() {
		return this.epaisseur;
	}

	/**
	 * Methode accesseur permettant de recuperer la couleur du trace
	 * 
	 * @return La couleur du trace
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * Methode accesseur permettant de recuperer la liste des traits du trace
	 * 
	 * @return La liste des traits du trace
	 */
	public ArrayList<Trait> getTraits() {
		return this.traits;
	}
}
