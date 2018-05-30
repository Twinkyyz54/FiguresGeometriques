package modele;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;

// Classe asbtraite representant une figure coloree de type polygone
public abstract class Polygone extends FigureColoree {

	// Le polygon de java.awt representant la classe Polygon
	private Polygon p;

	/**
	 * Constructeur d'une figure coloree de type polygone
	 */
	public Polygone() {
		super();
		this.p = new Polygon();
	}

	@Override
	public boolean estDedans(int x, int y) {
		// Si le polygon representant la figure n'est pas null
		if(p != null) {
			// On retourne le fait que le point soit dans le polygon ou non
			return this.p.contains(x, y);
		}
		// Sinon on retourne faux
		return false;
	}

	@Override
	public void affiche(Graphics g) {
		if(g != null) {
			g.setColor(this.couleur);
			if(this.pleine) {
				g.fillPolygon(this.p);
			} else {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(4));
				g.drawPolygon(this.p);
			}
		}
		super.affiche(g);
	}

	@Override
	public int nbClics() {
		return this.nbPoints();
	}

	@Override
	public void modifierPoints(Point[] points) {
		// On verifie que le tableau de points n'est pas null et que sa taille correspond bien au nombre de points de la figure
		if(points != null && points.length == this.nbPoints()) {
			// On recontruit le polygon et le tableau de points a partir des points fournis
			Polygon nvpoly = new Polygon();
			for(int i = 0; i < points.length; i++) {
				Point point = points[i];
				// Si un point du tableau est null on ne modifie pas les points du tableau precedent
				if(point == null)
					return;
				nvpoly.addPoint(point.rendreX(), point.rendreY());
			}
			this.p = nvpoly;
			this.tab_mem = points;
		}
	}
}
