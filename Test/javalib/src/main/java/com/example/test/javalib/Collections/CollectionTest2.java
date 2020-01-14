package com.example.test.javalib.Collections;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import static java.lang.System.*;

public class CollectionTest2 {
    //List有序可重复集合
    //List操作集合找位置删除等也是根据元素的equals方法
   public static void main(String[] args){
       List<Person> ps = new ArrayList<>();
       Person p1 = new Person("Bob", 11);
       Person p2 = new Person("Alice", 21);
       Person p3 = new Person("Eric", 31);
       Person p4 = new Person("Bob", 11);
       Person p5 = new Person("Alice", 21);
       Person p6 = new Person("Eric", 31);
       Person p7 = new Person("Lily", 41);
       ps.add(p1);
       ps.add(p2);
       ps.add(p3);
       Person p8 = ps.set(0, p7); //插入到指定位置
       List<Person> ps1 = ps.subList(1,3);
       out.println("subList = "+ps1);
       out.println(ps);
//       ps1.replaceAll(new UnaryOperator<Person>() {
//           @Override
//           public Person apply(Person person) {
//               person.name = "KKK";
//               return person;
//           }
//       });
       out.println("replaceAll = "+ps1);
       out.println(ps);
       ps1.sort(new Comparator<Person>() { //按照指定规则排序
           @Override
           public int compare(Person person, Person t1) {
               return 0;
           }
       });

       //ArrayList和Vector类封装一个动态的允许再分配的Object[]数组
       //使用initialCapacity来设置数组的长度，超过长度可自动增加
       //一次性向数组中添加大量元素时，可使用ensureCapacity方法一次性增加initialCapacity，减少重新分配的次数，提高性能
       //知道长度一开始就指定长度，不指定默认为0，中间还可以重新分配长度
       ArrayList<Person> ps3 = new ArrayList<>(1); //可以传入size
       ps3.add(p1);
       ps3.ensureCapacity(10); //数组长度增加大于或等于10个长度空间
       out.println("ensureCapacity 数组大小="+ps3.size());
       ps3.trimToSize(); //
       out.println("trimToSize 数组大小="+ps3.size());

       //Arrays提供了Array.asList函数返回一个固定长度的list,但是这里返回的并不是ArrayList
       //而是Arrays的内部类Arrays.ArrayList 不能对对该List做add remove操作
       //会报unSupportedOperationException的异常




       //队列Queue先进先出
       //void add(Obeject e)加入到队尾
       //boolean offer(Object c)将元素加入到队列尾部，当有容量限制时，此方法比add更好
       //Object element()获取对头的元素，但是不从队列里删除该元素
       //Object peek()获取队列头部的元素，但是不删除元素，列表为空返回null
       //Object poll()获取队列头部的元素，并删除元素，队列为空，返回null
       //Object remove()获取队列头部的元素，并删除该元素

       //PriorityQueue 保存队列的顺序是按照是元素大小而不是加入顺序
       //peek或者poll时取出的是队列里最小的元素
       PriorityQueue p = new PriorityQueue<Integer>();
       p.add(7);
       p.add(-3);
       p.add(20);
       p.add(18);
       out.println(p);
       out.println(p.poll());//最先加入的元素是头部
       out.println(p.peek());

       //Deque 双端队列 不仅当队列使用也可以当栈使用
       //addFirst(Object e)指定元素插入到队列开头 //开头是指最先加入元素的地方
       //addLast(Object e)指定元素插入到队列的尾
       //Iterator descendingIterator()返回迭代器，以逆向顺序迭代队列中的元素
       //Object getFirst() 获取但不删除双端队列的第一个元素
       //Object getLast() 获取但不删除双端队列的最后一个元素
       //boolean OfferFirst(Object o)将指定元素插入队列的开头
       //boolean OfferLast(Object o)将指定元素插入队列的对尾
       //Object peekFirst()获取但不删除双端队列的第一个元素
       //Object peekLast() 获取但不删除双端队列的最后一个元素
       //Object pollFirst()获取并删除第一个元素
       //Object pollLast()获取并删除最后一个元素
       //Object pop()pop出栈顶元素
       //void push(Object e)将一个元素push进栈顶
       //Object removeFirst()
       //Object removeLast()
       //boolean removeLastOccurrence(Object o)删除最后一次出现的元素o

       //Deque的典型实现类时ArrayDeque 基于数组实现的双端队列，可指定numElement,不指定默认为16
       //
       //ArrayDeque作为栈使用
       ArrayDeque stack = new ArrayDeque();  //add相当于addLast
       stack.push("Java"); //先加入元素的一端算队列头，也是栈顶对，真正使用的时候画个图就知道了
       stack.push("PHP");
       stack.push("Android");
       out.println(stack);
       out.println("peek="+stack.peek());
       out.println("pop="+stack.pop());

       //作为队列使用
       ArrayDeque queue = new ArrayDeque();
       queue.offer("Java");
       queue.offer("PHP");
       queue.offer("Android");
       out.println("queue peek ="+queue.peek());
       out.println("queue poll="+queue.poll());


       //LinkedList与是List接口的实现类同时也实现了Deque接口所以既可以当成栈使用，也可以当队列使用,也可以当list使用
       //以链表形式存储，随机访问的性能较差，但是插入，删除性能较出色
       LinkedList linkedList = new LinkedList();
       linkedList.offer("Java");
       linkedList.push("PHP");
       linkedList.offerFirst("Android");
       for(int i = 0; i < linkedList.size(); i++){
           out.println(linkedList.get(i));
       }
       out.println(linkedList.peekFirst());
       out.println(linkedList.peekLast());
       out.println(linkedList.pop());
       out.println(linkedList.pollLast());

       //总结， 所有基于数组实现的线性表随机访问比较出色， 基于链式，插入删除比较好，总体ArrayList好些，推荐使用
       //遍历时ArrayList 使用get遍历性能更好，LinkedList采用Iterator更好
       //需要经常插入删除来改变包含大量数据的List集合，可考虑使用LinkedList,使用ArrayList可能需要多次重新分配内部数组的大小，效果较差
       //线程安全用
       Collections.synchronizedList(new ArrayList());


       //Map
       Map map  = new HashMap();
       map.put("Java", p1);
       map.put("PHP", p2);
       map.put("Android", p3);
       //相同的key,放入不同的value时，新的value会覆盖原来的value，并返回原来的value
       Person p9 = (Person) map.put("Android", p4);
       out.println("被覆盖的元素="+p9);
       out.println(map);
       map.containsKey("Java");
       map.containsValue(p1);
       for(Object key : map.keySet()){
           out.println("value="+map.get(key));
       }
//       map.compute("Java", new BiFunction() {
//           @Override
//           public Object apply(Object o, Object o2) {
//               Person p = (Person) o2;
//               Person ori = (Person) p;
//               p.age = 88+ori.age;
//               return p;
//           }
//       });
//       out.println("compute="+map);
       map.replace("Python", p7);
       out.println("replace ="+map); //没找到对应的key值所以不添加
       map.merge("Java", p7, new BiFunction() {
           @Override
           public Object apply(Object o, Object o2) { //
               Person po = (Person) o; //map里原来的元素
               Person p02 = (Person) o2; //传入的元素
               out.println(po);
               out.println(p02);
               return null; //返回nullJava的键值对删除了
           }
       });
       out.println("merge="+map);

       //HashMap 线程不安全 允许key null 也允许value null
       map.forEach(new BiConsumer() {
           @Override
           public void accept(Object o, Object o2) { //o是key o2是value
               String po = (String) o;
               Person po2 = (Person) o2;
               out.println("foreach o="+po);
               out.println("foreach o2="+po2);
           }
       });
       Set set = map.entrySet();
       Iterator it = set.iterator();
       while (it.hasNext()){
           Map.Entry entry = (Map.Entry) it.next();
           out.println("entry     "+entry.getKey());

       }

       //TreeMap与TreeSet相同，根据key值进行排序
       //EnumMap key值必须是同一个枚举类的实例
       EnumMap enumMap = new EnumMap(Season.class);
       enumMap.put(Season.SPRING, "春暖花开");
       //如果总是需要一个排序好的Map 则用TreeMap，其他时候用HashMap(底层也是数组实现）

       //Collections工具类
       //reverse 反转列表
       //shuffle(List list) 随机排序，相当于洗牌
       //sort(list, Comparator)
       //swap(list , i, j)
       //rotate(list distacne)为整数时将list的后distance个元素向前移， 为负数时将钱distance个移到后面

       //int binarySearch(List , Object) 二分法查找指定元素在集合中的索引， 集合处于有序状态
       //Object max(Collection c) max(Collection c, Comparator p)
       //min
       //fill(Collection c, Object o)
       //int frequency(Collection c, Object o) 返回指定元素出现的次数
       //int indexOfSubList(List source, List sub) 子List在父list中第一次出现的位置
       //int lastIndexOfSubList(List source, List sub) 子List在父list中第一次出现的位置
       //boolean replaceAll(list, Object old, Object new) 所有的old用New 替换

       //同步控制Collections.synchronizedList(new ArrayList())

       //设置不可变集合
       List l = Collections.emptyList();
       Set l1 = Collections.singleton("疯狂");
       Map source = new HashMap();
       source.put("Java", 88);
       source.put("PHP", 77);
       Map unmodify = Collections.unmodifiableMap(source); //返回集合的只读版本
       //unmodify.put("An", 88); //UnsupportedOperationException











   }
}
