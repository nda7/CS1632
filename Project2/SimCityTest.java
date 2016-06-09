


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
	SimCity mockSimCity = Mockito.mock(SimCity.class);
	Map mockMap = Mockito.mock(Map.class);
	
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
	//Check that entering a single integer command line argument causes checkInput to return true.
	//Here, we give it an array to represent args.  It contains a single string "8".
	//This is the base case.
	public void testBaseArgs(){
		String[] arr = new String[]{"8"};
		assertTrue(SimCity.checkInput(arr));		
	}
	
	@Test
	//Test that entering more than one command line argument causes checkInput to return false.
	//Here, we give it an array to represent args.  It contains two strings: "2" and "4".
	public void testNumArgs(){
		String[] arr = new String[]{"2","4"};
		assertFalse(SimCity.checkInput(arr));
	}
	
	@Test
	//Test that entering a non integer command line arguments causes checkInput to return false.
	//Here, we give it an array to represent args.  It contains a single string "t".
	public void testNonIntArg(){
		String[] arr = new String[]{"t"};
		assertFalse(SimCity.checkInput(arr));
	}
	
	@Test
	//Test that entering no command line arguments causes checkInput to return false.
	//Here, we give it an empty String array to represent args.
	public void testNoArgs(){
		String[] arr = new String[1];
		assertFalse(SimCity.checkInput(arr));
	}
	
	// --------------------------------------------------
	// Location Class Method Tests
	// --------------------------------------------------
	
	@Test
	//Test that the Location constructor sets the name attribute correctly.
	//Here we make a new location with a given name, and make sure that the method returns that same name
	public void testLocationName(){
		Location x = new Location("Tester");
		assertEquals("Tester", x.name);	
		}
	
	@Test
	//Test that the setDests method sets the correct values in the dest array in the Location object.
	//Here we make a new location.  We set its two destinations as our 2 mock locations with the method
	//We assert that both items in our dest array are equal to the values we wanted to set them as
	public void testSetDest(){
		Location x = new Location("Tester");
		x.setDests(mockLocation, mockLocation2);
		assertEquals(x.dest[1], mockLocation2);
		assertEquals(x.dest[0], mockLocation);
	}
	
	@Test
	//Test that the set roads sets the correct roads in the Location object.
	//Here we make a new location.  We set the two roads that extend from the location.
	//We assert that both roads in our roads array are equal to the values we set them as
	public void testSetRoads() {
		Location x = new Location("Tester");
		x.setRoads("First", "Second");
		assertEquals(x.roads[0],"First");
		assertEquals(x.roads[1],"Second");
	}
	
	@Test
	//Test that getDest returns correct destinations for a given location.
	//Here we make a new location.  We set its two destinations as our two mock locations.
	//We assert that both locations are properly returned when calling getDest()
	public void testGetDest(){
		Location x = new Location("Tester");
		x.dest[0] = mockLocation;
		x.dest[1] = mockLocation2;
		assertEquals(x.getDest(0),mockLocation);
		assertEquals(x.getDest(1),mockLocation2);	
	}
	
	@Test
	//Test that getRoad returns correct roads for a given location object.
	//Here we make a new location.  We set the two roads extending from it.
	//We assert that both roads are properly returned when calling getRoad()
	public void testGetRoads(){
		Location x = new Location("Tester");
		x.roads[0] = "First";
		x.roads[1] = "Second";
		assertEquals(x.getRoad(0),"First");
		assertEquals(x.getRoad(1),"Second");
	}
	
	@Test
	//Test that getName returns the correct name attribute of the Location object.
	//Here we make a new location with a name.  We assert that getName() returns the value we set it to
	public void testGetName(){
		Location x = new Location("Tester");
		assertEquals(x.getName(), "Tester");
	}
	
	@Test
	//Test that equals method says Locations with the same name are equal to each other.
	//Here we make two locations with the same name and assert that they are considered equal to each other
	public void testEquals(){
		Location x = new Location("Name!");
		Location y = new Location("Name!");
		assertTrue(x.equals(y));
	}
	
	@Test
	//Test that equals method says Locations with different names are not equal to each other.
	//Here we make two lcoations with different names and assert that they are considered not equal to each other
	public void testNotEquals(){
		Location x = new Location("Name!");
		Location y = new Location("Different Name!");
		assertFalse(x.equals(y));
	}
	
	
	// --------------------------------------------------
	// Map Class Tests
	// --------------------------------------------------
	
	@Test
	//Test that successfully adding a location to Map returns true
	//Here we make a new map.  It should not have any Locations in it.
	//Since this means there is room to add a Location, we assert that addLocation() returns true when we try
	public void testAddLocation(){
		Map m = new Map(1);
		assertTrue(m.addLocation(mockLocation));
	}
	
	@Test
	//Test that attempting to add more than the specified number of locations to Map returns false
	//Here we make a new map and fill its Location array.
	//Since this means there is no room to add a Location, we assert that addLocation() returns false when we try
	public void testAddLocationFailure(){
		Map m = new Map(1);
	    m.locArr[0]=mockLocation;
	    m.numLocs=1;
	    assertFalse(m.addLocation(mockLocation2));
	}
	
	@Test
	//Test that getLocation returns correct Location object
	//Here we make a new map.  We put a mock location into the first index of its Location array.
	//We assert that getLocation(0) returns the value that we just set it to.
	public void testGetLocation(){
		Map m = new Map(3);
		m.locArr[0]=mockLocation;
		assertEquals(m.getLocation(0),mockLocation);
	}
	
	@Test
	//Test that nextLocation returns a valid/correct adjacent location.
	//Here we make a new map.  We stub the Location.getDest() method to always return our mock location.
	//We then test calling the nextLocation() method by giving it a different mock location and the same index we stubbed with.
	public void testNextLocation(){
		Map m = new Map(4);
		Mockito.when(mockLocation.getDest(1)).thenReturn(mockLocation2);
		assertEquals(mockLocation2, m.nextLocation(mockLocation, 1));
	}
	
	@Test
	//Test that nextRoad returns the road at the given index of the Location road array.
	//Here we make a new map.  We stub the method that returns the road name.
	//We then assert that the road name returned is the one we expected.
	public void testNextRoad(){
		Map m = new Map(2);
		Mockito.when(mockLocation.getRoad(Mockito.anyInt())).thenReturn("Forbes Ave");
		assertEquals("Forbes Ave", m.nextRoad(mockLocation, 0));
	}
	
	@Test
	//Test that array size for map is returned.
	//Here we make a new map with a given size.
	//We assert that the number getSize() returns is the size that we initially established.
	public void testGetSize(){
		Map m = new Map(12);
		assertEquals(12, m.getSize());
	}
	
	// --------------------------------------------------
	// Driver Class Tests
	// --------------------------------------------------
	
	@Test
	//Test that driver goes to the correct next location.
	//Here we make a new driver.
	//We stub Map.getLocation(1) to return mockLocation.
	//We stub Random.nextInt([insert int here]) to always return 1 for consistency.  Randomness is not welcome here.
	//We stub Map.nextLocation() for our starting "location" and 1 (the two values we established we'd be giving it via the above stubs).
	//It is stubbed to return our end location.
	//Finally we stub the Map.nextRoad() method to return just some constant value we gave it.
	//	This is not super important to what is being checked, but we stub it just to avoid any potential issues.
	//We then call travel and test that the "next location" is equal to the one we expected it to be.
	//The reason we did not test the actual return value is because it is just nextLocation.getName().
	//	Stubbing the getName() method would be essentially useless to test. It would be the only relevant part and we fully control it.
	//	So instead we tested the object itself.
	public void testTravel(){
		Driver testDriver = new Driver(0);
		Mockito.when(mockMap.getLocation(1)).thenReturn(mockLocation);
		Mockito.when(mockRandom.nextInt(Mockito.anyInt())).thenReturn(1);
		Mockito.when(mockMap.nextLocation(mockLocation, 1)).thenReturn(mockLocation2);
		Mockito.when(mockMap.nextRoad(Mockito.any(Location.class), Mockito.anyInt())).thenReturn("Road to El Dorado");
		testDriver.travel(mockMap, mockRandom);
		assertEquals(mockLocation2, testDriver.nextLocation);
	}
	
	@Test
	//Test that printPath prints the correct output given sample parameters.
	//Here we make a new driver.
	//We set the driver's road variable as well as both of its location variables.
	//We then compare printPath()'s output to what we expect it to be given the variables we set, and make sure they're equal
	public void testPrintPath(){
		Driver testDriver = new Driver(0);
		testDriver.road="Haveteur Way";
		Mockito.when(mockLocation.getName()).thenReturn("Burger Kingdom");
		Mockito.when(mockLocation2.getName()).thenReturn("Laboontown");
		testDriver.currLocation=mockLocation;
		testDriver.nextLocation=mockLocation2;
		String compareString = "Driver 0 is heading from Burger Kingdom to Laboontown via Haveteur Way.";
		assertEquals(compareString, testDriver.printPath());
	}
	
	@Test
	//Test that printFinal prints the correct output if we do not go to Philadelphia.
	//Here we make a new driver.
	//We set its road variable to something not equal to "Fourth Ave".
	//Since "Fourth Ave" would bring us to Philly, and we know the road is not "Fourth Ave"... 
	//	We know we're going to Cleveland as it's the only other option.
	//	And, let's be real.  Where else would the Highway to Hell lead to?
	//We therefore compare the output given the variables we set to our expected output, and make sure they're equal.
	public void testPrintFinal(){
		Driver testDriver = new Driver(0);
		testDriver.road="Highway to Hell";
		String compareString = "Driver 0 has gone to Cleveland! That poor soul.";
		assertEquals(compareString, testDriver.printFinal());
		
	}
	
}
