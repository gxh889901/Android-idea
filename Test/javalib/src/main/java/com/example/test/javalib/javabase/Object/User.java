package com.example.test.javalib.javabase.Object;

import static java.lang.System.out;
//JavaBean每一个成员实例变量都被private修饰，并都提供了public修饰的setter和gettter方法
public class User{
    static int eyeNum = 2;
    protected String name ="kitty";
    private int age;

    public User(){

    }

    public User(String name){
        this.name = name;
    }

    public User(String name, int age){
        this(name);//使用this()的形式调用其他不同参数的构造器 而且必须放在代码的第一行
        this.age = age;
    }

    public void setName(String name){
        if(name == null || name.length() < 2 || name.length() > 6){
            System.out.println("用户名不合法");
        }else {
            this.name = name;
        }
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        if(age < 0 || age > 100){
            System.out.println("年龄不合法");
        }else {
            this.age = age;
        }
    }

    public int getAge(){
        return age;
    }

    public void getInfo(){
        out.println("I am "+name);
    }
}

class ManUser extends User{ //只能继承一个父类
    //子类不能继承父类的私有变量，只能通过继承的方法访问父类的私有变量
    public ManUser(){
        //there is no default constructor
        // 子类调用构造函数时，必须先调用父类的构造函数，如果父类中没有找到合适的构造函数就会报错
        //super();//会默认调用还是必须显式调用？？？
        name = "Man";
    }

}
