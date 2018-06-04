package modele;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

// Classe representant une figure coloree de type cercle
public class Cercle extends FigureColoree {

	@Override
	public int nbPoints() {
		return 2;
	}

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		// Si le tableau de point n'est pas null et qu'il contient le nombre de points necessaires
		if(points != null && points.length == this.nbPoints()) {
			Point centre = points[0];
			Point extremite = points[1];
			// On verifie que les deux points pour creer le cercle ne sont pas null
			if(centre != null && extremite != null) {
				// On ajoute ces deux points au tableau de points
				tab_mem[0] = centre;
				tab_mem[1] = extremite;
			}
		}
	}

	@Override
	public boolean estDedans(int x, int y) {
		Point centre = tab_mem[0];
		Point perim = tab_mem[1];
		// On verifie que les deux points du cercle existent
		if(centre != null && perim != null) {
			// On recupere le rayon du cercle et on regarde si le points est de le cercle
			double rayon = centre.distance(perim);
			return (x - centre.rendreX()) * (x - centre.rendreX()) + (y - centre.rendreY()) * (y - centre.rendreY()) <= (rayon * rayon);
		}
		return false;
	}

	@Override
	public void affiche(Graphics g) {
		// Si le graphique fournis n'est pas null
		if(g != null) {
			Point centre = tab_mem[0];
			Point perim = tab_mem[1];
			// On verifie que les deux points du cercle existent
			if(centre != null && perim != null) {
				// On dessine le cercle comme etant un oval de largeur et hauteur identique au double du rayon
				int dist = (int) Math.round(centre.distance(perim));
				g.setColor(this.couleur);
				if(this.pleine) {
					g.fillOval(centre.rendreX() - dist, centre.rendreY() - dist, dist * 2, dist * 2);
				} else {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(this.epaisseur));
					g2d.drawOval(centre.rendreX() - dist, centre.rendreY() - dist, dist * 2, dist * 2);
				}
				super.affiche(g);
			}
		}
	}
}
