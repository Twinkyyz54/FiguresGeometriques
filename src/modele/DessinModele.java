package modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

// Classe representant un modele de dessin pour creer et modifier des figures colorees
public class DessinModele extends Observable {

	// Liste des figures crees
	private ArrayList<Dessinable> lfg;
	// Figure coloree en cours de creation
	private FigureColoree figureEnCours;
	// Nombre de clics deja effectues pour la creation de la figure en cours
	private int nbClic;
	// Tableau des points deja cliques pour creer la figure coloree
	private Point[] points_Cliques;
	// Type d'action en cours pour le modele de dessin (0 = creation figure, 1 = Tracee a main levee, 2 = Manipulation)
	private int type;
	// La figure coloree qui est selectionnee
	private FigureColoree figureSelectionnee;

	/**
	 * Constructeur d'un modele de dessin
	 */
	public DessinModele() {
		this.lfg = new ArrayList<Dessinable>();
		this.points_Cliques = new Point[0];
	}

	/**
	 * Methode permettant d'ajouter une figure a la liste des celles deja construites
	 * 
	 * @param figure
	 *            La figure a ajouter a la liste
	 */
	public void ajouter(Dessinable figure) {
		// On verifie que la figure n'est pas null
		if(figure != null) {
			// On l'ajoute a la liste des figure et on informe la vue
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
	public void changerCouleur(FigureColoree figure, Color couleur) {
		// On verifie que la figure n'est pas null et qu'elle appartient a la liste des figures
		if(figure != null && (this.lfg.contains(figure) || this.figureEnCours == figure)) {
			// On change sa couleur et on informe la vue
			figure.changeCouleur(couleur);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de changer les points d'une figure deja construite
	 * 
	 * @param figure
	 *            La figure deja construite dont on veut changer les points
	 * @param points
	 *            Les nouveaux points de la figure
	 */
	public void changerPoints(FigureColoree figure, Point[] points) {
		// On verifie que la figure n'est pas null et qu'elle appartient a la liste des figures
		if(figure != null && (this.lfg.contains(figure) || this.figureEnCours == figure)) {
			// On change ses points et on informe la vue
			figure.modifierPoints(points);
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
		// On verifie que la figure n'est pas null
		if(figure != null) {
			// On declare la figure en cours comme etant celle fournis et on reinitialise les clics
			this.figureEnCours = figure;
			this.nbClic = 0;
			this.points_Cliques = new Point[figure.nbClics()];
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant d'ajouter un point a la construction de la figure en cours
	 * 
	 * @param x
	 *            L'abscisse du point a ajouter
	 * @param y
	 *            L'ordonnee du point a ajouter
	 * @param previsualisation
	 *            Boolean indiquant si il s'agit d'un point pour la previsualisation ou non
	 */
	public void ajouterPoint(int x, int y, boolean previsualisation) {
		// On verifie qu'il y a bien une figure en cours
		if(this.figureEnCours != null) {
			// On ajoute le point dans al liste des points cliques
			this.points_Cliques[this.nbClic] = new Point(x, y);
			// On modifie la figure avec les nouveaux points
			this.figureEnCours.modifierPoints(this.points_Cliques);
			// Si ce n'est pas un point de visualisation
			if(!previsualisation) {
				// On increment le nombre de points cliques de 1
				++this.nbClic;
				// Si on a atteint le nombre de clics necessaires pour creer la figure
				if(this.nbClic == this.figureEnCours.nbClics()) {
					// On ajoute la figure a la liste des figures
					this.ajouter(this.figureEnCours);
					// On tente d'instantier une figure du meme type que l'ancienne avec la meme couleur, remplissage...
					try {
						FigureColoree nouvelle = this.figureEnCours.getClass().newInstance();
						nouvelle.changeCouleur(this.figureEnCours.couleur);
						nouvelle.mettrePleine(this.figureEnCours.pleine);
						nouvelle.changerEpaisseur(this.figureEnCours.epaisseur);
						this.figureEnCours = nouvelle;
						this.points_Cliques = new Point[this.figureEnCours.nbClics()];
					} catch(InstantiationException | IllegalAccessException e) {
						this.figureEnCours = null;
						this.points_Cliques = new Point[0];
					}
					// On reinitialise le nombre de clics a 0 dans tous les cas
					this.nbClic = 0;
				}
			} else {
				// Sinon on informe la vue de la modification de la figure en cours
				this.setChanged();
				this.notifyObservers();
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
	 * Methode accesseur permettant de recuperer la liste de figures crees par le modele de dessin
	 * 
	 * @return La liste des figures crees par le modele de dessin
	 */
	public ArrayList<Dessinable> getLfg() {
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
	public void setLfg(ArrayList<Dessinable> lfg) {
		// On verifie que la liste de figure n'est pas null
		if(lfg == null) {
			// Si elle l'est en vide la liste des figures crees
			this.lfg.clear();
		} else {
			this.lfg = lfg;
		}
		// On informe la vue du changement
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
		// Si il y a deja un figure selectionnee
		if(this.figureSelectionnee != null) {
			// On la deselectionne
			this.figureSelectionnee.deSelectionne();
		}
		this.figureSelectionnee = null;
		int i = this.lfg.size() - 1;
		// On parcours les figures construites en partant des dernieres construites et on selectionne celle qu'il faut
		while(i >= 0 && this.figureSelectionnee == null) {
			if(this.lfg.get(i) instanceof FigureColoree) {
				FigureColoree figure = (FigureColoree) this.lfg.get(i);
				if(figure != null && figure.estDedans(x, y)) {
					this.figureSelectionnee = figure;
				}
			}
			--i;
		}
		// Si une figure a etait selectionnee
		if(this.figureSelectionnee != null) {
			// On la selectionne
			this.figureSelectionnee.selectionne();
		}
		// On en informe la vue
		this.setChanged();
		this.notifyObservers();
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
	}

	/**
	 * Methode accesseur permettant de recuperer la figure qui est selectionnee
	 * 
	 * @return La figure selectionnee
	 */
	public FigureColoree getFigureSelectionnee() {
		return this.figureSelectionnee;
	}

	/**
	 * Methode permettant de transformer une figure deja construite pour le dessin
	 * 
	 * @param dx
	 *            Le deplacement horizontal du carre de selection modifie
	 * @param dy
	 *            Le deplacement vertical du carre de selection modifie
	 * @param idxcarre
	 *            L'indice du carre de selection deplace
	 */
	public void transformerFigureSelectionnee(int dx, int dy, int idxcarre) {
		// On verifie qu'il y a bien une figure selectionnee
		if(this.getFigureSelectionnee() != null) {
			// On tranforme la figure selectionnee et on en informe la vue
			this.getFigureSelectionnee().transformation(dx, dy, idxcarre);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de redefinir la figure selectionnee du dessin
	 * 
	 * @param figureSelectionnee
	 *            La nouvelle figure selectionnee
	 */
	public void setFigureSelectionnee(FigureColoree figureSelectionnee) {
		// On modifie la figure selectionne par celle fournis et on en informe la vue
		this.figureSelectionnee = figureSelectionnee;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode permettant de mettre un figure appartenant au modele en pleine ou non
	 * 
	 * @param fc
	 *            La figure dont on veut changer le fait qu'elle soit pleine ou non
	 * @param pleine
	 *            Boolean indiquant si la figure doit etre pleine ou non
	 */
	public void mettrePleine(FigureColoree fc, boolean pleine) {
		// On verifie que la figure n'est pas null et que la liste des figures la contient
		if(fc != null && (this.lfg.contains(fc) || this.figureEnCours == fc)) {
			// On change son remplissage et on en informe le contructeur
			fc.mettrePleine(pleine);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de changer l'epaisseur du contour d'une figure du modele
	 * 
	 * @param fc
	 *            La figure dont on veut changer l'epaisseur du contour
	 * @param epaisseur
	 *            La nouvelle epaisseur du contour
	 */
	public void changerEpaisseur(FigureColoree fc, int epaisseur) {
		// On verifie que la figure n'est pas null et que la liste des figures la contient
		if(fc != null && (this.lfg.contains(fc) || this.figureEnCours == fc)) {
			// On change l'epaisseur de la figure et on en informe la vue
			fc.changerEpaisseur(epaisseur);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant d'ajouter un trait a un trace du dessin
	 * 
	 * @param trace
	 *            Le trace au quel on veut ajouter le trait
	 * @param t
	 *            Le trait a ajouter
	 */
	public void ajouterTrait(Trace trace, Trait t) {
		// On verifie que le trace n'est pas null et que la liste des figures le contient
		if(trace != null && this.lfg.contains(trace)) {
			// On ajoute le trait au trace et on informe la vue du changement
			trace.ajouterTrait(t);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode permettant de supprimer la premiere figure a une position donnee
	 * 
	 * @param x
	 *            Abscisse de la position
	 * @param y
	 *            Ordonnee de la position
	 */
	public void supprimerFigure(int x, int y) {
		// On parcours la liste des figures et on recupere la premier en partant de la fin dont la position est dedans
		Dessinable supprime = null;
		int i = this.lfg.size() - 1;
		while(i >= 0 && supprime == null) {
			Dessinable dessin = this.lfg.get(i);
			if(dessin.estDedans(x, y)) {
				supprime = dessin;
			}
			i--;
		}
		// Si on en a trouver une alors on la supprime et on en informe la vue
		if(supprime != null) {
			this.lfg.remove(supprime);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methode pour supprimer tous les dessins
	 */
	public void supprimerDessins() {
		// Si la liste des dessins n'est pas vide
		if(!this.lfg.isEmpty()) {
			// On vide la liste des dessins et on en informe la vue
			this.lfg.clear();
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methodes pour supprimer tous les traces
	 */
	public void supprimerTraces() {
		// On recupere tous les traces construit
		ArrayList<Trace> traces = new ArrayList<Trace>();
		for(Dessinable dessin : this.lfg) {
			if(dessin instanceof Trace) {
				traces.add((Trace) dessin);
			}
		}
		// Si il y a des traces
		if(!traces.isEmpty()) {
			// On supprime les traces et on en informe la vue
			this.lfg.removeAll(traces);
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Methodes pour supprimer toutes els figures colorees
	 */
	public void supprimerFiguresColorees() {
		// On recupere toutes les figures colorees construite
		ArrayList<FigureColoree> figures = new ArrayList<FigureColoree>();
		for(Dessinable dessin : this.lfg) {
			if(dessin instanceof FigureColoree) {
				figures.add((FigureColoree) dessin);
			}
		}
		// Si il y a des figures colorees
		if(!figures.isEmpty()) {
			// On supprime les figures colorees et on en informe la vue
			this.lfg.removeAll(figures);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Methode permettant de reinitiliser la figure en cours en supprimant tous ces points et les points cliques
	 */
	public void reinitialiserFigureEnCours() {
		this.points_Cliques = new Point[this.points_Cliques.length];
		this.figureEnCours.modifierPoints(points_Cliques);
		this.nbClic = 0;
		this.setChanged();
		this.notifyObservers();
	}
}
