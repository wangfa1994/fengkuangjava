package com.wf;

import org.openjdk.jol.info.ClassLayout;

public class MarkWordExample {

   /* private int id;
    private String name;*/
    public static void main(String[] args) {
        MarkWordExample example=new MarkWordExample();
        System.out.println(ClassLayout.parseInstance(example).toPrintable());
    }
}
