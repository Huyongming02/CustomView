package com.hym.customview.paintbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
 * @date 2020/11/9-19:29
 * @annotation Paint基础学习
 */
public class PaintBaseView extends View {


    public PaintBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

//        Paint.setStyle(Style style) 设置绘制模式
//        Paint.setColor( int color)设置颜色
//        Paint.setStrokeWidth( float width)设置线条宽度
//        Paint.setTextSize( float textSize)设置文字大小
//        Paint.setAntiAlias( boolean aa)设置抗锯齿开关
//        Paint.setStrokeCap(Cap cap)设置笔触形状
//        Paint.setStrokeJoin(Paint.Join join)设置转角形状

        Paint mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int radius = width / 9;
        /**
         * 设置颜色
         */
        testSetColor(canvas, width, radius);

        /**
         * 设置抗锯齿开关
         */
        testSetAntiAlias(canvas, width, radius);
        /**
         * 设置绘制模式
         */
        testSetStyle(canvas, width, radius);
        /**
         * 设置线条宽度
         */
        testSetStrokeWidth(canvas, width, radius);
//
//        /**
//         * 画线
//         */
//        canvas.translate(0, radius * 3);
//        mPaint.setStrokeWidth(radius);
//        //FILL_AND_STROKE
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawLine(width / 7, radius * 2, width * 2 / 7, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawLine(width * 3 / 7, radius * 2, width * 4 / 7, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
//        canvas.drawLine(width * 5 / 7, radius * 2, width * 6 / 7, radius * 2, mPaint);
//
//        /**
//         * 画点
//         */
//        canvas.translate(0, radius * 3);
//        mPaint.setStrokeWidth(radius);
//        //FILL_AND_STROKE
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 2 / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 3 / 4, radius * 2, mPaint);
//        /**
//         * 画点(StrokeCap==Paint.Cap.ROUND)
//         */
//        canvas.translate(0, radius + 10);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(radius);
//        //FILL_AND_STROKE
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 2 / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 3 / 4, radius * 2, mPaint);
//
//        /**
//         * 画点(StrokeCap==Paint.Cap.BUTT)
//         */
//        canvas.translate(0, radius + 10);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        mPaint.setStrokeWidth(radius);
//        //FILL_AND_STROKE
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 2 / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 3 / 4, radius * 2, mPaint);
//
//        /**
//         * 画点(StrokeCap==Paint.Cap.SQUARE)
//         */
//        canvas.translate(0, radius + 10);
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        mPaint.setStrokeWidth(radius);
//        //FILL_AND_STROKE
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 2 / 4, radius * 2, mPaint);
//        //STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(width * 3 / 4, radius * 2, mPaint);
    }

    private void testSetStrokeWidth(Canvas canvas, int width, int radius) {
        /**
         * 设置线条宽度
         */
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        canvas.translate(0, radius * 3);
        mPaint.setStrokeWidth(radius / 2);
        //FILL_AND_STROKE
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(width / 4, radius * 2, radius / 2, mPaint);
        //FILL
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width * 2 / 4, radius * 2, radius / 2, mPaint);
        //STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width * 3 / 4, radius * 2, radius / 2, mPaint);
        /**
         * 设置线条宽度(StrokeWidth占据的空间：一半在FILL区域，一半在外面增加)
         */
        canvas.translate(0, radius * 3);
        mPaint.setStrokeWidth(radius / 2);
        //FILL_AND_STROKE
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(width / 4, radius * 2, radius / 2, mPaint);
        //STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#00FF00"));
        canvas.drawCircle(width / 4, radius * 2, radius / 2, mPaint);
        //STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#00FF00"));
        canvas.drawCircle(width / 2, radius * 2, radius / 2, mPaint);
        //FILL
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#0000FF"));
        canvas.drawCircle(width / 2, radius * 2, radius / 2, mPaint);
    }

    private void testSetStyle(Canvas canvas, int width, int radius) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        canvas.translate(0, radius * 3);
        //FILL_AND_STROKE
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(width / 4, radius * 2, radius, mPaint);
        //STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width * 2 / 4, radius * 2, radius, mPaint);
        //FILL
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width * 3 / 4, radius * 2, radius, mPaint);
    }

    private void testSetAntiAlias(Canvas canvas, int width, int radius) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.translate(0, radius * 3);
        //没有设置抗锯齿
        canvas.drawCircle(width / 4, radius * 2, radius, mPaint);
        //设置了抗锯齿
        mPaint.setAntiAlias(true);
        canvas.drawCircle(width * 2 / 4, radius * 2, radius, mPaint);
    }

    private void testSetColor(Canvas canvas, int width, int radius) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawCircle(width / 4, radius * 2, radius, mPaint);
    }
}
