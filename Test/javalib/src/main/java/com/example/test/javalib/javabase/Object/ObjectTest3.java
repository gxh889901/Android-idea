package com.example.test.javalib.javabase.Object;

import java.util.Arrays;
import static java.lang.System.*;

public class ObjectTest3 {
    public static void main(String[] args){
        //自动装箱 基本类型赋值给引用类型
        Integer inObj = 5;
        Object boolObj = true;

        //自动拆箱 引用类型赋值给基本类型
        int it = inObj;
        if(boolObj instanceof Boolean){
            boolean b = (Boolean) boolObj;
            out.println(b);
        }

        //parseXxx(String s) 把字符串转为对应的类型的值
        //Xxx(String s) 包装类提供的构造器
        //String类的ValueOf()可以将基本类型转为字符串
        String str = "123";
        String str2 = "123.4";
        String str1= "kkkk";
        int int1 = Integer.parseInt(str);
        out.println(int1);
        int int2 = new Integer(str); //String类型构造参数
        //报NumberFormatException异常
        //int intStr2 = Integer.parseInt(str1);
        float f1 = Float.parseFloat(str2);
        out.println(f1);
        float f2 = new Float(str2);
        out.println(f2);
        String str3 = String.valueOf(2.345f);
        out.println(str3);
        String str4 = String.valueOf(2.34);
        out.println(str4);
        String str5 = String.valueOf(true);
        out.println(str5.toUpperCase());
        //包装类可以直接与基本类型比较
        Integer a = new Integer(6);
        out.println("6的包装实例是否大于5"+ (a > 5));
        //两个包装类进行比较，比较的是引用类型，只有对象完全相同，才相等
        out.println(new Integer(2) == new Integer(2)); //不是同一个对象为false
        Integer inta = 2;
        Integer intb = 2;
        out.println("两个2自动装箱是否相等"+(inta == intb)); //返回true
        Integer biga = 128;
        Integer bigb = 128;
        out.println("两个128自动装箱是否相等"+(biga == bigb));//返回false
        //看Integer包装类源码，它创建了一个-128到127的256个Integer实例放在cache数组内部
        //这个范围内的自动装箱都是从数组里取数据，并没有创建新的实例，但是超出范围的就需要重新创建实例
        //源码学习：把创建成本较大，需要频繁使用的对象缓存起来，提高程序的运行性能
        //包装静态compare(xxx val , xxx val2)比较方法
        out.println(Boolean.compare(true, false)); //连个boolean值是不能直接比较的

        //包装类转换为对应的无符号数
        byte b = -3;
        out.println("byte类型的-3对应的无符号整数"+Byte.toUnsignedInt(b));
        int val = Integer.parseUnsignedInt("abcdf", 16);//abcdf是16进制的展示
        out.println(val);
        out.println(Integer.toUnsignedString(-12, 16));
        out.println(Integer.divideUnsigned(-2, 3));
        out.println(Integer.remainderUnsigned(-2, 7));

        //处理对象函数
        //toString方法
        //不重写Object的toString方法打印的是类名+@+hashcode
        //打印对象信息需要重写toString方法
        //格式 类名[field1 = 值1， field2 = 值2]
        Apple apple = new Apple("红色", 12);
        out.println(apple);

        //==和equals方法
        int it1 = 65;
        float f3 = 65.0f;
        out.println("65 与 65.0是否相等"+ (it1 == f3));//true
        char ch = 'A';
        out.println("65与'A'是否相等"+(it1 == ch)); //true
        String str6 = new String("hello");
        //如果常量池中还没有"hello"则这一步创建了两个对象
        //第一步创建"hello"常量String对象，放在常量池里
        //第二步通过构造器创建一个新的String实例放在堆内存中
        String str7 = new String("hello");
        out.println("str6是否== str7"+(str6 == str7)); //false new出来的两个String对象是不相等的

        String str8 = "hello";
        String str9 = "hello";
        out.println("str8是否== str9"+(str8 == str9)); //true 但是这种写法都是从常量池里拿出相同的"hello"

        out.println("str6是否equals str7"+ str6.equals(str7)); //TRUE
        //out.println("hello" == (new Integer(4))); //没有继承关系的两个实例不能进行比较

        //常量池用于管理再编译时就被确定并保存在已编译的.class文件中的一些数据
        //包括类，方法，接口中的常量和字符串常量
        String s4 = "疯狂"+"Java";
        String s1 = "疯狂Java";
        String s2 = "疯狂";
        String s3 = "Java"; //在编译的时候就能确定，直接引用常量池里的"疯狂Java"
        String s5 = "疯"+"狂"+"Java";//在编译的时候就能确定，直接引用常量池里的"疯狂Java"
        String s6 = s2 + s3;
        String s7 = new String("疯狂Java");
        out.println(s1 == s4);//true
        out.println(s4 == s5);//true
        out.println(s1 == s6); //false s6在编译时确定不了，不能引用常量池里的字符串
        out.println(s1 == s7);//false s7是运行时创建，保存在堆内存(运行时内存）
        //equls方法 是Object的实例方法，如果没有重写跟==的作用相同都是同一对象才相等
        //大部分时候需要重写equals方法

        Person p1 = new Person("孙悟空","12345678");
        Person p2 = new Person("孙行者", "12345678");
        Person p3 = new Person("猪八戒","456789");
        out.println("equals方法"+p1.equals(p2));//true
        out.println("equals方法"+p1.equals(p3));//false

        //类变量类的加载时机
        //1）第一次使用new 创建该类对象
        //2) 读取一个静态字段（被final修饰和在编译期已经确定被放在常量池的常量字段除外）
        //3）设置一个静态字段 （被final修饰和在编译期已经确定被放在常量池的常量字段除外）
        //4）调用静态方法

        //final修饰引用类型的变量
        final int[] intArr = {5, 4, 8, 9};
        out.println(Arrays.toString(intArr));
        Arrays.sort(intArr);//只是对原数组进行排序，并未返回新的数组
        out.println(Arrays.toString(intArr));
        intArr[2] = 3; //final修饰intArr，只是保证intArr永远指向一个数组对象，但是数组里面的值是可以改的
        out.println(Arrays.toString(intArr));
        //intArr = null; 非法

        //可执行宏替换的变量
        //用final修饰并且定义的时候指定了初始值，这个初始值在编译的时候就能确定
        //此时不会生成一个变量空间去保存这个值，而值直接使用这个变量的值
        //当指定的初始值是基本算数表达式，或者字符串连接，并没有访问普通变量时 都当成宏变量
        final int k = 5;
        out.println(k);//相当于out.println(5) main方法栈里也没有k这个变量
        //这四个都是宏变量
        final int a1 = 5;
        final double b1 = 1.2/3;
        final String str10 = "疯狂"+"Java";
        final String str11 = "疯狂"+99.0;
        //因为调用了方法，在编译时确定不了，不是宏变量
        final String str12 = "疯狂"+String.valueOf(99.0);
        out.println(str11 == "疯狂99.0");//true
        out.println(str12 == "疯狂99.0");//false

        String s8 = "疯狂Java";
        String s9 = "疯狂"+"Java";
        final String s11 = "疯狂";
        final String s12 = "Java";
        String s10 = s11+s12;
        out.println("宏变量"+(s9 == s8));
        out.println(s10 == s8); //当s11和s12没有final修饰时为flase
        // 但是当时有final修饰时， s11 ,s12不再是变量，而是宏变量即常量， 返回true
        //成员变量作为宏变量处理只有在定义的时候初始化才有效，在初始块里和构造器里初始化不能按照宏变量处理

        //Integer的缓存机制
        Integer int3 = new Integer(6);
        Integer int4 = Integer.valueOf(6);
        Integer int5 = Integer.valueOf(6); //valueOf的生成对象方式会从缓存区取
        out.println("Integer的缓存机制"+(int3 == int4)); //false
        out.print(int4 == int5); //true
        Integer int6 = Integer.valueOf(200);
        Integer int7 = Integer.valueOf(200);
        out.println(int6 == int7); //false 因为Integer的缓存区范围为-128——127

        //接口的继承
        out.println("接口的继承"+InterfaceC.PROP_A);
        out.println(InterfaceC.PROP_B);
        out.println(InterfaceC.PROP_C);

        Output o = new Printer();//接口引用变量，时机引用Printer对象
        o.getData("Java");
        o.getData("PHP");
        o.getData("Android");
        o.getData("Python");
        o.getData("Ios");
        o.getData("C");
        o.getData("C++");
        o.out();
        Product p = new Printer();
        out.println(p.getProductTime());
        Object obj = o;
        //任何类都默认继承了Object类或者其子类，接口的实现类肯定也是因此接口类型的引用变量可以直接赋值给Object类型的变量

        //面向接口编程
        Output output = OutputFactory.getOutput();
        Computer computer = new Computer(output);
        computer.keyIn("Java讲义");
        computer.print();

        int[] ints = new int[]{3, -4, 6, 4};
        ProcessArray pro = new ProcessArray();
        pro.process(ints, new PrintCommand());
        pro.process(ints, new AddCommand());








    }

