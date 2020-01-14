package com.example.test.javalib.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;


import static java.lang.System.*;

public class CollectionsTest1 {
    public static void main(String[] args){
        Collection c = new ArrayList();
        Collection ps = new ArrayList<Person>();
        Person p1 = new Person("Bob", 11);
        Person p2 = new Person("Alice", 21);
        Person p3 = new Person("Eric", 31);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        Collection ps2 = new ArrayList<Person>();
        Person p4 = new Person("Bob", 11);
        Person p5 = new Person("Alice", 21);
        Person p6 = new Person("Eric", 31);
        Person p7 = new Person("Lily", 41);
        ps2.add(p1);
        ps2.add(p5);
        ps2.add(p6);
        Collection ps3 = new ArrayList<Person>();
        ps3.add(p1);
        ps3.add(p2);
        ps3.add(p7);

        //collections的函数
//        out.println(ps2.retainAll(ps3)); //根据equals方法判断
//        out.println("retain ="+ps2);
//        out.println(ps.contains(p4)); //这里的contain是根据Peson的Equals方法确定的  返回true
//        c.add(new String("孙悟空")); //存入String类型
//        c.add(6); //存入的Integer类型       同一个集合允许存入不同的类型
//        c.remove(6);
//        c.add("Java");
//        c.add("PHP");

        //Collections的foreach
//        c.forEach(new Consumer() { //遍历集合
//            @Override
//            public void accept(Object o) {
//                out.println(o);
//            }
//        });
//
//        out.println(c.contains("孙悟空")); //返回true
//        out.println(c);

//        //Iterator遍历集合
//        Iterator it = c.iterator();
//        while (it.hasNext()){
//            String str = (String) it.next(); //这里只是把集合里的元素的传递给str并不是元素本身
//            out.println(str);
//            //str = "a"; //因为是值传递，所以这里并不能改变集合里的元素，但是这里str的值确实变了，因此下面的删除方法走不了
//            if(str.equals("Java")){
//                it.remove(); //从集合中删除当前访问的元素
//            }
//        }
//        out.println(c);
//
//
//        Iterator it2 = ps.iterator();
//        //ps.add(p5);  //一旦创建了Iterator迭代器，就不能再改集合，否则会报ConcurrentModificationException错误
//        while (it2.hasNext()){
//            Person p = (Person) it2.next();
//            if(p.name.equals("Bob")){
//                p.name = "James";
//            }
//        }
//        ps.add(p5);//迭代完成之后方可修改结合集合
//        out.println(ps);
//        out.println(ps2);

        //foreach遍历集合
        for(Object p:ps){ //这里前面不许是Object类，需要在循环内部进行强制转换
            Person person = (Person) p;
//            if(person.name.equals("James")){
//                //ps.remove(person); //也会报ConcurrentModificationException
//            }
        }

        //ps.remove(p5); //当集合中包含多个这个元素是，删除第一个符合条件的元素
        ps.add(p5);
        out.println(ps);
//        ps.removeIf(new Predicate() {
//            @Override
//            public boolean test(Object o) {
//                Person p = (Person) o;
//                return p.name.equals("Alice"); //删除符合条件的元素，删除所有的名字为Alice的元素
//            }
//        });
//        out.println(ps);

        //运用Predicate简化运算
        //查找名字为Alice的人的数量
//        Predicate predicate = new Predicate() {
//            @Override
//            public boolean test(Object o) {
//                Person p = (Person) o;
//                return p.name.equals("Alice");
//            }
//        };
//        int total = 0;
//        for(Object o: ps){
//            Person p = (Person) o;
//
//            if(predicate.test(p)){
//                total++;
//            }
//        }
//        out.println("name为Alice的人的数量="+total);
        //Stream 操作集合，执行大量的聚集操作，一个Stream只能执行一次聚集操作
        Stream psStream = ps.stream();  //可以支持像Rxjava那样的流式操作
//        psStream.max(new Comparator() {
//            @Override
//            public int compare(Object o, Object t1) {
//                if(((Person) o).age > ((Person) t1).age) return 1;
//                if(((Person) o).age == ((Person) t1).age) return 0;
//                else return -1;
//            }
//        }).get();

         //一个Stream只能执行一次聚集操作 会报错IllegalStateException: stream has already been operated upon or closed
//        long count = psStream.filter(new Predicate() {
//            @Override
//            public boolean test(Object o) {
//                Person p = (Person) o;
//                return p.age > 20;
//            }
//        }).count();
//        out.println("年龄大于20的人的数量="+count);

//        Person p = ((Optional<Person>) psStream.findFirst()).get();
//        out.println("集合中的第一个元素"+p);

        //psStream.toArray(); 转为数组
        int[] ints = psStream.mapToInt(new ToIntFunction() {
            @Override
            public int applyAsInt(Object o) {
                return ((Person) o).age;
            }
        }).toArray();
        out.println("打印年龄数组"+Arrays.toString(ints));

        //HashSet具有良好的存储和查找功能
        //不允许元素重复（hash值相等）
        //无序，可能和添加顺序不同
        //不同步
        //可以有null元素
        //HashSet根据元素的hashCode来决定元素的位置
        //如果equals相等但是hashCode不相等依然可以添加
        //HashSet判断两个元素相等必须equals相等hashCode也相等

        //当时则需要使用HashSet保存某个类的对象时，重写equals方法的同时，也应该重写hashCode方法，来保证equals返回true时，hasCode也相同
        //HashSet查找元素的速度快，根据元素的hashCode值根据hash算法快速找到元素的位置

        //重写hashCode方法的基本原则
        //同一个对象多次调用hasCode返回值相同
        //两个对象equals为true, hashCode为true
        //参与equals比较的实例变量，都应该参数hashCode计算

        //可以把两个实例变量的hashCode值相加， 为避免相等，可以给每个hashCode值乘以质数以后再相加f1.hashCode()*19+f2.hashCode()*31
        out.println("p1的hashCode="+p1.hashCode());
        out.println("p2的hashCode="+p4.hashCode());
        //如果修改HashSet种的元素，修改后可能会导致元素相等，HashSet发生错乱，应该避免修改HashSet中的元素

        //LinkedHashSet 用链表来维护元素的插入顺序，性能略低于hashset但是迭代访问全部元素时有很好的性能，这里也不允许有重复的元素
        LinkedHashSet lp = new LinkedHashSet<Person>();
        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(6);
        lp.add(p5); //相同的元素添加不成功
        out.println("LinkedListSet "+lp);

        //加入TreeSet的元素会按照采用红黑树的数据结构来存储元素，是已经排好序
        //支持自然排序和定制排序
        //把一个对象添加到TreeSet里该对象必须实现Comparable接口，否则抛出异常，添加的也必须时用同一个类的对象，注意HashSet可以添加不同类的实例
        TreeSet treeSet = new TreeSet();
        treeSet.add(p1);
        treeSet.add(p3);
        treeSet.add(p2);
        out.println("TreeSet "+treeSet);

        //TreeSet判断集合元素是否重复的标准是compareTo的返回值
        //因此要保证重写的时候compareTo与equls方法的返回值是同等的
        //也不要修改TreeSet内部的元素
        TreeSet treeSet1 = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                return 0;
            }
        }); //传入Comparator实例定制排序

        //EnumSet类
        //所有元素必须是指定枚举类型的枚举值
        //占用内存小，运行效率高
        EnumSet e1 = EnumSet.allOf(Season.class);
        out.println("EnumSet集合all="+e1);
        EnumSet e2 = EnumSet.noneOf(Season.class);
        out.println("EnumSet集合none="+e2);
        e2.add(Season.SPRING);
        EnumSet e3 = EnumSet.of(Season.SUMMER, Season.WINNTER);
        out.println("EnumSet集合of"+e3);
        EnumSet e4 = EnumSet.range(Season.SUMMER, Season.WINNTER);
        out.println("EnumSet集合range"+e4);
        EnumSet e5 = EnumSet.complementOf(e4);
        out.println("EnumSet集合complementOf"+e5);
        EnumSet e6 = EnumSet.copyOf(e5);
        out.println("EnumSet集合copyOf"+e6);
        //总结HashSet比TreeSet性能好，只有元素需要排序时使用TreeSet
        //LinkedList有一个链表维护插入顺序，插入删除操作比HashSet快，但是遍历会更快
        //EnumSet性能最好，只能保存同一个枚举类的枚举值作为元素
        //这些Set都是线程不安全的
        //保证线程安全Collections.synchronized包装集合
        Collections.synchronizedSet(new TreeSet<Person>());





    }
}
//set , List ,Map , Queue四种集合
//常见的集合实现类HashSet、TreeSet、ArrayList、ArrayQueue、LinkedList、TreeMap
//Iterable接口是Collection的父接口
class Person implements Comparable{
    String name;
    int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {

        if (this.age > ((Person) o).age) return 1;
        if (this.age == ((Person) o).age) return 0;
        else return -1;

    }
}

enum Season{
    SPRING, SUMMER, FALL, WINNTER
}