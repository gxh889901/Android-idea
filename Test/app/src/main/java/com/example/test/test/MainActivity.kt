package com.example.test.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.test.Model.FirstClassBean
import com.example.test.test.View.AnonymousInnerActivity
import com.example.test.test.View.ConcurrentThemeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_board_view.*
import kotlin.concurrent.thread

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf<FirstClassBean>()
        list.add(FirstClassBean("线程同步"){context->
            context?.startActivity(Intent(context, ConcurrentThemeActivity::class.java))
        })
        list.add(FirstClassBean("匿名内部类"){ context:Context->
            context?.startActivity(Intent(context, AnonymousInnerActivity::class.java))
        })

        mainPageBoard.updateList(list)


//        必须先创建Subject, 然后注册 observer, 然后通知变化，顺序不能改变
//        val weatherDataManager = WeatherDataManager()
//        val currentConditionBoard = CurrentConditionBoard(weatherDataManager)
//        weatherDataManager.setMeasurements(20f, 10f,22f)
//        currentConditionBoard.display{ item ->
//            temperature.text = resources.getString(R.string.temperature, item.temperature.toString())
//            pressure.text = resources.getString(R.string.humidity, item.humidity.toString())
//            humidity.text = resources.getString(R.string.pressure, item.pressure.toString())
//            touch.text = resources.getString(R.string.touchFeel, item.touchFeel.toString())
//        }

    }

}