    //final修饰形参和局部变量
    public void test(final int a){ //final修饰形式参数
        //a = 5; 不能赋值给final修饰的形参
        final String str = "hello";
        //str = "heoll";
        final double d; //final修饰的局部变量必须在使用之前完成初始化
        d = 6.0;
        out.println(d);
    }
}

//类里只能包含成员变量，方法，初始化块，构造器，内部类（包括接口和枚举）5中成员
//其中构造器不能使用static修饰，其他的都能
class Apple{
    private String color;
    private double weight;
    private static int num = 10; //私有的静态成员变量外部也不能访问
    public Apple(String color, double weight){
        this.color = color;
        this.weight = weight;
    }
    public String toString(){
        return "[color="+color+", weight="+weight+"]";
    }
}

class Person{
    private String name;
    private String idStr;
    public Person(){}
    public Person(String name, String idStr){
        this.name = name;
        this.idStr = idStr;
    }
    public boolean equals(Object obj){
        if(this == obj) return true; //如果是同一个对象
        if(obj != null && obj.getClass() == Person.class){//都是同一个类的实例，
            // 用instanceof 比如Teacher是Person的子类也会返回true,这样不合适
            Person personObj = (Person)obj;
            if(this.idStr.equals(personObj.idStr)){
                //这里要用equls方法，String类型已经重写了equals方法，字符串相等即为相等
                return true;

            }
        }
        return false;
    }
}
//静态成员变量可以初始化的地方，1）定义时指定 2）静态初始化块
//非静态成员变量可以初始化的地方 1）定义时指定 2）非静态初始化块 3）构造器
class FinalVariabletest{
    final int a = 6; //在定义成员变量时指定默认值
    final String str;
    final int c;
    final static double d;
    final static double f = 10.0; //在定义类变量时指定初始值
    //final char ch; //必须进行由程序员进行显式初始化，系统不会给默认初始化
    {
        str = "hello"; //在普通初始化块里，为final实例变量指定初始值
        //d = 5.0;
        //在非静态初始化块里，初始化final静态变量不起作用
        //因为非静态初始化块只有在创建对象时才执行，类的final变量必须在类初始化的时候就完成
        //a = 9 已经初始化的final不能重新复制
        //out.println(c); final在初始化之前不能被访问
    }
    static
    {
        d = 5.0;//在静态初始化块中为类变量初始化
    }

