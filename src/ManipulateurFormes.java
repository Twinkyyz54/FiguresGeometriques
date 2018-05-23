import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class ManipulateurFormes implements MouseListener, MouseMotionListener {

	// Le modele de dessin pour effectuer les changement lors de la manipulation des figures
	private DessinModele model;
	// Abscisse de la derniere position de la souris lors d'une manipulation
	private int lastX;
	// Ordonnee de la derniere position de la souris lors d'une manipulation
	private int lastY;
	// Indice de carre de selection qui est selectionne ou -1 si il n'y en a pas
	private int selection;

	/**
	 * Constructeur d'un manipulation de formes geometriques
	 * 
	 * @param model
	 *            Le modele de dessin dont on veut manipuler les figures
	 */
	public ManipulateurFormes(DessinModele model) {
		this.model = model;
		this.selection = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.model != null && this.model.getType() == 2 && this.model.getFigureSelectionnee() != null) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				FigureColoree fc = this.model.getFigureSelectionnee();
				int x = e.getX();
				int y = e.getY();
				if(fc.tab_mem != null && fc.tab_mem.length > 0) {
					int dx = x - this.lastX;
					int dy = y - this.lastY;
					Point[] nouveaux = new Point[fc.tab_mem.length];
					for(int i = 0; i < nouveaux.length; i++) {
						Point ancien = fc.tab_mem[i];
						nouveaux[i] = new Point(dx + ancien.rendreX(), dy + ancien.rendreY());
					}
					this.model.changerPoints(fc, nouveaux);
				}
				this.lastX = x;
				this.lastY = y;
			} else if(SwingUtilities.isRightMouseButton(e) && this.selection != -1) {
				int x = e.getX();
				int y = e.getY();
				int dx = x - lastX;
				int dy = y - lastY;
				this.model.transformerFigureSelectionnee(dx, dy, this.selection);
				this.lastX = x;
				this.lastY = y;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.model != null && this.model.getType() == 2) {
			int button = e.getButton();
			if(button == MouseEvent.BUTTON1) {
				int x = e.getX();
				int y = e.getY();
				this.model.selectionnerFigure(x, y);
				if(this.model.getFigureSelectionnee() != null) {
					FigureColoree selection = this.model.getFigureSelectionnee();
					this.lastX = x;
					this.lastY = y;
					ArrayList<FigureColoree> lfg = this.model.getLfg();
					lfg.remove(selection);
					lfg.add(selection);
					this.model.setLfg(lfg);
				}
			} else if(button == MouseEvent.BUTTON3 && this.model.getFigureSelectionnee() != null) {
				FigureColoree selection = this.model.getFigureSelectionnee();
				int x = e.getX();
				int y = e.getY();
				this.selection = selection.carreDeSelection(x, y);
				this.lastX = x;
				this.lastY = y;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.selection = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
