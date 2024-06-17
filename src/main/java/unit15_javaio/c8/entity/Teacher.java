package unit15_javaio.c8.entity;

import java.io.Serializable;

/**
 * @author alvin
 * @date 2020-05-10 8:12
 */
public class Teacher implements Serializable {
    private String name;
    private PersonNormal student;
    public Teacher(String name, PersonNormal student) {
        this.name = name;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonNormal getStudent() {
        return student;
    }

    public void setStudent(PersonNormal student) {
        this.student = student;
    }
}
