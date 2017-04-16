import java.util.Random;

//import java.awt.Color;

public class TileQueue {

	private TileModel[] queue;
	final int SIZE = 5;
	public static int movesLeft;
	Random rand = new Random();

	public TileQueue() {

		// initialize queue and tiles
		queue = new TileModel[SIZE];
		for (int i = 0; i < SIZE; i++) {
			queue[i] = new TileModel();
		}
		movesLeft = 50;
	}

	public int pop() {

		// get number of first tile in queue
		int temp = queue[0].getNumber();
		// move remaining tiles in queue forward
		for (int i = 0; i < SIZE - 1; i++) {
			queue[i].setNumber(queue[i + 1].getNumber());
		}
		
		// new tile at end of queue
		queue[SIZE - 1].setNumber(new TileModel().getNumber());

		return temp;
	}

	public TileModel getQueueTop() {
		return queue[0];
	}

	public TileModel[] getQueue() {
		return queue;
	}

	public void setQueue(TileModel[] queue) {
		this.queue = queue;
	}
	
	//Instantiates a new queue and sets the queue equal to it
	public void resetQueue() {
		for(int i = 0; i < queue.length; i++) {
			queue[i].setNumber(rand.nextInt(10)); 
		}
	}

}
