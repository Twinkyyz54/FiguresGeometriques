package vue;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import modele.Trait;

// Classe representant le panel destine a afficher les dessins de l'utilisateur
public class VueDessin extends JPanel implements Observer {

	// Liste des figures colorees a dessiner sur le panel
	private ArrayList<FigureColoree> lfg;
	// Liste des traits colorees a dessiner sur le panel
	private ArrayList<Trait> traits;

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof DessinModele) {
			DessinModele dessin = (DessinModele) o;
			this.lfg = dessin.getLfg();
			this.traits = dessin.getTraits();
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(lfg != null) {
			for(Trait trait : traits) {
				trait.affiche(g);
			}
			for(FigureColoree figure : lfg) {
				figure.affiche(g);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		int maxX = 0;
		int maxY = 0;
		if(this.lfg != null) {
			for(FigureColoree fc : this.lfg) {
				for(Point p : fc.getTabMem()) {
					if(p.rendreX() > maxX) {
						maxX = p.rendreX();
					}
					if(p.rendreY() > maxY) {
						maxY = p.rendreY();
					}
				}
			}
		}
		if(this.traits != null) {
			for(Trait t : this.traits) {
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
		return new Dimension(maxX, maxY);
	}
}
