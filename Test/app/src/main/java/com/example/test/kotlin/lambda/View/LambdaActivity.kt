package com.example.test.kotlin.lambda.View

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.test.R
import com.example.test.secondclass.SecondClassViewPagerAdapter

import kotlinx.android.synthetic.main.lambda_activity_layout.*
import kotlinx.android.synthetic.main.second_class_layout.*

class LambdaActivity :AppCompatActivity(){

    val COLLECTION_API_FG = "CLLAPI"
    lateinit var listener:ViewPager.OnPageChangeListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_class_layout)
        var array = arrayOf("java","php", "phython")

        val transaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(COLLECTION_API_FG) as FunctionApiOfCollectionsFragment
        if( fragment == null){
            fragment = FunctionApiOfCollectionsFragment()
            transaction
                .add(R.id.content_fragment, fragment, COLLECTION_API_FG)
                .show(fragment)
                .commit()
        }else{
            transaction.show(fragment).commit()
        }

        titlePager.adapter = SecondClassViewPagerAdapter(initList(), this)
        listener = object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                fragment?.position = position
            }

        }
        titlePager.addOnPageChangeListener(listener)



    }

    fun initList():List<String>{
        val list = mutableListOf<String>()
        list.add("集合的函数API")
        list.add("惰性集合操作:序列")
        list.add("集合的函数API")
        list.add("集合的函数API")
        list.add("惰性集合操作:序列")
        list.add("集合的函数API")
        return list
    }

    override fun onDestroy() {
        titlePager.removeOnPageChangeListener(listener)
        super.onDestroy()
    }



}