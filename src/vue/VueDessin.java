package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.DessinModele;
import modele.Dessinable;
import modele.FigureColoree;
import modele.Point;
import modele.Trace;
import modele.Trait;

// Classe representant le panel destine a afficher les dessins de l'utilisateur
public class VueDessin extends JPanel implements Observer {

	// Liste des figures colorees a dessiner sur le panel
	private ArrayList<Dessinable> lfg;
	// Figure en cours du model
	private Dessinable enCours;

	public VueDessin() {
		this.lfg = new ArrayList<Dessinable>();
		this.setBackground(Color.WHITE);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Si l'objet observable est bien un modele
		if(o instanceof DessinModele) {
			// On recupere les constructions de ce modele et on redessine la vue dessin
			DessinModele model = (DessinModele) o;
			this.lfg = model.getLfg();
			this.enCours = model.getFigureEnCours();
			this.repaint();
			this.revalidate();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// On affiche toutes le figures
		for(Dessinable figure : this.lfg) {
			figure.affiche(g);
		}
		// On affiche aussi la figure en cours en previsualisation si il y en a une
		if(this.enCours != null) {
			this.enCours.affiche(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int maxX = 0;
		int maxY = 0;
		// On retourne la position de la plus grande abscisse et ordonnee parmis les figures et traits
		for(Dessinable figure : this.lfg) {
			if(figure instanceof FigureColoree) {
				FigureColoree fc = (FigureColoree) figure;
				for(Point p : fc.getTabMem()) {
					if(p.rendreX() > maxX) {
						maxX = p.rendreX();
					}
					if(p.rendreY() > maxY) {
						maxY = p.rendreY();
					}
				}
			} else if(figure instanceof Trace) {
				Trace trace = (Trace) figure;
				for(Trait t : trace.getTraits()) {
					if(t.getX1() > maxX) {
						maxX = t.getX1();
					}
					if(t.getX2() > maxX) {
						maxX = t.getX2();
					}
					if(t.getY1() > maxY) {
						maxY = t.getY1();
					}
					if(t.getY2() > maxY) {
						maxY = t.getY2();
					}
				}
			}
		}
		return new Dimension(maxX + 1, maxY + 1);
	}
}
