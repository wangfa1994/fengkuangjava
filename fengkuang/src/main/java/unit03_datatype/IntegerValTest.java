package unit03_datatype;

public class IntegerValTest 
{
	public static void main(String[] args) 
	{
		// 下面代码是正确的，系统会自动把56 当成byte 类型处理
		byte a = 56;
		// 下面代码报错，byte 数据范围为 -128(-2的7次方) - 127(2的7次方-1)
		// byte b = 128;
		// byte c = -129;
		// 下面代码报错， int 数据范围为 -2147483648(-2的31次方 ) - 2147483647(2的31次方 - 1)
		// int intA = 2147483648;
		// 下面代码正确，1 为 int 类型，int 类型的值会自动类型转换到 long 类型。
		long longA = 1;
		// 下面代码错误，超出 int 范围。
		// long longB = 2147483648;
		// 下面代码正确
		long longC = 2147483648L;
		
		// =======================================
		// 以 0 开头的整数值是八进制的整数
		int octalValue = 013;
		System.out.println("octalValue:" + octalValue); // 11
		// 以 0X 开头的整数值是十六进制的整数
		int hexValue1 = 0X13;
		int hexValue2 = 0XAF;
		System.out.println("hexValue1:" + hexValue1); // 19
		System.out.println("hexValue2:" + hexValue2); // 160+15
		// 以0B开头的整数值是二进制的整数
		int binVal1 = 0B11010100;
		byte binVal2 = 0B01101001;
		int binVal3 = 0B110;
		int binVal4 = 0B10000000000000000000000000000011;
		System.out.println("binVal1:" + binVal1); // 212
		System.out.println("binVal2:" + binVal2); // 105
		System.out.println("binVal3:" + binVal3); // 6
		System.out.println("binVal4:" + binVal4); // -2147483645
		// =================================================
		// 无法从 int 直接转为 byte
		// byte binVal5 = 0B10000000000000000000000000000011;
		// long型第64位才是符号位
		// 但是 binVal6 赋值符号右边没有加L，其实是一个 int 类型数字，所以还是负数
		long binVal6 = 0B10000000000000000000000000000011; 
		System.out.println("binVal6:" + binVal6); // -2147483645
		// 要用二机制表示long，需要用L
		long binVal7 = 0B10000000000000000000000000000011L;
		System.out.println("binVal7:" + binVal7); // 2的31次方 + 3 = 2147483648 + 3
	}
}
