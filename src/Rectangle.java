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
			
		}
	}

}
