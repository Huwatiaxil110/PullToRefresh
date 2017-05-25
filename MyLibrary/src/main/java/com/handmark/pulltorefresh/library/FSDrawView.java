package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zc on 2017/5/10.
 */

public class FSDrawView extends View{
    private static final String TAG = FSDrawView.class.getSimpleName();
    Paint mPaint;

    public FSDrawView(Context context) {
        this(context, null);
    }

    public FSDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FSDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        setBackgroundColor(Color.BLUE);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw(Canvas canvas)");
        canvas.drawCircle(50, 50, 100, mPaint);
    }

}





















































































