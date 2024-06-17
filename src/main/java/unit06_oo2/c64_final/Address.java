package unit06_oo2.c64_final;

/**
 * 不可变类
 */
public class Address
{
    private final String detail;
    private final String postCode;
    public Address()
    {
        this.detail = "";
        this.postCode = "";
    }
    public Address(String detail, String postCode)
    {
        this.detail = detail;
        this.postCode = postCode;
    }
    public String getDetail()
    {
        return detail;
    }
    public String getPostCode()
    {
        return postCode;
    }
    // 重写 equals() 方法，判断两个对象是否相等
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj != null && obj.getClass() == Address.class)
        {
            Address address = (Address)obj;
            if(this.getDetail().equals(address.getDetail())
                && this.getPostCode().equals(address.getPostCode()))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode()
    {
        return detail.hashCode() + postCode.hashCode() * 31;
    }
}
