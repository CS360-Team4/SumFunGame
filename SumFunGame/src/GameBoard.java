import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GameBoard extends JFrame {

	private TileModel[][] tiles;
	private Tile[][] tileButtons;
	private TileQueue queue;
	private JPanel mainPanel;
	private JPanel gridPanel;
	private JPanel queuePanel;
	private JPanel queueBorderPanel;
	private JMenuBar menuBar;
	private JMenu newMenu;
	private JMenuItem untimedGame;
	JLabel lblMovesLeft;
	JLabel lblScore;
	int score;

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
		tiles = new TileModel[9][9];
		tileButtons = new Tile[9][9];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new TileModel();
				tileButtons[i][j] = new Tile(tiles[i][j]);
				// if tiles is on outer edge set it as blank tile
				if (i == 0 || j == 0 || i == tiles.length - 1 || j == tiles[i].length - 1) {
					tiles[i][j].setBlank();
				}
				// add tiles to panel and add actionlisteners
				gridPanel.add(tileButtons[i][j]);
				tileButtons[i][j].addActionListener(new SwapListener());
			}
		}

		// set tile links
		linkTiles();

		// create queuePanel and initialize queue tiles
		queue = new TileQueue();
		setQueue();

		// create generalpanels and add queuepanel to main queue panel
		queueBorderPanel = new JPanel();
		queueBorderPanel.setLayout(new GridLayout(3, 2));

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

		queueBorderPanel.add(new JLabel("Tile Queue >>"));
		queueBorderPanel.add(queuePanel);

		// todo
		queueBorderPanel.add(new JLabel("Moves left: "));
		lblMovesLeft = new JLabel(String.valueOf(TileQueue.movesLeft));
		queueBorderPanel.add(lblMovesLeft);
		// queueBorderPanel.setPreferredSize(new Dimension(100,5));
		
		queueBorderPanel.add(new JLabel("Score: "));
		score = 0;
		lblScore = new JLabel(String.valueOf(score));
		queueBorderPanel.add(lblScore);

		mainPanel.add(queueBorderPanel, BorderLayout.EAST);
		mainPanel.add(gridPanel, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		newMenu = new JMenu("New..");
		menuBar.add(newMenu);
		untimedGame = new JMenuItem("Untimed Game");
		untimedGame.addActionListener(new NewGameListener());
		newMenu.add(untimedGame);
		setJMenuBar(menuBar);

		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.RED);
		setSize(1000, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// swaplistener is attached to all tiles in the 9x9 grid(not queue tiles)
	// when called it evaluates the tile clicked and the neighboring tiles and
	// determines
	// if it was a valid move, if so then it pops from queue and removes
	// neighbors as needed
	private class SwapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Tile button = (Tile) e.getSource();
			TileModel temp = button.getTileModel();
			
			// Decrement the moves remaining and update the JLabel text
			if (TileQueue.movesLeft > 0 && temp.isBlank()) {
				TileQueue.movesLeft--;
				lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));
				
				// if blank tile is clicked put queue tile onto board
				if (temp.getNumber() == 0 && temp.isBlank()) {
					temp.setNumber(queue.pop());
					// temp.update(temp.getNumObject(), temp);
					temp.setBlank(false);

					ArrayList<TileModel> neighbors = temp.getNeighbors();
					
					// if the sum mod 10 of neighbors is equal to tile clicked,
					// set tiles to false and make invisible
					if (temp.getSumMod() == temp.getNumber()) {
						for (TileModel tile : neighbors) {

							// dont remove blank tiles
							if (!tile.isBlank()) {
								// tile.setVisible(false);
								tile.setBlank();
								tile = null;
							}
						}
						temp.setBlank();
						temp = null;
						linkTiles();
						
						//Creating a temp score for that specific move and then updating the total score
						int tempScore = 0;
						if(neighbors.size() > 2){
							tempScore = neighbors.size()*10;
						}
						score += tempScore;
						lblScore.setText(String.valueOf(score));
					}

				}
			}
		}
	}

	private class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			new GameBoard();
		}

	}

	// Sets the output of the queuePanel to corresponding tiles in queue
	private void setQueue() {
		queuePanel = new JPanel(new GridLayout(5, 1));
		TileModel[] temp = queue.getQueue();
		
		for (int i = 0; i < temp.length; i++) {
			Tile tmp = new Tile(temp[i]);
			queuePanel.add(tmp);
		}
		queuePanel.setBackground(Color.WHITE);
		queuePanel.setVisible(true);

	}

	// set all the nsew links for the tiles
	private void linkTiles() {

		TileModel curr;

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