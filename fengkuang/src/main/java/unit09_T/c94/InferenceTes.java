package unit09_T.c94;

/**
 * @author alvin
 * @date 2020-04-26 22:19
 */
class MyUtil<E>{
    public static <Z> MyUtil<Z> nil(){
        return null;
    }
    public static <Z> MyUtil<Z> cons(Z head, MyUtil<Z> tail){
        return null;
    }
    E head(){
        return null;
    }
}
public class InferenceTes {
    public static void main(String[] args) {
        MyUtil<String> ls = MyUtil.nil();
        MyUtil<String> mu = MyUtil.<String>nil();
        MyUtil.cons(42, MyUtil.nil());
        MyUtil.cons(42, MyUtil.<Integer>nil());

        // 报错，推断不是万能的
        // String s = MyUtil.nil().head();
        String s = MyUtil.<String>nil().head();
    }
}
