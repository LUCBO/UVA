package template;



import _465.Main;

public class Test {

	 public static int warOfNumbers(int[]numbers){
		 int sumEven = 0, sumOdd = 0;
		 for(int i=0; i<numbers.length; i++) {
		 if(numbers[i] % 2 == 0)
	            sumEven += numbers[i];
	        else
	            sumOdd += numbers[i];
		 }
		return sumEven-sumOdd;
			
	  }
	
	public static void main(String[] args) {
		
		Test t = new Test();
	}
}
