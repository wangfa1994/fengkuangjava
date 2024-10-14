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

		/**
		 * 表示一个基本多文种平面（BMP）字符
		 *
		 * 在 UTF-8 编码中，中文字符通常占用三个字节。UTF-8 是一种变长编码，根据字符的不同范围，使用的字节数也不同：
		 * ASCII 字符（0x0000 - 0x007F）：占用 1 个字节。
		 * BMP 字符（0x0080 - 0xFFFF）：占用 2 个字节。
		 * 增补字符（0x10000 - 0x10FFFF）：占用 4 个字节。
		 * 大部分中文字符（0x4E00 - 0x9FFF）：占用 3 个字节
		 *
		 *
		 */
	}
}
