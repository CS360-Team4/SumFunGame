package topScoreList;

import java.util.ArrayList;
import java.util.Observable;

public class TopScoreModel extends Observable {
	
	private static final int MAX_VALUE = 999;
	private static final int LAST_PLACE = 9;	
	private static ArrayList<TopScoreModel> topTenFewestMoves;
	
	private static int noPlayersInList = 0;
	
	
	private String name;
	private int moves;
	//TODO how to store time? String? double? Object of some sort?
//	String totalTime;
	
	private TopScoreModel(String name, int moves){
		this.name = name;
		this.moves = moves;
//		this.totalTime = totalTime;
		
		this.addObserver(TopScoreList.getTopScoreList());
		
		setChanged();
		notifyObservers();
	}
	
	public String getName(){
		return name;
	}
	
	public int getMoves(){
		return moves;
	}
	
//	public String getTime(){
//		return totalTime;
//	}
	
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
	public static boolean checkScore(String name, int moves) {
		if (topTenFewestMoves == null) {
			System.out.println("Null array list");
			createTopTen();
		}
		
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
	
	private static void addTopScore(String name, int moves) {
		// Compare score to the top ten list

		if (noPlayersInList == 0) {
			topTenFewestMoves.add(0, new TopScoreModel(name, moves));
			noPlayersInList++;
		}

		else {
			for (int i = 0; i < noPlayersInList; i++) {
				// totMoves is fewer than the entry in the top ten list
				System.out.println("Index " + i + " position is: " + topTenFewestMoves.get(i).getName());
				System.out.println("Index " + i + " position is: " + topTenFewestMoves.get(i).getName());

				if (topTenFewestMoves.get(0).getMoves() > moves) {

					// insert top score in position
					topTenFewestMoves.add(i, new TopScoreModel(name, moves));

					if (noPlayersInList >= 10) {
						// remove last place - LAST_PLACE + 1 because there is
						// currently 1 extra value in array list
						topTenFewestMoves.remove(LAST_PLACE + 1);
					} else {
						// There are fewer than 10 top scores so increment
						// number of players in list
						noPlayersInList++;
					}
				} else {
					System.out.println("NO. " + i);
				}
			}
		}
	}
	
	/**
	 * Returns the array list of top ten scores
	 * @return ArrayList<TopScoreModel> - array list of top ten fewest moves
	 */
	public static ArrayList<TopScoreModel> getTopScoreList(){
		if (topTenFewestMoves ==null) createTopTen();
		return topTenFewestMoves;
	}
	
	public static int getNoPlayers(){
		return noPlayersInList;
	}
	
	private static void createTopTen(){
		
		topTenFewestMoves = new ArrayList<TopScoreModel>();
		for (int i = 0; i<10; i++){
			topTenFewestMoves.add(new TopScoreModel("-NO PLAYER-", MAX_VALUE));
		}
		
	}
	
	private static int getLastPlaceTotMoves(){
		return topTenFewestMoves.get(noPlayersInList-1).getMoves();
	}

}
