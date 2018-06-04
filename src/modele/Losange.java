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
			// Si le tableau de points est de la taille du nombre de clics necessaires
			if(points.length == this.nbClics()) {
				Point p1 = points[0];
				Point p2 = points[1];
				// On verifie que les deux points pour la construction du losange ne sont pas null
				if(p1 != null && p2 != null) {
					// On construit le losange a partir de ces deux points
					int diffx = p2.rendreX() - p1.rendreX();
					int diffy = p2.rendreY() - p1.rendreY();
					Point[] nouveaux = new Point[this.nbPoints()];
					nouveaux[0] = p1;
					nouveaux[1] = p2;
					nouveaux[2] = new Point(p2.rendreX() + diffx, p1.rendreY());
					nouveaux[3] = new Point(p2.rendreX(), p1.rendreY() - diffy);
					super.modifierPoints(nouveaux);
				}
				// Sinon si la taille du tableau de points correspond au nombre de points necessaires
			} else if(points.length == this.nbPoints()) {
				// On modifie les points du losange avec les nouveaux
				super.modifierPoints(points);
			}
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
