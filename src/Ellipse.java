import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

public class Ellipse extends FigureColoree {

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
		if(this.ellipse != null) {
			return this.ellipse.contains(x, y);
		}
		return false;
	}

	@Override
	public void modifierPoints(Point[] points) {
		if(points != null && points.length == this.nbPoints()) {
			Point p1 = points[0];
			Point p2 = points[1];
			Point p3 = points[2];
			if(p1 != null && p2 != null && p3 != null) {
				int distx = Math.abs(p2.rendreX() - p1.rendreX());
				int disty = Math.abs(p3.rendreY() - p1.rendreY());
				tab_mem[0] = p1;
				tab_mem[1] = new Point(p2.rendreX(), p1.rendreY());
				tab_mem[2] = new Point(p1.rendreX(), p3.rendreY());
				this.ellipse = new Ellipse2D.Double(p1.rendreX() - distx, p1.rendreY() - disty, 2*distx, 2*disty);
			}
		}
	}
	
	@Override
	public void affiche(Graphics g) {
		if(g != null) {
			if(tab_mem != null && tab_mem.length == this.nbPoints()) {
				Point p1 = tab_mem[0];
				Point p2 = tab_mem[1];
				Point p3 = tab_mem[2];
				if(p1 != null && p2 != null && p3 != null) {
					int distx = Math.abs(p2.rendreX() - p1.rendreX());
					int disty = Math.abs(p3.rendreY() - p1.rendreY());
					g.setColor(this.couleur);
					g.fillOval(p1.rendreX() - distx, p1.rendreY() - disty, 2*distx, 2*disty);
				}
			}
		}
		super.affiche(g);
	}
}
