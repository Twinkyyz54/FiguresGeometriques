import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ManipulateurFormes implements MouseListener, MouseMotionListener {

	private DessinModele model;
	private int lastX;
	private int lastY;

	public ManipulateurFormes(DessinModele model) {
		this.model = model;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.model != null && this.model.getType() == 2 && this.model.getFigureSelectionnee() != null) {
			FigureColoree fc = this.model.getFigureSelectionnee();
			int x = e.getX();
			int y = e.getY();
			if(fc.tab_mem != null && fc.tab_mem.length > 0) {
				ArrayList<FigureColoree> lfg = this.model.getLfg();
				lfg.remove(fc);
				int dx = x - this.lastX;
				int dy = y - this.lastY;
				Point[] nouveaux = new Point[fc.tab_mem.length];
				for(int i = 0; i < nouveaux.length; i++) {
					Point ancien = fc.tab_mem[i];
					nouveaux[i] = new Point(dx + ancien.rendreX(), dy + ancien.rendreY());
				}
				fc.modifierPoints(nouveaux);
				lfg.add(fc);
				this.model.setLfg(lfg);
			}
			this.lastX = x;
			this.lastY = y;
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
				this.lastX = x;
				this.lastY = y;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
