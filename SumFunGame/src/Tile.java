import java.awt.Color;
import java.awt.Dimension;
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
		setSize(20,20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
		setVisible(true);
		setPreferredSize(new Dimension(10, 10));
		setOpaque(true);
		setBackground(Color.WHITE);
	}
	
	@Override
	public void update(java.util.Observable o, Object arg) {
		TileModel model = (TileModel) o;
		if (model.isBlank()) {
			this.setText("");
		} else {
			this.setText(Integer.toString(model.getNumber()));		
		}
	}
	
	public TileModel getTileModel() {
		return model;
	}
	
}
