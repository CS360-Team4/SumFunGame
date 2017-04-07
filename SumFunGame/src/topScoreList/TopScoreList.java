package topScoreList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class TopScoreList extends JFrame {
	
	//singleton
	private static TopScoreList topTenFrame;
	
	private static final int TOP_TEN_ROWS = 10;
	private static final int TOP_TEN_COLUMNS = 2;
	private static final String TOP_TEN_TITLE = "Top Ten List";

	private JPanel mainPanel;
	private JPanel topTenListPanel;

	JLabel[] playerNames;
	JLabel[] playerScores;

	private TopScoreList() {

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
			playerNames[i] = new JLabel(TopScoreModel.getTopScoreList().get(i).getName());

			playerScores[i] = new JLabel(TopScoreModel.getTopScoreList().get(i).getMoves() + "");
			
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
		setSize(1000, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle(TOP_TEN_TITLE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
	
	private void updatePlayerScores(){
		ArrayList<TopScoreModel> topScores = TopScoreModel.getTopScoreList();
		
		for (int i = 0; i<10; i++){			
			playerNames[i].setText(topScores.get(i).getName());
			playerScores[i].setText(topScores.get(i).getMoves() + "");			
		}
	}
	
	public static TopScoreList getTopScoreList(){
		if (topTenFrame==null){
			//TODO
			topTenFrame = new TopScoreList();
		}
		return topTenFrame;
	}
		
}