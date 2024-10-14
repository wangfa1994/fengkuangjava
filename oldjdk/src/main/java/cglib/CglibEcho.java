package cglib;

public class CglibEcho {

    public String echo(String s){
        System.out.println("进入了代理对象");
        return s;
    }
}
