import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Classe controleur pour la fabrication des figures
public class FabricantFigures implements MouseListener {

	// Le modele de dessin pour fabriquer les figures
	private DessinModele model;

	/**
	 * Constructeur d'une fabriquant de figures a partir d'un modele de dessin
	 * 
	 * @param model
	 *            Le modele de dessin pour fabriquer les figures
	 */
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
