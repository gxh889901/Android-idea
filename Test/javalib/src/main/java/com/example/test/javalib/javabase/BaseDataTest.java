package com.example.test.javalib.javabase;



import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class BaseDataTest {
    public static void main(String[] args){
        char aChar = 'a';
        char enterChar = '\t';
        char ch = '\u9999';
        System.out.println(ch);
        char zhong = '疯';
        int zhValue = zhong;
        System.out.println(zhValue);
        char c  = 97;
        System.out.println(c);


        //正常文件路径 "c:\codes"
        //java代码里"c:\\codes"

        System.out.println("Hello"+3+4);
        //生成随机6位随机字符串
        String result = "";
        for(int i = 0 ; i < 6; i++){
            int intValue =(int)(Math.random()*26 + 97);
            result += (char)intValue;
        }
        System.out.println(result);

        String a = "45";
        System.out.println(Double.parseDouble("45"));
        System.out.println((-5/0.0));
        System.out.println(Math.pow(2, 5));
        System.out.println(Math.abs(4-8.5));
        System.out.println(Math.sqrt(13.4));
        System.out.println(Math.random());//[0,1)
        //推荐使用扩展运算符
        System.out.println( 5 == 5.0);
        System.out.println(97 == 'a');
        //异或 不同时返回true 相同时返回false

        //短路或|| 不短路或|
        int b = 5;
        int f = 10;
        if(b > 4 || f++ > 10) { //短路：前面为true不执行后面的判断
            System.out.println("b="+b+" ,f="+f);
        }
        int d = 5;
        int m = 10;
        //不短路：前面为true 也执行后面的判断
        if(b > 4 | m++ > 10) System.out.println("d ="+d+" ,m="+m);
        //三目运算符 条件？ 表达式1 ： 表达式2
        System.out.println( m < d ? " m 小于 d": (m > d ? "m 大于 d": "m 等于 d"));

        String score = "夏天";
        switch (score){
            case "夏天":
                System.out.println("夏天");
                break;
            case "冬天":
                System.out.println("冬天");
                break;
            default:
                System.out.println("季节输入错误"); //这里没有
        }
        int count = 4;
        while (count < 4){
            System.out.println("count="+count);
            count++;
        }

        int count2 = 10; //do 不满足条件也会执行一遍
        do{
            System.out.println("count2="+count2);
            count2++;
        }while (count2 < 4);

        //for循环标签
        outer:
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                if(j == 1){
                    System.out.println("i="+i+", j="+j);
                    break outer;
                }
            }
        }

        outer:
        for (int i = 0; i < 5; i++){
            for(int j = 0; j< 4; j++){
                if(j == 1){
                    continue outer;
                }
                System.out.println("i = "+i+", j="+j);
            }
        }

        //数组类型 type[] arrayName 一种引用类型 初始化type[] arrayName = new type[]{e1, e2}
        //数组初始化完成，空间就已经确定了，长度不可变，即便把元素清空，空间依然存在

        //静态初始化，程序员显式定义元素的初始值，系统判断长度
        int[] intArray;
        intArray = new int[]{1,2,3,4};

        Object[] objArr;
        objArr = new Object[]{"Java", "c++"};

        Object[] objArr2;
        objArr2 = new String[]{"PHP", "C"};

        int[] arr2 = {2,4,5,6};//一般都是在定义时就初始化，采用这种简洁的用法

        //动态初始化，显式定义长度，元素系统使用默认值
        int[] arr3 = new int[5];//默认是0
        boolean[] brr = new boolean[5];//默认是falser
        Object[] objArr3 = new Object[4];//默认是null

        //不要同时指定长度和元素

        //foreach的使用
        String[] books = {"javaEE", "java", "Android"};
        //book临时变量名：数组名字
        for(String book:books){ //kotlin里的foreach是val类型的变量，不能改值
           // book = "python"; 这里book只是一个临时变量，不能给他赋值，赋了也改变不了数组元素的值
            System.out.println(book);
        }

        //当方法执行时，会建立自己的内存栈，方法的定义会依次存放在栈内，方法执行完毕，内存栈销毁
        //创建对象时，保存在运行时数据区，堆内存中，方法结束也不会销毁，只有当没有任何引用变量指向它时，垃圾回收器会在合适的时候回收
        //当数组不在使用时，可以把数组的引用赋值null,这个数组就变成了垃圾，等待回收
        //创建一个数组时，创建了两部分内存，第一是堆内存分配了数组的实际内存对象，第二是栈中的引用变量实际存的是堆中分配的数组对象的地址

        final Person[] persons;
        persons = new Person[2];

        Person zhang = new Person();
        zhang.age = 15;
        zhang.height = 158;

        Person lee = new Person();
        lee.age = 16;
        lee.height = 162;

        persons[0] = zhang;
        persons[1] = lee;

        lee.info();
        persons[1].info();

        //二维数组
        int[][] arrTwo = new int[4][];
        for (int i = 0 ;i < arrTwo.length ; i++){
            System.out.println(arrTwo[i]);
        }
        arrTwo[0] = new int[2];
        arrTwo[0][1] = 6;
        for(int k:arrTwo[0]){
            System.out.println(k);
        }
        for(int i = 0; i < arrTwo[0].length; i++){
            System.out.println(arrTwo[0][i]);
        }

        //静态初始化，不指定长度，指定内容
        String[][] strArrays= new String[][]{new String[3], {"Hello"}}; //数组用大括号表示
        String[][] str3 = {new String[3], new String[]{"Hello"}}; //只有在变量定义时才允许使用简化的定义
        //动态初始化，指定长度，后指定内容
        String[][] str2 = new String[2][];
        str2[0] = new String[3];
        str2[1] = new String[]{"Hello"}; //这里不能使用简化的{"Hello"}


        int[][][] a3 = new int[2][][];
        a3[0] = new int[2][];
        a3[0][0] = new int[2];
        a3[0][0][1] = 7;
        System.out.println(a3[0][0][1]);

        //Arrays函数
        int[] a1 = new int[]{3, 4, 5, 6};
        int[] a2 = new int[]{3, 2, 5, 6};
        final Person person = new Person(15, 158);
        Person person1 = new Person(17, 162);
        Person[] p1 = new Person[1];
        p1[0] = person;
        Person[] p2 = new Person[1];
        p2[0] = person1;
        Person[] p3 = {person, person1};

        //boolean = equals(type[] a, type[] b)
        System.out.println(Arrays.equals(a1, a2));
        System.out.println(Arrays.equals(p1, p2)); //如果数组 p1, p2里的person元素是同一元素，则为true

        //type[] = copyOf(type[] a, int length) type[] = copyOfRange(type[] a, int from, int to)
        int[] a4 = Arrays.copyOf(a1, 6);
        a1[0] = 9;//copy得到的数组元素的值并没有变成9
        System.out.println(Arrays.toString(a4));
        person.age = 22; //所有copy的后生成的数组的person元素都为22
        Person[] p5 = Arrays.copyOf(p1 , 3);
        System.out.println(Arrays.toString(p3));
        int[] a5 = Arrays.copyOfRange(a1, 1, 4); //[from, to) 如果to大于数组size则后面的补充默认值
        System.out.println(Arrays.toString(a5));
        Person[] p4 = Arrays.copyOfRange(p1, 0, 4);
        System.out.println(Arrays.toString(p4));

        //void fill(type[] a, type value)  void fill(type[] a, int from, int to)
        Arrays.fill(a1, 5);
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a4));
        Arrays.fill(a1 , 1, 3, 4);
        System.out.println(Arrays.toString(a1));
        Arrays.fill(p1, person1);
        System.out.println(Arrays.toString(p1));
        Arrays.fill(p1, 0 , 0, p1); //这里不能超过数组长度限制

        //void sort(type[] a) void sort(type[] a , int from, int to)
        Arrays.sort(a1);
        System.out.println(Arrays.toString(a1));
        Arrays.sort(a2);
        System.out.println(Arrays.toString(a2));
        Arrays.sort(p3); //数组对象为引用类型的话必须继承Comparable<Person>接口，否则抛出异常，列表中也不能有null类型
        System.out.println(Arrays.toString(p3));

        //void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        a1[3] = 7;
        System.arraycopy(a1, 2, a2, 2, 2);
        System.out.println(Arrays.toString(a2));
        System.arraycopy(p4, 0, p3, 1, 1);
        System.out.println(Arrays.toString(p3));

        //Java8新增 充分利用cpu执行能力提高设值、排序性能
        int[] arr1 = new int[]{3, -4, 25, 16, 30, 18};
        //void parallelSort(type[] a)
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
        Arrays.parallelSort(p3);
        //void parallelPrefix(type[] array, xxxBinaryOperator op)
        // 按照指定公式op将当前元素左边的元素进行运算的到新的值,第一个元素左边的值为1
        int[] arr4 = {3, -4, 25, 16, 30, 18};
        Arrays.parallelPrefix(arr4, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int i, int i1) {
                return i * i1;
            }
        });
        System.out.println(Arrays.toString(arr4));
        Person person2 = new Person(55, 164);
        Person[] p6 = {person, person2, person1};
        Arrays.parallelPrefix(p6, new BinaryOperator<Person>() {
            @Override
            public Person apply(Person person, Person person2) { //person2时当前索引， person时前一个索引
                System.out.println("person="+person);
                System.out.println("person2="+person2);
                person2.age = person.age;
                return person;
            }
        });
        System.out.println(Arrays.toString(p6));

        //void setAll(type[] array, IntToXxxxFunction generator)  //指定生成器为所有数组元素设置值
        int[] arr5 = new int[5];
        Arrays.parallelSetAll(arr5, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int i) { //i代表当前元素的索引值
                return i * 5;
            }
        });
        System.out.println(Arrays.toString(arr5));

        Person[] people = new Person[5];
        Arrays.parallelSetAll(people, new IntFunction<Person>(){

            @Override
            public Person apply(int i) {
                return new Person(1, i*10);
            }
        });
        System.out.println(Arrays.toString(people));

    }
}

class Person implements Comparable<Person>{
    public int age;
    public double height;

    Person(){

    }

    Person(int age, double height){
        this.age = age;
        this.height = height;
    }

    public void info(){
        System.out.println("age="+age+" ,height="+height);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        if( this.age > person.age) return 1;
        if(this.age < person.age) return -1;
        return 0;
    }
}
