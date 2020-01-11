package com.example.test.javalib.javabase.Object;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static  java.lang.System.*;
public class ObjectTest4 {
    private static  ObjectTest4 bj = null;//用于在finalize中回复引用，复活对象
    private double outerCost = 567.8;
    public static void main(String[] args){
        final double cost = 258.9;
        //定义变量Outer.Innner   生成对象new Out().new Inner
        Out.Inner inner = new Out().new Inner("内部类测试");
        Out outs = new Out();
        InnerSub innerSub = new InnerSub(outs);
        //静态内部类变量定义和对象生成
        StaticOut.StaticIn staticIn = new StaticOut.StaticIn();
        ObjectTest4 obj = new ObjectTest4();
        for(int i = 0; i < 4; i++){
            new ObjectTest4();
        }
        obj.test(new Product1() {
            @Override
            public double getPrice(double price) {
                return price;
            }

            @Override
            public String getName() {
                return "显卡";
            }
        }, 567.8);
        obj.test2(new Device("示波器") { //匿名内部类与父类具有相同形参列表的构造器
            @Override
            public double getPrice() { //必须覆写抽象方法
                return cost;
                //匿名内部类或者局部类访问方法外部变量必须是final的在Java8之前
                //之后改成可以访问非final的但是访问之后自动变成final
            }
            @Override
            public String getName(){ //也可以覆写非抽象方法
                return "键盘";
            }
        });
        for(Season s: Season.values()){
            out.println(s.name()+s.ordinal());
            out.println(Season.valueOf("Summer"));//必须与定义时的名称一样否则会报IllegalArgumentException
            //Season.values() //返回所有的枚举实例
            //int ordinal() 返回枚举值定义的位置，下标从0开始
            //String name()返回名字
            //toString返回常量名称
           //valueOf(String name)必须与定义时的名称一样，返回对应的枚举实例

        }
        judge(Season.Spring);
        for(Gender gender:Gender.values()){
            gender.info();
        }
        out.println("Operation="+Operation.DIVIDE.eval(10, 5));
        String str = new String("弱引用");
        WeakReference  wr = new WeakReference(str);
        str = null; //取消强引用
        //如果在这里使用wObj = wr.get()则这个对象又变成强引用，gc不会被回收


        out.println(wr.get()); //通过弱引用可以获取该对象
        String str2 = new String("虚引用");
        ReferenceQueue rf = new ReferenceQueue();//保存被回收后对象的引用
        PhantomReference pr = new PhantomReference(str2, rf);
        //虚引用可以在对象被回收之前就把虚引用添加到关联的队列种
        //用于追踪对象被回收的状态，查看引用队列中是否包含该虚引用
        out.println("虚引用"+pr.get());//虚引用获取不到引用的对象

        System.gc(); //通知系统回收
        System.runFinalization();//强制回收机制调用可回复对象的finalize()方法
        //取出引用队列种最先进入队列的引用与pr比较
        out.println(rf.poll() == pr);//
        Object wObj = wr.get();
        out.println(wObj);//弱引用当系统执行垃圾回收时，回收了弱引用对象，打印null
        if( wObj == null){//如果弱引用的对象被回收了，那么在需要使用该对象的地方重新创建对象
            wObj = new String("弱引用");//重新创建对象
            wr = new WeakReference(wObj);
        }
        if(wr.get() == null){
            wr = new WeakReference(new String("弱引用")); //如果在这两句之间发生gc wr.get()还是可能为null
            wObj = wr.get(); //wObj与对象之间也是强引用
        }
        //操作wObj对象
        //wObj = null//切断str3应用的对象的强引用
        out.println("复活"+bj);


    }

    @Override
    protected void finalize() throws Throwable {
        bj = this;
        out.println("系统正在回收ObjectTest4");
    }

    //匿名实现接口
    public void test(Product1 p, double price){
        out.println("购买了一个"+p.getName()+",花掉了"+p.getPrice(price));
    }
    //匿名实现抽象类
    public void test2(Device d){
        out.println("购买了一台"+d.getName()+", 花掉了"+d.getPrice());
    }

