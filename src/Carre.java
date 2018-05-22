
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
}
