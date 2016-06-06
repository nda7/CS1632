


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Random;


import org.mockito.*;



public class SimCityTest {

	@SuppressWarnings("unchecked")
	
	@Mock
	Location mockLocation = Mockito.mock(Location.class);
	Location mockLocation2 = Mockito.mock(Location.class);
	Random mockRandom = Mockito.mock(Random.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(mockLocation);
		MockitoAnnotations.initMocks(mockLocation2);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	// --------------------------------------------------
	// Command Line Argument Tests
	// --------------------------------------------------
	

	@Test
	//Check that entering a single integer command line argument causese checkInput to return true. (Base Case).
	public void testBaseArgs(){
		String[] arr = new String[]{"8"};
		assertTrue(SimCity.checkInput(arr));		
	}
	
	@Test
	//Test that entering more than one command line argument causes checkInput to return false.
	public void testNumArgs(){
		String[] arr = new String[]{"2","4"};
		assertFalse(SimCity.checkInput(arr));
	}
	
	@Test
	//Test that entering a non integer command line arguments causes checkInput to return false.
	public void testNonIntArg(){
		String[] arr = new String[]{"t"};
		assertFalse(SimCity.checkInput(arr));
	}
	
	@Test
	//Test that entering no command line arguments causes checkInput to return false.
	public void testNoArgs(){
		String[] arr = new String[1];
		assertFalse(SimCity.checkInput(arr));
	}
	
	// --------------------------------------------------
	// Location Class Method Tests
	// --------------------------------------------------
	
	@Test
	//Test that the Location constructor sets the name attribute correctly.
	public void testLocationName(){
		Location x = new Location("Tester");
		assertEquals("Tester",x.name);	
		}
	
	@Test
	//Test that the setDests method sets the correct values in the dest array in the Location object.
	public void testSetDest(){
		Location x = new Location("Tester");
		x.setDests(mockLocation, mockLocation2);
		assertEquals(x.dest[1], mockLocation2);
		assertEquals(x.dest[0], mockLocation);
	}
	
	@Test
	//Test that the set roads sets the correct roads in the Location object.
	public void testSetRoads() {
		Location x = new Location("Tester");
		x.setRoads("First", "Second");
		assertEquals(x.roads[0],"First");
		assertEquals(x.roads[1],"Second");
	}
	
	@Test
	//Test that getDest returns correct destinations for a given location.
	public void testGetDest(){
		Location x = new Location("Tester");
		x.dest[0] = mockLocation;
		x.dest[1] = mockLocation2;
		assertEquals(x.getDest(0),mockLocation);
		assertEquals(x.getDest(1),mockLocation2);	
	}
	
	@Test
	//Test that getRoad returns correct roads for a given location object.
	public void testGetRoads(){
		Location x = new Location("Tester");
		x.roads[0] = "First";
		x.roads[1] = "Second";
		assertEquals(x.getRoad(0),"First");
		assertEquals(x.getRoad(1),"Second");
	}
	
	@Test
	//Test that getName returns the correct name attribute of the Location object.
	public void testGetName(){
		Location x = new Location("Tester");
		assertEquals(x.getName(), "Tester");
	}
	
	@Test
	//Test that equals method says Locations with the same name are equal to each other.
	public void testEquals(){
		Location x = new Location("Name!");
		Location y = new Location("Name!");
		assertTrue(x.equals(y));
	}
	
	@Test
	//Test that equals method says Locations with different names are not equal to each other.
	public void testNotEquals(){
		Location x = new Location("Name!");
		Location y = new Location("Different Name!");
		assertFalse(x.equals(y));
	}
	
	@Test
	//Test that nextLocation() returns the value it's supposed to.
	public void testNextLocationTrue(){
		Mockito.when(mockRandom.nextInt(2)).thenReturn(1); // stub!
		assertEquals(mockRandom.nextInt(2), 1);
		Location hotel = new Location("hotel");
		Location library = new Location("library");
		hotel.dest[0]=mockLocation;
		hotel.dest[1]=library;
		hotel.roads[0]="Phil St.";
		hotel.roads[1]="Bill St.";
		assertTrue(SimCity.nextLocation(hotel, 0, mockRandom).equals(library));
		
		
	}
	
	@Test
	//Test that nextLocation() returns the value it's supposed to.
	public void testNextLocationFalse(){
		Mockito.when(mockRandom.nextInt(2)).thenReturn(0); // stub!
		assertEquals(mockRandom.nextInt(2), 0);
		Location hotel = new Location("hotel");
		Location library = new Location("library");
		Location whocares = new Location("I do!");
		hotel.dest[0]=whocares;
		hotel.dest[1]=library;
		hotel.roads[0]="Phil St.";
		hotel.roads[1]="Bill St.";
		assertFalse(SimCity.nextLocation(hotel, 0, mockRandom).equals(library));
		
		
	}
}
