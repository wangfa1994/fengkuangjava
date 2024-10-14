package unit09_T.c92;

/**
 * @author alvin
 * @date 2020-04-23 23:28
 */
// 定义 Apple 类时使用了泛型声明
public class Apple<T> {
    // 使用 T 类型定义实例bianl
    private T info;
    public Apple(){}
    // 使用 T 类型类定义构造器
    public Apple(T info){
        this.info = info;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }

    public static void main(String[] args) {
        // 由于参给 T 形参的是 String，所以构造器参数只能是 String
        Apple<String> a1 = new Apple<>("苹果");
        System.out.println(a1.getInfo());
        // 只能是 Double 或 double
        Apple<Double> a2 = new Apple<>(5.67);
        System.out.println(a2.getInfo());
    }
}
