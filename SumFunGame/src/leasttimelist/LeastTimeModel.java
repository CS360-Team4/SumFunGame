package leasttimelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Scanner;

public class LeastTimeModel extends Observable implements Serializable {

	/**
	 *serialVersionUID so that changes to model does not corrupt serialized object
	 */
	private static final long serialVersionUID = 1L;
	private final int nameIndex = 0;
	private final int timesIndex = 1;
	private String[][] leastTimes;
	private int[] times;
	private Date[] dates;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	//leastTimeModel uses a 2d String array to maintain a list of the names and times
	//Operates as the model for the leastTimeList
	public LeastTimeModel() throws IOException {

		leastTimes = new String[10][2];
		times = new int[10];
		dates = new Date[10];
		createTopTen();// initialize to dummy list

		// this.totalTime = totalTime;

		saveLeastTime();
		setChanged();
		notifyObservers();
	}
	
	public LeastTimeModel(String[][] leastTimes, int[] times, Date[] dates) throws IOException {
		/*topScores = new String[10][10];
		File input = new File("TopTen.txt");
		Scanner scanFile = new Scanner(input);
		for(int i = 0; i < topScores.length; i++){
			topScores[i][0] = scanFile.next();
			topScores[i][1] = Integer.toString(scanFile.nextInt());
			scanFile.nextLine();
		}
		scanFile.close();*/
		
		this.leastTimes = new String[10][2];
		this.times = new int[10];
		this.dates = new Date[10];
		for(int i = 0; i < leastTimes.length; i++){
			this.leastTimes[i][0] = leastTimes[i][0];
			this.times[i] = times[i];
			this.dates[i] = dates[i];
			this.leastTimes[i][1] = leastTimes[i][1];
		}
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
	public boolean checkTime(String name, int time, Date date) {
		
		for(int i = 0; i < leastTimes.length - 1; i++) {
		
			//if score is high  enough to be in top add to list
			if(time < times[i]) {
				//System.out.println("checkscore model");
				addLeastTime(name, time, date, i);
				return true;
			}
		}

		return false;

	}

	//updates the topscore model
	//only called by checkScore(), takes the index of that the new score belongs in and moves the rest of the scores down accordingly
	private void addLeastTime(String name, int time, Date date, int index) {

		//move all scores down
		for(int i = leastTimes.length - 1 ; i > index; i--) {
			leastTimes[i][nameIndex] = leastTimes[i-1][nameIndex];
			leastTimes[i][timesIndex] = leastTimes[i-1][timesIndex];
			times[i] = times[i-1];
			dates[i] = dates[i-1];
		}
		
		leastTimes[index][nameIndex] = name;
		times[index] = time;
		leastTimes[index][timesIndex] = getTimeString(time);
		dates[index] = date;

		//System.out.println("added");
		try {
			saveLeastTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	//use to set topten to default values
	private void createTopTen() {
		// topTenFewestMoves = new ArrayList<TopScoreModel>();
		for (int i = 0; i < 10; i++) {
			// topTenFewestMoves.add(new TopScoreModel("-NO PLAYER-",
			// MAX_VALUE));
			leastTimes[i][0] = "NO_PLAYER";
			times[i] = 300000;
			leastTimes[i][1] = getTimeString(300000);
			dates[i] = new Date();
		}

	}

	
	//Serializes this object
	public void saveLeastTime() throws IOException {
		/*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"TopScore.ser"));
		out.writeObject(this);
		out.close();*/
		
		PrintWriter output = new PrintWriter("LeastTimes.txt");
		for(int i = 0; i < leastTimes.length; i++){
			output.print(leastTimes[i][nameIndex] + " ");
			output.print(Integer.toString(times[i]) + " ");
			output.print(formatter.format(dates[i]));
			output.println("");
		}
		output.close();
	}

	public int[] getTimes() {
		return times;
	}

	public void setTimes(int[] times) {
		this.times = times;
	}

	public String[][] getLeastTimes() {
		return leastTimes;
	}

	public void setLeastTimes(String[][] topScores) {
		this.leastTimes = topScores;
	}

	public int getTimesIndex() {
		return timesIndex;
	}
	
	public String getTimeString(int time){
		String timeString = "";
		if (time%60000 == 0){
			timeString = (Integer.toString(time/60000) + ":" + Integer.toString((time%60000)/1000) + "0");
		} else if(time%60000 > 0 && time%60000 < 10){
			timeString = (Integer.toString(time/60000) + ":0" + Integer.toString((time%60000)/1000));
		} else {
			timeString = (Integer.toString(time/60000) + ":" + Integer.toString((time%60000)/1000));
		}
		return timeString;
	}
	
	//Sorting the values in case the text file is in the wrong order
	public void sort() throws IOException{
		String tempString = "";
		int tempInt = 0;
		Date tempDate = null;
		
		for(int i = 0; i < 9; i++){
			if(times[i] > times[i+1]){
				tempInt = times[i];
				tempString = leastTimes[i][0];
				tempDate = dates[i];
				
				times[i] = times[i+1];
				dates[i] = dates[i+1];
				leastTimes[i][0] = leastTimes[i+1][0];
				leastTimes[i][1] = getTimeString(times[i+1]);
				
				leastTimes[i+1][0] = tempString;
				leastTimes[i+1][1] = getTimeString(tempInt);
				times[i+1] = tempInt;
				dates[i+1] = tempDate;
			}
		}
		saveLeastTime();
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}
}
