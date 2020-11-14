package com.hym.customview.paintbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/10-19:38
 * @annotation canvas的基本方法
 */
public class CanvasBaseView extends View {

    public CanvasBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint getPaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        return mPaint;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int radius = width / 9;
        /**
         *1. drawColor()
         * 2. drawRGB/drawARGB()
         * 3. drawCircle()
         * 4. drawRect()
         * 5. drawPoint()
         * 6. drawPoints()
         * 7. drawOval()
         * 8. drawLine()
         * 9. drawLines
         * 10. drawRoundRect()
         * 11. drawArc()
         */
        testDrawColor(canvas, width, radius);
        testDrawCircle(canvas, width, radius);
        testDrawRect(canvas, width, radius);
        testDrawPoint(canvas, width, radius);
        testDrawPoints(canvas, width, radius);
        testDrawOval(canvas, width, radius);
        testDrawLine(canvas, width, radius);
        testDrawLines(canvas, width, radius);
        testDrawRoundRect(canvas, width, radius);
        testDrawArc(canvas, width, radius);

    }

    private void testDrawArc(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(dp2px(getContext(), 5));
        canvas.drawArc(new RectF(width / 7, 0, width * 3 / 7, width / 7), 90, 150, true, paint);
        canvas.drawArc(new RectF(width * 4 / 7, 0, width * 6 / 7, width / 7), 90, 150, false, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void testDrawRoundRect(Canvas canvas, int width, int radius) {
        /**
         * 圆角矩形：有圆角的时候，Paint.Join无影响；无圆角的时候和矩形一样
         */
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(dp2px(getContext(), 15));
        int rx = dp2px(getContext(), 25);
        int ry = dp2px(getContext(), 25);

        canvas.drawRoundRect(width / 8, 0, width * 3 / 8, width / 8, rx, ry, getPaint());

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);//圆角
        paint.setStrokeWidth(dp2px(getContext(), 10));
        canvas.drawRoundRect(width * 4 / 8, 0, width * 6 / 8, width / 8, rx, ry, paint);

        canvas.translate(0, radius * 2);

//        rx = dp2px(getContext(), 5);
//        ry = dp2px(getContext(), 25);
        paint.setStrokeJoin(Paint.Join.BEVEL);//平角
        canvas.drawRoundRect(width / 8, 0, width * 3 / 8, width / 8, rx, ry, paint);

//        rx = dp2px(getContext(), 25);
//        ry = dp2px(getContext(), 5);
        paint.setStrokeJoin(Paint.Join.MITER);//尖角
        canvas.drawRoundRect(width * 4 / 8, 0, width * 6 / 8, width / 8, rx, ry, paint);
    }

    private void testDrawLines(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(dp2px(getContext(), 15));
        canvas.drawLines(new float[]{width / 10, 0, width * 3 / 10, 0, width * 4 / 10, 0, width * 6 / 10, 0, width * 7 / 10, 0, width * 9 / 10, 0}, paint);

        canvas.translate(0, radius);
        //跳过四个数，绘制后面8个数组成的线条
        canvas.drawLines(new float[]{width / 10, 0, width * 3 / 10, 0, width * 4 / 10, 0, width * 6 / 10, 0, width * 7 / 10, 0, width * 9 / 10, 0}, 4, 8, paint);

//        canvas.translate(0, radius);
//        canvas.drawLine(width / 10, 0, width * 3 / 10, 0, paint);
//        canvas.drawLine(width * 3 / 10, 0, width * 5 / 10, width / 10, paint);
    }

    private void testDrawLine(Canvas canvas, int width, int radius) {
        /**
         * 线条：（Paint.Style三种样式都一样，宽度等于strokewidth）
         */
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(dp2px(getContext(), 15));
        canvas.drawLine(width / 10, 0, width * 3 / 10, 0, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(width * 4 / 10, 0, width * 6 / 10, 0, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(width * 7 / 10, 0, width * 9 / 10, 0, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void testDrawOval(Canvas canvas, int width, int radius) {
        /**
         * 椭圆：（Paint.Style.STROKE和Paint.Style.FILL_AND_STROKE模式，stroke宽度一半在fill区域）
         */
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(dp2px(getContext(), 15));
        canvas.drawOval(width / 8, 0, width * 3 / 8, width / 8, paint);
        canvas.drawOval(width * 4 / 8, 0, width * 5 / 8, width / 8, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(width / 8, 0, width * 3 / 8, width / 8, paint);
        canvas.drawOval(width * 4 / 8, 0, width * 5 / 8, width / 8, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(width / 8, 0, width * 3 / 8, width / 8, paint);
        canvas.drawOval(width * 4 / 8, 0, width * 5 / 8, width / 8, paint);

    }

    private void testDrawPoints(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius);
        Paint paint = getPaint();
        paint.setStrokeWidth(dp2px(getContext(), 20));
        canvas.drawPoints(new float[]{width / 5, 0, width * 2 / 5, 0, width * 3 / 5, 0, width * 4 / 5, 0}, paint);

        canvas.translate(0, radius);
        /**
         * 设置（跳过offset个数，计算count个数，这里要是2的倍数）
         */
        canvas.drawPoints(new float[]{width / 5, 0, width * 2 / 5, 0, width * 3 / 5, 0, width * 4 / 5, 0}, 2, 4, paint);
    }

    private void testDrawPoint(Canvas canvas, int width, int radius) {
        /**
         * 绘制点的时候，Paint.Cap.ROUND绘制圆点，其他方点，点的大小都一样
         */
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        paint.setStrokeWidth(dp2px(getContext(), 20));
        canvas.drawPoint(width / 5, 0, paint);
        paint.setStrokeCap(Paint.Cap.ROUND);//圆角
        canvas.drawPoint(width * 2 / 5, 0, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(width * 3 / 5, 0, paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(width * 4 / 5, 0, paint);
    }

    private void testDrawRect(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        /**
         * 绘制矩形（Paint.Style.FILL_AND_STROKE和Paint.Style.FILL一样，没有边距）
         */
        canvas.drawRect(width / 8, 0, width * 3 / 8, width / 8, getPaint());

        Paint paint = new Paint(getPaint());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);//圆角
        paint.setStrokeWidth(dp2px(getContext(), 10));
        canvas.drawRect(width * 4 / 8, 0, width * 6 / 8, width / 8, paint);

//        Paint paint2 = new Paint(mPaint);
//        paint2.setStyle(Paint.Style.FILL);//验证Paint.Style.FILL_AND_STROKE和Paint.Style.FILL一样，没有边距
//        paint2.setColor(Color.GREEN);
//        canvas.drawRect(width * 4 / 8, 0, width * 6 / 8, width / 8, paint2);

        canvas.translate(0, radius * 2);

        paint.setStrokeJoin(Paint.Join.BEVEL);//平角
        canvas.drawRect(width / 8, 0, width * 3 / 8, width / 8, paint);

        paint.setStrokeJoin(Paint.Join.MITER);//尖角
        canvas.drawRect(width * 4 / 8, 0, width * 6 / 8, width / 8, paint);
    }

    private void testDrawCircle(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        canvas.drawCircle(width / 4, 0, radius, getPaint());

        Paint paint = new Paint(getPaint());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dp2px(getContext(), 10));
        canvas.drawCircle(width * 3 / 4, 0, radius, paint);
    }

    private void testDrawColor(Canvas canvas, int width, int radius) {
//        canvas.drawColor(getContext().getResources().getColor(R.color.colorAccent));
//        canvas.drawRGB(0,0,255);
    }

    public int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }
}
