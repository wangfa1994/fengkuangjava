package unit05_oo1;


public class Balana extends Fruit {
    public static void main(String[] args) {
        Balana b = new Balana();
        // Balana对象本身没有 weight 成员变量
        b.weight = 56;
        b.info();
    }
}