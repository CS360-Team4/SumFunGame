
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
	 * ******************************************************** Tests for
	 * getNeighbors ********************************************************
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
	 * ******************************************************** Tests for
	 * getSumMod ********************************************************
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
	 * Need 17 tests whooooo
	 */

	@Test
	public void testGetNeighbors1() {
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

	// north = null
	@Test
	public void testGetNeighbors2() {
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

		x.setNorth(null);
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
		assertEquals(5, result);
	}

	// north = blank
	@Test
	public void testGetNeighbors3() {
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
		north.setBlank(true);
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
		assertEquals(5, result);
	}

	// south = null
	@Test
	public void testGetNeighbors4() {
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
		x.setSouth(null);
		x.setEast(east);
		x.setWest(west);
		x.setNortheast(northeast);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(6, result);
	}

	// south = blank
	@Test
	public void testGetNeighbors5() {
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
		south.setBlank(true);
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
		assertEquals(6, result);
	}

	// east = null
	@Test
	public void testGetNeighbors6() {
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
		x.setEast(null);
		x.setWest(west);
		x.setNortheast(northeast);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(7, result);
	}

	// east = blank
	@Test
	public void testGetNeighbors7() {
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
		east.setBlank(true);
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
		assertEquals(7, result);
	}

	// east = null
	@Test
	public void testGetNeighbors8() {
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
		x.setWest(null);
		x.setNortheast(northeast);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(8, result);
	}

	// west = blank
	@Test
	public void testGetNeighbors9() {
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

		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(true);
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
		assertEquals(7, result);
	}

	// NE = null
	@Test
	public void testGetNeighbors10() {
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

		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

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
		x.setNortheast(null);
		x.setSouthwest(southwest);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(7, result);
	}

	// NE = blank
	@Test
	public void testGetNeighbors11() {
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
		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(true);
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
		assertEquals(7, result);
	}

	// NE = null
	@Test
	public void testGetNeighbors12() {
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

		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

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
		x.setSouthwest(null);
		x.setNorthwest(northwest);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(7, result);
	}

	// NE = blank
	@Test
	public void testGetNeighbors13() {
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
		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(false);
		northwest.setBlank(true);
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
		assertEquals(7, result);
	}

	// NE = null
	@Test
	public void testGetNeighbors14() {
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

		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

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
		x.setNorthwest(null);
		x.setSoutheast(southeast);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(7, result);
	}

	// NE = blank
	@Test
	public void testGetNeighbors15() {
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
		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(false);
		northwest.setBlank(false);
		southeast.setBlank(true);
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
		assertEquals(7, result);
	}

	// NE = null
	@Test
	public void testGetNeighbors16() {
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

		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

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
		x.setSoutheast(null);

		// execute
		int result = x.getSumMod();

		// assert
		assertEquals(7, result);
	}

	// NE = blank
	@Test
	public void testGetNeighbors17() {
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
		north.setNumber(1);
		south.setNumber(1);
		east.setNumber(1);
		west.setNumber(1);
		northeast.setNumber(1);
		southwest.setNumber(1);
		northwest.setNumber(1);
		southeast.setNumber(1);

		// Set all tiles non-blank (should already be, but forcing to be sure)
		north.setBlank(false);
		south.setBlank(false);
		east.setBlank(false);
		west.setBlank(false);
		northeast.setBlank(false);
		northwest.setBlank(false);
		southeast.setBlank(false);
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
		assertEquals(7, result);
	}
	
	/**
	 * For getSumMod, 3 paths through code:
	 * 
	 * 1) [ nextTileExists ] => [ not blank] => finish
	 * 2) [ nextTileExists ] => [ is blank ] => :( (finish)
	 * 3) [ no nextTileExists ] => :( (finish)
	 * 
	 */
	
	@Test
	public void testGetSumModCase1() {
		TileModel x = new TileModel();

		TileModel north = new TileModel();
		north.setNumber(1);

		x.setNorth(north);

		int result = x.getSumMod();

		assertEquals(1, result);
	}
	
	@Test
	public void testGetSumModCase2() {
		TileModel x = new TileModel();

		TileModel north = new TileModel();
		north.setNumber(1);
		north.setBlank(true);

		x.setNorth(north);

		int result = x.getSumMod();

		assertEquals(0, result);
	}
	
	@Test
	public void testGetSumModCase3() {
		TileModel x = new TileModel();

		x.setNorth(null);

		int result = x.getSumMod();

		assertEquals(0, result);
	}
	
	

}
