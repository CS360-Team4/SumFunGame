package topscorelist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class TopScoreModel extends Observable implements Serializable {
	
	private  final int maxValue = 999;
	private  final int lastPlace = 9;	
	private String[][] topScores;
	//private  ArrayList<TopScoreModel> topTenFewestMoves;
	
	private  int noPlayersInList = 0;
	private TopScoreList topScore;
	
	
	private String name;
	private int moves;
	//TODO how to store time? String? double? Object of some sort?

	public TopScoreModel() throws IOException {
		topScores = new String[10][2];
		createTopTen();//initialize to dummy list		
		
		setChanged();
		notifyObservers();
	}
	
	public String getName(){
		return name;
	}
	
	public int getMoves(){
		return moves;
	}
	
	// -------------------------------------------------------------
	// --------------- Static Methods ------------------------------
	// -------------------------------------------------------------

	/**
	 * This method checks whether the final score should be added to the
	 * top score list.
	 * 
	 * @param name - player name
	 * @param moves - total moves
	 * @return boolean - true if top score, false o.w.
	 */
	public boolean checkScore(String name, int moves) {
		/*if (topTenFewestMoves == null) {
			System.out.println("Null array list");
			createTopTen();
		}
		*/
		
		if (noPlayersInList < 10){
			System.out.println("Fewer than 10 players");
			addTopScore(name, moves);
			return true;
		}

		// check moves against array list
		if (moves < getLastPlaceTotMoves()) {
			System.out.println("Moves beat existing list");
			// Compare score to the top ten list
			addTopScore(name, moves);
			return true;

		}

		return false;

	}
	
	private void addTopScore(String name, int moves) {
		// Compare score to the top ten list

		if (noPlayersInList == 0) {
			//topTenFewestMoves.add(0, new TopScoreModel(name, moves));
			topScores[0][0] = name;
			topScores[0][1] = Integer.toString(moves);
			noPlayersInList++;
		} else {
			for (int i = noPlayersInList - 1; i > 0; i--) {
				
				if (moves < Integer.parseInt(topScores[i][1])) {
					topScores[i][0] = name;
					topScores[1][1] = Integer.toString(moves);
				}
			
			}
		}
	}
	
	/**
	 * Returns the array list of top ten scores
	 * @return ArrayList<TopScoreModel> - array list of top ten fewest moves
	
	public  ArrayList<TopScoreModel> getTopScoreList(){
		if (topTenFewestMoves ==null) createTopTen();
		return topTenFewestMoves;
	}
	 */
	
	
	public String[][] getTopScoreList() {
		return topScores;
	}
	public  int getNoPlayers() {
		return noPlayersInList;
	}
	
	private void createTopTen() {
		
		//topTenFewestMoves = new ArrayList<TopScoreModel>();
		for (int i = 0; i<10; i++){
			//topTenFewestMoves.add(new TopScoreModel("-NO PLAYER-", MAX_VALUE));
			topScores[i][0] = "-NO PLAYER-";
			topScores[i][1] = Integer.toString(maxValue);
		}
		
	}
	
	private  int getLastPlaceTotMoves(){
		return Integer.parseInt(topScores[9][1]);
	}

	public void saveTopScore() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TopScore.ser"));
		out.writeObject(this);
		out.close();
	}
}
