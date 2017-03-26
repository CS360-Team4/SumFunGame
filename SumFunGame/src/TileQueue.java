
public class TileQueue {

	private Tile[] queue;
	final int SIZE = 5;

	public TileQueue() {

		// initialize queue and tiles
		queue = new Tile[SIZE];
		for (int i = 0; i < SIZE; i++) {
			queue[i] = new Tile();
			System.out.print(queue[i].getNumber() + " ");

		}
	}

	public int pop() {

		// grab next tile from queue

		int temp = queue[0].getNumber();
		System.out.println("IN POP FUNCTION " + temp);
		

		// move remaining tiles in queue forward
		for (int i = 0; i < SIZE - 1; i++) {
			queue[i].setNumber(queue[i + 1].getNumber());
		}

		//System.out.println("POP");
		// new tile at end of queue
		queue[SIZE - 1].setNumber(new Tile().getNumber());


		return temp;
	}

	public Tile[] getQueue() {
		return queue;
	}

}
