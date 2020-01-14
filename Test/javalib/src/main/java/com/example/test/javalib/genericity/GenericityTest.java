package com.example.test.javalib.genericity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static java.lang.System.*;
public class GenericityTest {
    public static void main(String[] args){
        //Apple<String> a1 = new Apple<>("苹果");  //定义类型形参必须是Number或者其子类,这里编译错误
        Apple<Integer> a1 = new Apple<>(12);
        Apple<Double> a2 = new Apple<>(12.0);
        SubApple<Integer> s1 = new SubApple<>(12);
        if( a1 instanceof  Apple){
            a1.getInfo();
        }
        List list = new ArrayList();
        list.add(a1);
        list.add(a2);
        test(list);
        List list2 = new ArrayList();
        list2.add(s1);
        test(list2);

        List<Circle> circles = new ArrayList<>();
        Canvas c = new Canvas();
        c.drawAll(circles); //List<Circle> 不能当List<Shape>使用
        Integer[] ints = {1,2,3};
        List listInt = new ArrayList();
        frmArrayToCollection(ints, listInt); //泛型方法

    }
    public static  void test(List<?> c){ //类型通配符？ 当函数不确定传入的是哪种泛型实参时，可以用通配符代表
        out.println(c.size());
    }
    public void addRectangle(List<? extends Shape> shapes){ //设定通配符上限 ？extends Shape
        //shapes.add(new Rectangle()) //? extends Shape 还不知道具体是什么类型所以不能执行add操作
    }
    //为了解决上面的问题，可以定义泛型方法
    //修饰符<T,S> 返回类型 方法名(形参列表）{}
    static <T> void frmArrayToCollection(T[] a, Collection<T> c){
        for(T o : a){
            c.add(o);
        }
    }
}

//List<SubApple> 并不是 List<Apple>的子类 也不是List<Object>的子类，所以不能用List<Object>作通用匹配
//泛型接口和类
class  Apple<T extends Number>{ //不能在静态声明中使用，类型形参   //定义类型形参必须是Number或者其子类
    private T info;     //类型形参上限 T extends Number 可以设置多个上限，但是至多一个父类上限，可以有多个接口上限
                        // T extends Number & java.io.Serializable 接口上限位于类上限之后
    public  Apple(T info){
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
class SubApple<M extends Number> extends Apple{
    private M subInfo;
    public SubApple(M subInfo){
        super(subInfo);
        this.subInfo = subInfo;
    }
}

abstract class Shape{
    abstract public void draw(Canvas c);
}
class Circle extends Shape{
    public void draw(Canvas c){
        out.println("在画布上画一个圆");
    }
}
class Rectangle extends Shape{
    public void draw(Canvas c){
        out.println("在画布上画一个三角形");
    }
}
class Canvas{
    public void drawAll(List<? extends Shape> shapes){  //使用类型通符
        for(Shape shape:shapes){
            shape.draw(this);
        }
//        for(Object obj:shapes){ //这里使用Object 然后需要强制转换成Shape, 显得臃肿
//            Shape s = (Shape) obj;
//            s.draw(this);
//        }
        //这里的?通配符 其实应该都是Shape的子类，提供被限制的泛型通配符， ？extends Shape
    }
}
