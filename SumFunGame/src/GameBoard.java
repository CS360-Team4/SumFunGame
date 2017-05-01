import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.glass.events.KeyEvent;

import leasttimelist.LeastTimeList;
import topscorelist.TopScoreList;
import topscorelist.TopScoreModel;

public class GameBoard extends JFrame {

	protected TileModel[][] tiles;
	protected Tile[][] tileButtons;
	TileQueue queue;
	private JPanel mainPanel;
	protected JPanel buttonPanel;
	protected JPanel gridPanel;
	private JPanel queuePanel;
	private JPanel queueBorderPanel;
	protected JPanel labelGridPanel;
	private JButton btnNewTimedGame;
	private JButton btnNewUntimedGame;
	private JButton btnResetQueue;
	private JButton btnTopScores;
	private JButton btnLeastTimes;
	private JButton btnRemoveNumber;
	protected JButton hintButton;
	protected JLabel lblMovesTitle;
	protected JLabel lblMovesLeft;
	protected JLabel lblScore;
	protected JLabel lblTimeTitle;
	protected JLabel lblTime;
	protected int score;
	protected String name;
	private int resetQueueValue = 1;
	protected int playerMoves = 0;
	protected boolean gameIsWon = false;
	protected TopScoreList topScore;
	protected LeastTimeList leastTimes;
	protected int numHints = 3;
	protected int numRemovals = 1;
	public GameBoard() throws IOException, ClassNotFoundException, ParseException {

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

				// give the tiles actionListeners in the subclass
				// tileButtons[i][j].addActionListener(new SwapListener());
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
		labelGridPanel.setLayout(new GridLayout(3, 2));
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
		GridLayout paddingLayout = new GridLayout(8, 1);
		GridLayout paddingLayoutN = new GridLayout(4, 1);
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

		lblMovesTitle = new JLabel("Moves: ");
		lblMovesTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblMovesTitle);
		lblMovesLeft = new JLabel("");
		lblMovesLeft.setFont(new Font("Arial", Font.BOLD, 20));
		labelGridPanel.add(lblMovesLeft);
		// queueBorderPanel.setPreferredSize(new Dimension(100,5));

		lblTimeTitle = new JLabel("Time: ");
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

