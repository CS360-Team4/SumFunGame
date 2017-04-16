import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class UntimedGame extends GameBoard {
	
	public UntimedGame(String name) throws IOException, ClassNotFoundException {
		super(name);
		lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tileButtons[i][j].addActionListener(new SwapListener());
			}
		}
	}

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
					
					playerMoves++;

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
			UntimedGame.this.checkWin();
		}
	}
}
