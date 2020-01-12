package com.example.test.javalib.javabase.MultiThreading;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.*;
public class SynchronizedThread {
    public static void main(String[] args){
        final Account acc = new Account("123446", 1000);
          //线程安全
//        new DrawThread("甲", acc, 800).start();
//        new DrawThread("乙", acc, 800).start();

          //线程死锁
//        DeadLock d = new DeadLock();
//        new Thread(d).start();
//        d.init();

          //线程通信
//        AccountCom accountCom = new AccountCom("1123445",0);
//        new DrawThreadCom("取钱者", accountCom, 900).start();
//        new DepositThreadCom("存钱甲", accountCom, 900).start();
//        new DepositThreadCom("存钱乙", accountCom, 900).start();
//        new DepositThreadCom("存钱丙", accountCom, 900).start();
        //虽然有一个取钱者，三个存钱者，但是存钱取钱还是交替执行的
        //最后线程会阻塞，因为剩余的存钱操作得不到通知，一直处于wait阻塞状态，但是这里不是死锁

        //阻塞队列BlockingQueue
//        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
//        try{
//            bq.put("Java");
//            bq.put("Java");
//            //bq.put("Java"); //队列已经满了，再调用put会阻塞线程
//            //bq.add("Java"); //队列已经满了，再调用add会抛出异常
//            bq.offer("java"); ////队列已经满了，再调用offer返回false
//        }catch (InterruptedException in){
//            in.printStackTrace();
//        }

        //阻塞队列BlockingQueue
        //向已经满了的队列中放入元素会阻塞线程
        //向空队列取出元素会阻塞线程
        //使用于生产者消费者模式这样两个线程共享一个队列的情形
//        BlockingQueue bq = new ArrayBlockingQueue<String>(1); //这个不对呀为什么会出现连续出现生产者的情况
//        new Producer("生产者1", bq).start();
//        new Producer("生产者2", bq).start();
//        new Producer("生产者3", bq).start();
//        new Consumer("消费者1", bq).start();

        //线程组ThreadGroup
        //执行过程中抛出了未处理异常，在JVM结束该线程之前会寻找是否有对应的Thread.UncaughtExceptionHandler对象，他是Threa类的静态内部接口
        //该处理器对象会调用uncaughtException(Thread t, Throwable e)方法处理异常
        //static setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) 线程类的所有实例设置
        //setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) 指定线程实例设置
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //默认属于主线程组，子线程默认和父线程同一个组
        //ThreadGroup 实现了Thread.UncaughtExceptionHandler接口，作为线程的默认处理器
        //如果该线程设置了异常处理器则使用，否则寻找线程组的异常处理器
        //线程组的异常处理器 如果异常对象时ThreadDeath则不处理，否则，将异常跟踪栈的信息打印到System.err错误输出流，并结束该线程
        out.println("主线程组的名字  "+mainGroup.getName());
        out.println("主线程是否是后台线程"+mainGroup.isDaemon());
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        new Thread(tg, new Runnable() {
            @Override
            public void run() {

            }
        }).start();
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable throwable) {
//
//            }
//        });
//        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
//        int a = 5/0; //还是会异常传递给上级，因此不能执行下面的打印语句，ExceptionHandler的作用就是打印信息便于追踪问题
//        out.println("程序正常结束");
//
//        //创建线程池                                   //创建具有固定6个线程的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(6); //Executors生产线程池的工厂类  ExecutorService线程池
//        //创建Runnable或者Callable任务
//        Runnable target = new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0; i < 100; i++){
//                    out.println(Thread.currentThread().getName()+"的i值为"+i);
//                }
//            }
//        };
//        //提交任务
//        Future f= pool.submit(target); //run方法没有返回值因此这里也有，但是可以通过调用isDone isCancelled方法获取Runnable的执行状态
////        try {
////            out.println("Future="+f.get());
////        }catch (ExecutionException in ) {
////            in.printStackTrace();
////        }catch (InterruptedException in){
////            in.printStackTrace();
////        }
//        out.println("isDone="+f.isDone());
//        pool.submit(target);
//        //关闭线程池
//        pool.shutdown();


        //ForkJoinPool
//        ForkJoinPool fork = new ForkJoinPool();
//        out.println(fork.getPoolSize()); //为什么是0个
//        fork.submit(new PrintTask(0, 300));
//        try{
//            fork.awaitTermination(2, TimeUnit.SECONDS);
//        }catch (InterruptedException in){
//            in.printStackTrace();
//        }
//        fork.shutdown();

        //有返回值的ForkJoinPool
        int arr[] = new int[100];
        Random random = new Random();
        int total = 0;
        for(int i = 0, len = arr.length; i < len; i++){
            int tmp = random.nextInt(20);
            total += (arr[i] = 1);
        }
        out.println("total"+ total);
        ForkJoinPool fork2 = new ForkJoinPool();
        Future<Integer> res = fork2.submit(new CalTask(arr, 0,arr.length));
        try{
            out.println("ForkJoinPool Return="+res.get());
        }catch (ExecutionException ex){
            ex.printStackTrace();
        }catch (InterruptedException in){
            in.printStackTrace();
        }
        fork2.shutdown();

        //ThreadLocal类
        AccountThreadLocal at = new AccountThreadLocal("初始名");
        //两个线程共享一个账户
        //但是账户名是ThreadLocal类型的，所以每个线程有各自的账户名副本， 因此在i==6之后
        //两个线程访问同一个账户时出现不同账户名
        //加上主线程，实际账户名有三个副本
        new ThreadLocalTest(at, "线程甲").start();
        new ThreadLocalTest(at, "线程乙").start();
    }
}
class MyExHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        out.println(thread.getName()+" 线程出现了异常:"+ throwable);
    }
}
//线程安全是以降低程序效率为代价的
//不要对类的所有方法进行同步，只对那些改变共享资源的方法进行同步
//如果可变类有两种运行环境，单线程和多线程，则为该可变类提供线程不安全版本和线程安全版本
//如StringBuilder在单线程下具有较好的性能， 保证多线程安全用StringBuffer
class Account{
    private String accountNo;
    private double balance;
    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String accountNo, double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int hashCode(){
        return accountNo.hashCode();
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj != null && obj.getClass() == Account.class){
            Account target = (Account) obj;
            return accountNo.equals(target.accountNo); //这里的字符串比较用equals不能用==
        }
        return false;
    }

