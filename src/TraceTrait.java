import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TraceTrait implements MouseListener, MouseMotionListener {
	
	private DessinModele model;
	private int lastX;
	private int lastY;
	
	public TraceTrait(DessinModele model) {
		this.model = model;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.model != null && this.model.getType() == 1) {
			this.model.ajouteTrait(new Trait(this.model.getFigureEnCours().couleur, this.lastX, this.lastY, e.getX(), e.getY()));
			this.lastX = e.getX();
			this.lastY = e.getY();
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
		if(this.model != null && this.model.getType() == 1) {
			int button = e.getButton();
			if(button == MouseEvent.BUTTON1) {
				this.lastX = e.getX();
				this.lastY = e.getY();
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
