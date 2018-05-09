
public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point p) {
		return Math.sqrt((this.x - p.rendreX()) * (this.x - p.rendreX()) + (this.y - p.rendreY()) * (this.y - p.rendreY()));
	}
	
	public int rendreX() {
		return this.x;
	}
	
	public int rendreY() {
		return this.y;
	}
	
	public void incrementerX(int dx) {
		this.x += dx;
	}
	
	public void incrementerY(int dy) {
		this.y += dy;
	}
	
	public void modifierX(int x) {
		this.x = x;
	}
	
	public void modifierY(int y) {
		this.y = y;
	}
	
	public void translation(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
}