//    //同步方法
//    //同步方法不用指定同步监视器，同步方法的监视器就是调用方法的对象this
//    //当执行该方法时，不允许其他对象发修改该对象
//    public synchronized void draw(double drawAmount){
//        if(balance >= drawAmount){
//            out.println("吐出钞票："+drawAmount);
//            balance -= drawAmount;
//            out.println("余额为："+balance);
//        }else {
//            out.println("余额不足");
//        }
//    }

    public void draw(double drawAmount){
        //加锁
        lock.lock();  //一个lock对象对应一个accout对象，所以同一时刻只能有一个线程进入临界区
        try{
            if(balance >= drawAmount){
                out.println("吐出钞票："+drawAmount);
                balance -= drawAmount;
                out.println("余额为："+balance);
            }else {
                out.println("余额不足");
            }
        }finally { //使用finally块来确保释放锁
            //修改完成释放锁
            lock.unlock();
        }
    }
    public void minus(){
        balance -= 300;
        out.println("减法余额="+balance);
    }
}

class DrawThread extends Thread{
    private Account account;
    private double drawAmount;
    public DrawThread(String name, Account account, double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }
    @Override
    public void run(){
        //同步代码块
        //执行代码块的里代码之前先获得小括号里的资源的锁
        //在此期间其他任何的线程都无法修改该资源
        //等到同步代码块执行完毕，释放锁，其他线程才可以修改该资源
//        synchronized (account){
//            if(account.getBalance() >= drawAmount){//第二个线程进入时setBalance还没有更新，此时还是1000，但是当第二个线程执行取出时，getBalance变为了200
//                out.println("吐出钞票  "+drawAmount);
//                account.setBalance(account.getBalance() - drawAmount);
//                out.println("余额为："+account.getBalance());
//            }else{
//                out.println("余额不足！！");
//            }
//        }
        account.draw(drawAmount);

    }
}
//释放同步监视器
//1)同步代码块和同步方法执行完毕
//2)同步代码块和同步方法遇到break, return终止
//3)同步代码块和同步方法遇到未处理的Error和Exception 导致异常结束
//4)同步代码块和同步方法遇到,执行了同步监视器的wait()方法，当前线程暂停，并释放同步监视器

