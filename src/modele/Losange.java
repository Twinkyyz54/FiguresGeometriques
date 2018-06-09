package modele;

// Classe representant une figure de type losange
public class Losange extends Quadrilatere {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		// On verifie que le tableau de points n'est pas null
		if(points != null) {
			// On compte le nombre de points ajoutes
			int nbPoints = 0;
			while(nbPoints < points.length && points[nbPoints] != null) {
				++nbPoints;
			}
			// Si le nombre de points correspond au nombre de clics necessaires
			if(nbPoints == this.nbClics()) {
				// On modifie le tableau de points pour pouvoir construire la figure
				Point p1 = points[0];
				Point p2 = points[1];
				int diffx = p2.rendreX() - p1.rendreX();
				int diffy = p2.rendreY() - p1.rendreY();
				points = new Point[this.nbPoints()];
				points[0] = p1;
				points[1] = p2;
				points[2] = new Point(p2.rendreX() + diffx, p1.rendreY());
				points[3] = new Point(p2.rendreX(), p1.rendreY() - diffy);
			}
			super.modifierPoints(points);
		}
	}

	@Override
	public void transformation(int dx, int dy, int idxcarre) {
		// On verifie que l'indice du carre est compris dans le tableau des points
		if(idxcarre < tab_mem.length && idxcarre >= 0) {
			Point p1 = tab_mem[0];
			Point p2 = tab_mem[1];
			// On verifie que les deux points du losange pour la transformation ne sont pas null
			if(p1 != null && p2 != null) {
				// On modifie le point les points pour la transformation
				Point modif = (idxcarre & 1) == 0 ? p1 : p2;
				modif.incrementerX((idxcarre == 2) ? -dx : dx);
				modif.incrementerY((idxcarre == 3) ? -dy : dy);
				this.modifierPoints(new Point[] { p1, p2 });
			}
		}
	}
}
