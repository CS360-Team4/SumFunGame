import javax.swing.Timer;

public class TimedGame extends GameBoard {

	private Timer timer;
	
	public TimedGame(String name) {
		super(name);
		lblTime.setText("05:00");
	}
	
}
