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
			// Si le nombre de points cliques correspond au nombre de clics necessaires
			if(points.length == this.nbClics()) {
				Point p1 = points[0];
				Point p2 = points[1];
				// Si les deux points necessaires a la construciton du carre ne sont pas null
				if(p1 != null && p2 != null) {
					// On construit le carre a partir de ces deux points
					Point[] nouveaux = new Point[this.nbPoints()];
					int diffx = p2.rendreX() - p1.rendreX();
					int diffy = p2.rendreY() - p1.rendreY();
					nouveaux[0] = p1;
					nouveaux[1] = p2;
					nouveaux[2] = new Point(p2.rendreX() + diffy, p2.rendreY() - diffx);
					nouveaux[3] = new Point(p1.rendreX() + diffy, p1.rendreY() - diffx);
					super.modifierPoints(nouveaux);
				}
				// Sinon si le nombre de points fournis correspond au nombre de points de la figure on la construit a partir
			} else if(points.length == this.nbPoints()) {
				super.modifierPoints(points);
			}
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
