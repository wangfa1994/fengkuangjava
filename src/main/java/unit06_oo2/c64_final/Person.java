package unit06_oo2.c64_final;

public class Person
{
    private final Address address;
    public Person(Address address)
    {
        // 设置新对象，防止 Person 类的 address 成员变量被改
        this.address = new Address(address.getDetail(), address.getPostCode());
    }
    public Address getAddress()
    {
        // 返回新对象
        return new Address(address.getDetail(), address.getPostCode());
    }
}
