package com.hym.customview.paint.shader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/24-16:11
 * @annotation 文字效果
 */
public class LinearShaderTextView extends AppCompatTextView {
    private static final String TAG = "LinearShader";
    //线性着色器
    private LinearGradient mLinearGradient;
    //矩阵
    private Matrix mMatrix;
    //当前移动的值
    private float mTransX;
    //动画
    private ValueAnimator mValueAnimator;

    public LinearShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLinearGradient = new LinearGradient(0, 0, w, h, new int[]{getCurrentTextColor(), Color.RED, getCurrentTextColor()},
                new float[]{0.25f, 0.5f, 0.75f}, Shader.TileMode.CLAMP);

        mMatrix = new Matrix();
        mTransX = -w;
        Paint paint = getPaint();
        paint.setShader(mLinearGradient);

        startAnimator(-w, w);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLinearGradient != null) {
            mMatrix.setTranslate(mTransX, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
        }
    }

    public void startAnimator(int start, int end) {
        mValueAnimator = ValueAnimator.ofFloat(start, end);
        mValueAnimator.setDuration(1500);
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

    public void onResume() {
        if (mValueAnimator != null && mValueAnimator.isStarted()) {
            mValueAnimator.resume();
        }
    }

    public void onPause() {
        if (mValueAnimator != null && mValueAnimator.isStarted()) {
            mValueAnimator.pause();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }
    }
}
