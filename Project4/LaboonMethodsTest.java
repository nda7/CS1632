import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Random;
public class LaboonMethodsTest {

	
	//This test will check that each value in the 
	//Laboonified array has been squared from its original value.
	//It will not check the last value (the sum) in the array.
	@Test
	public void testSquares() {
		
		//create new random array at each iteration and create the Laboonified version
		for (int i=0;i<10000;i++){
			
			int[] next = newArray();
			int[] test = LaboonMethods.laboonify(next);
			
			//traverse arrays
			for(int w=0;w<test.length-1;w++){
				
				//check each index of Laboonifed array against the squared value
				//of the original array 
				assertEquals(test[w],next[w]*next[w]);
//				System.out.println(test[w]+" "+next[w]*next[w]);
			}
		}
	}

	//This test will check that the last value in
	//the Laboonified array is the sum of all other values.
	@Test
	public void testSumofSquares(){
		
		//create new random array at each iteration and create the Laboonified version
		for (int i=0;i<10000;i++){
			
			int[] next = newArray();
			int[] test = LaboonMethods.laboonify(next);
			int sum = 0;
			
			//Traverse test array and sum all values except the last.
			for(int w=0;w<test.length-1;w++){
				sum+=test[w];
			}
			//Assert that the final value in the array is equal
			//to the sum computed above.
			assertEquals(sum, test[test.length-1]);
		}
	}
	
	//This test will check that the length of the
	//Laboonified array is one value longer than
	//the input array.
	@Test
	public void testLength(){
		
		//create new random array at each iteration and create the Laboonified version
		for (int i=0;i<10000;i++){
			
			int[] next = newArray();
			int[] test = LaboonMethods.laboonify(next);
			
			//Assert that the length of the Laboonified array
			//is equal to the length of the input array +1.
			assertEquals(test.length, next.length+1);
		}
	}
	
	//Helper method to generate Random length arrays between 0 and 100
	//with values between 1 and 100.
	public static int[] newArray(){
		Random r = new Random();
		int[] tester = new int[r.nextInt(100)+1];
		for(int i=0; i<tester.length;i++){
			tester[i]=r.nextInt(100)+1;
		}
		return tester;
	}
}
