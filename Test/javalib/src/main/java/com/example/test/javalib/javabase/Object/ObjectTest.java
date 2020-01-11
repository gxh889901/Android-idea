package com.example.test.javalib.javabase.Object;


public class ObjectTest {
    static int a = 10;
    //方法的参数传递机制 值传递(将实际参数的副本传入方法内进行处理，参数本身不会受影响）
    public static void main(String[] args){

        //基本类型形式参数传递
        int a = 10;
        int b = 5;
        swap(a, b); //因为传入到方法里的只是a,b的副本，不是a,b本身所以，a,b没有交换
        System.out.println("实参a="+a+", b="+b);

        //引用类型参数传递
        DataWrap dataWrap = new DataWrap();
        dataWrap.a = 10;
        dataWrap.b = 5;
        swap(dataWrap);
        System.out.println("交换结束后  a成员="+ dataWrap.a+", b成员="+dataWrap.b);

        //形式参数个数可变的方法
        test("Java讲义", "Android讲义", "PHP讲义", "Python讲义");
        String[] books = {"Java讲义", "Android讲义", "PHP讲义"};
        test(books); //可变参数可以使用数组作为实参传递

        //方法递归
        System.out.println(fn(10));
        System.out.println(upFn(10));

        //方法重载
        overloadFun();
        overloadFun("aa");
        overloadFun("aa", "bb");
        overloadFun(new String[]{"aa"});

        //成员变量(类成员变量和实例成员变量）
        // 局部变量(形参，方法局部变量和代码块局部变量)局部变量必须显式初始化，直到被赋值才会分配内存，保存在栈内存中
        //方法结束或者代码块结束所拥有的栈就释放了，不需要垃圾系统回收
        //代码块局部变量
        {
            int c; //如果代码块定义在方法局部变量a之前，则这里可以使用，a，否则不可以
            c = 5;
            System.out.println("代码块里的局部变量="+ c); //代码块的局部变量不能默认初始化，必须给予初始值
        }
        //System.out.println(c); c的作用范围只是代码块内部，外部访问不了
        //方法局部变量
        localVariable(5);
        //因为变量的生存周期不一样，内存开销不同，能缩小变量的使用范围尽量缩小，变量内存中停留的时间越短，性能越好
        //能用代码块变量，不要定义方法局部变量，能用方法局部变量，不定义成员变量

        //类或者实例内存分配的默认初始化
        Animal animal = new Animal(); //String类型的默认初始化是null
        System.out.println(animal);




    }
    //基本类型的参数传递
    public static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
        System.out.println("swap方法里 形参x="+x+", y="+y);
    }
    //引用类型的参数传递  //方法参数不一样，不算一种方法
    public static void swap(DataWrap dw){
        int tmp = dw.a;
        dw.a = dw.b;
        dw.b = tmp;
        System.out.println("swap方法里 dw成员a="+dw.a+", dw成员b="+dw.b);
    }

    //形式参数个数可变的方法 最后一个参数类型后加...加形参名,最后的参数在方法里被当成数组使用 ,必须是最后一个参数
    public static void test(String...books){
        for(String book:books){
            System.out.println(book);
        }
    }

     //递归方法 一定要往已经方向递归，小值知道就不断往n-1递归
     // 题目描述给出的表达式是f(0) = 1, f(1) =4, f(n+2) = 2*f(n+1)+f(n)，这里需要把n变成n-2那等式左边就是f(n)了，求f(10)
    //要看所求的f(10)与已知的值得大小，关系，如果已知的值比求得值小，已知方向为小方向，往n-1递归
    //已知的值比所求的值大，则大方向已知，往n+1递归
    public static int fn(int n){
        if( n == 0) return 1;
        if(n == 1) return 4;
        //return fn(n+2) - 2*fn(n+1);
        // 因为这里是0，1已知，所以一定要向减小的方向递归，
        // 即为fn(n)里调用fn(n-1)，绝对不能调用fn(n+1)这样会无限递归
        return 2*fn(n-1)+fn(n-2);
    }

    //如果是f(20)= 1, f(21) = 4，还是求f(10)则需要往大端递归
    public static int upFn(int n){
        if(n == 20) return 1;
        if(n == 21) return 4;
        return upFn(n+2)-2*upFn(n+1);
    }


    //方法重载 方法名称相同，形参列表不同
    //注意如果两个方法的其他部分都相同只能返回值不同，也会报错同一方法，因为int f() 与void f()
    // 在调用的时候都使用f()区分不出想调用哪个
    //修饰符也不同作为重载考虑
    public static void overloadFun(){

        System.out.println("方法1 无参数");
    }
    public static void overloadFun(String msg){
        System.out.println("方法2 一个参数");
    }
    public static void overloadFun(String...msgs){
        System.out.println("方法3 不定个数参数");
    }

    //方法局部变量
    public static void localVariable(int b){
        int a = 0; //方法局部变量可以与成员变量重名，访问成员变量用this
        System.out.println(ObjectTest.a);
        System.out.println(a);
        System.out.println(b);

    }





}

class  DataWrap{
    int a; //默认是包访问权限
    int b;
}

class Animal{
    static int eyeNum;
    String name;

    @Override
    public String toString() {
        return "Animal{" +
                "eyeNum=" + eyeNum +
                ", name='" + name + '\'' +
                '}';
    }
}
