package com.hym.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/13-20:16
 * @annotation ....
 */
public class PathFillTypeView extends View {
    public PathFillTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1. even_odd（偶数-奇数的）
         * 2. winding（缠绕）
         * 3. inverse_even_odd
         * 4. inverse_winding
         */
        int width = getMeasuredWidth();
        int radius = width / 9;
        testDrawEvenOdd(canvas, width, radius);
//        testDrawWinding(canvas, width, radius);
//        testDrawStar(canvas, width, radius * 2);
    }

    private void testDrawStar(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);

        List<Point> points = new ArrayList<>();
        float degree = 18;
        for (int i = 0; i < 5; i++) {
            /**
             * 角度坐标系：x轴正方向为0，向上为+；sin，cos在各象限的正负
             */
            double angle = (degree + i * 72) / 180 * Math.PI;
            int x = (int) (Math.cos(angle) * radius);
            int y = -(int) (Math.sin(angle) * radius);
            points.add(new Point(x, y));
        }

        canvas.save();
        canvas.translate(width / 4, 0);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(points.get(1).getX(), points.get(1).getY());
        path.lineTo(points.get(4).getX(), points.get(4).getY());
        path.lineTo(points.get(2).getX(), points.get(2).getY());
        path.lineTo(points.get(0).getX(), points.get(0).getY());
        path.lineTo(points.get(3).getX(), points.get(3).getY());
        path.lineTo(points.get(1).getX(), points.get(1).getY());
        canvas.drawPath(path, paint);

        canvas.translate(width * 2 / 4, 0);
        Path path2 = new Path();
        path2.setFillType(Path.FillType.WINDING);
        path2.moveTo(points.get(1).getX(), points.get(1).getY());
        path2.lineTo(points.get(4).getX(), points.get(4).getY());
        path2.lineTo(points.get(2).getX(), points.get(2).getY());
        path2.lineTo(points.get(0).getX(), points.get(0).getY());
        path2.lineTo(points.get(3).getX(), points.get(3).getY());
        path2.lineTo(points.get(1).getX(), points.get(1).getY());
        canvas.drawPath(path2, paint);
        canvas.restore();

        canvas.translate(0, radius * 2);
        canvas.save();
        canvas.translate(width / 4, 0);
        Path path3 = new Path();
        path3.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path3.moveTo(points.get(1).getX(), points.get(1).getY());
        path3.lineTo(points.get(4).getX(), points.get(4).getY());
        path3.lineTo(points.get(2).getX(), points.get(2).getY());
        path3.lineTo(points.get(0).getX(), points.get(0).getY());
        path3.lineTo(points.get(3).getX(), points.get(3).getY());
        path3.lineTo(points.get(1).getX(), points.get(1).getY());
        canvas.drawPath(path3, paint);

        canvas.translate(width * 2 / 4, 0);
        Path path4 = new Path();
        path4.setFillType(Path.FillType.INVERSE_WINDING);
        path4.moveTo(points.get(1).getX(), points.get(1).getY());
        path4.lineTo(points.get(4).getX(), points.get(4).getY());
        path4.lineTo(points.get(2).getX(), points.get(2).getY());
        path4.lineTo(points.get(0).getX(), points.get(0).getY());
        path4.lineTo(points.get(3).getX(), points.get(3).getY());
        path4.lineTo(points.get(1).getX(), points.get(1).getY());
        canvas.drawPath(path4, paint);
        canvas.restore();
    }

    public static class Point {
        public float x;
        public float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

    private void testDrawWinding(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 5);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.setFillType(Path.FillType.WINDING);
        path1.addCircle(width * 2 / 9, radius, radius, Path.Direction.CW);
        path1.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path1, paint);

        Path path2 = new Path();
        path2.setFillType(Path.FillType.WINDING);
        path2.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        path2.addCircle(width * 7 / 9, radius, radius, Path.Direction.CCW);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 3);
        Path path3 = new Path();
        path3.setFillType(Path.FillType.INVERSE_WINDING);
        path3.addCircle(width * 2 / 9, radius, radius, Path.Direction.CW);
        path3.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path3, paint);

        Path path4 = new Path();
        path4.setFillType(Path.FillType.INVERSE_WINDING);
        path4.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        path4.addCircle(width * 7 / 9, radius, radius, Path.Direction.CCW);
        canvas.drawPath(path4, paint);
    }

    private void testDrawEvenOdd(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
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
        Path path3 = new Path();
        path3.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path3.addCircle(width * 2 / 9, radius, radius, Path.Direction.CW);
        path3.addCircle(width * 3 / 9, radius, radius, Path.Direction.CW);
        canvas.drawPath(path3, paint);


        Path path4 = new Path();
        path4.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path4.addCircle(width * 6 / 9, radius, radius, Path.Direction.CW);
        path4.addCircle(width * 7 / 9, radius, radius, Path.Direction.CCW);
        canvas.drawPath(path4, paint);
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
