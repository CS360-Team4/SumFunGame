package leasttimelist;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class LeastTimeList extends JFrame implements Observer {
	
	//singleton
	private  LeastTimeList topTenFrame;
	
	private  final int topTenRows = 10;
	private  final int topTenColumns = 3;
	private  final String topTenTitle = "Top Ten Least Times";
	
	private LeastTimeModel model;

	private JPanel mainPanel;
	private JPanel topTenListPanel;
	
	JLabel[] playerNames;
	JLabel[] playerTimes;
	JLabel[] playerDates;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public LeastTimeList() throws IOException, ClassNotFoundException, ParseException {

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
		topTenListPanel.setLayout(new GridLayout(topTenRows+1, topTenColumns));
		topTenListPanel.setVisible(true);
		topTenListPanel.setBackground(Color.WHITE);
		
		JLabel playerTitle = new JLabel("Player");
		JLabel timeTitle = new JLabel("Time");
		JLabel dateTitle = new JLabel("Date");
		playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		timeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topTenListPanel.add(playerTitle);
		topTenListPanel.add(timeTitle);
		topTenListPanel.add(dateTitle);
		
		playerNames = new JLabel[10];
		playerTimes = new JLabel[10];
		playerDates = new JLabel[10];
		
		String[][] temp = model.getLeastTimes();
		Date[] tempDates = model.getDates();
		
		//initialize 
		for (int i = 0; i < temp.length; i++) {
			playerNames[i] = new JLabel(temp[i][0]);

			playerTimes[i] = new JLabel(temp[i][1] + "");
			
			playerDates[i] = new JLabel(formatter.format(tempDates[i]) + " ");
			
			//System.out.println(temp[i][0]);
			topTenListPanel.add(playerNames[i]);
			topTenListPanel.add(playerTimes[i]);
			topTenListPanel.add(playerDates[i]);
			playerNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerTimes[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerDates[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			if(playerNames[i].getText().equals("NO_PLAYER")){
				playerNames[i].setVisible(false);
				playerTimes[i].setVisible(false);
				playerDates[i].setVisible(false);
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
	
	public LeastTimeModel getModel() {
		return model;
	}

	public void setModel(LeastTimeModel model) {
		this.model = model;
	}

	//sets the jlabels of the top ten to the current score list
	public void updatePlayerTimes() throws IOException{
		model.sort();
		String[][] temp = model.getLeastTimes();
		Date[] tempDates = model.getDates();
		
		for (int i = 0; i < temp.length; i++) {
			playerNames[i].setText(temp[i][0]);

			playerTimes[i].setText(temp[i][1] + "");
			
			playerDates[i].setText(formatter.format(tempDates[i]));
		}
	}
	
	//call the checkscore method for the model
	public boolean checkTime(String name, int time, Date date ) {
		return model.checkTime(name, time, date);
	}
	
	public  LeastTimeList getLeastTimeList() throws IOException, ClassNotFoundException, ParseException{
		if (topTenFrame==null){
			//TODO
			topTenFrame = new LeastTimeList();
		}
		return this;
	}

	
	public void update(java.util.Observable o, Object arg) {
		
		try {
			updatePlayerTimes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void loadLeastTimes() throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
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
			Date[] tempDates = new Date[10];
			File input = new File("LeastTimes.txt");
			Scanner scanFile = new Scanner(input);
			for(int i = 0; i < temp.length; i++){
				temp[i][0] = scanFile.next();
				tempTimes[i] = scanFile.nextInt();
				tempDates[i] = formatter.parse(scanFile.next());
				temp[i][1] = getTimeString(tempTimes[i]);
			}
				model = new LeastTimeModel(temp, tempTimes, tempDates);
				scanFile.close();
		} else{
			model = new LeastTimeModel();
		}
		model.sort();

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