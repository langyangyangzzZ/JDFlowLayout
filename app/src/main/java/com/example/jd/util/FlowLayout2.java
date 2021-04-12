package com.example.jd.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * author : Tiaw.
 * date   : 4/12/21
 * 博客：https://blog.csdn.net/weixin_44819566
 * desc   : FlowLayout 流式布局
 */
public class FlowLayout2 extends ViewGroup {

    //水平间距
    private static final int horSpacing = 15;
    //垂直间距
    private static final int verSpacing = 5;
    //用来记录每一行的View 为了让onLayout遍历
    private ArrayList<ArrayList<View>> listlistView;
    private ArrayList<Integer> listLineHeight;

    public FlowLayout2(Context context) {
        super(context);
    }

    public FlowLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 子控件获取marge方法   start
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    // 子控件获取marge方法   stop

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        listlistView = new ArrayList<>();
        listLineHeight = new ArrayList<>();

        //用来记录父容器的宽/高
        int parentWidth = 0;
        int parentHeight = 0;

        //用来记录一行的宽/高
        int lineWidth = 0;
        int lineHeight = 0;

        int GroupWidth = MeasureSpec.getSize(widthMeasureSpec);
        int GroupHeight = MeasureSpec.getSize(heightMeasureSpec);

        ArrayList<View> views = new ArrayList<>();

        for (int i = 0; i < getChildCount(); i++) {
            //获取每一个View
            View childAtView = getChildAt(i);

            //子View实际宽和高
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAtView.getLayoutParams();

            int measureWidth = getChildMeasureSpec(widthMeasureSpec,
                    childAtView.getPaddingLeft() + childAtView.getPaddingRight(),
                    layoutParams.width);
            int measureHeight = getChildMeasureSpec(heightMeasureSpec,
                    childAtView.getPaddingTop() + childAtView.getPaddingBottom(),
                    layoutParams.height);

            //设置每一个View实际宽高
            childAtView.measure(measureWidth, measureHeight);

            int childWidth = childAtView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAtView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;


            //如果当前记录的宽 + 下一个宽 + 间距 > ViewGroup的宽则换行
            if (lineWidth + childWidth + horSpacing > GroupWidth) {

                //已经换行 添加Views
                listlistView.add(views);

                //记录每一行的高度
                listLineHeight.add(lineHeight);

                //ViewGroup的宽 = 记录的最大值的宽
                parentWidth = Math.max(lineWidth + verSpacing, parentWidth + verSpacing);
                //ViewGroup的高 = 当前的高 + 间隙
                parentHeight += lineHeight + horSpacing;

                //换行之后清除View继续记录
                views = new ArrayList<>();
                //换行之后清空让他重新记录
                lineWidth = 0;
                lineHeight = 0;
            }
            //记录每一个View的值
            views.add(childAtView);
            //当前的宽 = 每一个View的宽 + 间距
            lineWidth += childWidth + horSpacing;
            //当前的一行的高 = 最高的Height
            lineHeight = Math.max(lineHeight, childHeight + verSpacing);


            //处理最后一条数据
            if (i == getChildCount() - 1) {
                //已经换行 添加Views
                listlistView.add(views);
                //记录每一行的高度
                listLineHeight.add(lineHeight);
                //ViewGroup的高 = 当前的高 + 间隙
                parentHeight += lineHeight + horSpacing;
            }
        }

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        Log.i("EXACTLY", GroupWidth + "\t" + GroupHeight);
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? GroupWidth : parentWidth,
                modeHeight == MeasureSpec.EXACTLY ? GroupHeight : parentHeight
        );

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int padLeft = getPaddingLeft();
        int padTop = getPaddingTop();

        for (int i = 0; i < listlistView.size(); i++) {
            ArrayList<View> views = listlistView.get(i);
            //遍历每一个view
            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();

                int left = padLeft + layoutParams.leftMargin;
                int right = left + view.getMeasuredWidth();
                int top = padTop + layoutParams.topMargin;
                int button = top + view.getMeasuredHeight();
                Log.i("onLayoutView1", i + "\t" + left + "\t" + right + "\t" + top + "\t" + button);
                Log.i("onLayoutView2", i + "\t" + view.getMeasuredWidth() + "\t" + view.getMeasuredHeight());
                view.layout(left, top, right, button);

                //记录第二View ,第二个View的left = 第一个View的right + 间距 + rightMargin
                padLeft = right + horSpacing + layoutParams.rightMargin;
            }
            //获取每行的高度
            Integer lineHeight = listLineHeight.get(i);
            //第二行的高度 = 第二行的高度 + 每行的高度 + 间距
            padTop += lineHeight + verSpacing;
            //下一列的时候将padLeft 初始化为paddingleft
            padLeft = getPaddingLeft();

        }
    }
}
