package com.example.test.kotlin.lambda.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.kotlin.lambda.View.Model.Person
import kotlin.math.log


class FunctionApiOfCollectionsFragment:Fragment(){
    var position:Int = 0
        set(value) {
            positionFunc(value)
        }
    @Override
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?, savedInstanceState: Bundle?):View?{
        super.onCreateView(inflater,container, savedInstanceState)
        return  inflater.inflate(R.layout.function_api_collection_layout, container, false)

    }

    fun positionFunc(position:Int){
        when(position){
            1 -> {
                funcAPI()
            }

            2 -> {

            }
        }
    }


    fun funcAPI(){
        val persons = listOf(Person(31, "Alice"), Person(31, "Bob"), Person(29, "Carol"))
        Log.d("gxh", persons.groupBy { it.age }.toString())

    }

}