package com.example.jd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jd.R
import com.example.jd.beans.DateBean


/**
 *    author : Tiaw.
 *    date   : 4/8/21
 *    博客：https://blog.csdn.net/weixin_44819566
 *    desc   :
 */
class RelAdapter<T>(val context: Context, val list: List<T>) :
    RecyclerView.Adapter<RelAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rel_item_layout, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.tv.setText((list[position] as DateBean).title)


    class ViewHolder(arg0: View) : RecyclerView.ViewHolder(arg0) {
        val tv by lazy {
            arg0.findViewById<TextView>(R.id.tv)
        }
    }

}
