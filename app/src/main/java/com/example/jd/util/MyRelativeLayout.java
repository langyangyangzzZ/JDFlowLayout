package com.example.jd.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();

            int measureSpecWidth = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.width);
            int measureSpecHeight = getChildMeasureSpec(heightMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.height);

            child.measure(measureSpecWidth, measureSpecHeight);

            int width = 0;
            int height = 0;
            if (layoutParams.width == LayoutParams.WRAP_CONTENT) {
                width = layoutParams.width;
            } else if (layoutParams.width == LayoutParams.MATCH_PARENT) {
                width = child.getMeasuredWidth();
            } else {
                width = (int) (layoutParams.width * scaleX);
            }

            if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
                height = layoutParams.height;
            } else if (layoutParams.height == LayoutParams.MATCH_PARENT) {
                height = child.getMeasuredHeight();
            } else {
                height = (int) (layoutParams.height * scaleY);
            }

            Log.i("??????View??????0:", i + "\t" + width + "\t" + height);
            Log.i("??????View??????1:", i + "\t" + child.getHeight() + "\t" + child.getMeasuredHeight());
            Log.i("??????View??????2:", i + "\t" + layoutParams.width + "\t" + layoutParams.height);

            layoutParams.width = width;
            layoutParams.height = height;
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
        }
    }
}








