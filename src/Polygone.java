import java.awt.Graphics;
import java.awt.Polygon;

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
		if(p != null) {
			return this.p.contains(x, y);
		}
		return false;
	}

	@Override
	public void affiche(Graphics g) {
		if(g != null) {
			g.setColor(this.couleur);
			g.fillPolygon(this.p);
		}
		super.affiche(g);
	}

	@Override
	public int nbClics() {
		return this.nbPoints();
	}

	@Override
	public void modifierPoints(Point[] points) {
		if(points != null && points.length == this.nbPoints()) {
			Polygon nvpoly = new Polygon();
			Point[] nvtab_mem = new Point[this.nbPoints()];
			for(int i = 0; i < points.length; i++) {
				Point point = points[i];
				if(point == null)
					return;
				nvpoly.addPoint(point.rendreX(), point.rendreY());
				nvtab_mem[i] = point;
			}
			this.p = nvpoly;
			this.tab_mem = nvtab_mem;
		}
	}
}
