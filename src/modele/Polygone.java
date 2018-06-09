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
		// On verifie que la graphique n'est pas null
		if(g != null) {
			// On s'assure que le polygone n'est pas null pour l'afficher
			if(this.p != null) {
				// On affiche le polygone sur le graphique
				g.setColor(this.couleur);
				if(this.pleine) {
					g.fillPolygon(this.p);
				} else {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(this.epaisseur));
					g.drawPolygon(this.p);
				}
			}
			super.affiche(g);
		}
	}

	@Override
	public int nbClics() {
		return this.nbPoints();
	}

	@Override
	public void modifierPoints(Point[] points) {
		// On verifie que le tableau de points n'est pas null et qu'il contient moins ou autant de points encessaires pour la construction
		if(points != null && points.length <= this.nbPoints()) {
			// On reinitialise le tableau de points et le polygone
			this.tab_mem = new Point[this.nbPoints()];
			this.p = new Polygon();
			// On compte le nombre de points ajoutes au polygone
			int nbPoints = 0;
			while(nbPoints < points.length && points[nbPoints] != null) {
				++nbPoints;
			}
			// Si le nombre de points est identique au nombre de points pour construire la figure
			if(nbPoints == this.nbPoints()) {
				// On construit la figure en ajoutant les points au tableau et au polygone
				for(int i = 0; i < points.length; i++) {
					Point point = points[i];
					this.tab_mem[i] = point;
					this.p.addPoint(point.rendreX(), point.rendreY());
				}
			} else {
				// On ajoute seulement els points au tableau
				for(int i = 0; i < points.length; i++) {
					this.tab_mem[i] = points[i];
				}
			}
		}
	}
}
