package unit14_annotation.c142;

/**
 * @author alvin
 * @date 2020-05-03 23:04
 */
@Inheritable
class Base{}

// InheritableTest 只是继承了 Base 类
// 并未直接使用 @Inhetitalbe 修饰
public class InheritableTest extends Base{
    public static void main(String[] args) {
        // 打印 IngeritableTest 类是否有 @Inheritable 修饰
        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
    }
}
