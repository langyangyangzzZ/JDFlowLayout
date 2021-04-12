package com.example.jd.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

/**
 * author : Tiaw.
 * date   : 4/9/21
 * 博客：https://blog.csdn.net/weixin_44819566
 * desc   :
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float scaleX = UIUtils.getInstance(getContext()).getHorValue();
        float scaleY = UIUtils.getInstance(getContext()).getVerValue();
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) child.getLayoutParams();

            int measureSpecWidth = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.width);
            int measureSpecHeight = getChildMeasureSpec(heightMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.height);

            child.measure(measureSpecWidth, measureSpecHeight);

            int width = 0;
            int height = 0;
            if (layoutParams.width == RelativeLayout.LayoutParams.WRAP_CONTENT) {
                width = layoutParams.width;
            } else if (layoutParams.width == RelativeLayout.LayoutParams.MATCH_PARENT) {
                width = child.getMeasuredWidth();
            } else {
                width = (int) (layoutParams.width * scaleX);
            }

            if (layoutParams.height == RelativeLayout.LayoutParams.WRAP_CONTENT) {
                height = layoutParams.height;
            } else if (layoutParams.height == RelativeLayout.LayoutParams.MATCH_PARENT) {
                height = child.getMeasuredHeight();
            } else {
                height = (int) (layoutParams.height * scaleY);
            }

            Log.i("具体View值为0:", i + "\t" + width + "\t" + height);
            Log.i("具体View值为1:", i + "\t" + child.getHeight() + "\t" + child.getMeasuredHeight());
            Log.i("具体View值为2:", i + "\t" + layoutParams.width + "\t" + layoutParams.height);

            layoutParams.width = width;
            layoutParams.height = height;
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
        }
    }
}
