package com.example.test.test

class WeatherDataManager: Subject{
    private val observers = mutableListOf<Observer>()
    private var weatherData:WeatherDataBean? = null

    override fun registerObserver(o: Observer) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObervers() {
        observers.forEach {item->
            weatherData?.let {
                item.update(weatherData!!)
            }
        }
    }

    fun setMeasurements(temperature:Float, humidity:Float, pressure:Float){
        weatherData = WeatherDataBean(temperature =temperature, humidity = humidity, pressure = pressure)
        measurementsChanged()
    }

    fun measurementsChanged(){
        notifyObervers()
    }

}