import java.awt.Color;
import java.awt.Graphics;

public abstract class FigureColoree {
	
	public final static int TAILLE_CARRE_SELECTION = 9;
	protected Point[] tab_mem;
	private boolean selected;
	public Color couleur;
	
	public FigureColoree() {
		tab_mem = new Point[this.nbPoints()];
		couleur = Color.BLACK;
	}
	
	public abstract int nbPoints();
	
	public abstract int nbClics();
	
	public abstract void modifierPoints(Point[] points);
	
	public abstract boolean estDedans(int x, int y);
	
	public void affiche(Graphics g) {
		if(g != null && selected) {
			g.setColor(Color.BLACK);
			for(Point p : tab_mem) {
				g.fillRect(p.rendreX() - TAILLE_CARRE_SELECTION / 2, p.rendreY() - TAILLE_CARRE_SELECTION / 2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
			}
		}
	}
	
	public void selectionne() {
		this.selected = true;
	}
	
	public void deSelectionne() {
		this.selected = false;
	}
	
	public void changeCouleur(Color couleur) {
		if(couleur != null) {
			this.couleur = couleur;
		}
	}
}
