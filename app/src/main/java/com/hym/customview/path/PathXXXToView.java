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
 * @date 2020/11/13-19:13
 * @annotation ....
 */
public class PathXXXToView extends View {
    public PathXXXToView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1. moveTo
         * 2. lineTo
         * 3. arcTo
         * 4. quadTo
         * 5. cubicTo
         * 6. rMoveTo
         * 7. rLineTo
         * 8. rQuadTo
         * 9. rCubicTo
         */
        int width = getMeasuredWidth();
        int radius = width / 9;
        testLineTo(canvas, width, radius);
        testArcTo(canvas, width, radius);
        testQuadTo(canvas, width, radius);
        testCubicTo(canvas, width, radius);
        testClose(canvas, width, radius);
        testSetLastPoint(canvas, width, radius);
        /**
         * rMoveTo、rLineTo、rQuadTo、rCubicTo中的x，y表示相对位移
         */
    }

    private void testSetLastPoint(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 5);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(width / 8, 0);
        path1.lineTo(width * 4 / 8, 0);
        path1.lineTo(width * 7 / 8, radius);
        path1.lineTo(width * 7 / 8, radius * 2);
        path1.lineTo(width * 6 / 8, radius * 4);
        path1.lineTo(width * 5 / 8, radius * 7);
        path1.lineTo(width * 4 / 8, radius * 11);//最后这个设置没有了
        path1.setLastPoint(radius, radius);
        canvas.drawPath(path1, paint);
    }

    private void testClose(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 5);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(width / 8, 0);
        path1.lineTo(width * 4 / 8, 0);
        path1.lineTo(width * 7 / 8, radius);
        canvas.drawPath(path1, paint);

        canvas.translate(0, radius);
        Path path2 = new Path();
        path2.moveTo(width / 8, 0);
        path2.lineTo(width * 4 / 8, 0);
        path2.lineTo(width * 7 / 8, radius);
        path2.close();
        canvas.drawPath(path2, paint);
    }

    private void testCubicTo(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 5);
        Paint paint1 = getPaint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setColor(Color.GRAY);
        canvas.drawRect(new RectF(width / 8, 0, width * 7 / 8, radius * 3), paint1);

        Paint paint = getPaint();
        paint.setStrokeWidth(dp2px(getContext(), 1));
        paint.setStyle(Paint.Style.STROKE);

        Path path0 = new Path();
        path0.moveTo(width / 8, 0);
        float height0 = radius * 9;
        path0.cubicTo(width * 3 / 8, height0, width * 5 / 8, (radius * 3 - height0), width * 7 / 8, radius * 3);
        canvas.drawPath(path0, paint);

        paint.setColor(Color.BLACK);
        Path path1 = new Path();
        path1.moveTo(width / 8, 0);
        float height = radius * 3;
        path1.cubicTo(width * 3 / 8, height, width * 5 / 8, (radius * 3 - height), width * 7 / 8, radius * 3);
        canvas.drawPath(path1, paint);

        paint.setColor(Color.BLUE);
        Path path2 = new Path();
        path2.moveTo(width / 8, 0);
        float height2 = radius * 2;
        path2.cubicTo(width * 3 / 8, height2, width * 5 / 8, (radius * 3 - height2), width * 7 / 8, radius * 3);
        canvas.drawPath(path2, paint);

        paint.setColor(Color.YELLOW);
        Path path3 = new Path();
        path3.moveTo(width / 8, 0);
        float height3 = radius * 1.5f;
        path3.cubicTo(width * 3 / 8, height3, width * 5 / 8, (radius * 3 - height3), width * 7 / 8, radius * 3);
        canvas.drawPath(path3, paint);
    }

    private void testLineTo(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 2);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(width / 8, 0);
        path1.lineTo(width * 4 / 8, 0);
        path1.lineTo(width * 7 / 8, radius);
        canvas.drawPath(path1, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.FILL);
        Path path2 = new Path();
        path2.moveTo(width / 8, 0);
        path2.lineTo(width * 4 / 8, 0);
        path2.lineTo(width * 7 / 8, radius);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path3 = new Path();
        path3.moveTo(width / 8, 0);
        path3.lineTo(width * 4 / 8, 0);
        path3.lineTo(width * 7 / 8, radius);
        canvas.drawPath(path3, paint);
    }

    private void testQuadTo(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint1 = getPaint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setColor(Color.GREEN);
        canvas.drawRect(new RectF(width / 8, 0, width * 7 / 8, radius * 3), paint1);

        Paint paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(width / 8, radius * 3);
        path1.quadTo(width * 4 / 8, 0, width * 7 / 8, radius * 3);
        canvas.drawPath(path1, paint);

        canvas.translate(0, radius * 4);
        canvas.drawRect(new RectF(width * 2 / 8, 0, width * 6 / 8, radius * 3), paint1);
        paint.setStyle(Paint.Style.FILL);
        Path path2 = new Path();
        path2.moveTo(width * 2 / 8, radius * 3);
        path2.quadTo(width * 4 / 8, 0, width * 6 / 8, radius * 3);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 4);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(new RectF(width * 3 / 8, 0, width * 5 / 8, radius * 3), paint1);
        Path path3 = new Path();
        path3.moveTo(width * 3 / 8, radius * 3);
        path3.quadTo(width * 4 / 8, 0, width * 5 / 8, radius * 3);
        canvas.drawPath(path3, paint);
    }

    private void testArcTo(Canvas canvas, int width, int radius) {
        canvas.translate(0, radius * 3);
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.arcTo(new RectF(width / 8, 0, width * 3 / 8, radius), 180, -180);
        path1.arcTo(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), 180, 180);
        canvas.drawPath(path1, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.STROKE);
        Path path2 = new Path();
        path2.arcTo(new RectF(width / 8, 0, width * 3 / 8, radius), 180, -180, false);
        path2.arcTo(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), 180, 180, false);
        canvas.drawPath(path2, paint);

        canvas.translate(0, radius * 2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path3 = new Path();
        path3.arcTo(new RectF(width / 8, 0, width * 3 / 8, radius), 180, -180, false);
        path3.arcTo(new RectF(width * 5 / 8, 0, width * 7 / 8, radius), 180, 180, false);
        canvas.drawPath(path3, paint);

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
