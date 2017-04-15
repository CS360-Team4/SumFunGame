import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;

public class UntimedGame extends GameBoard {
	
	public UntimedGame(String name) throws IOException, ClassNotFoundException {
		super(name);
		lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));
	}
	
}
