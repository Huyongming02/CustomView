package com.hym.customview.floatview.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hym.customview.R;
import com.hym.customview.floatview.manager.MirrorModeWindowManager;


/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v3.1.0
 * @date 2020/11/26-17:13
 * @annotation 镜面悬浮窗
 */
public class MirrorFloatView extends MirrorModeWindowManager.FloatView {
    private static final String TAG = "MirrorFloatView";
    private static final String SP_NAME = MirrorFloatView.class.getSimpleName();
    private static final String KEY_FLOATING_X = "floating_x";
    private static final String KEY_FLOATING_Y = "floating_y";

    private LocationGravity mGravity;
    private int mMarginTop;

    //记录手指按下时在小悬浮窗的View上的横坐标的值
    private float xInView;
    //记录手指按下时在小悬浮窗的View上的纵坐标的值
    private float yInView;
    //记录当前手指位置在屏幕上的横坐标值
    private float xInScreen;
    //记录当前手指位置在屏幕上的纵坐标值
    private float yInScreen;
    //记录手指按下时在屏幕上的横坐标的值
    private float xDownInScreen;
    //记录手指按下时在屏幕上的纵坐标的值
    private float yDownInScreen;
    //滑动中
    private boolean isAnchoring = false;
    //是否显示
    private boolean isShowing = false;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private int mScreenWidth;
    private int mScreenHeight;

    private View mFloatView;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private ImageView mIvMideBg;
    private TextView mTvLeft;
    private TextView mTvRight;
    private View mDividlineLeft;
    private View mDividlineRight;

    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;

    public MirrorFloatView(@NonNull Context context, LocationGravity gravity, int marginTop) {
        super(context);
        this.mGravity = gravity;
        this.mMarginTop = marginTop;
        initSharedPreferences();
        initWindowManager(context);
        initView(context);
    }

    private void initSharedPreferences() {
        mSp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mEditor = mSp.edit();
    }

    private int getStoreLocationY() {
        return mSp.getInt(KEY_FLOATING_Y, -1);
    }

    private int getStoreLocationX() {
        return mSp.getInt(KEY_FLOATING_X, -1);
    }

    /**
     * 保存位置
     */
    void savePosition(int x, int y) {
        mEditor.putInt(KEY_FLOATING_X, x);
        mEditor.putInt(KEY_FLOATING_Y, y);
        mEditor.commit();
    }

    private void initWindowManager(Context context) {
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.packageName = context.getPackageName();
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        int mType;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mType = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
        mParams.type = mType;
        mParams.format = PixelFormat.RGBA_8888;

        Point size = new Point();
        mWindowManager.getDefaultDisplay().getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mFloatView = inflater.inflate(R.layout.mirror_float_view_layout, this, false);
        mIvLeft = mFloatView.findViewById(R.id.iv_left);
        mIvRight = mFloatView.findViewById(R.id.iv_right);
        mIvMideBg = mFloatView.findViewById(R.id.iv_mid_bg);
        mTvLeft = mFloatView.findViewById(R.id.tv_left);
        mTvRight = mFloatView.findViewById(R.id.tv_right);
        mDividlineLeft = mFloatView.findViewById(R.id.divider_left);
        mDividlineRight = mFloatView.findViewById(R.id.divider_right);
        addView(mFloatView);
    }

    @Override
    public void showWindow(Context context) {
        if (mWindowManager != null) {
            mParams.gravity = Gravity.START | Gravity.TOP;
            int x = getStoreLocationX();
            int y = getStoreLocationY();
            if (x < 0 && y < 0) {
                if (mGravity == LocationGravity.START) {
                    mParams.x = 0;
                    mParams.y = mMarginTop;
                } else if (mGravity == LocationGravity.END) {
                    Log.d(TAG, "mScreenWidth:" + mScreenWidth);
                    Log.d(TAG, "mFloatView.getMeasuredWidth():" + mFloatView.getMeasuredWidth());
                    mParams.x = mScreenWidth - mFloatView.getMeasuredWidth();
                    mParams.y = mMarginTop;
                } else if (mGravity == LocationGravity.START_CENTER) {
                    mParams.x = 0;
                    mParams.y = mScreenHeight / 2 + mMarginTop;
                } else {
                    mParams.x = mScreenWidth - mFloatView.getMeasuredWidth();
                    mParams.y = mScreenHeight / 2 + mMarginTop;
                }
                if (mParams.y > mScreenHeight) {
                    mParams.y = mScreenHeight;
                }
            } else {
                mParams.x = x;
                mParams.y = y;
            }
            showLeftOrRight();
            setIsShowing(true);
            mWindowManager.addView(this, mParams);
        }
    }

    private void showLeftOrRight() {
        if (mParams.x <= 10) {
            showLeft();
        } else {
            showRight();
        }
    }

    @Override
    public void dismissWindow() {
        if (mWindowManager != null) {
            mWindowManager.removeViewImmediate(this);
            setIsShowing(false);
        }
    }


