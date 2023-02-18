package battleship;

// TODO: how can I better combine the board class with the point class?
// this will also be relevant for chess
public class Coordinate {
	private int x;
	private int y;
	
	// Is it bad news to set the coordinate to random values?
	// Is there a deafult value that one could expect instead?
	public Coordinate() {
		x = (int) Math.round(Math.random() * (Utility.getSize() - 1));
		y = (int) Math.round(Math.random() * (Utility.getSize() - 1));
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isValid() {
		return x > 0 && x <= Utility.getSize() && y > 0 && y <= Utility.getSize();
	}
	
	public void shiftCoord(int shiftX, int shiftY) {
		x += shiftX;
		y += shiftY;
	}
	
}
