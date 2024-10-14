package unit06_oo2.c64_final;

class Cachelmmutale
{
    private static int MAX_SIZE=10;
    private static Cachelmmutale[] cache = new Cachelmmutale[MAX_SIZE];
    private static int pos = 0;
    private final String name;
    // 隐藏构造器，使得只能使用 valueOf 方法来创建实例
    private Cachelmmutale(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public static Cachelmmutale valueOf(String name)
    {
        for (int i = 0; i < MAX_SIZE; i++)
        {
            if(cache[i] != null && cache[i].getName().equals(name))
            {
                return cache[i];
            }
        }
        if(pos == MAX_SIZE)
        {
            cache[0] = new Cachelmmutale(name);
            pos = 1;
        }
        else
        {
            cache[pos++] = new Cachelmmutale(name);
        }
        // 走到这里，肯定是新对象，存到了缓存，然后pos+1
        return cache[pos - 1];
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj != null && obj.getClass() == Cachelmmutale.class)
        {
            Cachelmmutale ci = (Cachelmmutale)obj;
            return name.equals(ci.getName());
        }
        return false;
    }
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
}
public class CachelmmutaleTest
{
    public static void main(String[] args)
    {
        Cachelmmutale c1 = Cachelmmutale.valueOf("hello");
        Cachelmmutale c2 = Cachelmmutale.valueOf("hello");
        System.out.println(c1 == c2); // true
    }
}