//不会释放同步监视器
//1)线程同步代码块和同步方法，程序调用Thread.sleep和Thread.yield，不会释放同步监视器
//2)线程同步代码块和同步方法，其他线程调用了该线程的suspend方法，也不会释放

//同步锁Lock
//常用ReentrantLock可重入锁

//死锁，两个线程都在等待对方释放同步监视器
class A{
    public synchronized void foo(B b){
        out.println("当前线程："+Thread.currentThread().getName()+"进入了A实例的foo()方法");
        try{
            Thread.sleep(200);
        }catch (InterruptedException in){
            in.printStackTrace();
        }
        out.println("当前线程："+Thread.currentThread().getName()+"企图调用B实例的last()方法");
        b.last();
    }
    public synchronized void last(){
        out.println("进入了A类的last方法内部");
    }
}
class B{
    public synchronized void bar(A a){
        out.println("当前线程："+Thread.currentThread().getName()+"进入了B实例的bar方法");
        try{
            Thread.sleep(200);
        }catch (InterruptedException in){
            in.printStackTrace();
        }
        out.println("当前线程："+Thread.currentThread().getName()+"企图调用A实例的last()方法");
        a.last();
    }
    public synchronized void last(){
        out.println("进入了B类的last方法内部");
    }
}
class DeadLock implements Runnable{
    A a = new A();
    B b = new B();
    public void init(){
        Thread.currentThread().setName("主线程");
        a.foo(b);
        out.println("进入了主线程之后");
    }
    public void run(){
        Thread.currentThread().setName("副线程");
        b.bar(a);
        out.println("进入了副线程之后");
    }

}

