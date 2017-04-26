// import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TileModelTest {

	/*
	 * ********************************************************
	 * Tests for getNeighbors
	 * ********************************************************
	 */
	@Test
	public void testGetNeighborsNoNeighbors() {
		// no neighbors
		TileModel x = new TileModel();
		
		// execute
		ArrayList<TileModel> neighbors = x.getNeighbors();
		
		// assert
		assertEquals(0, neighbors.size());
	}
	
	@Test
	public void testGetNeighborsOneNeighbor() {
		// no neighbors
		TileModel x = new TileModel();
		TileModel north = new TileModel();
		
		x.setNorth(north);
		
		// execute
		ArrayList<TileModel> neighbors = x.getNeighbors();
		
		// assert
		assertEquals(1, neighbors.size());
		assertEquals(north.getNumber(), neighbors.get(0).getNumber());
	}
	
	@Test
	public void testGetNeighborsFullNeighbors() {
		// all neighbors
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		TileModel west = new TileModel();
		TileModel northeast = new TileModel();
		TileModel southwest = new TileModel();
		TileModel northwest = new TileModel();
		TileModel southeast = new TileModel();
		
		
		x.setNorth(north);
		x.setSouth(south);
		x.setEast(east);
		x.setWest(west);
		x.setNortheast(northeast);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);
		
		// execute
		ArrayList<TileModel> neighbors = x.getNeighbors();
		
		// assert
		assertEquals(8, neighbors.size());
	}

	
	/*
	 * ********************************************************
	 * Tests for getSumMod
	 * ********************************************************
	 */
	@Test
	public void testGetSumModThreeNeighborsNoRemainder() {
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel northeast = new TileModel();
		TileModel east = new TileModel();
		
		north.setNumber(0);
		northeast.setNumber(0);
		east.setNumber(0);
		
		x.setNorth(north);
		x.setNortheast(northeast);
		x.setEast(east);
		
		int result = x.getSumMod();
		
		assertEquals(0, result);
	}
	
	@Test
	public void testGetSumModEightNeighbors() {
		// all neighbors
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		TileModel west = new TileModel();
		TileModel northeast = new TileModel();
		TileModel southwest = new TileModel();
		TileModel northwest = new TileModel();
		TileModel southeast = new TileModel();
		
		north.setNumber(9);
		south.setNumber(9);
		east.setNumber(9);
		west.setNumber(9);
		northeast.setNumber(9);
		southwest.setNumber(9);
		northwest.setNumber(9);
		southeast.setNumber(9);
		
		x.setNorth(north);
		x.setSouth(south);
		x.setEast(east);
		x.setWest(west);
		x.setNortheast(northeast);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);
		
		// execute
		int result = x.getSumMod();
		
		// assert
		assertEquals(2, result);
	}
	
	@Test
	public void testGetSumModMaxRemainder() {
		// all neighbors
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		
		north.setNumber(3);
		south.setNumber(3);
		east.setNumber(3);
		
		x.setNorth(north);
		x.setSouth(south);
		x.setEast(east);
		
		// execute
		int result = x.getSumMod();
		
		// assert
		assertEquals(9, result);
	}

}
