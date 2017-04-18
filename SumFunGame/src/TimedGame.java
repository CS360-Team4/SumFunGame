import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimedGame extends GameBoard {

    private Timer timer;
    private int timeLeft = (1000 * 60 * 5);
    private boolean timerOn = true;
    private boolean winFlag = false;

	public TimedGame() throws IOException, ClassNotFoundException {
		super();
		lblTime.setText("5:00");
		
		timer = new Timer(1000 ,new TimerListener());
		//System.out.println(getTimeString());
		timer.start();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tileButtons[i][j].addActionListener(new SwapListener());
			}
		}
		super.lblMovesLeft.setVisible(false);
		super.lblMovesTitle.setVisible(false);
	}
	
	private class TimerListener implements ActionListener{
		
	    public void actionPerformed(ActionEvent e) {
	        timeLeft -= 1000;
	        lblTime.setText(getTimeString());
	        if(timeLeft<=0 || gameIsWon) {
	            timer.stop();
	            timerOn = false;
	        }
	    }
	}
	
	private class SwapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Tile button = (Tile) e.getSource();
			TileModel temp = button.getTileModel();
			
			if (timerOn && temp.isBlank() && !gameIsWon){
				
				// if blank tile is clicked put queue tile onto board
				if (temp.getNumber() == 0 && temp.isBlank()) {
					temp.setNumber(TimedGame.this.queue.pop());
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
	
	private String getTimeString(){
		String timeString = "";
		if (timeLeft%60000 == 0){
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000) + "0");
		} else if(timeLeft%6000 > 0 && timeLeft%6000 < 10){
			timeString = (Integer.toString(timeLeft/60000) + ":0" + Integer.toString((timeLeft%60000)/1000));
		} else {
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000));
		}
		return timeString;
	}
}
