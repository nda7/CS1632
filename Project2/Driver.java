import java.util.Random;

public class Driver {
	
	private int driverNum;
	private Location currLocation;
	private Location nextLocation;
	private String road;
	private int start = 0;

	public Driver(int num){
		driverNum = num;
	}
	
	
	public String travel(Map m, Random rng){
		currLocation = nextLocation;
				
		if (start ==0){
			int startingLoc = rng.nextInt(5); // nextInt returns an integer between 0 (inc.) and its argument (exc.)
			currLocation = m.getLocation(startingLoc);
			start++;
		}
		
			int next = rng.nextInt(2);
			nextLocation = m.nextLocation(currLocation, driverNum, next);
			road = m.nextRoad(currLocation, next);

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
