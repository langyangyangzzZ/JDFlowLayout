package com.example.jd.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView


/**
 *    author : Tiaw.
 *    date   : 4/9/21
 *    博客：https://blog.csdn.net/weixin_44819566
 *    desc   :RecyclerView模版方法模式
 */
abstract class BaseRelTemplate {
    /**
     * 范型T：表示RecyclerView绑定的数据类型Bean类
     */
    open fun <T> template(context: Context, rel: RecyclerView) {

        rel.layoutManager = buildLayoutManager(context)

        rel.adapter = buildAdapter<T>(
            context,
            buildData()
        )
    }

    /**
     * 布局
     */
    abstract fun buildLayout(): Int

    /**
     * 布局管理器
     */
    abstract fun buildLayoutManager(context: Context): RecyclerView.LayoutManager

    /**
     * 适配器
     */
    abstract fun <T> buildAdapter(
        context: Context,
        buildData: List<T>
    ): RecyclerView.Adapter<RelAdapter.ViewHolder>

    /**
     * list数据
     */
    abstract fun <T> buildData(): List<T>

}