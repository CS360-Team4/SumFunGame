package topscorelist;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class TopScoreList extends JFrame implements Observer {
	
	//singleton
	private  TopScoreList topTenFrame;
	
	private  final int topTenRows = 10;
	private  final int topTenColumns = 3;
	private  final String topTenTitle = "Top Ten Most Points";
	
	private TopScoreModel model;

	private JPanel mainPanel;
	private JPanel topTenListPanel;
	
	JLabel[] playerNames;
	JLabel[] playerScores;
	JLabel[] playerDates;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public TopScoreList() throws IOException, ClassNotFoundException, ParseException {

		//read model from serialized file
		loadTopScore();
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
		
		playerNames = new JLabel[10];
		playerScores = new JLabel[10];
		playerDates = new JLabel[10];
		
		JLabel playerTitle = new JLabel("Player");
		JLabel scoreTitle = new JLabel("Score");
		JLabel dateTitle = new JLabel("Date");
		playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topTenListPanel.add(playerTitle);
		topTenListPanel.add(scoreTitle);
		topTenListPanel.add(dateTitle);

		String[][] temp = model.getTopScores();
		Date[] tempDates = model.getDates();
		
		//initialize 
		for (int i = 0; i < temp.length; i++) {
			playerNames[i] = new JLabel(temp[i][0]);

			playerScores[i] = new JLabel(temp[i][1] + "");
			
			playerDates[i] = new JLabel(formatter.format(tempDates[i]) + " ");
			
			//System.out.println(temp[i][0]);
			topTenListPanel.add(playerNames[i]);
			topTenListPanel.add(playerScores[i]);
			topTenListPanel.add(playerDates[i]);
			playerNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerScores[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerDates[i].setHorizontalAlignment(SwingConstants.CENTER);

			if(playerNames[i].getText().equals("NO_PLAYER")){
				playerNames[i].setVisible(false);
				playerScores[i].setVisible(false);
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
	
	public TopScoreModel getModel() {
		return model;
	}

	public void setModel(TopScoreModel model) {
		this.model = model;
	}

	//sets the jlabels of the top ten to the current score list
	public void updatePlayerScores() throws IOException{
		model.sort();
		String[][] temp = model.getTopScores();
		Date[] tempDates = model.getDates();
		
		for (int i = 0; i < temp.length; i++) {
			playerNames[i].setText(temp[i][0]);

			playerScores[i].setText(temp[i][1] + "");
			
			playerDates[i].setText(formatter.format(tempDates[i]));
		}
	}
	
	//call the checkscore method for the model
	public boolean checkScore(String name, int score, Date date) {
		return model.checkScore(name, score, date);
	}
	
	public  TopScoreList getTopScoreList() throws IOException, ClassNotFoundException, ParseException{
		if (topTenFrame==null){
			//TODO
			topTenFrame = new TopScoreList();
		}
		return this;
	}

	
	public void update(java.util.Observable o, Object arg) {
		
		try {
			updatePlayerScores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void loadTopScore() throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
		/*if(new File("TopScore.ser").exists()){
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TopScore.ser"));
			model = (TopScoreModel) in.readObject();
			in.close();
		}
		else{
			model = new TopScoreModel();
		}*/
		if(new File("TopScore.txt").exists())	{
			String[][] temp = new String[10][2];
			Date[] tempDates = new Date[10];
			File input = new File("TopScore.txt");
			Scanner scanFile = new Scanner(input);
			for(int i = 0; i < temp.length; i++){
				temp[i][0] = scanFile.next();
				temp[i][1] = Integer.toString(scanFile.nextInt());
				tempDates[i] = formatter.parse(scanFile.next());
			}
				model = new TopScoreModel(temp, tempDates);
				scanFile.close();
		} else{
			model = new TopScoreModel();
		}
		
		model.sort();

		/*String[][] tester = model.getTopScores();
		for(int i = 0; i < tester.length; i++){
			System.out.println(tester[i][0]);
			System.out.println(tester[i][1]);
		}*/
		
	}	
}