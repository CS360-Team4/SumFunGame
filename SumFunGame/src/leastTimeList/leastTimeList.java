package leastTimeList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class leastTimeList extends JFrame implements Observer {
	
	//singleton
	private  leastTimeList topTenFrame;
	
	private  final int topTenRows = 10;
	private  final int topTenColumns = 2;
	private  final String topTenTitle = "Top Ten Least Times";
	
	private leastTimeModel model;

	private JPanel mainPanel;
	private JPanel topTenListPanel;
	
	JLabel[] playerNames;
	JLabel[] playerTimes;

	public leastTimeList() throws IOException, ClassNotFoundException {

		//read model from serialized file
		loadLeastTimes();
		model.sort();  
		//or create a dummy list
		//model = new TopScoreModel();
		
		model.addObserver(this);
		// create mainpanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// create gridpanel to hold tiles
		topTenListPanel = new JPanel();
		topTenListPanel.setLayout(new GridLayout(topTenRows, topTenColumns));
		topTenListPanel.setVisible(true);
		topTenListPanel.setBackground(Color.WHITE);
		
		playerNames = new JLabel[10];
		playerTimes = new JLabel[10];
		
		String[][] temp = model.getLeastTimes();
		
		//initialize 
		for (int i = 0; i < temp.length; i++) {
			playerNames[i] = new JLabel(temp[i][0]);

			playerTimes[i] = new JLabel(temp[i][1] + "");
			
			//System.out.println(temp[i][0]);
			topTenListPanel.add(playerNames[i]);
			topTenListPanel.add(playerTimes[i]);
			
			if(playerNames[i].getText().equals("NO_PLAYER")){
				playerNames[i].setVisible(false);
				playerTimes[i].setVisible(false);
			}

			
		}

		mainPanel.add(topTenListPanel, BorderLayout.CENTER);

		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.RED);
		setSize(500, 400);
		setVisible(false);
		setLocationRelativeTo(null);
		setTitle(topTenTitle);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
	
	public leastTimeModel getModel() {
		return model;
	}

	public void setModel(leastTimeModel model) {
		this.model = model;
	}

	//sets the jlabels of the top ten to the current score list
	public void updatePlayerTimes(){
		model.sort();
		String[][] temp = model.getLeastTimes();
		
		for (int i = 0; i < temp.length; i++) {
			playerNames[i].setText(temp[i][0]);

			playerTimes[i].setText(temp[i][1] + "");
		}
	}
	
	//call the checkscore method for the model
	public boolean checkTime(String name, int time) {
		return model.checkTime(name, time);
	}
	
	public  leastTimeList getLeastTimeList() throws IOException, ClassNotFoundException{
		if (topTenFrame==null){
			//TODO
			topTenFrame = new leastTimeList();
		}
		return this;
	}

	
	public void update(java.util.Observable o, Object arg) {
		
		updatePlayerTimes();

	}
	

	public void loadLeastTimes() throws FileNotFoundException, IOException, ClassNotFoundException {
		/*if(new File("TopScore.ser").exists()){
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TopScore.ser"));
			model = (TopScoreModel) in.readObject();
			in.close();
		}
		else{
			model = new TopScoreModel();
		}*/
		if(new File("LeastTimes.txt").exists())	{
			String[][] temp = new String[10][2];
			int[] tempTimes = new int[10];
			File input = new File("LeastTimes.txt");
			Scanner scanFile = new Scanner(input);
			for(int i = 0; i < temp.length; i++){
				temp[i][0] = scanFile.next();
				tempTimes[i] = scanFile.nextInt();
				scanFile.nextLine();
				temp[i][1] = getTimeString(tempTimes[i]);
			}
				model = new leastTimeModel(temp, tempTimes);
				scanFile.close();
		}
		else{
			model = new leastTimeModel();
		}

		/*String[][] tester = model.getTopScores();
		for(int i = 0; i < tester.length; i++){
			System.out.println(tester[i][0]);
			System.out.println(tester[i][1]);
		}*/
		
	}	
	
	public String getTimeString(int time){
		String timeString = "";
		if ((time%60000)/1000 == 0){
			timeString = (Integer.toString(time/60000) + ":" + Integer.toString((time%60000)/1000) + "0");
		} else if((time%60000)/1000 > 0 && (time%60000)/1000 < 10){
			timeString = (Integer.toString(time/60000) + ":0" + Integer.toString((time%60000)/1000));
		} else {
			timeString = (Integer.toString(time/60000) + ":" + Integer.toString((time%60000)/1000));
		}
		return timeString;
	}
}