package com.example.test.javalib.javabase.Object;
import com.example.test.javalib.algorithms.WomanUser;


import static com.example.test.javalib.javabase.Object.Animal.eyeNum; //eyeNum用于导入指定的静态成员，*用于导入所有的静态成员
import static java.lang.System.*;

public class ObjectTest2 { //有public修饰的类文件名字必须与类名相同，default修饰的可以不一致
    public static void main(String[] args){
        User user = new User();
        user.setAge(100);
        user.setName("上官婉儿的的");
        out.println("name="+user.getName()+", age="+user.getAge()); //静态导入后使用out可以不写System了
        out.println("Animal eyeNum="+eyeNum); //使用import static导入静态成员后，调用的时候连类名都不用带了
        ManUser manUser = new ManUser();
        manUser.getInfo();
        WomanUser womanUser = new WomanUser();
        womanUser.getInfo();

        Bird b = new Bird();
        b.setAge(10, 11);
        out.println("age ="+b.age);
        b.fly();

        Bird b1 = new Ostrich();
        //编译类型是声明时的类型，即Bird 运行类型是调用的实际实例是Ostrich，两者不同就叫多态
        //换一种理解方式，就是声明的类型相同，但是调用同一函数时却有不同的执行结果就叫多态
        //这里Bird是声明的类型，因此在写调用的时候不能调用父类没有，而子类扩展的方法
        //对于这种形式如果子类没有覆调写父类的方法，
        // 调用父类的方法（父类方法修改的父类的变量），如果子类覆写了，代用子类的方法（子类方法默认修改的是子类自己的变量）
        //对于变量的直接访问，父类类型的对象访问父类成员变量，子类类型的变量，访问子类的变量，没有访问父类的
        b1.setAge(30, 12);
        out.println("age ="+b1.age); //通过b1.age调用的还是父类的成员变量
        b1.fly();                    //b1.fly调用的是子类覆写的方法 ，但是方法里面调用的age是子类的age

        Ostrich os = new Ostrich();
        os.setAge(40, 13);
        //子类的setAge修改子类的age，父类的setAge修改父类的age
        //如果子类没有重写setAge调用父类的setAge修改父类的age
        out.println("age ="+os.age);
        os.fly();

        Bird bird = os;
        out.println("age ="+bird.age);
        bird.fly();

        Bird.getInfo();
        Ostrich.getInfo();
        out.println(Bird.eyeNum);
        out.println(Ostrich.eyeNum);

        Bird bird1 = new Ostrich(20);
        out.println("age="+bird1.age);
        Ostrich d1 =(Ostrich) bird1;
        out.println(bird1 == d1);//TRUE
        out.println("d1.age ="+d1.age);
        Bird bird2 = new Bird();
        out.println(bird2 instanceof Ostrich);
        //Ostrich d2 = (Ostrich) bird2; //父类实例不能强制转换为子类实例,强制转换是父类转子类
        //out.println(d2.age);


        //instance 判断前面的实例是不是后的类或者后面的类的子类，实现类的实例
        Object hello = "Hello";
        String str = "Hello";
        Object obj = new Object();
        out.println((hello instanceof String)); //返回true
        out.println(hello instanceof Object); //返回true
        out.println(hello instanceof Bird); //返回false
        out.println(str instanceof  Object); //返回true 子类是父类的instance
        out.println(hello instanceof Comparable);
        out.println(obj instanceof  String); //false 父类不是子类的instance
        //编译时类型与Object没有继承关系不知道为啥能运算，比较的时候的是运行时类型String 实现了Comparable接口所以返回true
        // 判断能不能使用instanceof用的是编译时类型， 实际判断是不是实例用的运行时类型
        //使用instanceof 前面的实例的编译时类型是与后面的类相同或者有继承关系，才能使用intanceof,否则报错
        //Object obr = new Integer(4);
        //String obStr = (String) obr;  强制转换使用的是运行时Integer不是与String没有继承关系所以报错
        //防止运行时错误，进行强制转换之前都先进行instanceof类型判断，如果实例是转换类型的子类或者实现类才可以


        //组合复用， 需要复用的类作为新类的成员变量，此时应该设为私有最好
        Annimal a = new Annimal(); //显式创建被组合的对象
        Wolf wolf = new Wolf(a);
        wolf.breath();
        wolf.run();

        //初始化块
        out.println("k= "+wolf.k);
        out.println("m= "+Wolf.m);
        new Leaf();
        new Leaf("msg");


    }
}

class Bird{
    int age = 10;
    int weight = 0;
    public static int eyeNum = 2;
    public Bird(){
        out.println("父类构造器");
    }
    public Bird(int age){
        this.age = age;
        out.println("父类构造器一个参数");
    }
    public void fly(){
        out.println("我可以飞翔 age="+age);
    }

    protected void setAge(int age){
        this.age = age;
    }

    protected void setAge(int age, int weight){
        //当子类没有此重载方法时，调用的还是父类的方法，
        //setAge(age); //如有如果父类调用setAge方法，由于被覆盖过调用的还是子类的方法，修改子类年龄
        this.age = age; //如果只是修改变量引用，那修改的还是父类的age
        this.weight = weight;
    }

