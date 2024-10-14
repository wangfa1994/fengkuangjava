package unit04_array.practice;

public class Table99 {
    public static void main(String[] args) {
        for(int i=1; i<10; i++){
            for(int j=1; j<=i; j++){
                System.out.print(i + " x " + j + " = " + i*j + "  ");
            }
            System.out.println();
        }
    }
}
