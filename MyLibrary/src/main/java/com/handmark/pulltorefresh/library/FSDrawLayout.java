package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by zc on 2017/5/10.
 */

public class FSDrawLayout extends FrameLayout{
    private static final String TAG = FSDrawLayout.class.getSimpleName();
    Paint mPaint;

    public FSDrawLayout(Context context) {
        super(context);
        init();
    }

    public FSDrawLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FSDrawLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(Color.BLUE);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
