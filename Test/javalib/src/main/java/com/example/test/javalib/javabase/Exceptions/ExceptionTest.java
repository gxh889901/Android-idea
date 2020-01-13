package com.example.test.javalib.javabase.Exceptions;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Date;

import static java.lang.System.*;
public class ExceptionTest {
    public static void main(String[] args)  throws  FileNotFoundException{
        //test(); //test方法抛出异常，main方法必须处理
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

        try{
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a/b;
            out.println("您输入的两个数相除的结果是:"+c);
        }catch (IndexOutOfBoundsException|NumberFormatException ie){ //可以同时捕获多个异常，中间"|"分隔
            out.println("数组越界,数据格式错误");
            //ie = new NumberFormatException("test"); //在捕获多异常时 异常变量默认有final修饰不能赋值
        }catch (ArithmeticException ae){ //除0异常
            out.println("算术异常");
        }catch (Exception e){
            out.println("未知异常");
            e = new RuntimeException();//捕获单异常时，没有final修饰
        }

        Date d = null;
        try{
            out.println(d.after(new Date()));
        }catch (NullPointerException ne){
            out.println("空指针异常");
        }catch (Exception e){
            out.println("未知异常");
        }
        //总是把Exception放到最后，先处理小异常后处理大异常

        //异常对象的方法
        try{
            FileInputStream fis = new FileInputStream("a.txt");
        }catch (FileNotFoundException ff){
            out.println(ff.getMessage());
            ff.printStackTrace(); //打印异常的跟踪栈信息
        }

        //finally块
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("a.txt");
        }catch(IOException ie){
            out.println(ie.getMessage());
            //return; //使用return也会执行finally块  //这里return语句不是跳出try块而是，main方法的return
            //System.exit(1); //System.exit退出虚拟机将不执行finally块，除了这种情况都执行finally块
        }finally {
            //关闭磁盘文件，回收资源
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            out.println("执行finally块");
        }

        //Java7 自动关闭资源的try语句
        //在try()的括号里声明初始化要关闭的资源 包含这样声明的语句相当于有一个隐含的finally块
        //因此可以只有一个try块，而没有finally块
        //即使有这样的声明也还是可以有finally块的

        try(BufferedReader br = new BufferedReader(new FileReader("ExceptionTest.java"));
            PrintStream ps = new PrintStream("a.txt")) {
            out.println(br.readLine());
            ps.println("自动关闭资源");
        }catch (IOException ie){
            ie.printStackTrace();
        }finally {
            out.println("有自动关闭资源声明的finally块");
        }

        //自行throw checked异常
        try{
            throwChecked(3);
        }catch (Exception e){
            out.println("Checked异常="+e.getMessage()); //Message即为创建异常实例时定义的值
        }

        //自行throw Runtime异常
        //throwRuntime(3); //RunTimeException既可以不处理，直接默认抛到上级，最后由main到JVM 退出应用， 也可以放在try catch里处理

        //
        try{
            bid("bg");
        }catch (MyException my){
            out.println("处理下层抛出的异常");
        }

        //java7增强的throw语句
        try{
            new FileOutputStream("a.txt");  //这里只可能抛出FileNotFoundException 因此main方法只需throws FileNotFoundException 而不必是Exception
        }catch (Exception e){ //虽然这里定义的是Exception 类型 但是编译器会检查抛出异常的实际类型
           e.printStackTrace();
           throw e;
        }


