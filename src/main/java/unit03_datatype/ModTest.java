package unit03_datatype;

public class ModTest 
{
	public static void main(String[] args)
	{
		double a = 5.2;
		double b = 3.1;
		double mod = a % b;
		System.out.println(mod); // mod 的值为2.1
		System.out.println( "5 对0.0 求余的结果是:" + 5 % 0.0);   // 输出非数: NaN
		System.out.println( "-5.0 对0 求余的结果是:" + -5.0 % 0); // 输出非数: NaN
		System.out.println( "0对5.0 求余的结果是:" + 0 %5.0);    // 输出0.0
		System.out.println( "0对0.0 求余的结果是:" + 0 % 0.0);   // 输出非数: NaN
		// 下面代码将出现异常: java.1ang . ArithmeticException : by zero
		System.out.println( "-5 对0 求余的结果是:" + -5 % 0) ;
	}
}
