package com.hym.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
 * @date 2020/11/14-10:54
 * @annotation ....
 */
public class PathOtherView extends View {
    public PathOtherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int radius = width / 9;
        //approximate()
        testApproximate(canvas, width, radius);
        //computeBounds()
        testComputeBounds(canvas, width, radius);
        //offset
        testOffset(canvas, width, radius);
        //rewind
        testrewind(canvas, width, radius);
        //testtoggleInverseFillType()
        testtoggleInverseFillType(canvas, width, radius);
    }

    private void testtoggleInverseFillType(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.setFillType(Path.FillType.EVEN_ODD);
        path1.addCircle(width * 2 / 9, radius, radius, Path.Direction.CW);
        path1.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path1, paint);

        Path path2 = new Path();
        path2.setFillType(Path.FillType.EVEN_ODD);
        path2.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        path2.addCircle(width * 7 / 9, radius, radius, Path.Direction.CCW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 3);
        path1.toggleInverseFillType();//快速反转FillType
        canvas.drawPath(path1, paint);
        path2.toggleInverseFillType();//快速反转FillType
        canvas.drawPath(path2, paint);

    }


    private void testrewind(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 2 / 8, 0, radius, Path.Direction.CW);
        canvas.drawPath(path, paint);

        Path path1 = new Path();
        path.offset(width / 2, 0, path1);
        path1.rewind();
        canvas.drawPath(path1, paint);
    }

    private void testOffset(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 2 / 8, 0, radius, Path.Direction.CW);
        canvas.drawPath(path, paint);

        Path path1 = new Path();
        path.offset(width / 2, 0, path1);
        canvas.drawPath(path1, paint);
    }

    private void testComputeBounds(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 5);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 2 / 8, 0, radius, Path.Direction.CW);
        canvas.drawPath(path, paint);
        RectF rectF = new RectF();
        path.computeBounds(rectF, false);
        Log.d("testComputeBounds", rectF.toString());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testApproximate(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        Path path = new Path();
        path.addCircle(width * 2 / 8, 0, radius, Path.Direction.CW);
        canvas.drawPath(path, paint);

        float[] points = path.approximate(3f);
        canvas.save();
        canvas.translate(width / 2, 0);
        canvas.drawPoints(points, paint);
        canvas.restore();
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
