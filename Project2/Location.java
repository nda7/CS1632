import java.util.Random;

public class Location {

	public String name;
	public Location[] dest = new Location[2];
	public String[] roads = new String[2];

	// Constructor
	public Location(String nameArg){
		name = nameArg;
	}

	// Set the two destinations reachable from this location.
	// These locations should accurately reflect the city setup shown in the project description.
	public void setDests(Location x, Location y){
		dest[0] = x;
		dest[1] = y;
	}

	// Set the two roads available for travel from this location.
	// Order of the roads should correspond to the order of the locations you can get to.
	public void setRoads(String r1, String r2){
		roads[0] = r1;
		roads[1] = r2;
	}

	// Retrieve one of the two possible destinations from this location.
	// num should be an integer either 0 or 1.
	public Location getDest(int num) throws IllegalArgumentException{
		if (num!=0 && num!=1){
			throw new IllegalArgumentException("Dun fukk'd uppppppppppp");
		}
		return dest[num];
	}

	// Retrieve whichever road we are taking to get to our destination.
	// num should be an integer either 0 or 1.
	public String getRoad(int num){
		return roads[num];
	}

	// Returns a String containing the name of this location.
	// Should only return one of "Hotel", "Diner", "Library", "College", or "Outside City."
	public String getName() {
		return name;
	}
	
	
	public boolean equals(Location loc){
		return loc.getName().equals(this.name);
	}
}