    public void showLeft() {
        mIvLeft.setVisibility(VISIBLE);
        mIvRight.setVisibility(GONE);
        mIvMideBg.setVisibility(GONE);
        mTvLeft.setVisibility(VISIBLE);
        mTvRight.setVisibility(GONE);
        mDividlineLeft.setVisibility(VISIBLE);
        mDividlineRight.setVisibility(GONE);
    }

    public void showRight() {
        mIvLeft.setVisibility(GONE);
        mIvRight.setVisibility(VISIBLE);
        mIvMideBg.setVisibility(GONE);
        mTvLeft.setVisibility(GONE);
        mTvRight.setVisibility(VISIBLE);
        mDividlineLeft.setVisibility(GONE);
        mDividlineRight.setVisibility(VISIBLE);
    }

    public void showMide() {
        mIvLeft.setVisibility(GONE);
        mIvRight.setVisibility(GONE);
        mIvMideBg.setVisibility(VISIBLE);
        mTvLeft.setVisibility(GONE);
        mTvRight.setVisibility(GONE);
        mDividlineLeft.setVisibility(GONE);
        mDividlineRight.setVisibility(GONE);
    }


    private void setIsShowing(boolean isShowing) {
        this.isShowing = isShowing;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnchoring) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                Log.d("testontouch", "ACTION_DOWN getX:" + event.getX() + ";getY:" + event.getY());
                Log.d("testontouch", "ACTION_DOWN getRawX:" + event.getRawX() + ";getRawY:" + event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("testontouch", "ACTION_DOWN getX:" + event.getX() + ";getY:" + event.getY());
                Log.d("testontouch", "ACTION_DOWN getRawX:" + event.getRawX() + ";getRawY:" + event.getRawY());
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                // 手指移动的时候更新小悬浮窗的位置
                if (Math.abs(xDownInScreen - xInScreen) > ViewConfiguration.get(getContext()).getScaledTouchSlop()
                        || Math.abs(yDownInScreen - yInScreen) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    showMide();
                }
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(xDownInScreen - xInScreen) > ViewConfiguration.get(getContext()).getScaledTouchSlop()
                        || Math.abs(yDownInScreen - yInScreen) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    //吸附效果
                    anchorToSide();
                }else{
                    performClick();
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void anchorToSide() {
        isAnchoring = true;


        int xDistance = 0;
        int yDistance = 0;

        int middleX = mParams.x + getWidth() / 2;
        if (middleX < mScreenWidth / 2) {//靠左
            xDistance = 0 - mParams.x;
        } else {//靠右
            xDistance = mScreenWidth - getWidth() - mParams.x;
        }
        yDistance = 0;
        int animTime = Math.abs(xDistance) * 600 / mScreenWidth;
        this.post(new AnchorAnimRunnable(animTime, xDistance, yDistance, System.currentTimeMillis()));
    }

    public static int dp2px(Context context,float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private class AnchorAnimRunnable implements Runnable {

        private int animTime;
        private long currentStartTime;
        private Interpolator interpolator;
        private int xDistance;
        private int yDistance;
        private int startX;
        private int startY;

        public AnchorAnimRunnable(int animTime, int xDistance, int yDistance, long currentStartTime) {
            this.animTime = animTime;
            this.currentStartTime = currentStartTime;
            interpolator = new AccelerateDecelerateInterpolator();
            this.xDistance = xDistance;
            this.yDistance = yDistance;
            startX = mParams.x;
            startY = mParams.y;
        }

        @Override
        public void run() {
            if (System.currentTimeMillis() >= currentStartTime + animTime) {
                if (mParams.x != (startX + xDistance) || mParams.y != (startY + yDistance)) {
                    mParams.x = startX + xDistance;
                    mParams.y = startY + yDistance;
                    mWindowManager.updateViewLayout(MirrorFloatView.this, mParams);
                }
                savePosition(mParams.x, mParams.y);
                showLeftOrRight();
                isAnchoring = false;
                return;
            }
            float delta = interpolator.getInterpolation((System.currentTimeMillis() - currentStartTime) / (float) animTime);
            int xMoveDistance = (int) (xDistance * delta);
            int yMoveDistance = (int) (yDistance * delta);
            Log.e(TAG, "delta:  " + delta + "  xMoveDistance  " + xMoveDistance + "   yMoveDistance  " + yMoveDistance);
            mParams.x = startX + xMoveDistance;
            mParams.y = startY + yMoveDistance;
            if (!isShowing) {
                return;
            }
            mWindowManager.updateViewLayout(MirrorFloatView.this, mParams);
            MirrorFloatView.this.postDelayed(this, 16);
        }
    }

    private void updateViewPosition() {
        //增加移动误差
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        mWindowManager.updateViewLayout(this, mParams);
    }

    public static enum LocationGravity {
        START, END, START_CENTER, END_CENTER
    }

}
