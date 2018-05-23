import java.awt.Color;
import java.awt.Graphics;

public class Trait {
	
	private Color couleur;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Color getColor() {
		return couleur;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	public Trait(Color couleur, int x1, int y1, int x2, int y2) {
		this.couleur = couleur;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void affiche(Graphics g) {
		if(g != null) {
			g.setColor(this.couleur);
			g.drawLine(this.x1, this.y1, this.x2, this.y2);
		}
	}
}
