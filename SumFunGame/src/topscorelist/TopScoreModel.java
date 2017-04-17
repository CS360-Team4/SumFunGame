package topscorelist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

public class TopScoreModel extends Observable implements Serializable {

	/**
	 *serialVersionUID so that changes to model does not corrupt serialized object
	 */
	private static final long serialVersionUID = 1L;
	private final int nameIndex = 0;
	private final int pointsIndex = 1;
	private String[][] topScores;

	//TopScoreModel uses a 2d String array to maintain a list of the top scores
	//Operates as the model for the TopScoreList
	public TopScoreModel() throws IOException {

		topScores = new String[10][2];
		createTopTen();// initialize to dummy list

		// this.totalTime = totalTime;

		saveTopScore();
		setChanged();
		notifyObservers();
	}

	/**
	 * This method checks whether the final score should be added to the top
	 * score list. If so it is added to the top score
	 * 
	 * @param name
	 *            - player name
	 * @param points
	 *            - total points
	 * @return boolean - true if top score, false o.w.
	 */
	public boolean checkScore(String name, int points) {
		
		for(int i = 0; i < topScores.length - 1; i++) {
		
			//if score is high  enough to be in top add to list
			if(points > Integer.parseInt(topScores[i][pointsIndex])) {
				//System.out.println("checkscore model");
				addTopScore(name, points, i);
				return true;
			}
		}

		return false;

	}

	//updates the topscore model
	//only called by checkScore(), takes the index of that the new score belongs in and moves the rest of the scores down accordingly
	private void addTopScore(String name, int points, int index) {

		//move all scores down
		for(int i = topScores.length - 1 ; i > index; i--) {
			topScores[i][nameIndex] = topScores[i-1][nameIndex];
			topScores[i][pointsIndex] = topScores[i-1][pointsIndex];
		}
		
		topScores[index][nameIndex] = name;
		topScores[index][pointsIndex] = Integer.toString(points);

		//System.out.println("added");
		try {
			saveTopScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public String[][] getTopScoreList() {
		return topScores;
	}


	//use to set topten to default values
	private void createTopTen() {
		// topTenFewestMoves = new ArrayList<TopScoreModel>();
		for (int i = 0; i < 10; i++) {
			// topTenFewestMoves.add(new TopScoreModel("-NO PLAYER-",
			// MAX_VALUE));
			topScores[i][0] = "-NO PLAYER-";
			topScores[i][1] = Integer.toString(0);
		}

	}

	
	//Serializes this object
	public void saveTopScore() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"TopScore.ser"));
		out.writeObject(this);
		out.close();
	}
}
