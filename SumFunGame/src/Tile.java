import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.StyledDocument;

public class Tile extends JButton {

	Random rand = new Random();

	private Tile north;
	private Tile east;
	private Tile south;
	private Tile west;
	private Tile northeast;
	private Tile northwest;
	private Tile southeast;
	private Tile southwest;
	private int number;
	private boolean isBlank;

	// Constructor takes a boolean as a parameter that specifies if it is a
	// blank tile
	public Tile() {

		this.isBlank = isBlank;
		north = null;
		east = null;
		south = null;
		west = null;
		northeast = null;
		northwest = null;
		southeast = null;
		southwest = null;
		number = rand.nextInt(9) + 1;
		setText(Integer.toString(number));
		setSize(20,20);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
		setVisible(true);
		setPreferredSize(new Dimension(10, 10));

	}
	
	//returns the sum of neighbors mod 10
	public int getSumMod(){
		
		return (north.getNumber() + east.getNumber() + south.getNumber() + west.getNumber() +
			   northeast.getNumber() + northwest.getNumber() + southeast.getNumber() + southwest.getNumber()) % 10;
	}
	
	public void setText(int in)
	{
		
		this.number = in;
		setText(Integer.toString(in));
	}
	
	public void setBlank(){
		this.number = 0;
		setText("");
		revalidate();
		repaint();
	}

	public int getNumber(){
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		setText(Integer.toString(number));
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
