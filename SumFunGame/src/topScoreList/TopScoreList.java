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

import javax.swing.*;

public class TopScoreList extends JFrame implements Observer {
	
	//singleton
	private  TopScoreList topTenFrame;
	
	private  final int TOP_TEN_ROWS = 10;
	private  final int TOP_TEN_COLUMNS = 2;
	private  final String TOP_TEN_TITLE = "Top Ten List";
	
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
		topTenListPanel.setLayout(new GridLayout(TOP_TEN_ROWS, TOP_TEN_COLUMNS));
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
						
		
//		JLabel lblScoreTitle = new JLabel("Score: ");
//		lblScoreTitle.setFont(new Font("Arial", Font.BOLD, 20));
//		labelGridPanel.add(lblScoreTitle);		
//		score = 0;
//		lblScore = new JLabel(String.valueOf(score));
//		lblScore.setFont(new Font("Arial", Font.BOLD, 20));
//		labelGridPanel.add(lblScore);
		
//		queueBorderPanel.add(labelGridPanel, BorderLayout.SOUTH);

//		mainPanel.add(queueBorderPanel, BorderLayout.EAST);
		mainPanel.add(topTenListPanel, BorderLayout.CENTER);

		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.RED);
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle(TOP_TEN_TITLE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
	
	private void updatePlayerScores(){
		String[][] topScores = model.getTopScoreList();

		if (model.getNoPlayers() > 0) {
			for (int i = 0; i < model.getNoPlayers(); i++) {
//				playerNames[i].setText(topScores.get(i).getName());
//				playerScores[i].setText(topScores.get(i).getMoves() + "");
				
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
//		TileModel model = (TileModel) o;
//		if (model.isBlank()) {
//			this.setText("");
//		} else {
//			this.setText(Integer.toString(model.getNumber()));		
//		}
//		this.updateColor();
		
		updatePlayerScores();

	}
	

	public void loadTopScore() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("TopScore.ser"));
		model = (TopScoreModel) in.readObject();
	}
	
	
	
		
}