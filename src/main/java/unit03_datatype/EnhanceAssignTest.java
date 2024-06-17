package unit03_datatype;

public class EnhanceAssignTest 
{
	public static void main(String[] args)
	{
		//下面语句出错，因为 5  默认是 int 类型， a+5 就是int 类型
		// 把 int 类型赋给 byte 类型的变量，所以出错
		byte a = 5;
		// a = a + 5;
		
		byte b = 5;
		b += 5;
	}
}
