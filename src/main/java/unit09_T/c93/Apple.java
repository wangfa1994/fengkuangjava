package unit09_T.c93;

import java.io.Serializable;

/**
 * @author alvin
 * @date 2020-04-26 21:26
 */
public class Apple<T extends Number & Serializable> {
    T col;
    public static void main(String[] args){
        Apple<Integer> ai = new Apple<>();
        Apple<Double> ad = new Apple<>();
        // 编译报错，String 不是 Number 的子类
        //Apple<String> as = new Apple<>();
    }
}
