package modele;

public class Carre extends Quadrilatere {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		if(points != null) {
			if(points.length == this.nbClics()) {
				Point p1 = points[0];
				Point p2 = points[1];
				if(p1 != null && p2 != null) {
					Point[] nouveaux = new Point[this.nbPoints()];
					int diffx = p2.rendreX() - p1.rendreX();
					int diffy = p2.rendreY() - p1.rendreY();
					nouveaux[0] = p1;
					nouveaux[1] = p2;
					nouveaux[2] = new Point(p2.rendreX() + diffy, p2.rendreY() - diffx);
					nouveaux[3] = new Point(p1.rendreX() + diffy, p1.rendreY() - diffx);
					super.modifierPoints(nouveaux);
				}
			} else if(points.length == this.nbPoints()) {
				super.modifierPoints(points);
			}
		}
	}

	@Override
	public void transformation(int dx, int dy, int idxcarre) {
		if(tab_mem != null && idxcarre < tab_mem.length && idxcarre >= 0) {
			Point p1 = tab_mem[0];
			Point p2 = tab_mem[1];
			if(p1 != null && p2 != null) {
				Point modif = (idxcarre % 3) == 0 ? p1 : p2;
				modif.incrementerX(dx);
				modif.incrementerY(dy);
				if(idxcarre > 1) {
					int direction = (idxcarre & 1) == 0 ? 1 : -1;
					p1.incrementerX(direction * -dy);
					p2.incrementerX(direction * -dy);
					p1.incrementerY(direction * dx);
					p2.incrementerY(direction * dx);
				}
				this.modifierPoints(new Point[] { p1, p2 });
			}
		}
	}
}
