import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import topscorelist.TopScoreList;
import topscorelist.TopScoreModel;

public class GameBoard extends JFrame {

	protected TileModel[][] tiles;
	protected Tile[][] tileButtons;
	TileQueue queue;
	private JPanel mainPanel;
	protected JPanel gridPanel;
	private JPanel queuePanel;
	private JPanel queueBorderPanel;
	protected JPanel labelGridPanel;
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenu queueMenu;
	private JMenu topTenMenu;
	private JMenuItem newGame;
	private JMenuItem resetQueue;
	private JMenuItem mnuTopTenMoves;
	private JMenuItem addTopPlayer;
	protected JLabel lblMovesLeft;
	protected JLabel lblScore;
	protected JLabel lblTime;
	int score;
	private int resetQueueValue = 1;
	private String playerName;
	protected int playerMoves = 0;
	protected boolean gameIsWon = false;
	private TopScoreList topScore;

	public GameBoard(String name) throws IOException, ClassNotFoundException {

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
				
				//give the tiles actionListeners in the subclass
				//tileButtons[i][j].addActionListener(new SwapListener());
			}
		}

		// set tile links
		linkTiles();

		// create queuePanel and initialize queue tiles
		queue = new TileQueue();
		setQueue();

		// create generalpanels and add queuepanel to main queue panel
		queueBorderPanel = new JPanel();
		queueBorderPanel.setLayout(new BorderLayout());
		queueBorderPanel.setBackground(Color.WHITE);
		queueBorderPanel.setOpaque(true);
		
		labelGridPanel = new JPanel();
		labelGridPanel.setLayout(new GridLayout(3,2));
		labelGridPanel.setOpaque(true);
		labelGridPanel.setBackground(Color.WHITE);

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

		JLabel lblQueue = new JLabel("Tile Queue");
		lblQueue.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueue.setVerticalAlignment(SwingConstants.CENTER);
		lblQueue.setFont(new Font("Arial", Font.BOLD, 20));
		
		queueBorderPanel.add(lblQueue, BorderLayout.NORTH);
		JPanel innerBorder = new JPanel();
		BorderLayout innerBorderLayout = new BorderLayout();
		JPanel northPadding = new JPanel();
		JPanel southPadding = new JPanel();
		GridLayout paddingLayout = new GridLayout(8,1);
		GridLayout paddingLayoutN = new GridLayout(4,1);
		northPadding.setLayout(paddingLayoutN);
		southPadding.setLayout(paddingLayout);
		northPadding.setBackground(Color.WHITE);
		southPadding.setBackground(Color.WHITE);
		innerBorder.setLayout(innerBorderLayout);
		innerBorder.setBackground(Color.WHITE);
		innerBorder.add(queuePanel, BorderLayout.CENTER);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.NORTH);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.NORTH);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.NORTH);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.NORTH);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		southPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		northPadding.add(new JLabel("                   "), BorderLayout.SOUTH);
		innerBorder.add(northPadding, BorderLayout.NORTH);
		innerBorder.add(southPadding, BorderLayout.SOUTH);
		innerBorder.add(new JLabel("       "), BorderLayout.EAST);
		innerBorder.add(new JLabel("       "), BorderLayout.WEST);
		queueBorderPanel.add(innerBorder);

		JLabel lblMovesTitle = new JLabel("Moves: ");
		lblMovesTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblMovesTitle);
		lblMovesLeft = new JLabel("");
		lblMovesLeft.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblMovesLeft);
		// queueBorderPanel.setPreferredSize(new Dimension(100,5));
		
		JLabel lblTimeTitle = new JLabel("Time: ");
		lblTimeTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblTimeTitle);
		lblTime = new JLabel("--:--");
		lblTime.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblTime);
		
		JLabel lblScoreTitle = new JLabel("Score: ");
		lblScoreTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblScoreTitle);		
		score = 0;
		lblScore = new JLabel(String.valueOf(score));
		lblScore.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblScore);
		
		queueBorderPanel.add(labelGridPanel, BorderLayout.SOUTH);

		mainPanel.add(queueBorderPanel, BorderLayout.EAST);
		mainPanel.add(gridPanel, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		queueMenu = new JMenu("Queue");
		menuBar.add(queueMenu);
		topTenMenu = new JMenu("Top 10");
		menuBar.add(topTenMenu);
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new NewGameListener());
		resetQueue = new JMenuItem("Reset Queue");
		resetQueue.addActionListener(new ResetQueueListener());
		mnuTopTenMoves = new JMenuItem("Top 10 Least Moves");
		mnuTopTenMoves.addActionListener(new TopTenMovesListener());
		addTopPlayer = new JMenuItem("Add Top 10 Player");
		addTopPlayer.addActionListener(new FakeTopTenMovesListener());
		gameMenu.add(newGame);
		queueMenu.add(resetQueue);
		topTenMenu.add(mnuTopTenMoves);
		topTenMenu.add(addTopPlayer);
		setJMenuBar(menuBar);
		
		
		topScore = new TopScoreList();
		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.RED);
		setSize(1000, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		playerName = name;
		setTitle(playerName+"'s Sum Fun Game");
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
			if (TileQueue.movesLeft > 0 && temp.isBlank() && !gameIsWon){
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
			playerMoves++;
			GameBoard.this.checkWin();
		}
	}

	private class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			//new GameBoard();
			new PreGameScreen();
		}

	}
	
	private class ResetQueueListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			if(resetQueueValue > 0){
				resetQueue();
			}
			resetQueueValue--;
		}
	}
	
	private class TopTenMovesListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			//Need logic here to load the top ten least moves object and display it in a new JPane/JFrame
			//TODO change - this is getting a static instance
			try {
				topScore.getTopScoreList().setVisible(true);
			} catch (IOException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class FakeTopTenMovesListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			/*
			TopScoreModel.checkScore("TestPerson", 5);
			TopScoreModel.checkScore("TestPerson2", 15);
			TopScoreModel.checkScore("TestPerson3", 52);
			TopScoreModel.checkScore("TestPerson4", 45);
			TopScoreModel.checkScore("TestPerson5", 25);
			*/						
			}
	}
	
	//Resets the values in the queue
	private void resetQueue() {
		queue.resetQueue();
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
		queuePanel.setOpaque(true);
		queuePanel.setMaximumSize(new Dimension(50,50));
	}

	// set all the nsew links for the tiles
	void linkTiles() {

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

				} else if (i == 0 && j != tiles[i].length - 1) {
					curr.setSouth(tiles[i + 1][j]);
					curr.setEast(tiles[i][j + 1]);
					curr.setWest(tiles[i][j - 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
				} else if (i == 0 && j == tiles[i].length - 1) {
					curr.setWest(tiles[i][j - 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
					curr.setSouth(tiles[i + 1][j]);
				} else if (j == 0 && (i != 0 && i != tiles.length - 1)) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);
					curr.setSoutheast(tiles[i + 1][j + 1]);
					curr.setSouth(tiles[i + 1][j]);
				} else if (i == tiles.length - 1 && j == 0) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);
				} else if (i == tiles.length - 1 && (j != tiles[i].length - 1 && j != 0)) {
					curr.setWest(tiles[i][j - 1]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setNorth(tiles[i - 1][j]);
					curr.setNortheast(tiles[i - 1][j + 1]);
					curr.setEast(tiles[i][j + 1]);

				} else if (i == tiles.length - 1 && j == tiles[i].length - 1) {
					curr.setWest(tiles[i][j - 1]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setNorth(tiles[i - 1][j]);
				} else if ((i != 0 && i != tiles.length - 1) && j == tiles[i].length - 1) {
					curr.setNorth(tiles[i - 1][j]);
					curr.setNorthwest(tiles[i - 1][j - 1]);
					curr.setWest(tiles[i][j - 1]);
					curr.setSouthwest(tiles[i + 1][j - 1]);
					curr.setSouth(tiles[i + 1][j]);
				} else {

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
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	public boolean checkWin(){
		boolean output = false;
		int counter = 0;
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(tiles[i][j].getNumber() == 0 && tiles[i][j].isBlank()){
					counter++;
				}
			}
		}
		
		if(counter == 81 && !gameIsWon){
			output = true;
			System.out.println("You won the game!");
			//endMoves = TileQueue.movesLeft;
			TileQueue.movesLeft = 0;
			gameIsWon = true;
		}
		
		System.out.println("Moves: " + playerMoves);
		return output;
	}
}