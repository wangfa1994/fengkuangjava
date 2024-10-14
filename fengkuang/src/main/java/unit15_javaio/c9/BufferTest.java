package unit15_javaio.c9;

import java.nio.CharBuffer;

/**
 * @author Alvin.Li
 * CharBuffer 的基本使用
 * 		关键概念：position limit capacity mark
 * 		filp()：为读取 Buffer 中数据作准备 limit=position position=0
 * 		clear(): 为将数据写入 Buffer 做准备 limit=capacity position=0
 */
public class BufferTest {
	public static void main(String[] args) {
		// 创建 Buffer
		CharBuffer buff = CharBuffer.allocate(8);
		// 8 8 0
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());
		
		// 放入元素
		buff.put('a');
		buff.put('b');
		buff.put('c');
		System.out.println("放入三个元素后：");
		// 8 8 3
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		// 为读取数据做准备 limit=position position=0
		buff.flip();
		System.out.println("flip后：");
		// 8 3 0
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		System.out.println("第一个元素：" + buff.get());
		System.out.println("取出一个元素后：");
		// 8 3 1
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		// 为写入数据做准备 limit=capacity position=0
		buff.clear();
		System.out.println("clear后：");
		// 8 8 0
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		System.out.println("执行clear()后，缓冲区元素并没有被清除：例如还可以取到第三个元素：" + buff.get(2));
		
		System.out.println("执行绝对取数后：position 不变");
		// 8 8 0
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());
	}
}
