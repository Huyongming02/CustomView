package com.hym.customview.paint.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.hym.customview.R;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/24-17:02
 * @annotation Paint着色器：BitmapShader
 */
public class BitmapShaderView extends View {
    private Bitmap mBitmap;
    private Paint mPaint;
    private Shader.TileMode mTileMode;

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.BitmapShaderView);
        int tileMode = array.getInt(R.styleable.BitmapShaderView_BitmapShaderViewTileMode, 0);
        switch (tileMode) {
            case 0:
                mTileMode = Shader.TileMode.CLAMP;
                break;
            case 1:
                mTileMode = Shader.TileMode.REPEAT;
                break;
            case 2:
                mTileMode = Shader.TileMode.MIRROR;
                break;
        }
        array.recycle();


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.key);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(dp2px(getContext(), 40));
        mPaint.setFakeBoldText(true);
        mPaint.setShader(new BitmapShader(mBitmap, mTileMode, mTileMode));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius = height / 2;
        canvas.drawCircle(width / 5, radius, radius, mPaint);

        canvas.drawText("你好", width * 3 / 5, radius, mPaint);
    }

    public int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }
}
