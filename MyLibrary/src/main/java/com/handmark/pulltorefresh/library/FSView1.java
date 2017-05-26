package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 心得：
 *      一、位移，旋转等改变的不是画布，而是坐标系
 *      二、先有translate(), rotate()改变坐标系，后有相应绘制的改变
 *      三、至于阅读方式可以采用由最内部的canvas.save()到最外端的canvas.restore()
 */
public class FSView1 extends View{
    private static final String TAG = FSView1.class.getSimpleName();
    private static final int MSG_UPDATE_DELAY = 0x13;
    Context mContext;
    Paint mPaint;
    Bitmap mBitmap;
    float rotate;
    boolean isCircleTurn = false;
    float degree = 18;
    long delayTime = 20;
    float viewWidth, viewHeight;
    int picID;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_UPDATE_DELAY:
                    postInvalidate();
                    break;
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(viewWidth/2, viewHeight/2);

        canvas.save();
        canvas.rotate(rotate);

        canvas.save();
        canvas.translate(-viewWidth/2, -viewHeight/2);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        canvas.restore();
        canvas.restore();
        canvas.restore();

//        canvas.save();
//        canvas.translate(380, 380);
//
//        canvas.save();
//        canvas.rotate(rotate);
//
//        canvas.save();
//        canvas.translate(-80, -80);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//
//        canvas.restore();
//        canvas.restore();
//        canvas.restore();

        if(isCircleTurn){
            rotate = rotate + degree;
            if(!mHandler.hasMessages(MSG_UPDATE_DELAY)){
                mHandler.sendEmptyMessageDelayed(MSG_UPDATE_DELAY, delayTime);
            }
        }
    }

    /** 设置转圈的速度（度/圈）以及延时多久一次 */
    public void setTurnSpeed(float degree, long delayTime){
        this.degree = degree;
        this.delayTime = delayTime;
    }

    /** 停止旋转 */
    public void stopTurn(){
        isCircleTurn = false;
    }

    /** 开始旋转 */
    public void startTurn(){
        isCircleTurn = true;
        postInvalidate();
    }

    /** 转动角度 */
    public void turnDegress(float rotate){
        if(!isCircleTurn){
            this.rotate = rotate;
            postInvalidate();
        }
    }

    public void setCircleTurn(boolean circleTurn) {
        isCircleTurn = circleTurn;
        if(isCircleTurn){
            postInvalidate();
        }
    }

    /** 初始化画笔 */
    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    public FSView1(Context context) {
        this(context, null);
    }

    public FSView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FSView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray mTArray = context.obtainStyledAttributes(attrs, R.styleable.FSView1, defStyleAttr, 0);

        if(mTArray.hasValue(R.styleable.FSView1_width)){
            viewWidth = mTArray.getDimensionPixelSize(R.styleable.FSView1_width,
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        }else{
            viewWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        }

        if(mTArray.hasValue(R.styleable.FSView1_height)){
            viewHeight = mTArray.getDimensionPixelSize(R.styleable.FSView1_height,
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        }else{
            viewHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        }

        if(mTArray.hasValue(R.styleable.FSView1_pic_id)){
            picID = mTArray.getResourceId(R.styleable.FSView1_pic_id, R.drawable.test);
        }else{
            picID = R.drawable.loading_panda;
        }
        mTArray.recycle();

        mContext = context;
        initPaint();
        initBitmapById(picID);
    }

    /** 通过Resourse的ID获取Bitmap */
    private void initBitmapById(int resourceID){
        Bitmap tempBitmap = BitmapFactory.decodeResource(getResources(), resourceID);

        int width = tempBitmap.getWidth();              // 获得图片的宽高
        int height = tempBitmap.getHeight();
        float scaleWidth = viewWidth / width;           // 计算缩放比例
        float scaleHeight = viewHeight / height;
        Matrix matrix = new Matrix();                   // 取得想要缩放的matrix参数
        matrix.postScale(scaleWidth, scaleHeight);

        mBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, width, height, matrix, true);   // 得到新的图片
        if(!tempBitmap.isRecycled()){
            tempBitmap.recycle();
        }
    }

}
