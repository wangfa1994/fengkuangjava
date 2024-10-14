package unit07_basicclasss.c1;

public class ArgsTest {

	/**
	 * public：由 JVM 调用，为了让 JVM 自由调用，所以设置为 public
	 * static：JVM 调用这个方法时，不会先创建该主类对象
	 * void：没有值返回给 JVM
	 * args：JVM 传的参数
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("参数个数：" + args.length);
		for (String arg : args) {
			System.out.println(arg);
		}
	}

}
