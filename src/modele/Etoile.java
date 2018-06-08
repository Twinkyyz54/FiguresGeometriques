package modele;

public class Etoile extends Polygone {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public int nbPoints() {
		return 10;
	}

	@Override
	public void modifierPoints(Point[] points) {
		if(points != null) {
			if(points.length == this.nbClics()) {
				Point centre = points[0];
				Point extremite = points[1];
				if(centre != null && extremite != null) {
					double dist = centre.distance(extremite);
					if(dist > 0) {
						Point[] nouveaux = new Point[10];
						double angleInitial = extremite.recupererAngle(centre);
						double angle = 72 * (Math.PI / 180);
						double ajout = 36 * (Math.PI / 180);
						for(int i = 0; i < 5; i++) {
							nouveaux[2 * i] = new Point(centre.rendreX() + (int) Math.round(Math.cos(angleInitial + i * angle) * dist), centre.rendreY() + (int) Math.round(Math.sin(-(angleInitial + i * angle)) * dist));
							nouveaux[2 * i + 1] = new Point(centre.rendreX() + (int) Math.round(Math.cos(angleInitial + ajout + i * angle) * (dist / 3)), centre.rendreY() + (int) Math.round(Math.sin(-(angleInitial + ajout + i * angle)) * (dist / 3)));
						}
						super.modifierPoints(nouveaux);
					}
				}
			} else if(points.length == this.nbPoints()) {
				super.modifierPoints(points);
			}
		}
	}

	@Override
	public void transformation(int dx, int dy, int idxcarre) {
		// Si l'indice fournis est dans le tableau de points
		if(idxcarre < tab_mem.length && idxcarre >= 0) {
			// On recupere les points qui permettent la construction de la figure
			Point extremite = tab_mem[0];
			Point selec = tab_mem[idxcarre];
			// On verifie qu'ils ne sont pas null
			if(extremite != null && selec != null) {
				double moyX = 0;
				double moyY = 0;
				int nbpoints = 0;
				for(Point p : this.tab_mem) {
					if(p != null) {
						moyX += p.rendreX();
						moyY += p.rendreY();
						++nbpoints;
					}
				}
				moyX /= nbpoints;
				moyY /= nbpoints;
				Point centre = new Point((int) Math.round(moyX), (int) Math.round(moyY));
				Point selecFinal = new Point(selec.rendreX() + dx, selec.rendreY() + dy);
				double distFinal = (idxcarre & 1) == 0 ? centre.distance(selecFinal) : centre.distance(selecFinal) * 3;
				double diffAngle = selecFinal.recupererAngle(centre) - selec.recupererAngle(centre);
				extremite.rotation(centre, diffAngle, distFinal);
				this.modifierPoints(new Point[] { centre, extremite });
			}
		}
	}
}