//线程通信  wait() notify() notifyAll() 这三个方法属于Object类，不属于Thread类，
//方法必须由同步监视器对象来调用
//对于同步方法，该类的实例是同步监视器，所以可以在同步方法内部直接调用
//对于同步代码块只能由小括号里的对象调用
//wait()：导致该线程等待，直到其他线程调用该同步监视器的notify或notifyAll()方法 wait(mills)
//notify()：唤醒在此同步监视器上等待的单个线程，如果有多个线程，那么随机唤醒一个，
// 只有当前线程放弃对同步监视器的锁定(执行wait()方法就是对同步监视器的锁定），才可以执行被唤醒的线程
//notifyAll
class AccountCom{
    private String accountNo;
    private double balance;
    private boolean flag = false;//是否有人存过钱
    private ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public AccountCom(String accountNo, double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    //Synchronized与wait搭配的线程通信
//    public synchronized void draw(double drawCount){
//        try{
//            if(!flag){
//               wait(); //如果flase,则没有人存钱，取钱线程阻塞
//            }else {
//                out.println(Thread.currentThread().getName()+"取钱:"+drawCount);
//                balance -= drawCount;
//                flag = false; //是否有存款标志为false
//                notifyAll();//唤醒存钱线程
//
//            }
//        }catch (InterruptedException in){
//            in.printStackTrace();
//        }
//    }
//    public synchronized void deposit(double deposit){
//        try{
//            if(flag){
//                wait();//为true说已经有人存钱，存钱方法阻塞
//            }else {
//                out.println(Thread.currentThread().getName()+"存钱:"+deposit);
//                balance += deposit;
//                flag = true;//有人存过钱了
//                notifyAll();//通知其他线程
//            }
//        }catch (InterruptedException in){
//            in.printStackTrace();
//        }
//    }


    //线程通信 Lock对象 对应Condition对象，通过Lock实例调用newCondition的构造器
    //await() awaitUntil(Date deadline)
    //signal() signalAll()
    public void draw(double drawCount){
        lock.lock();
        try {
            if(!flag){//如果没有存钱
                condition.await();  //还没人存钱则必须线程放弃锁的资源，线程开始等待
            }else {
                out.println(Thread.currentThread().getName()+"取钱:"+drawCount);
                balance -= drawCount;
                flag = false;//有人取钱啦
                condition.signalAll(); //刚取走1钱通知可以存钱了

            }
        }catch (InterruptedException in){
            in.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void deposit(double depositCount){
        lock.lock();
        try{
            if(flag){ //如果已经有人存钱
                condition.await();  //已经存钱了不能连续存钱，线程进入阻塞状态，释放同步资源
            }else {
                out.println(Thread.currentThread().getName()+"存钱:"+depositCount);
                balance += depositCount;
                flag = true; //有人存钱了
                condition.signalAll(); //有人刚存钱，通知所有拥有同一个锁资源的线程

            }

        }catch (InterruptedException in){ //catch不必放在await周围可以放在这里
            in.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

class DrawThreadCom extends Thread{
    private AccountCom accountCom;
    private double drawCount;
    public DrawThreadCom(String name, AccountCom accountCom, double drawCount){
        super(name);
        this.accountCom = accountCom;
        this.drawCount = drawCount;
    }
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            accountCom.draw(drawCount);
        }
    }
}
class DepositThreadCom extends Thread{
    private AccountCom accountCom;
    private double depositCount;
    public DepositThreadCom(String name, AccountCom accountCom, double drawCount){
        super(name);
        this.accountCom = accountCom;
        this.depositCount = drawCount;
    }
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            accountCom.deposit(depositCount);
        }
    }
}

//BlockingQueue控制线程通信
class Producer extends Thread{
    private BlockingQueue<String> bq;

    public Producer(String name, BlockingQueue bq){
        super(name);
        this.bq = bq;
    }
    @Override
    public void run(){
        String[] strArr = {"Java", "Struts", "Spring"};
        for(int i = 0; i < 5; i++){
            //out.println(getName()+"  生产者准备生产集合元素");
            try{
                Thread.sleep(200);
                bq.put(strArr[i%3]);
                out.println(getName()+"  生产完成"+bq);
            }catch (InterruptedException in){
                in.printStackTrace();
            }


        }
    }
}

class Consumer extends  Thread{
    private BlockingQueue bq;
    public Consumer(String name, BlockingQueue bq){
        super(name);
        this.bq = bq;
    }
    @Override
    public void run(){
        while(true){
            //out.println(getName()+"  消费者准备消费元素");
            try{
                Thread.sleep(100);
                bq.take();
                out.println(getName()+"  消费完成："+bq);
            }catch (InterruptedException in){
                in.printStackTrace();
            }

        }
    }
}

//线程池：系统启动新线程的成本高，使用线程池可以提高性能，当程序中需要创建大量生存期很短的线程时，可以考虑使用线程池
//在系统启动时创建大量空闲线程，将一个Runnable或者Callable对象传递给线程池。线程池会启动一个线程来执行他们的run或者call方法
//当run()或call执行完成后线程不会死亡，会返回线程池成为空闲状态等待下一个Runnable对象的run()或call()方法
//线程数过大时，JVM性能剧烈下降，导致JVM崩溃，线程池可以设置最大线程数

//Executors工厂类用来生产ExecutorServcie线程池
//newCachedThreadPool() 系统根据需要创建线程，线程执行完不会死亡而是放在缓存池中，供使用
//newFixedThreadPool(int nThreads)创建可重用的，具有固定线程数的线程池
//newSingleThreadExecutor()创建一个只有单线程的线程池
//newScheduledThreadPool(int corePooSize) 具有固定线程数的线程池, 可以在指定延迟后执行任务 返回ScheduledFutureExecutorServic
//newSingleScheduledThreadPool() 创建一个只有单线程的线程池, 可以在指定延迟后执行任务

//Java8新增 支持并发
//ExecutorsService newWorkStealingWork(int parallelism) //创建持有足够多的线程的线程池来支持给定的并行级别
//ExecutorsService newWorkStealingWork() //如果当前机器又4个CPU，则就相当于上个方法传入4

//ExecutorServcie代表尽快执行线程的线程池提供下面3个方法
//Future<?> submit(Runnable task) 将Runnable对象所代表的任务提交给线程池，线程池有空闲就执行任务，Future代表返回值，run没有返回值
//<T>Future<T> submit(Runnable task, T result)
//<T>Future<T> submit(Callable task, T result)

//ScheduledFutureExecutorService 指定延迟后或者周期性执行线程任务的线程池
//ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit time) 指定callable任务在延迟后执行
//ScheduledFuture<?> schedule(Runnable<V> runnable, long delay, TimeUnit time) 指定runnable任务在延迟后执行
//ScheduledFuture<?> scheduleAtFixedRate(Runnable<V> runnable, long initdelay, long period ,TimeUnit time)
// 指定runnable任务在延迟后执行,并以period频率重复执行
//ScheduledFuture<?> scheduleWithFixedRate(Runnable<V> runnable, long initdelay, long delay,TimeUnit time)

//shutdown 线程池不再接受新的任务，但会将以前提交的任务完成，所有任务完成后，池内所有的线程死亡
//shutdownNow 停止所有正在执行的任务，暂停处理等待的任务，并但会等待执行的任务的列表

//ForkJoinPool
//ForkJoinPool9(int parallerism)
//ForkJoinPool()
//ForkJoinPool commonPool()
//int getCoommonPoolParallelsism()返回并行级别
//forkJoinPool.summit(ForkJoinTask task)
//ForkJoinTask抽象子类 RecursiveAction(无返回值） RecursiveTask(有返回值）
class PrintTask extends RecursiveAction{
    private static final int THRESHOLD = 50;
    private int start;
    private int end;
    public PrintTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if(end - start < THRESHOLD){
            for(int i = start; i < end; i++){
                out.println(Thread.currentThread().getName()+"的i值:"+i);
            }
        }else {
            int middle = (start + end) / 2;
            //分解成两个小任务
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个任务
            left.fork();
            right.fork();

        }
    }
}
class CalTask extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;
    public CalTask(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(end - start < THRESHOLD){
            for(int i = start; i < end; i++){
                sum += i;
            }
            return sum;
        }else {
            int middle = (start+end)/2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            out.println("left.fork"+left.join()); //??结果和普通计算不一样？？？？
            return left.join()+right.join();
        }
    }
}

