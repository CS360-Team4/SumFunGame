import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		
		//gridPanel.add(new Tile());
		tiles = new Tile[9][9];
			
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{

				//if tiles is on outer edge set it as blank tile
				if(i == 0 || j == 0 || i == tiles.length-1 || j == tiles[i].length -1){
					
					tiles[i][j] = new Tile(true);
				}
				else{
					tiles[i][j] = new Tile(false);
				}
				
				gridPanel.add(tiles[i][j]);
			}
		}
		
		JPanel queuePanel = new JPanel();
		JPanel queueBorderPanel = new JPanel();
		BorderLayout queueBorderLayout = new BorderLayout();
		queueBorderPanel.setLayout(queueBorderLayout);
		JPanel generalPanel = new JPanel();
		JPanel generalPanel2 = new JPanel();
		JPanel generalPanel3 = new JPanel();
		JPanel generalPanel4 = new JPanel();
		queueBorderPanel.add(generalPanel, BorderLayout.EAST);
		queueBorderPanel.add(generalPanel2, BorderLayout.WEST);
		queueBorderPanel.add(generalPanel3, BorderLayout.NORTH);
		queueBorderPanel.add(generalPanel4, BorderLayout.SOUTH);
		queueBorderPanel.add(queuePanel, BorderLayout.CENTER);
		queueBorderPanel.setPreferredSize(new Dimension(100,600));

		
		GridLayout queueLayout = new GridLayout(5,1);
		queuePanel.setLayout(queueLayout);
		queuePanel.setVisible(true);
		queuePanel.setBackground(Color.CYAN);
		
		for(int i=0; i<5; i++){
			queuePanel.add(new Tile(false));
		}
		
		mainPanel.add(queueBorderPanel, BorderLayout.EAST);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.RED);
		setSize(1000,800);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
}
