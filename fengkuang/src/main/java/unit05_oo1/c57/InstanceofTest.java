package unit05_oo1.c57;

public class InstanceofTest {
    public static void main(String[] args) {
        // 编译类型是Object，实际类型是String
        Object hello = "hello";
        System.out.println("String是否是Object类的实例："
                + (hello instanceof Object) );
        System.out.println("String是否是String类的实例："
                + (hello instanceof String));
        System.out.println("String是否是Math类的实例:"
                + (hello instanceof Math));
        // String实现了Comparable接口
        System.out.println("String是否是Comparable接口的实例："
                + (hello instanceof Comparable));
        String a = "hello";
        // String类与Math类没有继承关系，编译报错
//        System.out.println("String是否是Math类的实例："
//                + (a instanceof Math));
    }
}
