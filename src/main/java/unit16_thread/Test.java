package unit16_thread;

public class Test {
	
	public static void main(String[] args) {
		printStar(3);
	}
	
	private static void printStar(int n) {
		for(int i=0; i<2*n-1; i++){
			for(int j=0; j<2*n-1; j++){
				
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
