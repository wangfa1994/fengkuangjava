package unit09_T.c95;

/**
 * @author alvin
 * @date 2020-04-26 22:49
 */
class Apple<T extends Number>{
    T size;
    public Apple(){}
    public Apple(T size){
        this.size = size;
    }
    public void setSize(T size){
        this.size = size;
    }
    public T getSize(){
        return this.size;
    }
}
public class ErasureTest {
    public static void main(String[] args) {
        Apple<Integer> a = new Apple<>(6);
        Integer as = a.getSize();

        // 丢失尖括号信息
        Apple b = a;
        Number size1 = b.getSize();
        // 编译报错
        // Integer size2 = b.getSize();
    }
}
