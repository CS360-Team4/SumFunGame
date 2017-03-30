import java.util.Observable;
import java.util.Random;

//Observable class for the model
public class Number extends Observable{
	
	Random rand = new Random();

	private int num;
	
	public Number(){
		num = rand.nextInt(10);
	}
	
	public String toString(){
		String output = "";
		output = Integer.toString(num);
		return output;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		setChanged();
		notifyObservers();
	}
}
