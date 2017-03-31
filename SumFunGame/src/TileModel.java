import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;


public class TileModel extends Observable {

	private TileModel north;
	private TileModel east;
	private TileModel south;
	private TileModel west;
	private TileModel northeast;
	private TileModel northwest;
	private TileModel southeast;
	private TileModel southwest;
	private int number;
	private boolean isBlank;

	// Constructor takes a boolean as a parameter that specifies if it is a
	// blank tile
	public TileModel() {
		Random rand = new Random();
		north = null;
		east = null;
		south = null;
		west = null;
		northeast = null;
		northwest = null;
		southeast = null;
		southwest = null;
		number = rand.nextInt(10);
	}
	
	//returns the sum of neighbors mod 10
	public int getSumMod() {

		ArrayList<TileModel> temp = getNeighbors();

		int val = 0;

		//for each tile in  neighborlist, add val to total only if it is visible and not blank
		for (TileModel tile : temp) {
			// if (tile.isVisible() && !tile.isBlank) {
			if (!tile.isBlank) {
				val += tile.getNumber();
			}
		}

		return val % 10;
	}
	
	public boolean isBlank(){
		return isBlank;
	}
	
	//returns arrayList of all non-null, non-blank neighbors
	public ArrayList<TileModel> getNeighbors(){
		ArrayList<TileModel> temp = new ArrayList<TileModel>();
		
		if(north != null && !north.isBlank()){
			temp.add(north);
		}
		if(south != null && !south.isBlank()){
			temp.add(south);
		}
		if(east != null && !east.isBlank()){
			temp.add(east);
		}
		if(west != null && !west.isBlank()){
			temp.add(west);
		}
		if(northeast != null && !northeast.isBlank()){
			temp.add(northeast);
		}
		if(northwest != null && !northwest.isBlank()){
			temp.add(northwest);
		}
		if(southeast != null && !southeast.isBlank()){
			temp.add(southeast);
		}
		if(southwest != null && !southwest.isBlank()){
			temp.add(southwest);
		}
		
		return temp;
	}
	
	//setBlank() is used to initialize the outer edges of the tiles to blank tiles
	public void setBlank(){
		//this.number.setNum(0);;
		this.number = 0;
		isBlank = true;
		setChanged();
		notifyObservers();
	}
	
	//setblank with the parameter is used to mark i a tile is blank, mainly used to mark tiles as notblank that started blank
	public void setBlank(boolean b){
		isBlank = b;
		setChanged();
		notifyObservers();
	}

	
	//setters/getters only below
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
		setChanged();
		notifyObservers();
	}

	public TileModel getNorth() {
		return north;
	}

	public void setNorth(TileModel north) {
		this.north = north;
	}

	public TileModel getEast() {
		return east;
	}

	public void setEast(TileModel east) {
		this.east = east;
	}

	public TileModel getSouth() {
		return south;
	}

	public void setSouth(TileModel south) {
		this.south = south;
	}

	public TileModel getWest() {
		return west;
	}

	public void setWest(TileModel west) {
		this.west = west;
	}

	public TileModel getNortheast() {
		return northeast;
	}

	public void setNortheast(TileModel northeast) {
		this.northeast = northeast;
	}

	public TileModel getNorthwest() {
		return northwest;
	}

	public void setNorthwest(TileModel northwest) {
		this.northwest = northwest;
	}

	public TileModel getSoutheast() {
		return southeast;
	}

	public void setSoutheast(TileModel southeast) {
		this.southeast = southeast;
	}

	public TileModel getSouthwest() {
		return southwest;
	}

	public void setSouthwest(TileModel southwest) {
		this.southwest = southwest;
	}
}
