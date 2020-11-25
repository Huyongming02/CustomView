package com.hym.customview.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
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
 * @date 2020/11/24-14:31
 * @annotation paint详解之效果
 */
public class PaintEffectView extends View {
    public PaintEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int radius = width / 9;
        /**
         * ### 2.1 setAntiAlias(boolean aa)设置抗锯齿
         *
         * ### 2.2 setStyle()
         *
         * ### 2.3 线条形状
         * 1. setStrokenWidth(float width)
         * 2. setStrokenCap(Paint.Cap cap)
         * 3. setStrokenJoin(Paint.Join join)
         * 4. setStokenMiter(float miter)
         */


        /**
         * ### 2.4 色彩优化
         * 1. setDither(boolean dither)抖动
         * 2. setFilterBitmap(boolean filter)设置是否采用双线性过滤来绘制bitmap
         */

        /**
         * ### 2.5 setPathEffect(PathEffect effect)给图形的轮廓设置效果
         */
        /**
         * ### 2.6 setShadowLayer()在绘制的内容下面加一层阴影
         *
         * ### 2.7 setMaskFilter()在绘制的上层添加效果
         */
        testShadowLayer(canvas, width, radius);
//        testShadowLayerByShape(canvas, width, radius);
        /**
         * ### 2.8 获取绘制的path
         */
    }

    private void testShadowLayerByShape(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        RadialGradient radialGradient = new RadialGradient(width / 2, width / 2, radius * 2,
                new int[]{Color.parseColor("#404364"), Color.parseColor("#404364"), Color.parseColor("#00404364")},
                new float[]{0.0f, 0.7f, 1f}, Shader.TileMode.CLAMP);
        Paint paint = getPaint();
        paint.setShader(radialGradient);
        canvas.drawCircle(width / 2, width / 2, radius * 2, paint);
    }

    private void testShadowLayer(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        paint.setShadowLayer(dp2px(getContext(), 10), dp2px(getContext(), 10), dp2px(getContext(), 10), Color.BLUE);
        canvas.drawCircle(width / 6, 0, radius, paint);
        paint.setShadowLayer(dp2px(getContext(), 10), dp2px(getContext(), 10), dp2px(getContext(), 10), Color.parseColor("#404364"));
        canvas.drawCircle(width * 3 / 6, 0, radius, paint);
        paint.setShadowLayer(dp2px(getContext(), 10), dp2px(getContext(), 10), dp2px(getContext(), 10), Color.parseColor("#55404364"));
        canvas.drawCircle(width * 5 / 6, 0, radius, paint);


        //
        canvas.translate(0, radius * 3);
        paint.setShadowLayer(dp2px(getContext(), 10), 0, radius * 2, Color.BLUE);
        canvas.drawCircle(width / 6, 0, radius, paint);
        paint.setShadowLayer(dp2px(getContext(), 10), 0, radius * 2, Color.parseColor("#404364"));
        canvas.drawCircle(width * 3 / 6, 0, radius, paint);
        paint.setShadowLayer(dp2px(getContext(), 10), 0, radius * 2, Color.parseColor("#55404364"));
        canvas.drawCircle(width * 5 / 6, 0, radius, paint);

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
