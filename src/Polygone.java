import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Polygone extends FigureColoree {
	
	private Polygon p;
	
	public Polygone() {
		super();
		this.p = new Polygon();
	}
	
	@Override
	public boolean estDedans(int x, int y) {
		return this.p.contains(x, y);
	}
	
	@Override
	public void affiche(Graphics g) {
		super.affiche(g);
		if(g != null) {	
			g.setColor(this.couleur);
			g.fillPolygon(this.p);
		}
	}
	
	@Override
	public int nbClics() {
		return this.nbPoints();
	}
	
	@Override
	public void modifierPoints(Point[] points) {
		if(points != null && points.length == this.nbPoints()) {
			this.p = new Polygon();
			for(int i = 0; i < points.length; i++) {
				Point point = points[i];
				this.p.addPoint(point.rendreX(), point.rendreY());
				tab_mem[i] = point;
			}
		}
	}
}
