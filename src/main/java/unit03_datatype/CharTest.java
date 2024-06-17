package unit03_datatype;

public class CharTest 
{
	public static void main(String[] args)
	{
		// 直接指定单个字符作为字符值
		char aChar = 'a';
		// 使用转义字符来作为字符值
		char enterChar = '\r';
		// 使用Unicode 编码值来指定字符值
		char ch = '\u9999';
		// 将输出一个'香' 字符
		System.out.println(ch);
		// 定义一个' 疯' 字符值
		char zhong = '疯';
		// 直接将一个char 变量当成int 类型变量使用
		int zhongValue = zhong ;
		System.out.println(zhongValue) ;
		// 直接把一个0 - 65535 范围内的int 整数赋给一个c har 变量
		char c = 97 ;
		System.out.println(c) ;
	}
}
