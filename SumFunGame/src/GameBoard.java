import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameBoard extends JFrame{
	
	private Tile[][] tiles;
	
	public GameBoard(){
		JPanel mainPanel = new JPanel();
		BorderLayout mainLayout = new BorderLayout();
		mainPanel.setLayout(mainLayout);
		
		JPanel gridPanel = new JPanel();
		GridLayout mainGrid = new GridLayout(9,9);
		gridPanel.setLayout(mainGrid);
		gridPanel.setVisible(true);
		gridPanel.setBackground(Color.GREEN);
		gridPanel.add(new Tile());
		
		JPanel queuePanel = new JPanel();
		GridLayout queueLayout = new GridLayout(5,1);
		queuePanel.setLayout(queueLayout);
		queuePanel.setVisible(true);
		queuePanel.setBackground(Color.BLUE);
		queuePanel.add(new Tile());
		
		mainPanel.add(queuePanel, BorderLayout.EAST);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.RED);
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tiles = new Tile[9][9];
	}
	
}
