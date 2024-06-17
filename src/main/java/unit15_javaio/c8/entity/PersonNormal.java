package unit15_javaio.c8.entity;

import unit15_javaio.c8.objstream.Human;

import java.io.Serializable;

/**
 * @author Alvin.Li
 */
public class PersonNormal extends Human implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public PersonNormal(String name, int age)
	{
		System.out.println("有参构造器");
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}