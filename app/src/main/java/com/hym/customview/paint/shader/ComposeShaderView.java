package com.hym.customview.paint.shader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.hym.customview.R;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/25-17:15
 * @annotation Paint着色器：混合模式
 */
public class ComposeShaderView extends View {
    private static final String TAG = "ComposeShaderView";
    //线性着色器
    private LinearGradient mLinearGradient;
    //图片着色器
    private BitmapShader mBitmapShader;
    //混合着色器
    private ComposeShader mComposeShader;
    //偏移值
    private float mTransX;
    //偏移矩阵
    private Matrix mMatrix;
    //着色器使用的图片
    private Bitmap mBitmap;
    //要绘制的矩形
    private RectF mRectF;
    //绘制的画笔
    private Paint mPaint;
    //动画
    private ValueAnimator mValueAnimator;

    //要绘制的矩形
    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mMatrix = new Matrix();
        mLinearGradient = new LinearGradient(0, 0, mBitmap.getWidth(), mBitmap.getHeight(),
//                new int[]{Color.GRAY, Color.RED, Color.GRAY},
                new int[]{Color.parseColor("#33000000"), Color.parseColor("#ffffffff"), Color.parseColor("#33000000")},
                new float[]{0.3f, 0.5f, 0.7f},
                Shader.TileMode.CLAMP);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasuredSize(widthMeasureSpec, mBitmap.getWidth());
        int height = getMeasuredSize(heightMeasureSpec, mBitmap.getHeight());
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        if (mRectF != null) {
            mMatrix.setTranslate(mTransX, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            //要注意PorterDuff.Mode，理解每种模式的混合原理，以便出现不如预期的效果的时候能够排查出来
//            mComposeShader = new ComposeShader(mBitmapShader, mLinearGradient, PorterDuff.Mode.SRC_IN);
            mComposeShader = new ComposeShader(mBitmapShader, mLinearGradient, PorterDuff.Mode.MULTIPLY);
            mPaint.setShader(mComposeShader);
            canvas.drawRect(mRectF, mPaint);
        }
        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(0, 0, w, h);
        stopAnimator();
        startAnimator(-mBitmap.getWidth(), mBitmap.getWidth());
    }

    private int getMeasuredSize(int measureSpec, int defSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int value;
        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        } else {
            value = defSize;
        }
        return value;
    }

    public void stopAnimator() {
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
    }

    public void startAnimator(int start, int end) {
        mValueAnimator = ValueAnimator.ofFloat(start, end);
        mValueAnimator.setDuration(1300);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTransX = (float) animation.getAnimatedValue();
                invalidate();
                Log.d(TAG, "startAnimator onAnimationUpdate:" + mTransX);
            }
        });
        mValueAnimator.start();
    }


    public int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }
}
