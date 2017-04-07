package topScoreList;

import java.util.ArrayList;

public class TopScoreModel {
	
	private static final int MAX_VALUE = 999;
	private static final int LAST_PLACE = 9;	
	private static ArrayList<TopScoreModel> topTenFewestMoves;
	
	
	private String name;
	private int moves;
	//TODO how to store time? String? double? Object of some sort?
//	String totalTime;
	
	private TopScoreModel(String name, int moves){
		this.name = name;
		this.moves = moves;
//		this.totalTime = totalTime;
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
		if (topTenFewestMoves==null) createTopTen();
		
		// check moves against array list
		if (moves < getLastPlaceTotMoves())
			// Compare score to the top ten list

			for (int i = 0; i < 10; i++) {
				// totMoves is fewer than the entry in the top ten list
				if (topTenFewestMoves.get(0).getMoves() > moves) {

					// insert top score in position
					topTenFewestMoves.add(i, new TopScoreModel(name, moves));

					// remove last place - LAST_PLACE + 1 because there is currently 1 extra value in array list
					topTenFewestMoves.remove(LAST_PLACE + 1);
					
					return true;
				}
			}

		return false;

	}
	
	/**
	 * Returns the array list of top ten scores
	 * @return ArrayList<TopScoreModel> - array list of top ten fewest moves
	 */
	public static ArrayList<TopScoreModel> getTopScoreList(){
		if (topTenFewestMoves ==null) createTopTen();
		return topTenFewestMoves;
	}
	
	private static void createTopTen(){
		
		topTenFewestMoves = new ArrayList<TopScoreModel>();
		for (int i = 0; i<10; i++){
			topTenFewestMoves.add(new TopScoreModel("-NO PLAYER-", MAX_VALUE));
		}
		
	}
	
	private static int getLastPlaceTotMoves(){
		return topTenFewestMoves.get(LAST_PLACE).getMoves();
	}

}
