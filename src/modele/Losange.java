package modele;

public class Losange extends Quadrilatere {

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
					int diffx = p2.rendreX() - p1.rendreX();
					int diffy = p2.rendreY() - p1.rendreY();
					Point[] nouveaux = new Point[this.nbPoints()];
					nouveaux[0] = p1;
					nouveaux[1] = p2;
					nouveaux[2] = new Point(p2.rendreX() + diffx, p1.rendreY());
					nouveaux[3] = new Point(p2.rendreX(), p1.rendreY() - diffy);
					super.modifierPoints(nouveaux);
				}
			} else if(points.length == this.nbPoints()) {
				super.modifierPoints(points);
			}
		}
	}
	
}