    public static void getInfo(){
        out.println("I am a bird");
    }

    public final void getBirdInfo(){ //想让其他类访问，但是又不想被覆写
        out.println("这是Bird");
    }
}
//子类重写父类的方法访问权限只能更大或者相等
//创建子类实例时，总是从最顶层的父类构造器开始执行到子类的构造器（如果有super调用后走super）没有调用默认的构造器
final class Ostrich extends Bird{
    public static int eyeNum = 1;
    int age = 100;
    public Ostrich(){
        out.println("子类构造器");
    }
    public Ostrich(int age){
        //super(age);
        out.println("子类构造器一个参数");
        //在没有显式调用父类构造函数时，默认会调用父类的无参数构造函数，显式调用后有参父类构造函数时，不会再调用无参，只是调用有参数构造函数
        //this.age = age;
    }
    //如果子类没有覆调用写父类的方法，
    public void fly(){ //覆写方法 名称相同，形参列表相同，返回类型比父类小或者等于，抛出异常比父类小等于
        // 但是方法的访问权限要比父类大或者相等
        //super.fly(); //可以通过super访问父类中被覆盖的方法
        //覆盖的方法要么是子类父类都是实例方法，要么子类父类都是类方法，不能不一致，编译报错
        out.println("我只能在地上跑  age="+age);
        out.println("子类成员age ="+age+" 父类成员变量age"+super.age);
        //系统查找变量age的顺序，方法的局部变量——>当前类的成员变量——>父类的成员变量——>父父类的成员变量

        //super和this一样属于实例，不能放在static里
        //成员变量时不覆盖的，如果父类和子类有相同的名字的成员变量，则算子类拥有两个变量
    }

    public void setAge(int age){ //子类会扩展继承方法的访问权限
        this.age = age;
    }

    public static void getInfo(){
        out.println("我是一只鸵鸟");
    }

    public void likeOs(){

    }
//    public void getBirdInfo(){   //用final修饰的方法不能被覆写
//
//    }
}

//为了保护父类的封装性，最好把成员变量都设置为私有属性，不能被继承
//可以把父类构造器，设置为私有，提供一个静态方法提供实例
//或者用final修饰类不被继承，父类不希望被重写的方法也可final修饰
//父类内部使用的方法都用private修饰
//不要再父类构造器中调用将要被子类覆写的方法

class Annimal{
    private void beat(){
        out.println("心脏跳动");
    }

    public void breath(){
        out.println("呼吸中");
    }
}

class Wolf{

    //初始化块的使用，对所有的对象的做同一的初始化操作，且不需要传递参数，从各个构造器中抽取出不变且不需要参数的部分
    //有利于代码复用和可维护性
    //创建对象时，先执行最顶层父类的初始化块再执行顶层父类的构造器，依次向下，最后执行本类的初始化块和构造器
    //静态初始化块，再类初始化执行，而不是创建对象时，因此比普通初始化块要早，也不能访问静态变量
    //也是先执行父类的静态初始化块，最后执行本类的

    static {  //类创建的过程，是先为类变量分配内存空间，然后按照顺序为类变量进行初始化操作
        m = 11;//所以这段代码在m= 11后面的话输出m = 11,在前面输出的m = 8
    }
    static int m = 8;

    int k = 6;
    {           //实例对象创建的过程，是先为成员变量分配内存空间，然后按照顺序为成员变量进行初始化操作，再执行构造器里的初始化
        k = 9; //所以这段代码在k= 6后面的话输出k = 9,在前面输出的k =6
    }
    private  Annimal a;
    public Wolf(Annimal a){
        this.a = a;
    }

    public void breath(){
        a.breath();
    }
    public void run(){
        out.println("奔跑中。。。");
    }
}
//Root静态块->Mid类静态块—>Leaf静态块->Root非静态初始化块（静态块的初始化只会走一次）
//->Root构造器->Mid初始化块->Mid无参数构造->Leaf初始化块—>Leaf无参数构造

//Root初始化块->Root构造器->Mid初始化块->Mid无参数构造->Mid有参数构造—>Leaf初始块->Leaf有参数构造->
class Root{
    static {
        out.println("Root的静态初始化块");
    }
    {
        out.println("Root的非静态初始化块");
    }
    public Root(){
        out.println("Root的无参构造函数");
    }
}

class Mid extends Root{
    static {
        out.println("Mid的静态初始化块");
    }
    {
        out.println("Mid的非静态初始化块");
    }
    public Mid(){
        out.println("Mid的无参构造函数");
    }

    public Mid(String msg){
        this();
        out.println("Mid的带参构造函数"+msg);
    }
}
class Leaf extends Mid{
    static {
        out.println("Leaf的静态初始化块");
    }
    {
        out.println("Leaf的非静态初始化块");
    }
    public Leaf(){
        out.println("Leaf的无参构造函数");
    }

    public Leaf(String msg){
        //调用父类的构造函数时是调用无参数构造函数
        super("调用父类的带参构造函数"); //虽然再这里显式调用，但是还是先执行Mid的构造函数，然后执行Leaf非晶态初始化块，再执行leaf super下面的函数体
        out.println("Leaf的带参构造函数"+msg);
    }
}