    public FinalVariabletest(){
        c = 5; //既没有在定义时指定默认值，也没有在初始化块初始化的变量，可以在构造器初始化
    }

    public void changeFinal(){
        //ch = 'c'; //不能再普通方法是里初始化final变量
    }

}

//final修饰方法 不想被子类重写的方法 Object用final修饰的只有getClass()方法
class FinalMethodTest{
    final public void test(){

    }
    final public void test(String msg){ //final修饰的方法可以被重载

    }

    final private void test2(){

    }
}
//final修饰类，该类不能被继承 Math类就是Final类
final class Sub extends FinalMethodTest{

//    public void test(){  编译报错，不能覆盖final修饰的方法
//
//    }
    private void test2(){
        //由于父类方法是private对子类不可见，所以子类可以定义相同的方法
        //但这不是方法重写，这个方法是子类自己的方法

    }

}


//不可变类(immutable) 创建该类的实例后，实例变量不可以改变，
// 8个包装类和String类都是不可变类，没有提供修改实例的方法，
// 我们平时改值，只是修改了前面变量的的指向，并没有修改生成的对象本身

//定义不可变类
//1)private final修饰成员变量
//2)提供带参数的构造器，初始化成员变量
//3）仅为成员变量提供getter方法
//4)重写equals和hashCode方法， equals根据关键成员变量判断，保证用equals判断相等的对象hashCode也相等

