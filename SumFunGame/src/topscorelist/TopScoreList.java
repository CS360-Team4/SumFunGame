package topscorelist;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class TopScoreList extends JFrame implements Observer {
	
	//singleton
	private  TopScoreList topTenFrame;
	
	private  final int topTenRows = 10;
	private  final int topTenColumns = 2;
	private  final String topTenTitle = "Top Ten List";
	
	private TopScoreModel model;

	private JPanel mainPanel;
	private JPanel topTenListPanel;
	
	JLabel[] playerNames;
	JLabel[] playerScores;

	public TopScoreList() throws IOException, ClassNotFoundException {

		//read model from serialized file
		loadTopScore();
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
		playerScores = new JLabel[10];
		
		String[][] temp = model.getTopScoreList();
		
		//initialize 
		for (int i = 0; i < temp.length; i++) {
			playerNames[i] = new JLabel(temp[i][0]);

			playerScores[i] = new JLabel(temp[i][1] + "");
			
			//System.out.println(temp[i][0]);
			topTenListPanel.add(playerNames[i]);
			topTenListPanel.add(playerScores[i]);
			
			if(playerNames[i].getText().equals("-NO PLAYER-")){
				playerNames[i].setVisible(false);
				playerScores[i].setVisible(false);
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
	
	//sets the jlabels of the top ten to the current score list
	public void updatePlayerScores(){
		String[][] temp = model.getTopScoreList();
		
		for (int i = 0; i < temp.length; i++) {
			playerNames[i].setText(temp[i][0]);

			playerScores[i].setText(temp[i][1] + "");
		}
	}
	
	//call the checkscore method for the model
	public boolean checkScore(String name, int score) {
		return model.checkScore(name, score);
	}
	
	public  TopScoreList getTopScoreList() throws IOException, ClassNotFoundException{
		if (topTenFrame==null){
			//TODO
			topTenFrame = new TopScoreList();
		}
		return this;
	}

	
	public void update(java.util.Observable o, Object arg) {
		
		updatePlayerScores();

	}
	

	public void loadTopScore() throws FileNotFoundException, IOException, ClassNotFoundException {
		if(new File("TopScore.ser").exists()){
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TopScore.ser"));
			model = (TopScoreModel) in.readObject();
			in.close();
		}
		else{
			model = new TopScoreModel();
		}
	}
	
	
	
		
}