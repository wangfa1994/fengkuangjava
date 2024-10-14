package unit09_T.c94;

/**
 * @author alvin
 * @date 2020-04-26 22:02
 */
class Foo{
    public <T> Foo(T t){
        System.out.println(t);
    }
}
public class GenericConstructor {
    public static void main(String[] args) {
        new Foo("aaa");
        new Foo(200);
        new <String> Foo("bbbb");
        // 报错
        // new <String> Foo(12.3);
    }
}
