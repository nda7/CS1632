import java.util.Random;

public class SimCity {

	private static Random rng;
	private static Location[] locations;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		locations = new Location[5];
		
		if(checkInput(args)!=true){
			System.exit(1);
		};
		
		Location hotel = new Location("Hotel");
		Location diner = new Location("Diner");
		Location library = new Location("Library");
		Location college = new Location("College");
		Location outsideCity = new Location("Outside City");

		// Populate the 5 locations with their available destinations and roads by which to get there
		hotel.setDests(diner, library);
		hotel.setRoads("Fourth Ave", "Bill St");
		locations[0] = hotel;

		diner.setDests(outsideCity, college);
		diner.setRoads("Fourth Ave", "Phil St");
		locations[1] = diner;

		library.setDests(outsideCity, hotel);
		library.setRoads("Fifth Ave", "Bill St");
		locations[2] = library;

		college.setDests(library, diner);
		college.setRoads("Fifth Ave", "Phil St");
		locations[3] = college;

		outsideCity.setDests(hotel, college);
		outsideCity.setRoads("Fourth Ave", "Fifth Ave");
		locations[4] = outsideCity;

		// Construct the random number generator using the number from the command line argument
		rng = new Random(Integer.parseInt(args[0]));

		// Go through each of the five drivers and determine/output their paths
		// First up is Driver 0
		int drivers = 0;
		while(drivers<5){
			drivers=travel(drivers);
		}
		

		
}
		
	protected static boolean checkInput (String[] args){
		if (args.length != 1) {
			System.out.println("Please enter a single integer argument.");
			return false;
		}
		
		int v_arg;
		
		try{
			v_arg = Integer.parseInt(args[0]);
		}catch(NumberFormatException e){
			System.out.println("Please enter a single integer");
			return false;
		}
		
		return true;
	}

	public static int travel(int driverNum){
		int startingLoc = rng.nextInt(5); // nextInt returns an integer between 0 (inc.) and its argument (exc.)
		Location currLocation = locations[startingLoc];
		do{
			currLocation = nextLocation(currLocation, driverNum, rng);

		}while(!(currLocation.getName().equals("Outside City")));
		return driverNum+1;
	}
	
	public static Location nextLocation(Location currLocation, int driverNum, Random rng){
		int nextInt = 0;
		Location newLocation = null;
		try{
			nextInt = rng.nextInt(2);
			newLocation = currLocation.getDest(nextInt);	
		}catch(IllegalArgumentException iae){
			System.out.println("yaaaaaaaaaaaaaaaaaaayyyyyyyyyyyyyyyyyy");
			System.out.println(iae.getMessage());
			System.exit(1);
		}
		
		
		String newRoad = currLocation.getRoad(nextInt);
		System.out.print("Driver " + driverNum + " is heading from " + currLocation.getName());
		System.out.println(" to " + newLocation.getName() + " via " + newRoad + ".");

		if (newLocation.getName().equals("Outside City")){
			if (newRoad.equals("Fourth Ave")){
				System.out.println("Driver " + driverNum + " has gone to Philadelphia!");
			}else{
				System.out.println("Driver " + driverNum + " has gone to Cleveland! That poor soul.");
			}
			System.out.println("\n-----\n");
		}
		return newLocation;
	}

}
