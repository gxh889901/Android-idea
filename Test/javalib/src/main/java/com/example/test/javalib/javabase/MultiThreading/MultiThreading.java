package com.example.test.javalib.javabase.MultiThreading;
import static java.lang.System.*;
public class MultiThreading {
    public static void main(String[] args){
        //主线程的线程执行体

        //线程创建方式1
//        for(int i = 0; i < 100; i++){
//            out.println(Thread.currentThread().getName()+"    "+i);
//            //Thread.currentThread Thread类的静态方法返回当前正在执行的线程对象
//            //getName获取线程的名字
//            if( i == 20){
//                Thread thread1 = new FirstThread();
//                thread1.setName("线程1"); //setName设置线程的名字
//                thread1.start();
//                Thread thread2 = new FirstThread();
//                thread2.setName("线程2");
//                thread2.start();
//            }
//        }
        //线程创建方式2
        for(int i = 0; i < 100; i++){
            out.println(Thread.currentThread().getName()+"    "+i);
            if(i == 20){
                SecondThread st = new SecondThread();
                SecondThread st2 = new SecondThread();
                new Thread(st, "新线程1").start(); //可以传入线程名字
                new Thread(st, "新线程2").start();
                //两线程都传入st，可以使两个线程共享st实例变量，此时st里打印出的i是连续的
            }
        }


    }
}
//并发(concurrency)同一时刻只有一条指令执行，但是多个进程指令快速轮换执行
//并行（parallel）同一时刻多条指令在多个处理器上同时执行
//进程是系统进行资源分配和调度的单位，线程时是进程的执行单元，进程中独立并发的执行流
//线程拥有自己的堆栈，自己的程序计数器，自己的局部变量，与其他进程共享进程资源
//线程运行时抢占式的，线程的调度和管理由进程完成
//一个程序运行后至少有一个进程，一个进程至少包含一个线程
//线程优势因为划分的尺度小，多个线程共享内存，因此并发性高，执行效率高
//线程共享进程的虚拟空间,包括进程的代码段，共有数据等等，很容易实现相互之间的通信
//所有的线程都必须是Thread类或者其子类

//创建线程方式1，创建Thread的子类
class FirstThread extends Thread{
    private int i;
    public void run(){
        //子线程的线程执行体
        for(; i < 100; i++){ //线程1，2都能打印出0，1，2, 用Thread创建线程实例，多个线程之间无法共享实例变量
            out.println(getName()+"    " +i);
        }
    }
}
//方式2，实现Runnable接口
class SecondThread implements Runnable{
    private int i;
    @Override
    public void run(){
        for(; i < 100; i++){
            out.println(Thread.currentThread().getName()+"    "+i);
        }
    }
}