package com.hym.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
 * @date 2020/11/13-17:52
 * @annotation ....
 */
public class PathAddXXX extends View {
    public PathAddXXX(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1. addArc
         * 2. addCircle
         * 3. addOval
         * 4. addPath
         * 5. addRect
         * 6. addRoundRect
         */
        int width = getMeasuredWidth();
        int radius = width / 9;
        testAddArc(canvas, width, radius);
        testAddCircle(canvas, width, radius);
        testAddOval(canvas, width, radius);
        testAddPath(canvas, width, radius);
        testAddRect(canvas, width, radius);
        testAddRoundRect(canvas, width, radius);
    }

    private void testAddRoundRect(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path1 = new Path();
        int rx = dp2px(getContext(), 10);
        int ry = dp2px(getContext(), 10);
        path1.addRoundRect(new RectF(width / 8, 0, width * 3 / 8, radius), rx, ry, Path.Direction.CW);
        canvas.drawPath(path1, paint);
        Path path2 = new Path();
        path2.addRoundRect(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), rx, ry, Path.Direction.CW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.STROKE);
        Path path3 = new Path();
        path3.addRoundRect(new RectF(width / 8, 0, width * 3 / 8, radius), rx, ry, Path.Direction.CW);
        canvas.drawPath(path3, paint);
        Path path4 = new Path();
        path4.addRoundRect(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), rx, ry, Path.Direction.CW);
        canvas.drawPath(path4, paint);
    }

    private void testAddRect(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path1 = new Path();
        path1.addRect(new RectF(width / 8, 0, width * 3 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path1, paint);
        Path path2 = new Path();
        path2.addRect(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.STROKE);
        Path path3 = new Path();
        path3.addRect(new RectF(width / 8, 0, width * 3 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path3, paint);
        Path path4 = new Path();
        path4.addRect(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path4, paint);
    }

    private void testAddPath(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addOval(new RectF(width / 8, 0, width * 3 / 8, radius), Path.Direction.CW);
        Path path1 = new Path();
        path1.addPath(path);
        canvas.drawPath(path1, paint);
    }

    private void testAddOval(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        Path path1 = new Path();
        path1.addOval(new RectF(width / 8, 0, width * 3 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path1, paint);
        Path path2 = new Path();
        path2.addOval(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.STROKE);
        Path path3 = new Path();
        path3.addOval(new RectF(width / 8, 0, width * 3 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path3, paint);
        Path path4 = new Path();
        path4.addOval(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), Path.Direction.CW);
        canvas.drawPath(path4, paint);
    }

    private void testAddCircle(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path1 = new Path();
        path1.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path1, paint);

        Path path2 = new Path();
        path2.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 3);
        paint.setStyle(Paint.Style.STROKE);
        Path path3 = new Path();
        path3.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path3, paint);

        Path path4 = new Path();
        path4.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path4, paint);
    }

    private void testAddArc(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius);
        Paint paint = getPaint();
        Path path1 = new Path();
        path1.addArc(new RectF(width / 8, 0, width * 3 / 8, radius), 0, 180);
        canvas.drawPath(path1, paint);

        Path path2 = new Path();
        path2.addArc(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), 0, -180);
        canvas.drawPath(path2, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(0, radius);
        Path path3 = new Path();
        path3.addArc(new RectF(width / 8, 0, width * 3 / 8, radius), 0, 180);
        canvas.drawPath(path3, paint);

        Path path4 = new Path();
        path4.addArc(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), 0, -180);
        canvas.drawPath(path4, paint);
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
