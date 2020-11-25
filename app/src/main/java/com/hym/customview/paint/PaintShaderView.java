package com.hym.customview.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/19-20:47
 * @annotation ....
 */
public class PaintShaderView extends View {
    public PaintShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int radius = width / 9;
        /**
         * android中提供了几个它的子类
         * 1. LinearGradient：线性渐变
         * 2. RadialGradient：辐射渐变
         * 3. SweepGradient：扫描渐变
         * 4. BitmapShader：
         * 5. ComposeShader：组成
         */
    }


    private Paint getPaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(dp2px(getContext(), 5));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        return mPaint;
    }

    public int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }
}
