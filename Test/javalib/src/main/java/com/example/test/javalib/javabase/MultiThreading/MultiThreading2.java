package com.example.test.javalib.javabase.MultiThreading;
import static java.lang.System.*;
public class MultiThreading2 {
    public static void main(String[] args){
        //join线程
//        for(int i = 0; i < 100; i++){
//            if(i == 20){
//                FirstThread jt = new FirstThread();
//                jt.start();
//                try{
//                    jt.join(); //需要在start之后调用//main线程要等到jt线程执行完才能继续执行
//                    //join(mills)//等待被join的线程的时间最长为多少毫秒，如果超过时间还没执行完，则不再等待继续执行
//                }catch (InterruptedException in){
//                    in.printStackTrace();
//                }
//
//            }
//            out.println(Thread.currentThread().getName()+"    "+i);
//        }

//        Thread daemonThread = new Thread(){
//            @Override
//            public void run(){
//                for(int i = 0; i < 100; i++){
//                    out.println("后台线程 i="+i);
//                }
//            }
//        };
//        //线程开始前就要知道是前台还是后台，setDaemon必须在start之前设置，否则报异常
//        daemonThread.setDaemon(true);
//        //后台线程执行不到99，因为此时所有的前台线程都执行完成，死亡了，JVM退出，后台线程自然死亡
//        daemonThread.start();
//        //前台线程创建的默认是前台线程，后台线程创建的线程默认是后台线程

        Thread yieldThread = new Thread(){
            @Override
            public void run(){
                for(int i = 0; i < 50; i++){
                    out.println(getName()+"    "+i);
                    if(i == 20){
                        Thread.yield();
                    }
                }
            }
        };
        yieldThread.setName("高级");
        yieldThread.setPriority(Thread.MAX_PRIORITY);
        //如果是单cpu可以看到yield高级线程后，高级线程又立刻被执行
        //因为其他线程优先级都没有他高，所以会继续调度高级线程
        yieldThread.start();


        Thread yieldThread2 = new Thread(){
            @Override
            public void run(){
                for(int i = 0; i < 50; i++){
                    out.println(getName()+"    "+i);
                    if(i == 20){
                        Thread.yield();
                    }
                }
            }
        };
        yieldThread2.setName("低级");
        yieldThread2.setPriority(Thread.MIN_PRIORITY);
        yieldThread2.start();

    }
}

//join 让一个线程等待另外一个线程完成
//在A线程执行过程中，调用了B.join，则时要求A线程要等B线程执行完才可以
//用于将主线程的大问题分成很多小问题，分配到不同的线程，小问题之间并发处理，所有小问题处理完成，再继续执行主线程

//Daemon Thread
//后台线程，一直在后台运行，为前台线程提供服务，前台线程都死亡，后台线程自动死亡
//JVM垃圾回收线程就是典型的后台线程
//Thread对象设置setDaemon(true)就将线程设置为后台线程了

//Thread.sleep(mills) 让当前线程暂停执行，进入阻塞状态，这段时间不获得执行机会，即使此时没有其他线程在执行
//Thread.yield()也让线程暂停执行，但是线程进入的是就绪状态，线程调度器重新调度，有可能将此线程又调起来了

//sleep比yield有更好的移植性，不建议用yield控制并发线程的执行

//线程默认的优先级与创建它的父线程的优先级相同，main线程是普通优先级
//setPriority(int priority) getPriority()
//MAX_PRIORITY 10    MIN_PRIORITY 1  NORM_PRIORITY 5
