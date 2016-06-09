import java.util.Random;

public class Driver {
	
	public int driverNum;
	public Location currLocation;
	public Location nextLocation;
	public String road;
	public boolean firstTurn;

	public Driver(int num){
		driverNum = num;

		currLocation = null;
		nextLocation = null;
		road = null;
		firstTurn = true;
	}
	
	
	// travel() will move both currLocation and nextLocation one step forward.  Road will be updated
	// returns a String containing the name of the driver's next location
	public String travel(Map map, Random rng){
		currLocation = nextLocation; // advance driver to where their current location now should be
	
		// We want to ensure execution of at least one turn (i.e. if they start outside the city, have them come into the city).
		if (firstTurn){
			int startingLoc = rng.nextInt(map.getSize()); // nextInt returns an integer between 0 (inc.) and its argument (exc.)
			currLocation = map.getLocation(startingLoc);
			firstTurn = false;
		}
		
		// Now we want to *actually* travel.
		// Randomly choose between one of the two available locations to which the driver can go.
		// Using the same index (next) to get the location and road works because of how map was set up.
		int next = rng.nextInt(2);
		nextLocation = map.nextLocation(currLocation, next);
		road = map.nextRoad(currLocation, next);
		

		return nextLocation.getName();
	}
	
	public String printPath(){
		return("Driver " + driverNum + " is heading from " + currLocation.getName() + " to " + nextLocation.getName() + " via " + road + ".");
	}
	
	public String printFinal(){
	
		if (road.equals("Fourth Ave")){
			return("Driver " + driverNum + " has gone to Philadelphia!");
		}else{
			return("Driver " + driverNum + " has gone to Cleveland! That poor soul.");
		}
	}

}
