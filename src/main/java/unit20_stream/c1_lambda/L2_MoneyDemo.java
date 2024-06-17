package unit20_stream.c1_lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

interface IMoneyFormat{
    String format(int i);
}

class MyMoney{
    private final int money;

    public MyMoney(int money){
        this.money = money;
    }

    public void printMoney(IMoneyFormat moneyFormat){
        System.out.println("我的存款："+moneyFormat.format(this.money));
    }

    public void printMoney1(Function<Integer, String> moneyFormat){
        System.out.println("我的存款："+moneyFormat.apply(this.money));
    }
}

public class L2_MoneyDemo {
    public static void main(String[] args) {
        MyMoney me = new MyMoney(999999999);

        // 接口函数只有一个参数，括号可以省略
        me.printMoney(i -> new DecimalFormat("#,###").format(i));

        // 可以看出，lambda只需要知道接口函数的输入是什么，输出是什么即可。
        // 函数接口好处1：所以可以不需要接口，使用jdk自带的function即可，Function 本身就是一个函数式接口
        // 可以少定义一个文件呢
        me.printMoney1(i -> new DecimalFormat("#,###").format(i));

        // 函数接口好处2：链式操作
        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);
        me.printMoney1(moneyFormat.andThen(s -> "人民币" + s));
    }
}
