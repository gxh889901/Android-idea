package com.example.test.firstclass.model

import android.content.Context

class FirstClassBean(val title:String, val action:(context:Context)->Unit){
}