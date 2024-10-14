package unit03_datatype;

public class AutoPromote 
{
	public static void main(String[] args)
	{
		short sValue = 5;
		// 表达式中的 sValue 将自动提升到 int 类型，则右边的表达式类型为 int
		// 将一个 int 类型值赋给 short 类型变量将发生错误
		// sValue = sValue -2; //sValue = (short) (sValue -2);
		
		byte b = 40;
		char c = 'a';
		int i = 23 ;
		double d = .314;
		// 右边表达式中最高等级操作数为 double 类型
		// 则右边表达式的类型为 double 类型， 故赋给一个 double 类型变量
		double result = b + c + i * d;
		// 将输出144.222
		System.out.println(result);
		
		int val = 3;
		// 右边表达式中两个操作数都是 int 类型，故右边表达式的类型为 int
		// 虽然 23/3 不能除尽，但依然得到一个int 类型整数
		int intResult = 23/val;
		System.out.println(intResult); // 将输出7
		
		// 输出字符串Hello!a7
		System.out.println("Hello!" + 'a' + 7);
		// 输出字符串104Hell o !
		System.out.println('a' + 7 + "Hello!");


	}
}
