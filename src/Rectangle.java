// Classe representant une figure coloree de type rectangle
public class Rectangle extends Quadrilatere {

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
					Point[] nouveaux = new Point[4];
					nouveaux[0] = p1;
					nouveaux[2] = p2;
					nouveaux[1] = new Point(p2.rendreX(), p1.rendreY());
					nouveaux[3] = new Point(p1.rendreX(), p2.rendreY());
					super.modifierPoints(nouveaux);
				}
			} else if(points.length == this.nbPoints()) {
				super.modifierPoints(points);
			}
		}
	}

}
