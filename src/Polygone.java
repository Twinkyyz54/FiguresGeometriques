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
		if(g != null) {	
			g.drawPolygon(p);
		}
	}
	
	@Override
	public int nbClics() {
		return this.p.npoints;
	}
	
	@Override
	public void modifierPoints(Point[] points) {
		if(points != null) {
			this.p = new Polygon();
			for(int i = 0; i < points.length; i++) {
				Point point = points[i];
				this.p.addPoint(point.rendreX(), point.rendreY());
				tab_mem[i] = point;
			}
		}
	}
}
