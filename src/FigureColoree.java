import java.awt.Color;
import java.awt.Graphics;

public abstract class FigureColoree {
	
	public final static int TAILLE_CARRE_SELECTION = 9;
	protected Point[] tab_mem;
	private boolean selected;
	private Color couleur;
	
	public abstract int nbPoints();
	
	public abstract int nbClics();
	
	public abstract void modifierPoints(Point[] points);
	
	public void affiche(Graphics g) {
		g.setColor(Color.BLACK);
		for(Point p : tab_mem) {
			g.drawRect(p.rendreX() - TAILLE_CARRE_SELECTION / 2, p.rendreY() - TAILLE_CARRE_SELECTION / 2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
		}
	}
	
	public void selectionne() {
		selected = true;
	}
	
	public void deSelectionne() {
		selected = false;
	}
	
	public void changeCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
}