//ThreadLocal类
class AccountThreadLocal{
    //定一个一个ThreadLocal变量，该变量将时一个线程局变量
    //每个线程都会保留该变量的一个副本
    private ThreadLocal<String> name = new ThreadLocal<>();
    public AccountThreadLocal(String str){
        this.name.set(str);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

//ThreadLocal线程局部变量
//为每一个使用该变量的线程都提供一个使用该变量的副本
//与同步机制的区别：同步机制是同步多个资源对相同资源的并发访问，是多个线程之间通信的有效方式（也就是他们还是要改变同一个变量的，只是不同同时改，需要有先后顺序），此时用同步机制
//ThreadLocal：为了隔离多个线程对数据的共享，也就是他只是负责在各自的线程内更改变量，多个线程之间个自改各自的，此时用ThreadLcoal
//T get()返回线程局部变量中当前线程副本中的值
//void remove 删除线程局部变量中当前线程的值
//void set(T value)设置此线程局部变量中当前线程副本的值
//可以把不安全的整个变量封装进ThreadLocal
class ThreadLocalTest extends Thread{
    private AccountThreadLocal account;
    public ThreadLocalTest(AccountThreadLocal local, String name ){
        super(name);
        this.account = local;
    }
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            if(i == 6){
                account.setName(getName());
            }
            out.println(account.getName()+"账户i的值"+i);
        }
    }
}

//包装线程不安全的集合
//多线程访问线程安全的HashMap
//HashMap m = Collections.synchronized(new HashMap())
//创建集合之后立即包装
//支持高效并发访问的集合接口和实现类
//分两类
//Concurrent开头，ConcurrentHashMap,ConcurrentSkipList,ConcurrentSkipListSet,ConcurrentLinkedQueue,ConcurrentDeque
//CopyOnWrite开头， CopyOnWriteArrayList, CopyOnWriteArraySet
//Concurrent支持并发写入访问，读取操作则不锁定
//多个线程共享访问一个公共集合时，ConcurrentLinkedQueue比较好，不允许null元素，多个线程无需等待
//ConcurrentHashMap 默认支持16个线程并发写入，超过16个线程需要等待，可通过设置concurrencyLevel支持更多线程
//ConcurrentHashMap与ConcurrentLinkedQueue与普通集合的区别是，使用迭代器遍历元素，元素发生改变时，不能反映出更改，也不抛出异常
//Java8增强后的ConcurrentHashMap适合作为缓存实现类使用
//普通集合,创建迭代器元素发生改变时，会产生ConcurrentModificationException异常

//CopyOnWriteArrayList在读取的时候直接读取集合本身，在写入时，复制了一份数据副本，操作的是副本
//执行写入操作需要频繁的复制数组，性能差，但是读很快，适合读操作远大于写操作的场景，如缓存


