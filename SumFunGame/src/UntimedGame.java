import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UntimedGame extends GameBoard {

	private boolean winFlag = false;

	public UntimedGame() throws IOException, ClassNotFoundException, ParseException {
		super();
		lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tileButtons[i][j].addActionListener(new SwapListener());
			}
		}

		super.lblTimeTitle.setVisible(false);
		super.lblTime.setVisible(false);
	}

	private class SwapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			hintButton.setEnabled(true);
			
			Tile button = (Tile) e.getSource();
			TileModel temp = button.getTileModel();

			// Decrement the moves remaining and update the JLabel text
			if (TileQueue.movesLeft > 0 && temp.isBlank() && !gameIsWon) {
				TileQueue.movesLeft--;
				lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));

				// if blank tile is clicked put queue tile onto board
				if (temp.getNumber() == 0 && temp.isBlank()) {
					temp.setNumber(queue.pop());
					// temp.update(temp.getNumObject(), temp);
					temp.setBlank(false);
					playerMoves++;
					ArrayList<TileModel> neighbors = temp.getNeighbors();

					// if the sum mod 10 of neighbors is equal to tile clicked,
					// set tiles to false and make invisible
					if (temp.getSumMod() == temp.getNumber()) {
						
						//plays sound
						AudioInputStream audioInputStream;
						Clip clip;
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File("TODO-filenamehere").getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioInputStream);
							clip.start();
						} catch ( UnsupportedAudioFileException | LineUnavailableException | IOException e2 ) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}//end sound
						
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

						// Creating a temp score for that specific move and then
						// updating the total score
						int tempScore = 0;
						if (neighbors.size() > 2) {
							tempScore = neighbors.size() * 10;
						}

						score += tempScore;
						lblScore.setText(String.valueOf(score));
					}

				}
			}

			try {
				checkWin();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// checks if the board has been cleared of tiles, which is a win
	public boolean checkWin() throws ClassNotFoundException, IOException {
		boolean output = false;
		int counter = 0;

		// gets count of total number of blank tiles
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tiles[i][j].getNumber() == 0 && tiles[i][j].isBlank()) {
					counter++;
					tileButtons[i][j].setBackground(Color.WHITE);
				}
			}
		}

		// if there is 81 blank tiles then print out result and check for top
		// ten placement.
		if (counter == 81 && !gameIsWon) {
			output = true;
			//plays sound
			AudioInputStream audioInputStream;
			Clip clip;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File("TODO-filenamehere").getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch ( UnsupportedAudioFileException | LineUnavailableException e2 ) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}//end sound
			
			// System.out.println("You won the game!");
			// endMoves = TileQueue.movesLeft;
			TileQueue.movesLeft = 0;
			gameIsWon = true;

			String[][] temp = topScore.getModel().getTopScores();

			// checks if score is top ten worthy and displays a jbox either way.
			if (score > Integer.parseInt(temp[9][1])) {
				JFrame frame = new JFrame();
				String message = "You won! Your score has made it in the Top Ten Most Points! Please enter your name.";
				String text = JOptionPane.showInputDialog(frame, message);
				if (text != null) {
					name = text;
				}
				topScore.checkScore(name, score, new Date());
				// JOptionPane.showMessageDialog(null, "You won the game! Your
				// score has been added to the Top Ten Most Points List!");
			} else {
				JOptionPane.showMessageDialog(null, "You won the game!");
			}
		}
		return output;
	}
}
