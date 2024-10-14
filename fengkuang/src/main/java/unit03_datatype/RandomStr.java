package unit03_datatype;

public class RandomStr
{
	/**
	 * 生成一个6 位的随机字符串
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 定义一个空字符串
		String result = "";
		// 进行6 次循环
		for(int i = 0 ; i < 6 ; i ++)
		{
			// 生成一个97-122 之间的int 类型整数
			int intVal = (int) (Math.random() * 26 + 97);
			//将 intVal 强制转换为 char 类型后连接到 result 后面
			result = result + (char)intVal;
		}
		//输出随机字符串
		System.out.println(result);

		//直接把5.6 赋值给 f1oat 类型变量将出现错误， 因为 5.6 默认是 doub1e 类型
		// float a = 5.6;
		float a = (float)5.6;
	}
	// 利用random类也可以生产处随机数
}
