package com.example.test.test.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.test.Model.FirstClassBean
import com.example.test.test.R
import kotlinx.android.synthetic.main.board_view.view.*

class BoardView @JvmOverloads constructor(contex:Context, attr:AttributeSet ? = null, defaAttr: Int = 0):ConstraintLayout(contex, attr, defaAttr){
    private lateinit var adapter:BoardAdapter
    init {
        initViews()
    }
    private fun initViews(){
        //这种自定义布局必须是addView的情况，且LayoutParam 是本View的，因此root不能为空，attachToRoot不能为false
        LayoutInflater.from(context).inflate(R.layout.board_view, this, true)
        boardList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = BoardAdapter()
        boardList.adapter = adapter
    }

    fun updateList(datalist:MutableList<FirstClassBean>){
        adapter.updateData(datalist)
    }

}

