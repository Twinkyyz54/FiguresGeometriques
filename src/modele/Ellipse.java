package modele;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

// Classe representant une ellipse
public class Ellipse extends FigureColoree {

	// Ellipse2D representant la figure
	private Ellipse2D ellipse;

	@Override
	public int nbPoints() {
		return 3;
	}

	@Override
	public int nbClics() {
		return 3;
	}

	@Override
	public boolean estDedans(int x, int y) {
		// On verifie que l'ellipse representant la figure n'est pas null
		if(this.ellipse != null) {
			// On retourne le fait que le point soit dans l'ellipse ou non
			return this.ellipse.contains(x, y);
		}
		return false;
	}

	@Override
	public void modifierPoints(Point[] points) {
		// Si le tableau de point n'est pas null et qu'il contient le bon nombre de points
		if(points != null && points.length == this.nbPoints()) {
			Point p1 = points[0];
			Point p2 = points[1];
			Point p3 = points[2];
			// On verifie que les 3 points necessaires a la construction de l'ellipse ne sont pas nulles
			if(p1 != null && p2 != null && p3 != null) {
				// On construit l'ellipse a partir de ces 3 points
				int distx = Math.abs(p2.rendreX() - p1.rendreX());
				int disty = Math.abs(p3.rendreY() - p1.rendreY());
				tab_mem[0] = p1;
				tab_mem[1] = new Point(p2.rendreX(), p1.rendreY());
				tab_mem[2] = new Point(p1.rendreX(), p3.rendreY());
				this.ellipse = new Ellipse2D.Double(p1.rendreX() - distx, p1.rendreY() - disty, 2 * distx, 2 * disty);
			}
		}
	}

	@Override
	public void affiche(Graphics g) {
		// Si le graphique fournis n'est pas null
		if(g != null) {
			Point p1 = tab_mem[0];
			Point p2 = tab_mem[1];
			Point p3 = tab_mem[2];
			// On verifie que les 3 points de l'ellipse ne sont pas null
			if(p1 != null && p2 != null && p3 != null) {
				// On affiche l'ellipse sur le graphique
				int distx = Math.abs(p2.rendreX() - p1.rendreX());
				int disty = Math.abs(p3.rendreY() - p1.rendreY());
				g.setColor(this.couleur);
				if(this.pleine) {
					g.fillOval(p1.rendreX() - distx, p1.rendreY() - disty, 2 * distx, 2 * disty);
				} else {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(4));
					g.drawOval(p1.rendreX() - distx, p1.rendreY() - disty, 2 * distx, 2 * disty);
				}
				super.affiche(g);
			}
		}
	}
}
