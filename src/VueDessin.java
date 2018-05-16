import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

// Classe representant le panel destine a afficher les dessins de l'utilisateur
public class VueDessin extends JPanel implements Observer {

	// Liste des figures colorees a dessiner sur le panel
	private ArrayList<FigureColoree> lfg;

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof DessinModele) {
			DessinModele dessin = (DessinModele) o;
			this.lfg = dessin.getLfg();
			for(MouseListener ml : this.getListeners(MouseListener.class)) {
				this.removeMouseListener(ml);
			}
			for(MouseMotionListener mml : this.getListeners(MouseMotionListener.class)) {
				this.removeMouseMotionListener(mml);
			}
			if(dessin.getType() == 0) {
				FabricantFigures ff = new FabricantFigures(dessin);
				this.addMouseListener(ff);
			} else if(dessin.getType() == 2) {
				ManipulateurFormes mf = new ManipulateurFormes(dessin);
				this.addMouseListener(mf);
				this.addMouseMotionListener(mf);
			}
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(lfg != null) {
			for(FigureColoree figure : lfg) {
				figure.affiche(g);
			}
		}
	}
}
