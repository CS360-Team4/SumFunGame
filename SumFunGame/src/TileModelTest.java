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
	
	/**********************************************************************
	 ********************** BLACKBOX TESTING METHODS **********************
	 **********************************************************************/

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
	
	/**********************************************************************
	 ********************** GLASSBOX TESTING METHODS **********************
	 **********************************************************************/
	
	/**
	 * For getNeighbors(), to step through all options need following test cases:
	 * 1) All neighbors null
	 * 2) All neighbors blank
	 * 3) All neighbors non-null and non-blank
	 * 
	 */
	
	@Test
	public void testGetNeighborsAllNeighborsNull() {
		// all neighbors null
		TileModel x = new TileModel();
			
		
		x.setNorth(null);
		x.setSouth(null);
		x.setEast(null);
		x.setWest(null);
		x.setNortheast(null);
		x.setNorthwest(null);
		x.setSoutheast(null);
		x.setSouthwest(null);
		
		// execute
		int result = x.getSumMod();
		
		// assert
		assertEquals(0, result);
	}
	
	@Test
	public void testGetNeighborsAllNeighborsBlank() {
		// all neighbors blank
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		TileModel west = new TileModel();
		TileModel northeast = new TileModel();
		TileModel southwest = new TileModel();
		TileModel northwest = new TileModel();
		TileModel southeast = new TileModel();
		
		north.setBlank();
		south.setBlank();
		east.setBlank();
		west.setBlank();
		northwest.setBlank();
		northeast.setBlank();
		southwest.setBlank();
		southeast.setBlank();
			
		
		x.setNorth(north);
		x.setSouth(south);
		x.setEast(east);
		x.setWest(west);
		x.setNortheast(northeast);
		x.setNorthwest(southwest);
		x.setSoutheast(northwest);
		x.setSouthwest(southeast);
		
		// execute
		int result = x.getSumMod();
		
		// assert
		assertEquals(0, result);
	}
	
	@Test
	public void testGetNeighborsAllNeighborsReal() {
		// all neighbors non-blank, non-null
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		TileModel west = new TileModel();
		TileModel northeast = new TileModel();
		TileModel southwest = new TileModel();
		TileModel northwest = new TileModel();
		TileModel southeast = new TileModel();
		
		// All different, just for fun
		north.setNumber(9);
		south.setNumber(8);
		east.setNumber(7);
		west.setNumber(6);
		northeast.setNumber(5);
		southwest.setNumber(4);
		northwest.setNumber(3);
		southeast.setNumber(2);
		
		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(false);
		northwest.setBlank(false);
		southeast.setBlank(false);
		southwest.setBlank(false);
		
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
		assertEquals(4, result);
	}
	
	
	/**
	 * For getSumMod(), to step through all options need following test case:
	 * - At least 1 non-blank neighbor
	 * - At least 1 blank neighbor
	 */
	
	@Test
	public void testGetSumModGlassBox() {
		// at least one neighbor blank, at least one neighbor non-blank
		TileModel x = new TileModel();
		
		TileModel north = new TileModel();
		TileModel south = new TileModel();
		TileModel east = new TileModel();
		TileModel west = new TileModel();
		TileModel northeast = new TileModel();
		TileModel southwest = new TileModel();
		TileModel northwest = new TileModel();
		TileModel southeast = new TileModel();
		
		// All different, just for fun
		north.setNumber(9);
		south.setNumber(8);
		east.setNumber(7);
		west.setNumber(6);
		northeast.setNumber(5);
		southwest.setNumber(4);
		northwest.setNumber(3);
		southeast.setNumber(2);
		
		// Set half tiles non-blank, other half blank
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(true);
		northwest.setBlank(true);
		southeast.setBlank(true);
		southwest.setBlank(true);
		
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
		assertEquals(0, result);
	}

}
