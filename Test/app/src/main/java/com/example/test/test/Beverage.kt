package com.example.test.test

abstract class Beverage{
    var description = "Unknown Beverage"
    abstract fun animate()
    open fun stopAnimation(){

    }
    fun animateTwice(){ //默认是final类型不能被重写

    }
}