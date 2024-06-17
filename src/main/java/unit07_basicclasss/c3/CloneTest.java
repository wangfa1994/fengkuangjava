package unit07_basicclasss.c3;

/**
 * @author alvin
 * @date 2020-05-24 9:07
 */
class Address {
    String detail;
    public Address(String detail) {
        this.detail = detail;
    }
}
class User implements Cloneable {
    int age;
    Address address;
    public User(int age) {
        this.age = age;
        address = new Address("广州天河");
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }
}
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        final User u1 = new User(20);
        final User u2 = u1.clone();
        // false
        System.out.println(u1 == u2);
        // true(浅克隆)
        System.out.println(u1.address == u2.address);
    }
}
