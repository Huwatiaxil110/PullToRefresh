package com.testcanvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zc on 2017/5/26.
 */

public class FSPullBaseView extends LinearLayout {
    private Mode mMode = Mode.PULL_FROM_START;
    private boolean mIsBeingDragged = false;
    private boolean mScrollingWhileRefreshingEnabled = false;
    private State mState = State.RESET;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(!isPullToRefreshEnabled()){
            return false;
        }

        final int action = event.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsBeingDragged = false;
            return false;
        }

        if (action != MotionEvent.ACTION_DOWN && mIsBeingDragged) { //如果是Move且可以拖动
            return true;
        }

        switch (action){
            case MotionEvent.ACTION_MOVE:
                // If we're refreshing, and the flag is set. Eat all MOVE events
                if (!mScrollingWhileRefreshingEnabled && isRefreshing()) {
                    return true;
                }

//                if (isReadyForPull()) {
//                    final float y = event.getY(), x = event.getX();
//                    final float diff, oppositeDiff, absDiff;
//
//                    // We need to use the correct values, based on scroll
//                    // direction
//                    switch (getPullToRefreshScrollDirection()) {
//                        case HORIZONTAL:
//                            diff = x - mLastMotionX;
//                            oppositeDiff = y - mLastMotionY;
//                            break;
//                        case VERTICAL:
//                        default:
//                            diff = y - mLastMotionY;
//                            oppositeDiff = x - mLastMotionX;
//                            break;
//                    }
//                    absDiff = Math.abs(diff);
//
//                    if (absDiff > mTouchSlop && (!mFilterTouchEvents || absDiff > Math.abs(oppositeDiff))) {
//                        if (mMode.showHeaderLoadingLayout() && diff >= 1f && isReadyForPullStart()) {
//                            mLastMotionY = y;
//                            mLastMotionX = x;
//                            mIsBeingDragged = true;
//                            if (mMode == Mode.BOTH) {
//                                mCurrentMode = Mode.PULL_FROM_START;
//                            }
//                        } else if (mMode.showFooterLoadingLayout() && diff <= -1f && isReadyForPullEnd()) {
//                            mLastMotionY = y;
//                            mLastMotionX = x;
//                            mIsBeingDragged = true;
//                            if (mMode == Mode.BOTH) {
//                                mCurrentMode = Mode.PULL_FROM_END;
//                            }
//                        }
//                    }
//                }
                break;

            case MotionEvent.ACTION_DOWN:

                break;
        }

        return mIsBeingDragged;
    }

    /** 是否能下拉刷新*/
    public boolean isPullToRefreshEnabled(){
        return mMode.permitsPullToRefresh();
    }

    /** 是否正在刷新 */
    public final boolean isRefreshing() {
        return mState == State.REFRESHING || mState == State.MANUAL_REFRESHING;
    }

//    private boolean isReadyForPull() {
//        switch (mMode) {
//            case PULL_FROM_START:
//                return isReadyForPullStart();
//            case PULL_FROM_END:
//                return isReadyForPullEnd();
//            case BOTH:
//                return isReadyForPullEnd() || isReadyForPullStart();
//            default:
//                return false;
//        }
//    }

    /** 下拉模式 */
    public static enum Mode{
        DISABLED(0x0), PULL_FROM_START(0x1), PULL_FROM_END(0x2), BOTH(0x3), MANUAL_REFRESH_ONLY(0x4);

        public static Mode mapIntToValue(int modeInt1){
            for(Mode value: Mode.values()){
                if(modeInt1 == value.getModeInt()){
                    return value;
                }
            }

            return PULL_FROM_START;
        }

        private int modeInt;
        Mode(int modeInt){
            this.modeInt = modeInt;
        }

        int getModeInt(){
            return modeInt;
        }

        /** 是否允许下拉刷新 */
        public boolean permitsPullToRefresh(){
            return !(this == DISABLED || (this == MANUAL_REFRESH_ONLY));
        }
    }

    /** 下拉状态 */
    public static enum State {
        RESET(0x0), PULL_TO_REFRESH(0x1), RELEASE_TO_REFRESH(0x2), REFRESHING(0x8),
        MANUAL_REFRESHING(0x9), OVERSCROLLING(0x10);

        public static State mapIntToValue(final int stateInt) {
            for (State value : State.values()) {
                if (stateInt == value.getIntValue()) {
                    return value;
                }
            }

            return RESET;
        }

        private int mIntValue;
        State(int intValue) {
            mIntValue = intValue;
        }

        int getIntValue() {
            return mIntValue;
        }
    }

    public FSPullBaseView(Context context) {
        this(context, null);
    }

    public FSPullBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FSPullBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
































