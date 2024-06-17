package unit06_oo2.c67;
interface Product
{
    public double getPrice();
    public String getName();
}
public class AnonymousTest {
    public void test(Product p)
    {
        System.out.println("购买一个" + p.getName()
            + "，花掉了" + p.getPrice());
    }

    public static void main(String[] args) {
        AnonymousTest ta = new AnonymousTest();
        // 调用 test 方法时，需要传入一个 Product 参数
        // 此处传入其匿名实现类的实例
        ta.test(new Product() {
            @Override
            public double getPrice() {
                return 567.8;
            }

            @Override
            public String getName() {
                return "AGP显卡";
            }
        });
    }
}
