package unit05_oo1;

public class ThislnConstructor {
    public int foo;
    public ThislnConstructor()
    {
        int foo = 0;
        this.foo = 6;
    }

    public static void main(String[] args) {
        System.out.println(new ThislnConstructor().foo);
    }
}
