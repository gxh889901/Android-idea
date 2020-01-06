package com.example.test.test.View;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.test.R;

public class ConcurrentThemeActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_item_view);
        final Demo demo = new Demo();
        final Demo demo1 = new Demo();
        for(int i = 0;i < 30;i++){
            new Thread(){
                @Override
                public void run(){
                    super.run();
                    demoAdd(demo);
                    demoAdd(demo1);
                    //***如果在这里访问demo.count而在add方法里加入syschronized还是有可能会引起，线程不安全
                    //一定要注意syschronized的使用范围，必须包含所有访问共享变量，又要保证线程安全的地方，范围不能大也不能小

                }
            }.start();
        }

    }

    synchronized public void demoAdd(Demo demo){ //当修饰的方法里有静态变量时，就是针对类的同步
        //synchronized 使用范围要注意一定要放在最终要保证线程安全的地方
       // 比如synchronized放在Add方法前面,只能保证add方法里面是线程安全的，这里调用demo.count还是不安全的
       //范围也不能太大造成资源浪费
      demo.add();
      Log.d("Mytag", Thread.currentThread().getName()+"    "+Demo.count);
      //锁锁住的是访问方法的对象访问，当锁住的块访问了锁住的对象后，其他线程就不得再进入锁住的块访问该对像了
    }

}

    class SysTest implements Runnable{
        int count = 0;
        @Override
        public void run() {
            increateCount();
        }
        public void increateCount(){
            for(int i = 0; i < 5; i++){
                Log.d("Mytag", Thread.currentThread().getName()+"    "+count++);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException o){
                    o.printStackTrace();
                }
            }

        }
    }


class Demo{
    static int count = 0;//静态方法不能访问非静态变量      //出现线程不安全错误是因为两个方法都处于进入某一方法的状态，
    // 如果是有公共变量，
    // 前面进入的退出时也可能会拿到的是或者后者进入的已经处理过的结果，因此退出时拿着的是和后者退出时一样的值
    static public void add(){
        count++;
        try{
            Thread.sleep(100);
        }catch (InterruptedException o){

        }

    }
}