package com.example.jd.util

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import java.util.*

/**
 * author : Tiaw.
 * date   : 4/12/21
 * 博客：https://blog.csdn.net/weixin_44819566
 * desc   : FlowLayout 流式布局
 */
class FlowLayout : ViewGroup {

    //间隙
    val space = 5

    //每一行子View
    var listView = ArrayList<List<View>>()

    //每一行子View的高
    var listLineHeight = ArrayList<Int>()


    /**
     * 用来new对象
     */
    constructor(context: Context?) : super(context)

    /**
     * xml中执行
     */
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    /**
     * style中属性调用
     */
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    /**
     * 子控件获取marge方法
     */
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams? {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        listView = ArrayList()
        listLineHeight = ArrayList()

        //一行的宽和高
        var lineWidth = 0
        var lineHeight = 0

        //父容器的宽和高
        var parentWidth = 0;
        var parentHeight = 0;

        var views = ArrayList<View>()

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)


        //ViewGroup中所有子View
        (0 until childCount).forEach {
            //取出每个子View
            val childView = getChildAt(it)

            Log.i("childViewIt", "$it")

            //子View在ViewGroup中的实际布局  强转为as MarginLayoutParams为了获取margin的高度
            val layoutParams = childView.layoutParams as MarginLayoutParams

            //子View 宽的MeauserSpec
            val childWidthMeasureSpec = getChildMeasureSpec(
                widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width
            )
            val childHeightMeasureSpec = getChildMeasureSpec(
                heightMeasureSpec, paddingBottom + paddingTop, layoutParams.height
            )

            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)

            val childWidth = childView.measuredWidth + childView.marginLeft + childView.marginRight
            val childHeight =
                childView.measuredHeight + childView.marginTop + childView.marginBottom

            //如果记录的宽+当前的宽 > ViewGroup的宽说明需要换行
            if (lineWidth + childWidth > widthSize) {

                //记录每一行子View的高
                listLineHeight.add(lineHeight)
                //记录每行View信息
                listView.add(views)

                //每当换行记录最大的宽和累加的高作为父容器的宽高
                parentWidth = Math.max(lineWidth + space, parentWidth + space)
                parentHeight += lineHeight + space

                views = ArrayList()
                lineWidth = 0;
                lineHeight = 0;

            }

            //记录每一个子View
            views.add(childView)

            //记录当前的宽
            lineWidth += childWidth + space
            //取出一行中最高的作为当前的高
            lineHeight = Math.max(lineHeight, childHeight + space)
            Log.i("lineHeight", "$lineWidth  $lineHeight")

            //处理最后一个
            if (it == childCount - 1) {
                //记录每一行子View的高
                listLineHeight.add(lineHeight)
                //记录每行View信息
                listView.add(views)

                //每当换行记录最大的宽和累加的高作为父容器的宽高
                parentWidth = Math.max(lineWidth, parentWidth + space)
                parentHeight += lineHeight + space
            }

        }

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)


        Log.i(
            "测量结果为1",
            "${widthMode == MeasureSpec.EXACTLY}   ${heightMode == MeasureSpec.EXACTLY}"
        )
        Log.i("测量结果为2", "$widthSize   $heightSize")
        Log.i("测量结果为3", "$parentWidth   $parentHeight")

        parentWidth += paddingLeft + paddingRight + marginLeft + marginRight
        parentHeight += paddingTop + paddingBottom + marginTop + marginBottom
        //测量最终结果
        setMeasuredDimension(
            if (widthMode == MeasureSpec.EXACTLY) widthSize else parentWidth,
            if (heightMode == MeasureSpec.EXACTLY) heightSize else parentHeight
        )


    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        Log.i("Layoutpadding", "$paddingLeft  $paddingRight")
        var padLeft = paddingLeft
        var padTop = paddingTop
        //循环每行View
        for (index in (0 until listView.size)) {

            val it = listView[index]

            //每一行的高
            val lineHeight = listLineHeight[index]

            //循环每行View中的每个View
            it.forEach {

                //  获取margin
                val marginLayoutParams = it.layoutParams as MarginLayoutParams

                var left = padLeft + marginLayoutParams.leftMargin
                val right = left + it.measuredWidth
                val top = padTop + marginLayoutParams.topMargin
                val bottom = top + it.measuredHeight

                Log.i("View位置", "$index :$left $right $top $bottom")

                it.layout(left, top, right, bottom)

                //当循环第二个的时候，左侧间距为第一个的宽+间距 + 第一个右边的距离
                padLeft = right + space + marginLayoutParams.rightMargin
            }

            //当循环第二行的时候 高度需要添加第一行的高度
            padTop += lineHeight + space + marginBottom
            padLeft = paddingLeft + marginRight


        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}

