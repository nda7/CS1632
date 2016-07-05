
public class LaboonMethods {
	
	//Test run to see method output
	public static void main(String args[]){
		int[] test = {1,2,3,4,5,6,7,8,9,10};
		int[] finals = laboonify(test);
		for(int w=0;w<finals.length;w++){
			System.out.println(finals[w]);
		}
	}
	
	//Create a new array based off input array.
	//The length will be x.length+1
	//Each index i in the input array will be mapped to i^2 in the new array
	//The last index in the new array will be the sum of the first length-1 indexes
	public static int[] laboonify(int[] x){
		int len = x.length;
		int [] labooned = new int[len+1];
		
		int sum = 0;
		
		for(int i=0;i<len;i++){
			labooned[i]=x[i];
			labooned[i]=labooned[i]*labooned[i];
			sum+=labooned[i];
		}
		
		labooned[len]=sum;
		return labooned;
	}
	
}
