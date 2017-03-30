import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import javafx.beans.Observable;


public class Tile extends JButton implements Observer{

	private Tile north;
	private Tile east;
	private Tile south;
	private Tile west;
	private Tile northeast;
	private Tile northwest;
	private Tile southeast;
	private Tile southwest;
	private Number number;
	private ArrayList<Tile> neighbors;
	private boolean isBlank;

	// Constructor takes a boolean as a parameter that specifies if it is a
	// blank tile
	public Tile() {
		north = null;
		east = null;
		south = null;
		west = null;
		northeast = null;
		northwest = null;
		southeast = null;
		southwest = null;
		number = new Number();
		number.addObserver(this);
		setText(number.toString());
		setSize(20,20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
		setVisible(true);
		setPreferredSize(new Dimension(10, 10));
		setOpaque(true);
		setBackground(Color.WHITE);
	}
	
	//returns the sum of neighbors mod 10
	public int getSumMod() {

		ArrayList<Tile> temp = getNeighbors();

		int val = 0;

		//for each tile in  neighborlist, add val to total only if it is visible and not blank
		for (Tile tile : temp) {
			if (tile.isVisible() && !tile.isBlank) {
				val += tile.getNumber();
			}
		}

		return val % 10;
	}
	
	public boolean isBlank(){
		return isBlank;
	}
	
	//Controller for the model
	@Override
	public void update(java.util.Observable o, Object arg) {
		this.setText(this.number.toString());		
	}
	
	//returns arrayList of all non-null, non-blank neighbors
	public ArrayList<Tile> getNeighbors(){
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
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
		this.number.setNum(0);;
		isBlank = true;
		setText("");
		revalidate();
		repaint();
	}
	
	//setblank with the parameter is used to mark i a tile is blank, mainly used to mark tiles as notblank that started blank
	public void setBlank(boolean b){
		isBlank = b;
	}

	
	//setters/getters only below
	public int getNumber(){
		return number.getNum();
	}
	
	public void setNumber(int number) {
		this.number.setNum(number);
		setText(Integer.toString(number)); //sets output of tile to number
	}
	
	public void setNum(int in)
	{
		this.number.setNum(in);
	}
	
	public Number getNumObject(){
		return this.number;
	}
	
	public void setNumber(Number number) {
		this.number = number;
	}

	public Tile getNorth() {
		return north;
	}

	public void setNorth(Tile north) {
		this.north = north;
	}

	public Tile getEast() {
		return east;
	}

	public void setEast(Tile east) {
		this.east = east;
	}

	public Tile getSouth() {
		return south;
	}

	public void setSouth(Tile south) {
		this.south = south;
	}

	public Tile getWest() {
		return west;
	}

	public void setWest(Tile west) {
		this.west = west;
	}

	public Tile getNortheast() {
		return northeast;
	}

	public void setNortheast(Tile northeast) {
		this.northeast = northeast;
	}

	public Tile getNorthwest() {
		return northwest;
	}

	public void setNorthwest(Tile northwest) {
		this.northwest = northwest;
	}

	public Tile getSoutheast() {
		return southeast;
	}

	public void setSoutheast(Tile southeast) {
		this.southeast = southeast;
	}

	public Tile getSouthwest() {
		return southwest;
	}

	public void setSouthwest(Tile southwest) {
		this.southwest = southwest;
	}
}
