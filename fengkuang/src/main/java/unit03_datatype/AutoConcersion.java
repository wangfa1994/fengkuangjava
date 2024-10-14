package unit03_datatype;

public class AutoConcersion 
{
	public static void main(String[] args)
	{
		byte byteValue = 10;
		short shortValue = byteValue;
		int intValue = shortValue;
		long longValue = intValue;
		float floatValue = longValue;
		double doubleValue = floatValue;
		
		char charValue = 'a';
		double doubleValue2 = charValue;
		// 下面代码将出错， byte 类型不能自动类型转换为char 类型
		// byte byteValue2 = charValue;
		
		System.out.println("byteValue:"+byteValue);
		System.out.println("shortValue:"+shortValue);
		System.out.println("intValue:"+intValue);
		System.out.println("longValue:"+longValue);
		System.out.println("floatValue:"+floatValue);
		System.out.println("doubleValue:"+doubleValue);
		System.out.println("doubleValue2:"+doubleValue2);
	}
}
