package unit15_javaio.c8.serialmechanism;

import unit15_javaio.c8.entity.PersonNormal;
import unit15_javaio.c8.entity.Teacher;

import java.io.Serializable;

/**
 * @author alvin
 * @date 2020-05-10 8:15
 * 思考如果序列化该对象，再反序列化，会得到几个 PersonNormal 对象？
 * 序列化机制：
 * 1. 所有保存到磁盘中的对象都有一个序列化编号
 * 2. 当程序试图序列化一个对象时，程序将先检查该对象是否已经被序列化过，只有未被序列化过，才会序列化该对象
 * 3. 如果已经序列化过，程序只是输出一个序列化编号，而不是再次重新序列化该对象
 */
public class SerialMachine implements Serializable {
    PersonNormal per = new PersonNormal("孙悟空", 500);
    Teacher t1 = new Teacher("唐僧", per);
    Teacher t2 = new Teacher("菩提老祖", per);
}
