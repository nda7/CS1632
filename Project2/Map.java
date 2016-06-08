import java.util.Random;

public class Map {

	Location[] locArr;
	int numLocs;
	
	
	public Map(int size){
		locArr = new Location[size];
		numLocs = 0;
	}
	
	public boolean addLocation(Location newLoc){
		if (numLocs >=5){
			return false;
		}
		
		locArr[numLocs]=newLoc;
		numLocs++;
        return true;
	}
	
	public Location getLocation(int num){
		return locArr[num];
	}
	
	public Location nextLocation(Location currLocation, int driverNum, int nextInt){
		
		Location newLocation = null;
		try{
			newLocation = currLocation.getDest(nextInt);	
		}catch(IllegalArgumentException iae){
			System.out.print("Illegal Argument: ");
			System.out.println(iae.getMessage());
			System.exit(1);
		}
		return newLocation;
	}
	
	public String nextRoad(Location currLocation, int nextInt){
		return currLocation.getRoad(nextInt);
	}
}