        out.println("程序正常结束");
    }

    //Checked异常
    public static void test() throws IOException{
        //FileInputStream抛出FileNotFoundException
        //如果不try catch那么抛出
        FileInputStream fis = new FileInputStream("a.txt");
    }

    //throw自行抛出checked异常
    public static void throwChecked(int a) throws Exception{
        if(a > 0){
            //必须放在try catch里或者有throws声明的方法里
            throw new Exception("a的值大于0， 不符合需要要求"); //直接创建Exception对象默认是checked异常
        }
    }
    //throw自行抛出Runtime异常
    public static void throwRuntime(int a){
        if(a > 0){
            throw new RuntimeException("a的值大于0， 不符合需要要求");
        }
    }

    //throws和catch同时使用
    private static double initPrice = 30.0;
    public static void bid(String bidPrice) throws MyException{
        double d = 0.0;
        try{
            d = Double.parseDouble(bidPrice);
        }catch (Exception e){
            //此处完成本方法可以对异常执行的修复处理
            e.printStackTrace();
            throw new MyException("竞拍价值必须是数值");
        }
        if(initPrice > d){
            throw new MyException("竞拍价格比起拍价格低，不允许竞拍"); //throw之后的语句就不执行了
        }
        initPrice = d;
    }

//    public void calsal() throws MyException{
//        try{
//            //结算工资业务逻辑
//        }catch (SQLException sql){
//            //把原始异常记录下来，留给管理员
//            //抛出提示用户的异常
//            throw new MyException("访问底层数据库异常");
//            //throw new MyException(sql); //向上传递异常
//        }catch (Exception e){
//            //把原始异常记录下来，留给管理员
//            //抛出提示用户的异常
//            throw new MyException("系统出现未知异常");
//            //throw new MyException(e);
//        }
//    }

}
//业务实现和错误处理分离
//遇到异常，生成异常对象，把异常对象提交到Java运行时环境，叫抛出(throw)抛出异常
//运行时环境接受到异常，找到处理异常的catch块，交给catch块处理（捕获异常）
//找不到则运行时环境终止，JVM退出
//try块后面可以跟多个catch块可以针对不同类型异常不同处理
//try里定义的变量都是代码块内的局部变量，在catch块中不能访问该变量
//Error指与虚拟机相关的问题，如系统崩溃，虚拟机错误，动态链接失败，应用无法处理error

//异常对象的方法
//getMessage(）返回该异常的详细描述字符串儿
//printStackTrace()将异常的跟踪栈信息输出到标准错误输出
//printStackTrace(PrintStream s)将异常的跟踪栈信息输出到指定的输出流
//getStackTrace 返回该异常的跟踪栈信息

//finally块 用于回收资源
//try块里打开的物理资源 如数据库连接，网络连接，磁盘文件等 必须显式回收
//如果在try块里进行资源回收，当上面的语句发生错误的时候，下面的得不到执行
//finally块总被执行，即使try块和catch块里有return语句
//出现try块就必须出现catch块或者finally块

//尽量避免在finally块使用return或者throw等导致方法终止的语句
//这样try或者catch里的返回就失效了，最终返回的是finally里面的语句
//在try,catch,finally块里都可以在加入异常块

//Java7 自动关闭资源的try块  几乎所有的资源类都实现了Closable或者AutoClosable的接口
//try()声明初始化多个资源（必须显示关闭的资源） try语句会在结束时自动关闭资源
//try语句可以正常关闭的资源必须实现AutoClosable或Closeable接口(接口里有close方法）
//AutoClose 实现时可以抛出Exception异常和它的子类
//Closeable 只能抛出IOException异常和它的子类

//Checked异常合Runtime异常
//RuntimeException异常及其子类，剩余的异常都是Checked异常（Checked异常编译器提示必须处理的异常）
//Checked异常要么try catch处理，要么抛出(将需要checked的代码放在一个有throws声明抛出的方法中）
//RuntimeException如果没有捕获的话，会自动抛出

//throws声明抛出
//当方法不知道怎么处理这种类型的异常时，抛出由上一级调用者处理
//throws跟在方法签名后面main(String[] args) throws IOException，NullPointerException{}
//可以抛出多个异常用逗号分隔
//选择抛出异常就不需要再try catch了
//当main方法抛出异常时，会交给JVM处理，JVM会打印异常的跟踪栈信息，然后终止运行
//子类方法声明抛出的异常应该是父类方法声明抛出的异常的子类或者相同(被覆写的方法可以不抛出异常）

