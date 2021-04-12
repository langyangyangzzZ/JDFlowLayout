package com.example.jd.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jd.R
import com.example.jd.beans.DateBean
import java.nio.file.Files.list


/**
 *    author : Tiaw.
 *    date   : 4/9/21
 *    博客：https://blog.csdn.net/weixin_44819566
 *    desc   : RecyclerView模版方法模式
 */
class RelTemplate : BaseRelTemplate() {


    override fun buildLayout(): Int = R.layout.rel_item_layout

    override fun buildLayoutManager(context: Context): RecyclerView.LayoutManager =
        LinearLayoutManager(context)

    /**
     * 适配器
     */
    override fun <T> buildAdapter(
        context: Context,
        list: List<T>
    ): RecyclerView.Adapter<RelAdapter.ViewHolder> {
        return RelAdapter<DateBean>(context, buildData())
    }

    /**
     * 设置数据
     */
    override fun <T> buildData(): List<T> {
        val list = List(50) {
            DateBean("$it")
        }
        return list as List<T>
    }

}


