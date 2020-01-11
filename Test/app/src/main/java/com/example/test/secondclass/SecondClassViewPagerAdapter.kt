package com.example.test.secondclass


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.test.R


class SecondClassViewPagerAdapter(private val list:List<String>, private val context:Context):PagerAdapter(){
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View) //这里需要删除View，看源码找原理
    }

    override fun instantiateItem(container: ViewGroup, index: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.second_item_view, null)
        //为什么这里不能添加container 然后 true 然后直接省略下面的addView LayoutInflater的参数到底会产生什么影响
        val title = view.findViewById<TextView>(R.id.secondTitle)
        title.text = list[index]
        container.addView(view)  //这里必须有addView有时间研究下原理
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {  //为什么加这个函数 https://segmentfault.com/q/1010000000484617
        return view ==  obj
    }



    override fun getCount() = list.size
}