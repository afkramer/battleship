package battleship;

public class Ship {
	
	private String type;
	private int size;
	private int marker;
	private boolean isSunk = false;
	
	protected Ship(String type, int size, int marker) {
		this.type = type;
		this.size = size;
		this.marker = marker;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMarker() {
		return marker;
	}
	
	public boolean getIsSunk() {
		return isSunk;
	}
	
	public void setIsSunk(boolean isSunk) {
		this.isSunk = isSunk;
	}
		
}
