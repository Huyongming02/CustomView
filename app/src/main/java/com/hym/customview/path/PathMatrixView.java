package com.hym.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
 * @date 2020/11/14-14:34
 * @annotation ....
 */
public class PathMatrixView extends View {
    /**
     * 主语：Matrix矩阵作用于整个Path
     * 1. scale的默认锚点(0,0)，可设置（比如想要锚点在图形的正中间，要设置锚点为图形的正中间点在path中的坐标）
     * 2. roate的默认锚点(0,0)，可设置
     * 3. skew的默认锚点(0,0)，不可设置
     *
     * @param context
     * @param attrs
     */
    public PathMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int radius = width / 8;

        /**
         * 1. 缩放scale
         * 2. 位移translate
         * 3. 错切skew
         * 4. 旋转rotate
         */
        testScale(canvas, width, radius);
        testTranslate(canvas, width, radius);
        testSkew(canvas, width, radius);
        testRotate(canvas, width, radius);
    }

    private void testRotate(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        Paint paint = getPaint();
        rotate2(canvas, width, radius, paint, 30);
        rotate2(canvas, width, radius, paint, 45);
        rotate2(canvas, width, radius, paint, 60);
    }

    /**
     * Skew时,锚点默认是（0,0）且不可设置，如果Path中图形的起点不是（0,0），或者起点x和y不相等，则skew错切之后的图形不理想
     * 使用说明：path中矩阵使用skew时，图形的起点最好是(0,0)
     *
     * @param canvas
     * @param width
     * @param radius
     */
    private void testSkew(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        int realRadius = (int) (radius * 0.8);
        skew(canvas, width, realRadius, paint, 0.5f, 0f);
        skew(canvas, width, realRadius, paint, 0f, 0.5f);
        skew(canvas, width, realRadius, paint, 0.5f, 0.5f);
    }


    private void rotate2(Canvas canvas, int width, int radius, Paint paint, float degrees) {
        canvas.translate(0, radius * 3);
        canvas.save();
        canvas.translate(radius * 1.2f, 0);
        /**
         * 以Path的起点(0,0)为锚点
         */
        Path path = new Path();
        path.addRect(new RectF(0, 0, radius * 2, radius * 2), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);

        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        path.transform(matrix);
        paint.setColor(Color.GRAY);
        canvas.drawPath(path, paint);

        /**
         * 以view的正中心为锚点
         */
        canvas.translate(width / 2, 0);
        Path path2 = new Path();
        path2.addRect(new RectF(0, 0, radius * 2, radius * 2), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path2, paint);

        Matrix matrix2 = new Matrix();
        matrix2.setRotate(degrees, radius, radius);//在中心点旋转
        path2.transform(matrix2);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path2, paint);

        canvas.restore();
    }

    private void skew(Canvas canvas, int width, int radius, Paint paint, float kx, float ky) {
        canvas.translate(0, radius * 4);
        canvas.save();
        canvas.translate(radius, 0);
        Path path = new Path();
        path.addRect(new RectF(0, 0, radius * 2, radius * 2), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);

        canvas.save();
//        canvas.translate(width / 4, 0);
        Matrix matrix = new Matrix();
        matrix.setSkew(kx, ky);
        path.transform(matrix);
        paint.setColor(Color.GRAY);
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.restore();

        canvas.save();
        canvas.translate(width / 2, 0);
        Path path2 = new Path();
        path2.addRect(new RectF(radius, radius, radius * 2 + radius, radius * 2 + radius), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path2, paint);

        canvas.save();
//        canvas.translate(width / 4, 0);
        Matrix matrix2 = new Matrix();
        matrix2.setSkew(kx, ky);
        path2.transform(matrix2);
        paint.setColor(Color.GRAY);
        canvas.drawPath(path2, paint);
        canvas.restore();
        canvas.restore();
    }

    private void testTranslate(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 4);
        int dx = (int) (radius * 1.2);
        int dy = dx;
        translate(canvas, width, radius, dx, 0);
        translate(canvas, width, radius, 0, dy);
        translate(canvas, width, radius, dx, dy);
    }

    private void translate(Canvas canvas, int width, int radius, int dx, int dy) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        Path path = new Path();
        path.addRect(new RectF(radius, 0, radius * 2, radius), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);

        Matrix matrix = new Matrix();
        matrix.setTranslate(dx, dy);//x,y各平移width / 4
        path.transform(matrix);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);
    }

    /**
     * 缩放最好使用setScale(float sx, float sy, float px, float py)设置view的中心为锚点
     *
     * @param canvas
     * @param width
     * @param radius
     */
    private void testScale(Canvas canvas, int width, int radius) {
        Paint paint = getPaint();
        float scaleX = 0.5f;
        float scaleY = 0.5f;
        scale(canvas, width, radius, paint, scaleX, 1);
        scale(canvas, width, radius, paint, 1, scaleY);
        scale(canvas, width, radius, paint, scaleX, scaleY);
    }

    private void scale(Canvas canvas, int width, int radius, Paint paint, float scaleX, float scaleY) {
        canvas.translate(0, radius * 4);
        canvas.save();
        canvas.translate(radius * 0.5f, 0);
        /**
         * 以path的起点(0,0)为锚点
         */
        Path path = new Path();
        path.addRect(new RectF(0, 0, radius * 3, radius * 2), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);

        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);
        path.transform(matrix);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);

        canvas.restore();
        /**
         *  void setScale(float sx, float sy)
         */
        canvas.save();

        canvas.translate(radius * 3.5f, 0);
        Path path2 = new Path();
        path2.addRect(new RectF(radius, radius, radius * 4, radius * 3), Path.Direction.CW);
        paint.setColor(Color.RED);
        canvas.drawPath(path2, paint);

        Matrix matrix2 = new Matrix();
        matrix2.setScale(scaleX, scaleY, radius * 5 / 2, radius * 4 / 2);//将宽和高缩放两倍,以图形的中心圆锚点
        path2.transform(matrix2);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path2, paint);


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
