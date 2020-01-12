package com.example.test.javalib.javabase.MultiThreading;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
//        for(int i = 0; i < 100; i++){
//            out.println(Thread.currentThread().getName()+"    "+i);
//            if(i == 20){
//                SecondThread st = new SecondThread();
//                SecondThread st2 = new SecondThread();
//                new Thread(st, "新线程1").start(); //可以传入线程名字
//                new Thread(st, "新线程2").start();
//                //两线程都传入st，可以使两个线程共享st实例变量，此时st里打印出的i是连续的
//                //两线程分别传入st,st2则i不连续，两者不共享i
//            }
//        }
        //线程创建方式3
        //
        final FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() { //匿名内部类
            @Override
            public Integer call() throws Exception { //call方法是线程执行体
                int i= 0;
                for(; i < 100 && !Thread.currentThread().isInterrupted(); i++){
                    out.println(Thread.currentThread().getName()+"的循环变量i的值="+i);
                }
                return i;
            }
        });
        FutureTask<Integer> task2 = new FutureTask<Integer>(new Callable<Integer>(){
            public Integer call() throws Exception{
                int i= 0;
                for(; i < 100; i++){
                    out.println(Thread.currentThread().getName()+"的循环变量i的值="+i);
                }
                return i;
            }
        });
        FirstThread startDead = new FirstThread();
        for(int i = 0; i < 100; i++){
            out.println(Thread.currentThread().getName()+"    "+i);
            if(i == 20){
                new Thread(task, "线程1").start();
                //可以看到子线程的打印并不是从Main i =20之后立即执行的
                //此处想让子线程调用start后立即执行，可以调用Thread.sleep(1)，让主线程睡一毫秒，此时CPU就会立刻去执行其他处于就绪状态的线程
                try{
                    Thread.sleep(1);
                }catch (InterruptedException in){
                    in.printStackTrace();
                }

                startDead.start();
                //处于运行，阻塞，就绪状态的线程返回true, 处于死亡和新建状态返回false
                out.println("startDead是否还或着"+startDead.isAlive());

                //new Thread(task, "线程2").start(); //两个线程传入一个task只执行一个线程的程序，线程2不执行
            }
            if(i > 20 && !startDead.isAlive()){
                startDead.start();
                //不要对已经死亡的线程重新调用start抛出异常IllegalThreadStateException
                //一个线程实例只能有调用一次start
            }
            if (i == 30) {
//                try { //在此处执行FutureTask的get函数会阻塞主线程等到，子线程执行完毕后，主线程才继续执行
//                    out.println(task.get());
//                } catch (InterruptedException in) {
//                    out.println(in.getMessage());
//                } catch (ExecutionException ex) {
//                    out.println(ex.getMessage());
//                }

                try{
                    out.println(task.get(1, TimeUnit.MILLISECONDS));
                }catch (InterruptedException in){ //当子线程被中断的时候返回异常
                    out.println(in.getMessage());
                }catch (ExecutionException ex){
                    out.println(ex.getMessage());
                }catch (TimeoutException timeout){ //超出时间规定的时间抛出异常，继续执行主线程的程序
                    out.println(timeout.getClass());
                }
                task.cancel(true);
                //如果需要中断可以在call方法里加上判断Thread.currentThread().isInterrupted() == false时才执行任务
                //这里执行cancel不管用 task还是执行到了最后打印一直到999
                //目前的理解这里的cancel只是设置线程可以被intterupt但是并未真正中断，java不推荐中断
                out.println("isCancelled=" + task.isCancelled()); //如果Callable正常完成前被取消返回true， 没被取消打印值呢就返回了true
                out.println("isDone=" + task.isDone()); //Callable任务已经完成返回TRUe, 在这个时间节点上子线程的任务明明没有完成也返回了true
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

//三种方式优缺点
//使用Runnable和Callable，线程类还可以继承其他的类
//使用Runnable和Callable，可以让多个线程处理同一份资源
//Thread类就是使用稍微简单些，比如访问当前线程
//一般推荐使用使用Runnable和Callable

//new创建线程实例，线程处于New状态，JVM为其分配内存，初始化成员变量，此时只是一个普通对象
//调用start后，处于就绪状态，JVM为其创建方法调用栈和程序计数器，此时线程只是可以运行了，但是并没有运行，而是等待JVM线程调度器调度
//永远不要调用线程的run方法，调用后直接执行，不能与其他线程并发执行，此时只是一个普通方法，此时线程处于运行状态，不能再调用start方法了
//start方法只能对新建的线程使用，否则引发IllegalThreadException异常

//线程进入阻塞状态的几种情况
//1）调用sleep放弃所占用的资源   //sleep时间到
//2)调用了一个阻塞式的IO方法，在该方法返回前，该线程被阻塞  //IO方法返回
//3)线程试图获得一个同步监视器，但该同步监视器被其他线程持有 //获得同步锁
//4)线程在等待某个通知notify                           //收到通知
//5)程序调用了suspend方法将线程挂起，这个方法容易导致死锁，避免使用 //resume
//阻塞的线程，阻塞状态结束后重新进入就绪状态，等待调度器调用
//处于运行状态的线程失去处理器资源变为就绪状态
//yield()方法可以让处于运行状态的线程，进入就绪状态

//线程死亡
//run或call方法执行完成 //线程抛出一个未捕获的异常或者Error
//直接调用stop方法，容易产生死锁，不推荐使用

//当主线程结束时，其他线程也会继续执行，不受主线程影响

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
//方式3  创建有返回值得线程，使用Callable和Future



