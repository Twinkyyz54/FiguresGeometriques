import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Polygone extends FigureColoree {
	
	private Polygon p;
	
	public Polygone() {
		super();
		p = new Polygon();
	}
	
	@Override
	public void affiche(Graphics g) {
		super.affiche(g);
		g.drawPolygon(p);
	}
	
	@Override
	public int nbClics() {
		return this.p.npoints;
	}
	
	@Override
	public void modifierPoints(Point[] points) {
		this.p = new Polygon();
		for(Point point : points) {
			this.p.addPoint(point.rendreX(), point.rendreY());
		}
	}
}
