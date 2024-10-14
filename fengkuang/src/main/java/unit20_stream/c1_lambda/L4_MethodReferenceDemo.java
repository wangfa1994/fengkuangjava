package unit20_stream.c1_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

class Dog{
    private String name = "哮天犬";
    private int food = 10;
    public Dog(){}
    public Dog(String name){
        this.name = name;
    }
    public static void bark(Dog dog){
        System.out.println(dog.name + "叫了");
    }
    /**
     * 吃狗粮
     * JDK默认会把当前实例传入到非静态方法，参数名称为this，位置是第一个
     */
    public int eat(Dog this, int num){
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }
    public String getName()
    {
        return this.name;
    }
}

/**
 * lambda 表达式方法引用
 */
public class L4_MethodReferenceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(3);

        Consumer<String> consumerOrg1 = i -> System.out.println(i);
        // 方法引用（当输入输出参数相同，可以改写为方法引用）
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("接收的数据");

        // 静态方法的方法引用
        Consumer<Dog> consumerOrg2 = i -> Dog.bark(i);
        Consumer<Dog> consumer2 = Dog::bark;
        consumer2.accept(dog);

        // 非静态方法，使用对象实例的方法引用
        // Function<Integer, Integer> function = dog::eat;
        // 输入输出一样，可以变为一元函数形式
        // UnaryOperator<Integer> function = dog::eat;
        IntUnaryOperator unaryOrg = i -> dog.eat(i);
        IntUnaryOperator unary = dog::eat;
        System.out.println("还剩下：" + unary.applyAsInt(2) + "斤");

        // 使用类名来引用非静态方法
        BiFunction<Dog, Integer, Integer> eatFunctionOrg = (T, U) -> T.eat(U);
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("还剩下" + eatFunction.apply(dog,2)+"斤");

        // 无参构造函数的方法引用
        Supplier<Dog> supplierOrg = () -> new Dog();
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象：" + supplier.get().getName());

        // 有参构造函数的方法引用
        Function<String, Dog> function2Org = T -> new Dog(T);
        Function<String, Dog> function2 = Dog::new;
        System.out.println("创建了新对象：" + function2.apply("旺财").getName());

        List<String> list = new ArrayList<>();
        // 传值引用，lambda也是一样的原理。
        // 存疑。
        L4_MethodReferenceDemo demo = new L4_MethodReferenceDemo();
        demo.test(list);
        System.out.println(list.size());
    }

    private void test(List<String> list){
        list = null;
    }
}