    public void test3(){
        ObjectTest4 obj = new ObjectTest4();
        obj.test(new Product1() {
            @Override
            public double getPrice(double price) {
                return outerCost; //这里可以访问outerCost不必是final
            }

            @Override
            public String getName() {
                return "显卡";
            }
        }, 567.8);
    }

    public static void judge(Season s){
        switch (s){
            case Spring:
                out.println("春天");
                break;
            case Summer:
                out.println("夏天");
                break;
            case Fall:
                out.println("秋天");
                break;
            case Winner:
                out.println("冬天");
                break;
            default:
                out.println("季节错误");

        }
    }
}
//内部类可以访问外部类的私有变量，但是外部类不可以访问内部类的任何成员
//内部类对象持保存了一个外部类对象的引用
//非静态内部类对象
//非静态内部类不能拥有静态成员
//内部类可以使用private protected static修饰

//内部类可以放在类的任何位置，包括方法内部（局部内部类）
//局部内部类和匿名内部类不是类成员
//在文件路径存储OuterClass$InnerClass.

//查找变量 方法局部变量->内部类成员变量->外部类成员变量
//通过OuterClass.this.variable访问外部类变量

//外部类的静态成员不能使用使用非静态内部类定义变量，创建实例
//静态内部类可以包含静态成员，也可以包含非静态成员，不能访问外部的非静态成员(静态内部类的实例方法也不能访问外部的非静态成员）
//接口的内部类只能是静态内部类
class Out{
    public Out(){

    }
    class Inner{
        public Inner(String msg){
            out.println(msg);
        }

    }
}
//非静态内部类的构造器必须由外部类实例调用
//内部类的子类不一定是内部类，但是一样要保留对父类的外部类对象的引用
// ，因此构造的时候必须显式的传入一个外部类对象，类调用父类的构造器
class InnerSub extends Out.Inner{
    public InnerSub(Out o){
        o.super("内部类子类测试"); //这里的super指的是InnerSub的父类即Out.Inner
    }
}

//创建静态内部类时无需创建外部类对象
//静态内部类只要把外部类当成静态内部类的包空间即可，使用内部类时推荐使用静态内部类
class StaticOut{
    static class StaticIn{
        public StaticIn(){
            out.println("静态内部类的构造器");
        }
    }
}

class StaticInnerSub extends StaticOut.StaticIn {
    public StaticInnerSub(){

    }
}
//局部变量因为作用范围小，都不用控制访问符修饰或者static修饰
//局部内部类即在方法内部定义的类，一般不怎么使用