class Address{
    private final String detail;
    private final String postCode;

    public Address(){
        detail = "";
        postCode = "";
    }
    public Address(String detail, String postCode){
        this.detail = detail;
        this.postCode = postCode;
    }

    public String getDetail(){
        return this.detail;
    }

    public String getPostCode(){
        return this.postCode;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj != null &&  obj.getClass() == Address.class){
            Address addObj = (Address) obj;
            if(this.detail == addObj.detail && this.postCode == addObj.postCode){
                return true;
            }
        }
        return false;
    }

    public int hashCode(){
        return detail.hashCode()+postCode.hashCode()*31;
    }
}
//抽象类
//使用abstract修饰的类和方法
//抽象方法必须在抽象类中，但是只要abstract修饰的类都是抽象类，可以没有抽象方法
//抽象方法必须没有方法体(没有后面的大括号）public void test();
//抽象类可以有普通类的5种成员，不可创建实例，他的构造函数是为了给他的子类使用的

//抽象类主要用于模板设计模式，常用的设计模式
//抽象类只定义需要使用的方法，把不能实现的部分做成抽象方法交给子类实现
//父类中可能包含调用其他方法的方法（被调用的方法也可以是抽象方法）， 父类提供一个通用算法
abstract class Shape{
    {
        out.println("执行Shape的初始化块。。。");
    }
    private String color;
    public abstract double calPerimeter(); //计算周长的抽象方法
    public abstract String getType(); //返回形状的抽象方法
    public Shape(){}
    public Shape(String color){
        out.println("执行Shape的构造器。。。");
        this.color = color;

    }
    public String getPerimeterAndType(){ //抽象类的方法也可以调用抽象方法
        return "这是一个"+getType()+" 周长是"+calPerimeter();
    }
}
//final和abstract不能同时出现，
// static修饰抽象方法时，用类调用会调用没有方法体的方法，产生错误，因为不能abtract不能修饰静态方法
//但是在修饰内部类的时候两者可以同时使用
//private方法不能被继承重写，因此他与abstract是不同同时使用
class Triangle extends Shape{
    private double a;
    private double b;
    private double c;
    public Triangle(String color, double a, double b, double c){
        super(color);
        this.setSides(a, b, c);
    }
    @Override
    public double calPerimeter(){
       return a+b+c;
    }
    @Override
    public String getType(){
        return "三角形";
    }

