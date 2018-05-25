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
import modele.Trait;

// Classe representant le panel destine a afficher les dessins de l'utilisateur
public class VueDessin extends JPanel implements Observer {

	// Liste des figures colorees a dessiner sur le panel
	private ArrayList<FigureColoree> lfg;
	// Liste des traits colorees a dessiner sur le panel
	private ArrayList<Trait> traits;
	
	public VueDessin() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int) screen.getWidth(), (int) screen.getHeight()));
	}

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
}
