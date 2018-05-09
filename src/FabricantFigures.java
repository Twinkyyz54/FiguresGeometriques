import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FabricantFigures implements MouseListener {
	
	private DessinModele model;
	
	public FabricantFigures(DessinModele model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.model.ajoutePt(e.getX(), e.getY());
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
