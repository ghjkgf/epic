package com.example.gitoperate;

/**
 * @author zsl
 * @date 2023/1/13
 * @apiNote
 */
public class Test2 {
    public static void main(String[] args) {
        add(3,4);
        diff(4,9);
    }

    public static int add(int a,int b){
        return a+b>0 ? a+b : 0;
    }
    public static int diff(int a,int b){
        System.out.println("修改同一方法");
        return a-b;
    }

    public void TestBraces() {
        {
            System.out.println("A");
            System.out.println("B");
        }
        {
            System.out.println("试试小括号");
            System.out.println("修改括号二");
        }
    }
}
