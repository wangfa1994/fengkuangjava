package unit09_T.c94;

/**
 * @author alvin
 * @date 2020-04-26 22:05
 */
class MyClass<E>{
    public <T> MyClass(T t){
        System.out.println("t参数的值为：" + t);
    }
}
public class GenericDiarnondTest {
    public static void main(String[] args) {
        // E:String T:Integer
        MyClass<String> mc1 = new MyClass<>(5);
        // 显示指定 E T
        MyClass<String> mc2 = new <Integer>MyClass<String>(5);
        // 如果显示指定泛型构造器形参，此时不可使用形参，编译报错。
        // MyClass<String> mc3 = new <Integer>MyClass<>(5);
    }
}
