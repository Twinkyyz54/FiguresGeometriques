import java.awt.Color;

import javax.swing.JPanel;

public class PanneauChoix extends JPanel {
	
	private DessinModele dessin;
	
	public PanneauChoix(DessinModele dessin) {
		this.dessin = dessin;
	}
	
	private FigureColoree creeFigure(int index) {
		switch(index) {
		case 0:
			return new Quadrilatere();
		case 1:
			return new Triangle();
		case 3:
			return new Rectangle();
		default:
			return null;
		}
	}
	
	private Color determineCouleur(int index) {
		switch(index) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.CYAN;
		case 3:
			return Color.DARK_GRAY;
		case 4:
			return Color.GRAY;
		case 5:
			return Color.GREEN;
		case 6:
			return Color.LIGHT_GRAY;
		case 7:
			return Color.MAGENTA;
		case 8:
			return Color.ORANGE;
		case 9:
			return Color.PINK;
		case 10:
			return Color.RED;
		case 11:
			return Color.WHITE;
		case 12:
			return Color.YELLOW;
		default:
			return null;
		}
	}
}
