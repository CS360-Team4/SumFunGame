import java.util.Timer;
import java.util.TimerTask;

public class TimedGame extends GameBoard {

	private Timer timer;
	private int delay = 1000;
	private int period = 1000;
	private int timeSeconds = 300;
	
	public TimedGame(String name) {
		super(name);
		lblTime.setText("5:00");
		
		timer = new Timer();
		System.out.println(getTimeString());
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run(){
				setTime();
				String timeString = (getTimeString());
				System.out.println(timeString);
			}
		}, delay, period);
	}
	
	private void setTime() {
	    if (timeSeconds == 1)
	        timer.cancel();
	    timeSeconds--;
	}	
	
	private String getTimeString(){
		String timeString = "";
		if(timeSeconds%60 == 0){
			timeString = (Integer.toString(timeSeconds/60) + ":" + Integer.toString(timeSeconds%60) + "0");
		}
		else{
			timeString = (Integer.toString(timeSeconds/60) + ":" + Integer.toString(timeSeconds%60));
		}
		return timeString;
	}
}
