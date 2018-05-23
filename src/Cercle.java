import java.awt.Graphics;

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
		if(points != null && points.length == this.nbPoints()) {
			tab_mem[0] = points[0];
			tab_mem[1] = points[1];
		}
	}

	@Override
	public boolean estDedans(int x, int y) {
		if(tab_mem != null && tab_mem.length == this.nbClics()) {
			Point centre = tab_mem[0];
			Point perim = tab_mem[1];
			if(centre != null && perim != null) {
				double rayon = centre.distance(perim);
				return (x - centre.rendreX()) * (x - centre.rendreX()) + (y - centre.rendreY()) * (y - centre.rendreY()) <= (rayon * rayon);
			}
		}
		return false;
	}
	
	@Override
	public void affiche(Graphics g) {
		if(tab_mem != null && tab_mem.length == this.nbPoints()) {
			Point centre = tab_mem[0];
			int dist = (int) Math.round(centre.distance(tab_mem[1]));
			g.setColor(this.couleur);
			g.fillOval(centre.rendreX() - dist, centre.rendreY() - dist, dist * 2, dist * 2);
		}
		super.affiche(g);
	}

}
