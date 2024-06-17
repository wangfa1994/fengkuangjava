package unit04_array.practice;

public class PrintStar {
    public static void main(String[] args) {
        printStar(6);
    }
    public static void printStar(int n){
        for(int i=1; i<=n; i++){
            for(int j=1; j<=2*n-1; j++){
                if(j <= ((2*n-1) - (2*i-1))/2){
                    System.out.print(" ");
                } else if(j > ((2*n-1) - (2*i-1))/2 + (2*i-1)){
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
