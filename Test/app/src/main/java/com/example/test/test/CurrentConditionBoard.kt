package com.example.test.test


class CurrentConditionBoard(weatherSubject: Subject):Observer, Displayment{
    private var weatherData:WeatherDataBean?= null
    private val CONST_VALUE = 10
    val sum = {x:Float, y:Float -> x*CONST_VALUE + y}

    init {
        weatherSubject.registerObserver(this)
    }
    override fun update(data: WeatherDataBean) {
        weatherData  = data
        weatherData!!.touchFeel = sum(weatherData!!.humidity, weatherData!!.pressure)
    }

    override fun display(displayer:(weatherData:WeatherDataBean)-> Unit) {
        weatherData?.let {
            displayer.invoke(it)
        }

    }


}