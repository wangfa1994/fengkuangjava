package unit14_annotation.c143.repeatable;

/**
 * @author alvin
 * @date 2020-05-04 10:21
 */
@RepTag(age = 5)
@RepTag(name = "alvin01", age = 9)
//@RepTags(value = {@RepTag(age = 5),@RepTag(age = 9)})
public class RepTagTest {
    public static void main(String[] args) {
        Class<RepTagTest> clazz = RepTagTest.class;
        // 使用 Java 8 新增的方法
        final RepTag[] tags = clazz.getDeclaredAnnotationsByType(RepTag.class);
        // 遍历修饰 RepTagTest 类的多个 RepTag 注解
        for(RepTag tag : tags) {
            System.out.println(tag.name() + "--->" + tag.age());
        }

        // 使用 Java 8 以前的方法
        final RepTags container = clazz.getDeclaredAnnotation(RepTags.class);
        System.out.println(container);
        RepTag[] value = container.value();
        for (RepTag repTag : value) {
            System.out.println(repTag.name()+"----->"+repTag.age());
        }

    }
}
