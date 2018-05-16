// Classe representant une figure coloree de type rectangle
public class Rectangle extends Quadrilatere {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		if(points != null && points.length == this.nbClics()) {
			Point[] nouveaux = new Point[4];
			nouveaux[0] = points[0];
			nouveaux[2] = points[1];
			nouveaux[1] = new Point(points[1].rendreX(), points[0].rendreY());
			nouveaux[3] = new Point(points[0].rendreX(), points[1].rendreY());
			super.modifierPoints(nouveaux);
		}
	}

}
