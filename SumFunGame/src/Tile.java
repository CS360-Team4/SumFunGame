
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

//import javafx.beans.Observable;


public class Tile extends JButton implements Observer {

	private TileModel model;
	
	public Tile(TileModel observed) {
		model = observed;
		
		model.addObserver(this);
		setText(Integer.toString(observed.getNumber()));
		setFont(new Font("Arial", Font.BOLD, 30));
		setSize(20,20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
		setVisible(true);
		setPreferredSize(new Dimension(10, 10));
		setOpaque(true);
		setForeground(Color.WHITE);
		updateColor();
	}
	
	public void update(java.util.Observable o, Object arg) {
		TileModel model = (TileModel) o;
		if (model.isBlank()) {
			this.setText("");
		} else {
			this.setText(Integer.toString(model.getNumber()));		
		}
		this.updateColor();
	}
	
	public TileModel getTileModel() {
		return model;
	}
	
	public void setHintColor(){
		
		this.setBackground(Color.RED);
	}
	
	public void updateColor() {
		
		switch (this.getTileModel().getNumber()) {
			
			case 0:
				if (!this.getTileModel().isBlank()){
					this.setBackground(Color.BLACK);
					this.setForeground(Color.WHITE);
				} else {
					this.setBackground(Color.WHITE);
					this.setForeground(Color.BLACK);
				}
				break;
		
			case 1:
				this.setBackground(Color.decode("#e21d1d"));
				this.setForeground(Color.WHITE);
				break;
				
			case 2:
				this.setBackground(Color.decode("#fc9220"));
				this.setForeground(Color.WHITE);
				break;
				
			case 3:
				this.setBackground(Color.decode("#fcde20"));
				this.setForeground(Color.WHITE);
				break;
				
			case 4:
				this.setBackground(Color.decode("#57fc20"));
				this.setForeground(Color.WHITE);
				break;
				
			case 5:
				this.setBackground(Color.decode("#20fcf1"));
				this.setForeground(Color.WHITE);
				break;
				
			case 6:
				this.setBackground(Color.decode("#205efc"));
				this.setForeground(Color.WHITE);
				break;
				
			case 7:
				this.setBackground(Color.decode("#8a20fc"));
				this.setForeground(Color.WHITE);
				break;
				
			case 8:
				this.setBackground(Color.decode("#f120fc"));
				this.setForeground(Color.WHITE);
				break;
				
			case 9:
				this.setBackground(Color.decode("#fc207f"));
				this.setForeground(Color.WHITE);
				break;
			default:
		}
	}
	
}
