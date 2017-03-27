import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JFrame {

	private Tile[][] tiles;
	private TileQueue queue;
	private JPanel mainPanel;
	private JPanel gridPanel;
	private JPanel queuePanel;
	private JPanel queueBorderPanel;
	private JPanel generalPanel;
	private JPanel generalPanel2;
	private JPanel generalPanel3;
	private JPanel generalPanel4;

	public GameBoard() {

		// create mainpanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// create gridpanel to hold tiles
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(9, 9));
		gridPanel.setVisible(true);
		gridPanel.setBackground(Color.WHITE);

		// create tiles and add to board
		tiles = new Tile[9][9];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {

				// if tiles is on outer edge set it as blank tile
				if (i == 0 || j == 0 || i == tiles.length - 1 || j == tiles[i].length - 1) {

					tiles[i][j] = new Tile();
					tiles[i][j].setBlank();
				} else {
					tiles[i][j] = new Tile();
				}

				gridPanel.add(tiles[i][j]);
				tiles[i][j].addActionListener(new SwapListener());
			}
		}
		
		//set tile links
		linkTiles();

		// create queuePanel and initialize queue tiles
		queue = new TileQueue();
		setQueue();

		// create generalpanels and add queuepanel to main queue panel
		queueBorderPanel = new JPanel();
		queueBorderPanel.setLayout(new GridLayout(2, 2));

		/*
		 * generalPanel = new JPanel(); generalPanel2 = new JPanel();
		 * generalPanel3 = new JPanel(); generalPanel4 = new JPanel();
		 * 
		 * 
		 * queueBorderPanel.add(generalPanel, BorderLayout.EAST);
		 * queueBorderPanel.add(generalPanel2, BorderLayout.WEST);
		 * queueBorderPanel.add(generalPanel3, BorderLayout.NORTH);
		 * queueBorderPanel.add(generalPanel4, BorderLayout.SOUTH);
		 */

		// queueBorderPanel.add(queuePanel, BorderLayout.CENTER);

		queueBorderPanel.add(new JLabel("Tile Queue: "));
		queueBorderPanel.add(queuePanel);

		// todo
		queueBorderPanel.add(new JLabel("Moves left: "));
		queueBorderPanel.add(new JLabel("50"));
		// queueBorderPanel.setPreferredSize(new Dimension(100,5));

		mainPanel.add(queueBorderPanel, BorderLayout.EAST);
		mainPanel.add(gridPanel, BorderLayout.CENTER);

		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.RED);
		setSize(1000, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private class SwapListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Tile temp = (Tile) e.getSource();
			
			//if blank tile is clicked put queue tile onto board
			if(temp.getNumber() == 0)
			{
				temp.setText(queue.pop());
			}
			
			
			ArrayList<Tile> neighbors = temp.getNeighbors();
			
			//if the sum mod 10 of neighbors is 0 set tiles to false and make invisible
			if(temp.getSumMod() == 0){
				for(Tile tile : neighbors){
					tile.setVisible(false);
					temp.setVisible(false);
					temp = null;
					tile = null;
				}
			}
			
		
		}
		
	}

	//Sets the output of the queuePanel to corresponding tiles in queue
	private void setQueue(){
		queuePanel = new JPanel(new GridLayout(5,1));
		Tile[] temp = queue.getQueue();
		
		for(int i = 0; i < temp.length; i++ ){
	
			queuePanel.add(temp[i]);
		}
		queuePanel.setBackground(Color.WHITE);
		queuePanel.setVisible(true);

		
	}
	
	// set all the nsew links for the tiles
	private void linkTiles() {

		Tile curr;

		// i == row, j == column
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				curr = tiles[i][j];

				// First row first column
				if (i == 0 && j == 0) {
					curr.setSouth(tiles[i + 1][j]);
					curr.setEast(tiles[i][j + 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);

				}
				// first row not first or last column
				else if (i == 0 && j != tiles[i].length - 1) {
					curr.setSouth(tiles[i + 1][j]);
					curr.setEast(tiles[i][j + 1]);
					curr.setWest(tiles[i][j - 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
				}
				// first row last column
				else if (i == 0 && j == tiles[i].length - 1) {
					curr.setWest(tiles[i][j - 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
					curr.setSouth(tiles[i + 1][j]);
				}
				// first column any row besides 1st and last
				else if (j == 0 && (i != 0 && i != tiles.length - 1)) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);
					curr.setSouth(tiles[i + 1][j]);
				}
				// first column last row
				else if (i == tiles.length - 1 && j == 0) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);
				}
				// any column except first and last on last row
				else if (i == tiles.length - 1 && (j != tiles[i].length - 1 && j != 0)) {
					curr.setWest(tiles[i][j - 1]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);

				}
				// last row last column
				else if (i == tiles.length - 1 && j == tiles[i].length - 1) {
					curr.setWest(tiles[i][j - 1]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setNorth(tiles[i - 1][j]);
				}
				// last column any row except 1st or last
				else if ((i != 0 && i != tiles.length - 1) && j == tiles[i].length - 1) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setWest(tiles[i][j - 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
					curr.setSouth(tiles[i + 1][j]);
				}
				// otherwise its a non-border tile
				else {

					curr.setNorth(tiles[i - 1][j]);
					curr.setSouth(tiles[i + 1][j]);
					curr.setEast(tiles[i][j + 1]);
					curr.setWest(tiles[i][j - 1]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);

				}
			}
		}
	}

}