//Checked异常的不便之处
//1)必须处理处理增加程序复杂度
//2)如果方法是重写父类的方法，则该方法抛出的异常必须受到父类方法抛出异常的限制
class Sub extends  ExceptionTest{
    public static void test() throws IOException{

    }
}

//使用throw抛出异常，用于自行抛出异常
//什么属于异常是由用户自己定义的，当程序执行与业务逻辑不符时，程序员可以选择抛出异常
//比如在5子棋程序里，如果下棋的位置已经有棋子了，可以抛出异常,然后交给catch处理，这样即就很好的实现了业务逻辑和异常分离
//throw 后面是异常实例，不是异常类 throw new Exception("您输入的位置已经有棋子了)
//如果throw Chekced异常则throw语句必须放在try catch里或者带throws声明的方法里
//throw RunTimeException则无需处理会交给调用者处理
//RunTimeException灵活性更好，Checked可以提醒程序员必须处理异常

//自定义异常类
//根据业务需求，自定义异常类，方便查找错误
class MyException extends Exception{ //如果是定义RunTimeException 继承RunTimeException
    public MyException(){ //定义一个无参数构造器

    }
    public MyException(String message){ //定一个带有Message的构造器
        super(message);
    }
    public MyException(Throwable t){
        super(t); //Exception接收Throwable作为参数
    }
}

//catch和throws, throw同时使用
//当异常出现在当前方法中，当前方法只能处理一部分问题，还有些处理需要交给方法调用者处理，所以还应该抛出异常，让方法调用者也能捕获异常
//大型企业级应用中经常使用 1)应用后台通过日志记录异常发成的详细信息 2）根据异常向用户传达某种提提示 需要这两方面处理

//java7 增强的throw语句

//异常链 企业级应用有严格的分层关系，层与层之间有清晰的划分，不允许跨层访问，层的交互依赖于API
//那么下层的原始异常不应该直接抛到上层，如SQLException 第一是用户看不懂，第二是暴露出来不好
//通常的做法是先捕获原始异常，再抛出新的业务异常，业务异常中包含对用户信息的提示，这种做法叫异常转译
//保存底层的异常信息，仅向上提供必要的异常提示给用户
//捕获一个异常抛出另外一个异常，并把原始异常信息保存下来是一种典型的链式处理模式（23种设计模式之一：职责链模式），被称为异常链

//printStackTrace()方法方便追踪异常发生的情况 用来调试程序，但是在最后发布的程序中，避免使用它， 而是应该对捕获的异常做适当的处理

//异常处理的原则
//1)使程序代码混乱最小化
//2)捕获并保留诊断信息
//3)通知合适的人员
//4)以合适的方式结束异常活动

//不要过度使用异常
//1)只有对外部不能确定和预知的运行时错误才使用异常，不要和普通业务逻辑错误处理代码混淆，异常的catch处理效率差一些
//比如用户在已经有棋子的位置下棋，这种错误是完全可以预料的，完全可以针对错误做出相应的处理，而不是抛出异常
//2)异常的初衷是将不可逾期的异常处理代码与正常的业务逻辑处理代码分离，不要使用异常处理代替正常的业务逻辑判断，异常机制的效率比正常的流程控制效率差

//不要使用过于庞大的try块，可能发生的异常多，需要跟好多catch，要分析逻辑，把可能出现的异常，放在单独的块里
//避免使用CatchAll语句 即catch最上层的异常类
//应该把异常具体分类，分别处理，而且这样的语句会 吞没所有的异常而淹没关键异常


//不要忽略异常 绝对不能为空或者仅仅打印信息
//仅仅打印跟踪栈信息也会是不够的
//处理措施
//1)对异常进行合适的修复，然后绕过异常发生的地方继续执行，或者用别的数据进行计算,以代替期望的方法返回值
//或者提示用户重新操作，对Checked异常应该尽量回复

//重新抛出新异常，把当前运行环境下能做的事情尽量做完，然后进行异常转译，包装程新异常，抛给上层
//如果当前层不清楚如何处理异常，就不要在当前层使用try catch 捕获而是直接使用throws抛出异常，让合适的上层来处理


