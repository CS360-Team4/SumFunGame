package topScoreList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		loadTopScore();
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
		
		for (int i = 0; i<10; i++) {
			playerNames[i] = new JLabel("- NO PLAYER - ");

			playerScores[i] = new JLabel(9999 + "");
			
			topTenListPanel.add(playerNames[i]);
			topTenListPanel.add(playerScores[i]);

			
		}
		
		mainPanel.add(topTenListPanel, BorderLayout.CENTER);

		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.RED);
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle(topTenTitle);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
	
	private void updatePlayerScores(){
		String[][] topScores = model.getTopScoreList();

		if (model.getNoPlayers() > 0) {
			for (int i = 0; i < model.getNoPlayers(); i++) {
				
				playerNames[i].setText("Test");
				playerScores[i].setText(model.getNoPlayers() + "");
			}
		}
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
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("TopScore.ser"));
		model = (TopScoreModel) in.readObject();
	}
	
	
	
		
}