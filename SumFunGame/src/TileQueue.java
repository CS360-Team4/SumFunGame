import java.awt.Color;

public class TileQueue {

	private Tile[] queue;
	final int SIZE = 5;
	public static int movesLeft;

	public TileQueue() {

		// initialize queue and tiles
		queue = new Tile[SIZE];
		for (int i = 0; i < SIZE; i++) {
			queue[i] = new Tile();
		}
		movesLeft = 50;
		queue[0].setBackground(Color.decode("#f28282"));
	}

	public int pop() {

		// get number of first tile in queue
		int temp = queue[0].getNumber();
		// move remaining tiles in queue forward
		for (int i = 0; i < SIZE - 1; i++) {
			queue[i].setNumber(queue[i + 1].getNumber());
		}
		
		// new tile at end of queue
		queue[SIZE - 1].setNumber(new Tile().getNumber());

		return temp;
	}

	public Tile getQueueTop() {
		return queue[0];
	}

	public Tile[] getQueue() {
		return queue;
	}

	public void setQueue(Tile[] queue) {
		this.queue = queue;
	}

}
