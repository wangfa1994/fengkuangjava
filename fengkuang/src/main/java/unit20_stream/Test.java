package unit20_stream;

public class Test {

    public static int sub(int[] A,int[] B){
        int mark=-1;
        for(int i=0;i<A.length-B.length+1;i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i + j] != B[j])
                    break;
                if (j == B.length - 1)
                    mark = i;
            }
        }
        return mark;
    }
    public static void main(String[] args){
        System.out.println(sub(new int[]{4,5,6,7,5,6,8},new int[]{5,6}));
        System.out.println(sub(new int[]{4,5,7,5,8},new int[]{5,6}));
        System.out.println(sub(new int[]{4,5,6,7,5,6,8},new int[]{6}));
        System.out.println(sub(new int[]{4,5,6,7,5,6,8},new int[]{4,5,6}));
    }

}
