package topscorelist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

public class TopScoreModel extends Observable implements Serializable {

	private final int NAME = 0;
	private final int POINTS = 1;
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
		
		for(int i = topScores.length; i < 0; i--)
		{
			//if score is high  enough to be in top add to list
			if(points > Integer.parseInt(topScores[i][POINTS]))
			{
				addTopScore(name, points, i);
				return true;
			}
		}

		return false;

	}

	//updates the topscore model
	//only called by checkScore(), takes the index of that the new score belongs in and moves the rest of the scores down accordingly
	private void addTopScore(String name, int points, int index) {

		for(int i = index - 1; i > 1; i--)
		{
			topScores[i-1][NAME] = topScores[i][NAME];
			topScores[i-1][POINTS] = topScores[i][POINTS];
		}
		
		topScores[index][NAME] = name;
		topScores[index][POINTS] = Integer.toString(points);

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