		// button queue and buttons
		buttonPanel = new JPanel();
		GridLayout buttonLayout = new GridLayout(2, 4);
		buttonPanel.setLayout(buttonLayout);
		btnNewTimedGame = new JButton("New Timed Game");
		btnNewTimedGame.addActionListener(new NewTimedGameListener());
		btnNewUntimedGame = new JButton("New Untimed Game");
		btnNewUntimedGame.addActionListener(new NewUntimedGameListener());
		btnResetQueue = new JButton("Reset Queue");
		btnResetQueue.addActionListener(new ResetQueueListener());
		btnTopScores = new JButton("Top 10 Most Points");
		btnTopScores.addActionListener(new TopTenScoreListener());
		btnLeastTimes = new JButton("Top 10 Least Times");
		btnLeastTimes.addActionListener(new TopTenTimesListener());
		btnRemoveNumber = new JButton("Remove Number");
		btnRemoveNumber.addActionListener(new RemoveNumberListener());
		hintButton = new JButton("Hint");
		hintButton.setMnemonic(KeyEvent.VK_Z);
		hintButton.addActionListener(new HintListener());
		// addTopPlayer = new JMenuItem("Add Top 10 Player");
		// addTopPlayer.addActionListener(new FakeTopTenMovesListener());
		buttonPanel.add(btnNewUntimedGame);
		buttonPanel.add(btnNewTimedGame);
		buttonPanel.add(btnTopScores);
		buttonPanel.add(btnLeastTimes);
		buttonPanel.add(btnResetQueue);
		buttonPanel.add(btnRemoveNumber);
		buttonPanel.add(hintButton);
		// topTenMenu.add(addTopPlayer);
		buttonPanel.setVisible(true);
		buttonPanel.setOpaque(true);
		buttonPanel.setBackground(Color.WHITE);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);

		topScore = new TopScoreList();
		leastTimes = new LeastTimeList();
		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.RED);
		setSize(1000, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private class NewTimedGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			// new GameBoard();
			try {
				new TimedGame();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private class NewUntimedGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			// new GameBoard();
			try {
				new UntimedGame();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private class ResetQueueListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (resetQueueValue > 0) {
				resetQueue();
			}
			resetQueueValue--;
			
			//plays sound
			AudioInputStream audioInputStream;
			Clip clip;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File("ResetQueue.wav").getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (LineUnavailableException | IOException  | UnsupportedAudioFileException e2 ) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}//end sound
		}
	}

	private class RemoveNumberListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if(numRemovals > 0){
				JFrame frame = new JFrame();
				String message = "Please enter the number to be removed.";
				String number = JOptionPane.showInputDialog(frame, message);
				if (number != null) { //if number is null, user hit "cancel"

					try{
					// check now if number is valid
					int removeNumber = Integer.parseInt(number); // if not a number, will throw exception
					if (removeNumber < 0 || removeNumber > 9) throw new Exception(); // force throw exception
					
					for (int i = 0; i < tiles.length; i++) {
						for (int j = 0; j < tiles[i].length; j++) {
							if (tiles[i][j].getNumber() == Integer.parseInt(number)) {
								tiles[i][j].setBlank();
								linkTiles();
							}
						}
					}

					numRemovals--;
					// plays sound
					AudioInputStream audioInputStream;
					Clip clip;
					try {
						audioInputStream = AudioSystem
								.getAudioInputStream(new File("NumRemoval.wav").getAbsoluteFile());
						clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start();
					} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} // end sound
					}
				
					catch (Exception invalidNumber){
						
						JOptionPane.showMessageDialog(null,"Must enter a valid number from 0-9","Invalid Number Entry",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
	}

	private class TopTenScoreListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Need logic here to load the top ten least moves object and
			// display it in a new JPane/JFrame
			// TODO change - this is getting a static instance
			try {
				topScore = new TopScoreList();
				topScore.updatePlayerScores();
				topScore.setVisible(true);
			} catch (IOException | ClassNotFoundException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class TopTenTimesListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Need logic here to load the top ten least moves object and
			// display it in a new JPane/JFrame
			// TODO change - this is getting a static instance
			try {
				leastTimes = new LeastTimeList();
				leastTimes.updatePlayerTimes();
				leastTimes.setVisible(true);
			} catch (IOException | ClassNotFoundException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class HintListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (numHints > 0) {
				int bestRow = -1;
				int bestColumn = -1;
				int bestRemove = 0;
				int nextInQueue = queue.peek();
				int temp;
				JButton button;
				for (int i = 0; i < tiles.length; i++) {
					for (int j = 0; j < tiles.length; j++) {
						// temp = tiles[i][j].getTileModel();

						// check number of potential removals
						temp = tiles[i][j].getRemovalCount(nextInQueue);
						// mark tile location and removal values if better
						if (temp != 0 && temp > bestRemove) {
							bestRow = i;
							bestColumn = j;
							bestRemove = temp;
						}
					} // end inner for
				} // end outer for

				// disable help button and mark best spot in red
				if (bestRow != -1 && bestColumn != -1) {
					tileButtons[bestRow][bestColumn].setHintColor();
					button = (JButton) e.getSource();
					button.setEnabled(false);
				}

				numHints--;
				
				//plays sound
				AudioInputStream audioInputStream;
				Clip clip;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File("Hint.wav").getAbsoluteFile());
					clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				} catch (LineUnavailableException | IOException  | UnsupportedAudioFileException e2 ) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}//end sound
				
			}
		}
	}

	// Resets the values in the queue
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
		queuePanel.setMaximumSize(new Dimension(50, 50));
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
}