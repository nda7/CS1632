import java.util.Random;

public class Map {

	public Location[] locArr;
	public int numLocs;
	
	//Constructor
	public Map(int size){
		locArr = new Location[size];
		numLocs = 0;
	}
	
	//adds Location to next index in locArr
	public boolean addLocation(Location newLoc){
		if (numLocs > locArr.length-1){
			return false;
		}
		
		locArr[numLocs]=newLoc;
		numLocs++;
        return true;
	}
	
	public int getSize(){
		return locArr.length;
	}
	
	//return Location at index num in locArr
	public Location getLocation(int num){
		return locArr[num];
	}
	
	//return the Location specified by given parameters
	public Location nextLocation(Location currLocation, int nextInt){
		
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
	
	//return the road specified by given parameters
	public String nextRoad(Location currLocation, int nextInt){
		return currLocation.getRoad(nextInt);
	}
}
