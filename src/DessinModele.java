import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class DessinModele extends Observable {
	
	private ArrayList<FigureColoree> lfg;
	private FigureColoree figureEnCours;
	private int nbClic;
	private Point[] points_Cliques;

	
	public DessinModele() {
		this.lfg = new ArrayList<FigureColoree>();
		this.points_Cliques = new Point[0];
	}
	
	public void ajoute(FigureColoree figure) {
		if(figure != null) {
			this.lfg.add(figure);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void changeCoul(FigureColoree figure, Color couleur) {
		if(figure != null && this.lfg.contains(figure)) {
			figure.changeCouleur(couleur);
			this.setChanged();
			this.countObservers();
		}
	}
	
	public void construit(FigureColoree figure) {
		this.figureEnCours = figure;
		this.nbClic = 0;
		this.points_Cliques = new Point[0];
	}
	
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
				this.lfg.add(this.figureEnCours);
				this.setChanged();
				this.notifyObservers();
				this.points_Cliques = new Point[0];
				this.nbClic = 0;
				this.figureEnCours = null;
			} else {
				this.points_Cliques = copie;
				this.nbClic++;
			}
		}
	}
	
	public int getNbClic() {
		return this.nbClic;
	}
	
	public void setNbClic(int nbClic) {
		this.nbClic = nbClic;
	}
	
	public ArrayList<FigureColoree> getLfg() {
		return this.lfg;
	}
	
	public FigureColoree getFigureEnCours() {
		return this.figureEnCours;
	}
	
	public void setFigureEnCours(FigureColoree figureEnCours) {
		this.figureEnCours = figureEnCours;
	}
	
	public void setLfg(ArrayList<FigureColoree> lfg) {
		if(lfg == null) {
			this.lfg.clear();
		} else {
			this.lfg = lfg;
		}
		this.setChanged();
		this.notifyObservers();
	}

}
