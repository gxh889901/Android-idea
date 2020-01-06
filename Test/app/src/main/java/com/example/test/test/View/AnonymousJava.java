package com.example.test.test.View;

public class AnonymousJava {
    int k = 0;
    private  String prop ="外部类的实例变量";
    Person person = new Person(){ //匿名内部类
        @Override
        public void eat() {

        }
    };
    Bird bird = new Bird() {
        @Override
        public String fly() {
            k++; //非静态内部类可以访问外部类实例变量
            return desription().concat(" I am flying now.");
        }

        @Override
        String desription() {
            return "I am eagle.";
        }
    };
    public void personEat(){
        person.eat();
    }
    public String birdFly(){
        return bird.fly().concat(accessToInnterProp());
    }
    public String accessToInnterProp(){
        Inner inner = new Inner("外部累访问内部类prop");
        return inner.prop;

    }
    //内部类 可以访问外部类的成员变量，但是外部类不能访问非静态内部类的变量
    public class Inner{
        private String prop = "内部类实例变量";
        public Inner(String prop){
            this.prop = prop;
        }
    }

}
interface Person{ //目前的Android 内部的jdk都是7， 只有JAVA8支持默认接口
    void eat();

}
abstract class Bird{ //包含抽象方法的类必须是抽象类, 抽象类可以包含非抽象方法，不能抽象方法不能有实现
    abstract String fly();
    String desription(){
        return "I am a bird.";
    }
}

//class Bird{
//    String fly(){
//        return null;
//    }
//}
