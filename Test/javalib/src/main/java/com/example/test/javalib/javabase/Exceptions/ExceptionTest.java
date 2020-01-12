package com.example.test.javalib.javabase.Exceptions;
import static java.lang.System.*;
public class ExceptionTest {
    public static void main(String[] args){
        String[] inputStr = {"3:4", "5n6", "4:5"};
        for(int i = 0; i < inputStr.length; i++){
            try{
                String[] ps = inputStr[i].split(":");
                out.println(ps[0]);
                out.println(ps[1]);
            }catch (Exception e){
                out.println("输入字符不合法");
            }
        }
    }
}
//业务实现和错误处理分离
//遇到异常，生成异常对象，把异常对象提交到Java运行时环境，叫抛出(throw)抛出异常
//运行时环境接受到异常，找到处理异常的catch块，交给catch块处理（捕获异常）
//找不到则运行时环境终止，JVM退出
//try块后面可以跟多个catch块可以针对不同类型异常不同处理
//try里定义的变量都是代码块内的局部变量，在catch块中不能访问该变量
//Error指与虚拟机相关的问题，如系统崩溃，虚拟机错误，动态链接失败，应用e