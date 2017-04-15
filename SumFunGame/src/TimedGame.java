import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

public class TimedGame extends GameBoard {

	private Timer timer;
	private int timeLeft = (1000*60*5);
	
	public TimedGame(String name) throws IOException, ClassNotFoundException {
		super(name);
		lblTime.setText("5:00");
		
		timer = new Timer(timeLeft,new timerListener());
		System.out.println(getTimeString());
	}
	
	private class timerListener implements ActionListener{
		
	    public void actionPerformed(ActionEvent e)
	    {
	        timeLeft -= 1000;
	        //SimpleDateFormat df=new SimpleDateFormat("mm:ss:S");
	       // jLabel1.setText(df.format(timeLeft));
	        if(timeLeft<=0)
	        {
	            timer.stop();
	        }
	    }
	};
	
	private String getTimeString(){
		String timeString = "";
		if(timeLeft%60000 == 0){
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000) + "0");
		}
		else{
			timeString = (Integer.toString(timeLeft/60000) + ":" + Integer.toString((timeLeft%60000)/1000));
		}
		return timeString;
	}
}
