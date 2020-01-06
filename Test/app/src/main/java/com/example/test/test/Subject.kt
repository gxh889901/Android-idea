package com.example.test.test

interface Subject{
    fun registerObserver(o:Observer)
    fun removeObserver(o:Observer)
    fun notifyObervers()
}