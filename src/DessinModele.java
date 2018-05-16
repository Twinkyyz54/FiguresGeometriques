import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

// Classe representant un modele de dessin pour creer et modifier des figures colorees
public class DessinModele extends Observable {

	// Liste des figures colorees crees
	private ArrayList<FigureColoree> lfg;
	// Figure coloree en cours de creation
	private FigureColoree figureEnCours;
	// Nombre de clics deja effectues pour la creation de la figure en cours
	private int nbClic;
	// Tableau des points deja cliques pour creer la figure coloree
	private Point[] points_Cliques;
	// Type d'action en cours pour le modele de dessin (0 = creation figure, 1 = Tracee a main levee, 2 = Manipulation)
	private int type;

	/**
	 * Constructeur d'un modele de dessin
	 */
	public DessinModele() {
		this.lfg = new ArrayList<FigureColoree>();
		this.points_Cliques = new Point[0];
	}

	/**
	 * Methode permettant d'ajouter une figure coloree a la liste des celles deja construites
	 * 
	 * @param figure
	 *            La figure a ajouter a la liste
	 */
	public void ajoute(FigureColoree figure) {
		if(figure != null) {
			this.lfg.add(figure);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de changer la couleur d'une figure deja construite
	 * 
	 * @param figure
	 *            La figure dont on veut changer la couleur
	 * @param couleur
	 *            La nouvelle couleur de la figure
	 */
	public void changeCoul(FigureColoree figure, Color couleur) {
		if(figure != null && this.lfg.contains(figure)) {
			figure.changeCouleur(couleur);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de construire une nouvelle figure coloree a partir d'une instance de celle-ci
	 * 
	 * @param figure
	 *            L'instance de la figure coloree a construire
	 */
	public void construit(FigureColoree figure) {
		this.figureEnCours = figure;
		this.nbClic = 0;
		this.points_Cliques = new Point[0];
	}

	/**
	 * Methode permettant d'ajouter un point a la construction de la figure en cours
	 * 
	 * @param x
	 *            L'abscisse du point a ajouter
	 * @param y
	 *            L'ordonnee du point a ajouter
	 */
	public void ajoutePt(int x, int y) {
		if(this.figureEnCours != null) {
			Point[] copie = new Point[this.nbClic + 1];
			int i;
			for(i = 0; i < this.nbClic; i++) {
				copie[i] = this.points_Cliques[i];
			}
			copie[i] = new Point(x, y);
			if(this.nbClic + 1 == this.figureEnCours.nbClics()) {
				this.figureEnCours.modifierPoints(copie);
				this.ajoute(this.figureEnCours);
				this.points_Cliques = new Point[0];
				this.nbClic = 0;
				try {
					FigureColoree nouvelle = this.figureEnCours.getClass().newInstance();
					nouvelle.changeCouleur(this.figureEnCours.couleur);
					this.figureEnCours = nouvelle;
				} catch(InstantiationException | IllegalAccessException e) {
					this.figureEnCours = null;
				}
			} else {
				this.points_Cliques = copie;
				this.nbClic++;
			}
		}
	}

	/**
	 * Methode accesseur permettant de recuper le nombre de clics deja effectues pour construire la figure en cours
	 * 
	 * @return Le nombre de clics deja effectues pour construire la figure en cours
	 */
	public int getNbClic() {
		return this.nbClic;
	}

	/**
	 * Methode permettant de redefinir le de nombre de clics effectues pour construire la figure en cours
	 * 
	 * @param nbClic
	 *            Le nouveau nombre de clics effectues pour constuire la figure en cours
	 */
	public void setNbClic(int nbClic) {
		this.nbClic = nbClic;
	}

	/**
	 * Methode accesseur permettant de recuperer la liste de figures crees par le modele de dessin
	 * 
	 * @return La liste des figures crees par le modele de dessin
	 */
	public ArrayList<FigureColoree> getLfg() {
		return this.lfg;
	}

	/**
	 * Methode accesseur permettant de recuperer la figure en cours de construction
	 * 
	 * @return La figure en cours de construction
	 */
	public FigureColoree getFigureEnCours() {
		return this.figureEnCours;
	}

	/**
	 * Methode permettant de redefinir la figure en cours de construction
	 * 
	 * @param figureEnCours
	 *            La nouvelle figure en cours de construction
	 */
	public void setFigureEnCours(FigureColoree figureEnCours) {
		this.figureEnCours = figureEnCours;
	}

	/**
	 * Methode permettant de redefinir la liste des figures deja construites
	 * 
	 * @param lfg
	 *            La nouvelle liste des figures deja construites
	 */
	public void setLfg(ArrayList<FigureColoree> lfg) {
		if(lfg == null) {
			this.lfg.clear();
		} else {
			this.lfg = lfg;
		}
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode permettant de selectionner la derniere figure construite dont le point se trouve dedans
	 * 
	 * @param x
	 *            L'abscisse du point
	 * @param y
	 *            L'ordonnee du points
	 */
	public void selectionnerFigure(int x, int y) {
		FigureColoree selectionnee = null;
		for(FigureColoree figure : lfg) {
			figure.deSelectionne();
			if(figure.estDedans(x, y)) {
				selectionnee = figure;
			}
		}
		if(selectionnee != null) {
			selectionnee.selectionne();
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode accesseur permettant de recuperer le type d'action en cours du modele dessin
	 * 
	 * @return Le type d'action en cours du modele dessin
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Methode permettant de changer le type d'action en cours pour le modele de dessin
	 * 
	 * @param type
	 *            Le nouveau type d'action
	 */
	public void changerType(int type) {
		this.type = type;
		this.setChanged();
		this.notifyObservers();
	}
}
