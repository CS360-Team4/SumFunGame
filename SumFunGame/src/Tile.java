import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.StyledDocument;

public class Tile extends JPanel{
	
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
	
	public Tile(){
		north = null;
		east = null;
		south = null;
		west = null;
		northeast = null;
		northwest = null;
		southeast = null;
		southwest = null;
		number = rand.nextInt(9);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
		String text = String.valueOf(number);
		JLabel numberText = new JLabel(text);
		add(numberText);
		setVisible(true);
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
