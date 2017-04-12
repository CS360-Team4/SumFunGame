import java.awt.Font;

import javax.swing.JLabel;

public class UntimedGame extends GameBoard {
	
	public UntimedGame(String name) {
		super(name);
		lblMovesLeft.setText(String.valueOf(TileQueue.movesLeft));
	}
	
}