    final public  void setSides(double a, double b, double c){
        if(a >= b+c || b >= a+c || c >= a+b){
            out.println("三角形两边之和必须大于第三边");
            return; //return什么也不返回代表结束方法
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static abstract class Inner{

    }
}

//接口 接口不能包含普通方法，全部是抽象方法
//接口可以有多个直接的父接口public interface Inter extends Inter1, Inter2{} //支持多继承，继承所有的抽象方法和常量
//访问修饰符可以是public或者默认
//接口只能继承接口，不能继承类
//接口里的成员变量只能是静态常量，方法只能是抽象实例方法（Java8以上的版本允许有默认方法和类方法）， 内部类(内部接口，或者枚举定义）
//接口不能有构造器和初始化块
//接口是定义公共行为规范因此成员都是public访问权限，默认也是public
//成员变量默认都是public static final 修饰，只能在定义的时候初始化
//普通方法默认都是public abstract修饰必须没有方法体， 默认方法类方法必须有方法体
//内部类，内部接口，内部枚举默认都用public static修饰

//接口的使用
//声明引用类型变量，真正引用的是实现类,也可用于强制类型装换
//调用接口中定义的常量
//被其他类实现
//一个类可以同时实现(implements 如果有继承父类需要放到extends之后)多个接口，之间用逗号分隔，得到接口常量和方法（包括抽象方法和默认方法）
//实现时必须实现所有的抽象方法，否则该类必须定义为抽象类
//接口的default和static方法不能被实现类重写，但是可以被实现类拥有，实现类可实例可以调用这些方法
interface Output{
    int MAX_CACHE_LINE = 50; //只能是常量
    void out();
    void getData(String msg);
    //默认方法用default修饰 默认是public
    //静态方法与default不能同时出现
}
interface InterfaceA{
    int PROP_A = 5;
    void testA();
}

interface InterfaceB{
    int PROP_B = 6;
    void testB();
}

interface InterfaceC extends InterfaceA, InterfaceB{
    int PROP_C = 7;
    void testC();
}
interface Product{
    int getProductTime();
}

class Printer implements Output, Product{
    private String[] printData = new String[MAX_CACHE_LINE];
    private int dataNum;

    @Override
    public void out() {
        while(dataNum > 0){
            out.println("打印机打印:"+printData[0]);
            System.arraycopy(printData, 1, printData,0, --dataNum);
            //从printData第二个数据开始取出--dataNum个数据，放到printData从0开始的位置
            //用arraycopy很好的实现了数组向前移位的操作
        }
    }

    @Override
    public void getData(String msg) {
        if(dataNum > MAX_CACHE_LINE){
            out.println("输出队列已经满了");
        }else {
            printData[dataNum++] = msg; //添加到打印队列里，当前的待打印数量加1
        }
    }

    @Override
    public int getProductTime() {
        return 45;
    }
}
//接口的实现目的是提现规范，实现类确，定了向外提供的服务，调用者可以使用哪些服务，各个模块的连接通过接口，不应该常改
//抽象类是定义模板
//面向接口编程，降低耦合度
//示例简单工厂模式
class Computer{
    private Output output;
    //这里应该用Output接口，而不是具体的Printer类
    //因为如果有一天Printer类改了，下面方法里的调用也要改
    public Computer(Output output){
        this.output = output;
    }
    public void keyIn(String msg){
        output.getData(msg);
    }
    public void print(){
        output.out();
    }
}

//OutputFactory负责生成Output对象
class OutputFactory{
    public static Output getOutput(){
        return new BetterPrinter();
    }
}

class BetterPrinter implements Output{
    private String[] printData = new String[MAX_CACHE_LINE*2];
    private int dataNum = 0;
    @Override
    public void out() {
        while (dataNum > 0){
            out.println("高速打印机正在打印:"+printData[0]);
            System.arraycopy(printData, 1, printData,0, --dataNum);
        }
    }

    @Override
    public void getData(String msg) {
        if(dataNum > MAX_CACHE_LINE *2){
            out.println("输出队列已经满了");
        }else {
            printData[dataNum++] = msg;
        }
    }
}

interface Command{
    void process(int[] target);
}

class ProcessArray{
    public void process(int[] target, Command cmd){
        cmd.process(target);
    }
}

class PrintCommand implements Command{

    @Override
    public void process(int[] target) {
        for(int tmp:target){
            out.println(tmp);
        }
    }
}

class AddCommand implements Command{

    @Override
    public void process(int[] target) {
        int sum = 0;
        for(int tmp:target){
            sum += tmp;
        }
        out.println("数组元素的总和是:"+sum);
    }
}