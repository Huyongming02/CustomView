package com.hym.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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
 * @date 2020/11/14-10:20
 * @annotation ....
 */
public class PathOpView extends View {
    public PathOpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1. Path.Op.DIFFERENCE:
         * 2. Path.Op.INTERSECT:
         * 3. Path.Op.UNION:
         * 4. Path.Op.REVERSE_DIFFERENCE:
         * 5. Path.Op.XOR:
         */
        int width = getMeasuredWidth();
        int radius = width / 5;
        testDifference(canvas, width, radius);
        testIntersect(canvas, width, radius);
        testUnion(canvas, width, radius);
        testReversDifference(canvas, width, radius);
        testXor(canvas, width, radius);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testXor(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 3 / 8, 0, radius, Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(width * 5 / 8, 0, radius, Path.Direction.CW);
        path.op(path1, Path.Op.XOR);
        canvas.drawPath(path, paint);
    }

    private void testReversDifference(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 3 / 8, 0, radius, Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(width * 5 / 8, 0, radius, Path.Direction.CW);
        path.op(path1, Path.Op.REVERSE_DIFFERENCE);
        canvas.drawPath(path, paint);
    }

    private void testUnion(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 3 / 8, 0, radius, Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(width * 5 / 8, 0, radius, Path.Direction.CW);
        path.op(path1, Path.Op.UNION);
        canvas.drawPath(path, paint);
    }

    private void testIntersect(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 3 / 8, 0, radius, Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(width * 5 / 8, 0, radius, Path.Direction.CW);
        path.op(path1, Path.Op.INTERSECT);
        canvas.drawPath(path, paint);
    }

    private void testDifference(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 3 / 8, 0, radius, Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(width * 5 / 8, 0, radius, Path.Direction.CW);
        path.op(path1, Path.Op.DIFFERENCE);
        canvas.drawPath(path, paint);
    }

    private Paint getPaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(dp2px(getContext(), 5));
        mPaint.setStyle(Paint.Style.FILL);
        return mPaint;
    }

    public int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }
}
