package modele;

// Classe representant une figure coloree de type rectangle
public class Rectangle extends Quadrilatere {

	@Override
	public int nbClics() {
		return 2;
	}

	@Override
	public void modifierPoints(Point[] points) {
		// Si le tableau de points n'est pas null
		if(points != null) {
			// Si le tableau de points et de la taille du nombre de clics necessaires
			if(points.length == this.nbClics()) {
				Point p1 = points[0];
				Point p2 = points[1];
				// On verifie que les deux points permettant la construction ne sont pas null
				if(p1 != null && p2 != null) {
					// On construit la figure a partir d'eux
					Point[] nouveaux = new Point[4];
					nouveaux[0] = p1;
					nouveaux[2] = p2;
					nouveaux[1] = new Point(p2.rendreX(), p1.rendreY());
					nouveaux[3] = new Point(p1.rendreX(), p2.rendreY());
					super.modifierPoints(nouveaux);
				}
				// Si le tableau contient deja tous les points pour afficher
			} else if(points.length == this.nbPoints()) {
				// On construit la figure avec ces points
				super.modifierPoints(points);
			}
		}
	}

	@Override
	public void transformation(int dx, int dy, int idxcarre) {
		// Si l'indice fourni correspond bien a des indices des points du tableau
		if(idxcarre < tab_mem.length && idxcarre >= 0) {
			Point p1 = tab_mem[0];
			Point p2 = tab_mem[2];
			// On verifie que les deux points permettant la construction du nouveau rectangle ne sont pas null
			if(p1 != null && p2 != null) {
				// On recupere l'indice de p1 si l'indice et 0 ou 3, celui de p2 sinon
				int indicex = ((idxcarre % 3) == 0) ? 0 : 2;
				// On recupere l'indice de p1 si l'indice est 0 ou 1, celui de p2 sinon
				int indicey = (idxcarre < 2) ? 0 : 2;
				// On incremente l'abscisse de p1 ou p2 de dx
				tab_mem[indicex].incrementerX(dx);
				// On increment l'ordonnee de p1 ou p2 de dy
				tab_mem[indicey].incrementerY(dy);
				// On reconstruit le rectangle avec les nouvelles coordonees de p1 et p2
				this.modifierPoints(new Point[] { p1, p2 });
			}
		}
	}
}
