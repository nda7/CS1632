import java.util.Random;

public class SimCity {

	private static Random rng;
	private static Location[] locations;
	private static Map map;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Main first checks the input argument to make sure it's valid.
		if(checkInput(args)!=true){
			System.exit(1);
		};
		
		// Create and populate the city map
		map = new Map(5);
		
		Location hotel = new Location("Hotel");
		Location diner = new Location("Diner");
		Location library = new Location("Library");
		Location college = new Location("College");
		Location outsideCity = new Location("Outside City");

		// Populate the 5 locations with their available destinations and roads by which to get there
		hotel.setDests(diner, library);
		hotel.setRoads("Fourth Ave", "Bill St");

		diner.setDests(outsideCity, college);
		diner.setRoads("Fourth Ave", "Phil St");

		library.setDests(outsideCity, hotel);
		library.setRoads("Fifth Ave", "Bill St");

		college.setDests(library, diner);
		college.setRoads("Fifth Ave", "Phil St");

		outsideCity.setDests(hotel, college);
		outsideCity.setRoads("Fourth Ave", "Fifth Ave");

		map.addLocation(hotel);
		map.addLocation(diner);
		map.addLocation(college);
		map.addLocation(library);
		map.addLocation(outsideCity);

		// Construct the random number generator using the number from the command line argument.
		rng = new Random(Integer.parseInt(args[0]));

		// Go through each of the five drivers and determine/output their paths, starting with Driver 0.
		for(int drivers = 0 ; drivers < 5 ; drivers++){
			Driver currDriver = new Driver(drivers);
			
			// Continue to call travel() on the driver until they leave the city.
			while(!currDriver.travel(map, rng).equals("Outside City")){
				System.out.println(currDriver.printPath());
			}
			System.out.println(currDriver.printPath()); // Print the road they took to leave the city.
			System.out.println(currDriver.printFinal()); // Print which city they're going to.
			System.out.println("\n-----\n");
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
}
