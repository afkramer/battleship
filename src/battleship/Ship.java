package battleship;

public class Ship {
	private String type;
	private int size;
	private int[][] coordinates;
	private int marker;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int[][] getCoordinates(){
		return coordinates;
	}
	
	public void setCoordinates(int[][] coordinates) {
		this.coordinates = coordinates;
	}
	
	public int getMarker() {
		return marker;
	}
	
	public void setMarker(int marker) {
		this.marker = marker;
	}
	
	/**
	 * Changes the ship's hit coordinate from board location coordinates to -1, -1
	 * 
	 * @param coordinate	Contains x, y values for the coordinate to be hit
	 */
	public void hitCoordinate(int[] coordinate) {
		for (int x = 0; x < coordinates.length; x++) {
			if(coordinates[x][0] == coordinate[0] && coordinates[x][1] == coordinate[1]) {
				coordinates[x][0] = -1;
				coordinates[x][1] = -1;
			}
		}
	}
	
	/**
	 * Returns whether all coordinates of the ship have been hit.
	 * 
	 * @return	true if the ship has been sunk, false if the ship is still afloat
	 */
	public boolean isSunk() {
		boolean allCoordsHit = true;
		for (int x = 0; x < coordinates.length; x++) {
			for (int y = 0; y < coordinates[x].length; y++) {
				if (coordinates[x][y] != -1) {
					allCoordsHit = false;
				}
			}
		}
		return allCoordsHit;
	}
	
	
}
