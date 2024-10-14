package unit03_datatype;

public class EnhanceAssignTest 
{
	public static void main(String[] args)
	{
		String s =  "abc";
		System.out.println(s.length()+"=="+s.getBytes().length);

		String s1 = new String(  "中");
		System.out.println(s1.length()+"=="+s1.getBytes().length);

		//下面语句出错，因为 5  默认是 int 类型， a+5 就是int 类型
		// 把 int 类型赋给 byte 类型的变量，所以出错
		byte a = 5;
		// a = a + 5;
		
		byte b = 5;
		b += 5;
		System.out.println(b);
	}
}
