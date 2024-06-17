package unit20_stream.c1_lambda;

import java.util.stream.IntStream;

public class MinDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        int min = Integer.MAX_VALUE;
        for (int i : arr){
            if(i<min){
                min = i;
            }
        }
        System.out.println(min);

        // jdk8
        int min2 = IntStream.of(arr).parallel().min().getAsInt();
        System.out.println(min2);
    }
}
