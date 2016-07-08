import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


public class PinningTests {

	//************************************************************
	//
	//Testing: Cell.toString()
	//
	//************************************************************
	
	@Test
	//This will test that an alive cell will be converted to the string "X"
	public void testAliveString() {
		Cell c = new Cell();
		c.setAlive(true);
		assertEquals(c.toString(),"X");
	}
	
	@Test
	//This will test that a previously alive cell will be converted to the string "."
	public void testDeadString(){
		Cell c = new Cell();
		c.setAlive(true);
		c.setAlive(false);
		assertEquals(c.toString(),".");
	}
	
	@Test
	//This will test that a cell that was never alive is converted to the string "."
	public void testDormantString() {
		Cell c = new Cell();
		assertEquals(c.toString(),".");
	}
	
	//***************************************************************
	//
	//Testing: MainPanel.getNumNeighbors()
	//
	//***************************************************************
	@Test
	//This will test that the getNumNeigbors method will return 0
	//When a living cell has no living neighbors
	public void testNoNeighbors() {
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		c[5][5].setAlive(true);
		m.setCells(c);
		int test = m.getNumNeighbors(5, 5);
		assertEquals(0,test);
	}
	
	//This will test that the getNumNeigbors method returns 4 when a living
	//cell has 4 living neighbors.
	@Test
	public void testManyNeighbors(){
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		c[5][5].setAlive(true);
		c[5][4].setAlive(true);
		c[4][5].setAlive(true);
		c[7][9].setAlive(true); //not a neighbor
		c[5][6].setAlive(true);
		c[6][6].setAlive(true);
		m.setCells(c);
		int test = m.getNumNeighbors(5,5);
		assertEquals(4,test);
	}
	
	@Test
	//This will test that the getNumNeighbors method returns 2 when a cell
	//on the left edge of the grid has 2 "neighboring" cells on the right edge
	public void testEdgeCell(){
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		c[5][0].setAlive(true);
		c[5][9].setAlive(true);
		c[4][9].setAlive(true);
		m.setCells(c);
		int test = m.getNumNeighbors(5,0);
		assertEquals(2,test);		
	}
	
	//***************************************************************
	//
	//Testing: MainPanel.backup()
	//
	//***************************************************************
	
	@Test
	//Tests that the backup method creates an identical copy of the current _cells array
	//when there are alive cells
	public void testbackupAlive(){
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		c[5][5].setAlive(true);
		c[5][4].setAlive(true);
		c[4][5].setAlive(true);
		c[7][9].setAlive(true); //not a neighbor
		c[5][6].setAlive(true);
		c[6][6].setAlive(true);		
		m.setCells(c);
		m.backup();
		for (int j = 0; j < 10; j++) {
		    for (int k = 0; k < 10; k++) {
			assertEquals(c[j][k].toString(),m._backupCells[j][k].toString());		
		    }
		}
	}
	
	@Test
	//Test that the backup method creates and identical copy of the current _cells array
	//when all cells are dead
	public void testBackupDead(){
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		m.setCells(c);
		m.backup();
		for (int j = 0; j < 10; j++) {
		    for (int k = 0; k < 10; k++) {
			assertEquals(c[j][k].toString(),m._backupCells[j][k].toString());		
		    }
		}
	}
	
	@Test
	//Test that the backup method creates an identical copy of the current _cells array
	//after a run has been performed
	public void testBackupAfterRun(){
		MainPanel m = new MainPanel(10);
		Cell[][] c = generateCells(10);
		c[5][5].setAlive(true);
		c[5][4].setAlive(true);
		c[4][5].setAlive(true);
		c[7][9].setAlive(true); //not a neighbor
		c[5][6].setAlive(true);
		c[6][6].setAlive(true);
		m.setCells(c);
		m.run();
		c = m.getCells();
		m.backup();
		for (int j = 0; j < 10; j++) {
		    for (int k = 0; k < 10; k++) {
			assertEquals(c[j][k].toString(),m._backupCells[j][k].toString());		
		    }
		}
	}
	
	//****************************************************************************
	//
	// Manual Unit Tests
	//
	//****************************************************************************
	
	
	//***************************************************************
	//
	// Testing: MainPanel.runContinuous()
	//
	//***************************************************************
	
	//Identifier: Run Continuous Stop
	//Test Case: Stop Runs
	//Preconditions: The run continuous button has been clicked
	//Execution Steps: Click the Stop button
	//Postconditions: The program has stopped running
	
	//Identifier: Run Continuous Run
	//Test Case: Infinite Run
	//Preconditions: only 5 alive cells are present in a plus sign pattern
	//Execution Steps: Click run continuous
	//Postconditions: The program will run indefinitely, creating the same alive/dead cells
	
	//Identifier: Run Continuous Dead Run
	//Test Case: Limited Run
	//Preconditions: only 12 cells are alive, making a 0 pattern (5 cells tall, 3 cells wide)
	//Executions Steps: Click run continuous
	//Postconditions: No alive cells remain (all cells are grey or green)
	
	
	//Helper method to generate a 2D array of cells
	public Cell[][] generateCells(int size){
		
		Cell[][] c = new Cell[size][size];
		
		for (int j = 0; j < size; j++) {
		    for (int k = 0; k < size; k++) {
			c[j][k] = new Cell();
			c[j][k].setAlive(false);
		    }
		}
		return c;
	}

}