//匿名内部类，创建只是用一次的类，创建匿名内部类时即创建一个实例，所以不能是抽象类
//没有名字，所以没有构造器，但是可以有初始化块
//最常见的就是创建某个接口的对象
interface Product1{
    double getPrice(double price);
    String getName();
}
abstract class Device{
    private String name;
    public abstract double getPrice();
    public Device(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}

//Lambda表达式 函数式接口，只包含一个抽象方法的接口
//匿名内部类可以实现一个接口的多个抽象方法，但是Lamda表达式只针对有一个抽象方法的接口
//匿名内部类可以为抽象类甚至普通类创建实例
//匿名类内部实现的方法可以访问接口的默认方法，但是Lambda表达式的代码块里不允许访问接口的默认方法
//@FunctionalInterface函数式接口的注解
//Lambda表达式是一个对象，因此可以赋值给函数式接口的引用变量
//Lamda可以函数式接口类型的参数，传递给方法
//Runnable是java提供的函数式接口
//Lambda本身不能赋值不能赋值给Object变量，但是可以强制转换为函数式接口对象，赋值给Object变量
//XxxFunction 对应的apply()对指定参数按照Lambda进行处理后返回
//XxxConsumer accept()对参数按照Lambda进行处理但不反回
//XxxPredicate test() 对参数按照按照Lambda进行判断返回boolean
//XxxxSupplier getAsXxx() 不需要输入参数按照Lambda产生一个参数并且返回
//Comparator XxxxOperator XxxFunction都是函数式接口，可以使用Lambda表达式与Arrays函数结合使用
//只有一条语句的Lambda表达式可以，使用方法引用和构造器引用
//引用类方法
interface Convert{
    Integer convert(String from);
}
//Convert convert = from->Integer.valueOf(from) 写法等同于 Convert convert = Integer::valueOf


//枚举类 实例有限且固定的类 如四季，行星
//enum定义枚举类，可以有成员变量和方法，可以实现一个或者多个接口，可以定义构造器
//枚举类默认继承了java.lang.Enum类，而不是Object因此不能继承其他的类了，但是可以实现接口
//默认实现java.lang.Serializabler和java.lang.Comparable接口
//默认非抽象枚举累用final修饰不能被派生，抽象枚举类能被派生
//构造器只能是private
//所有实例在类的第一行显示列出，并且默认public static final修饰
enum Season{
    Spring, Summer, Fall, Winner
}
interface GenderDesc{
    void info();
}
//实现接口的枚举类
enum Gender implements GenderDesc{
    MALE("男"){ //这里不再是Gender的实例，而是Gender的匿名子类，生成三个class文件
        //也可以分别实现接口的抽象函数
        public void info(){
            out.println("男性枚举值");
        }
    }, FEMALE("女"){
        public void info(){
            out.println("女性枚举值");
        }
    };//这里要有分号
    private String name;
    Gender(String name){ //枚举类的构造器必须是私有的，因此可以省略
        this.name = name;
    }
    //可以整体实现接口里的函数
//    @Override
//    public void info(){ //这里必须是public,因为接口里是public
//        out.println("这是一个用于定义性别的枚举类");
//    }
}
//包含抽象方法的枚举类
enum Operation{ //会生成5个class文件
    PLUS
    {
        @Override
        public double eval(double x, double y) {
            return x+y;
        }
    },
    MINUS{
        @Override
        public double eval(double x, double y) {
            return x-y;
        }
    },
    TIMES{
        @Override
        public double eval(double x, double y) {
            return x*y;
        }
    },
    DIVIDE{
        @Override
        public double eval(double x, double y) {
            return x/y;
        }
    };
    //抽象方法由不同枚举值提供不同的实现
    public abstract double eval(double x, double y);

}

//对象的回收机制
//对象，数组都是应用类型实体，在堆中分配内存
//垃圾回收机制只回收堆内存中的对象，不会回收物理资源(如数据库连接和网络IO）
//一个对象被回收之前总是会显执行finalize(）方法，该方法可以使对象重新复活(使用引用变量引用该对象）
//可达状态：有一个或者一个以上引用变量引用它
//可恢复状态：没有引用变量引用它，但是对象的finalize还没执行, 每个类都有finalize方法可以重写
//不可达状态：对象没有引用，而且执行了finalize后处于不可达状态，所占用的资源会被回收
//当某个对象被其他类的类变量引用时，只有该类被销毁后，该对象才会进入可恢复状态
//当某个对象被其他实例变量引用时，只有该实例变量被销毁后，该对象才会进入可恢复状态
//强制垃圾回收，通知系统进行垃圾即回收，系统是否回收还不确定，一般都总会有一些效果
//System类的静态方法System.gc() Runtime对象的gc()实例 Runtime.getRuntime().gc
//finalize方法不一定会执行，只有垃圾回收机制回收时才执行finalize，而垃圾回收可能直到程序退出都不会回收某个对象
//永远不要主动调用finalize方法
//软引用SoftReference 当内存足够时引用的对象不会被回收，空间不足时可能会回收
//弱引用WeakReference，当内存足够时也可能被回收，类似处于不可达状态的对象
//虚引用PhantomRefence 类似于没有引用，不能单独使用，和引用队列结合使用ReferenceQueue
//使用这些引用，就不能保留任何强引用
//弱引用的使用在需要的使用的地方取出引用对象，或者在使用的地方赋值给引用变量变成强引用，使用完成后切换强引用链接，赋值null
//native修饰方法，方法里使用C语言，平台相关，失去了跨平台功能
//strictfp修饰的类，方法和内部类严格执行精确的浮点运算


