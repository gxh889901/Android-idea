package com.example.test.firstclass.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.firstclass.model.FirstClassBean
import com.example.test.R


class BoardAdapter(private val list:MutableList<FirstClassBean> = mutableListOf()) :RecyclerView.Adapter<BoardViewHolder>(){
    var  context:Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        //如果root 参数是 null和parent布局显示样式不一样, 如果root不为空inflate的View的param就是root的param限制
        //onCreateViewHolder里attachToRoot必须为false  为什么呢？
        context = parent.context
        return BoardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.board_item_view, parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = list.get(position)
        holder.apply {
            title.text = item.title
            itemView.setOnClickListener {
                item.action.invoke(context!!)
            }
        }
    }

    fun updateData(datalist:MutableList<FirstClassBean>? = null){
       datalist.let {
           list.clear()
           list.addAll(datalist!!)
       }
        notifyDataSetChanged()
    }

}
class BoardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val title = itemView.findViewById<TextView>(R.id.boardItem)
}