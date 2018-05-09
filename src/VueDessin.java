import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueDessin extends JPanel implements Observer {
	
	private ArrayList<FigureColoree> lfg;

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof DessinModele) {
			this.lfg = ((DessinModele) o).getLfg();
			this.repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(lfg != null) {
			for(FigureColoree figure : lfg) {
				figure.affiche(g);
			}
		}
	}
}
