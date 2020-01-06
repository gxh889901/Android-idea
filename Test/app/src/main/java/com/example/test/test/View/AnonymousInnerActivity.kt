package com.example.test.test.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.test.test.R
import kotlinx.android.synthetic.main.content_view.*
import java.util.concurrent.TimeUnit

class AnonymousInnerActivity:AppCompatActivity(){
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_view)
        descriptionContent.text = "匿名内部类：1) 继承接口或者父类 \n 2)只是使用一次的类 3）这样的类不需要定义类名，只需要直接new出一个包括实现的实例即可\n" +
                "3)回调，好了告诉我 \n" +
                "1.匿名内部类可以为任意接口创建实例——不管有多少个抽象方法，只要匿名内部类实现了所有方法即可。\n" +
                "但是Lambda表达式只能为函数式接口创建实例。\n" +
                "2.匿名内部类可以为抽象类甚至普通类创创建实例，\n" +
                "但lambda表达式只能为函数式接口创建实例。（函数式接口有且只有一个抽象方法的接口）\n" +
                "3.匿名内部类实现的抽象方法体允许调用接口中的默认方法，\n" +
                "但Lambda表达式的代码块不允许调用接口中的默认方法。"
        url.text = "匿名内部类 https://blog.csdn.net/hellocsz/article/details/81974251 \n" +
                "回调 https://www.jianshu.com/p/c0c33cabfa0f"
        var temp:Int = 0 //kotlin 匿名内部类可以访问外部的非final类型的变量并且可以改变值
        demo.setOnClickListener(object:View.OnClickListener{ //object就是实现View.OnClickListener接口的一个匿名内部类的实例
            override fun onClick(v: View?) {
                 temp = 10
                 demoContent.text = AnonymousJava().birdFly()
                 AnonymousJava().Inner("kkkk")
                 Thread(ThreadRunnable(object :RunnableCallBack{ //这里直接用lamda表达式为什么不可以？？ 因为ThreadRunnable是一个普通类而不是函数式接口
                     override fun rest() {
                         demoContent.text = "I have flied 1 sec. I need rest now."
                     }

                 })).start()

            }

        })
    }

    class ThreadRunnable(val callBack:RunnableCallBack):Runnable{
        override fun run() {
            Thread.sleep(1000)
            callBack.rest()
        }

    }

    interface RunnableCallBack{
        fun rest()
    }
}
