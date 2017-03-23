
public class TileQueue {
	
	
	private Tile[] queue;
	final int SIZE = 5;
	
	
	public TileQueue(){
		
		//initialize queue and tiles
		queue = new Tile[SIZE];
		for(int i = 0; i < SIZE; i++){
			queue[i] = new Tile(false);
		}
	}
	
	public Tile pop(){
		
		//grab next tile from queue
		Tile temp = queue[0];
		
		//move remaining tiles in queue forward
		for(int i = 0; i < SIZE - 1; i++){
			queue[i] = queue[i+1];
		}
		
		//new tile at end of queue
		queue[SIZE-1] = new Tile(false);
		return temp;
	}
	
	
	public Tile[] getQueue()
	{
		return queue;
	}

}
