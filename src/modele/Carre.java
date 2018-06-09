package modele;

// Classe representant une figure de type carre
public class Carre extends Quadrilatere {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		// On verifie que la tableau de points n'est pas null
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
				points = new Point[this.nbPoints()];
				int diffx = p2.rendreX() - p1.rendreX();
				int diffy = p2.rendreY() - p1.rendreY();
				points[0] = p1;
				points[1] = p2;
				points[2] = new Point(p2.rendreX() + diffy, p2.rendreY() - diffx);
				points[3] = new Point(p1.rendreX() + diffy, p1.rendreY() - diffx);
			}
			super.modifierPoints(points);
		}
	}

	@Override
	public void transformation(int dx, int dy, int idxcarre) {
		// Si l'indice fournis est dans le tableau de points
		if(idxcarre < tab_mem.length && idxcarre >= 0) {
			// On recupere les deux points qui permettent la construction de la figure
			Point p1 = tab_mem[0];
			Point p2 = tab_mem[1];
			// On verifie qu'ils ne sont pas null
			if(p1 != null && p2 != null) {
				// On modifie ces points pour obtenir la figure attendu apres transformation
				Point modif = (idxcarre % 3) == 0 ? p1 : p2;
				modif.incrementerX(dx);
				modif.incrementerY(dy);
				if(idxcarre > 1) {
					int sens = (idxcarre & 1) == 0 ? 1 : -1;
					p1.incrementerX(sens * -dy);
					p2.incrementerX(sens * -dy);
					p1.incrementerY(sens * dx);
					p2.incrementerY(sens * dx);
				}
				// On reconstruit la figure a partir des points modifies
				this.modifierPoints(new Point[] { p1, p2 });
			}
		}
	}
}
