import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

public class TimedGame extends GameBoard {

	private Timer timer;
	private int timeLeft = (1000*60*5);
	private boolean timerOn = true;
	
	public TimedGame(String name) throws IOException, ClassNotFoundException {
		super(name);
		lblTime.setText("5:00");
		
<<<<<<< HEAD
		timer = new Timer(1000 ,new TimerListener());
		//System.out.println(getTimeString());
=======
		timer = new Timer(1000 ,new timerListener());
		System.out.println(getTimeString());
>>>>>>> af34102b548a422b4a3d47f4b720e7e519d21740
		timer.start();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tileButtons[i][j].addActionListener(new SwapListener());
			}
		}
	}
	
	private class timerListener implements ActionListener{
		
	    public void actionPerformed(ActionEvent e)
	    {
	        timeLeft -= 1000;
	        //SimpleDateFormat df=new SimpleDateFormat("mm:ss:S");
	       // jLabel1.setText(df.format(timeLeft));
	        lblTime.setText(getTimeString());
	        if(timeLeft<=0)
	        {
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
			TimedGame.this.checkWin();
		}
	}
	
	private String getTimeString(){
		String timeString = "";
		if(timeLeft%60000 == 0){
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000) + "0");
		}
		else{
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000));
		}
		return timeString;
	}
}
