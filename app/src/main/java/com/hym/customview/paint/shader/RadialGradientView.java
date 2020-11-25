package com.hym.customview.paint.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
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
 * @date 2020/11/25-20:30
 * @annotation ....
 */
public class RadialGradientView extends View {
    private RadialGradient mRadialGradient;
    private Paint mPaint;
    private Shader.TileMode mTileMode;

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RadialGradientView);
        int tileMode = array.getInt(R.styleable.RadialGradientView_RadialGradientViewTileMode, 0);
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

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        mRadialGradient = new LinearGradient(w / 4, 0, w * 3 / 4, 0, new int[]{Color.RED, Color.GREEN, Color.BLUE},
//                new float[]{0.25f, 0.5f, 0.75f}, mTileMode);
        mRadialGradient = new RadialGradient(w / 2.0f, h / 2.0f, w / 4.0f, new int[]{Color.RED, Color.GREEN, Color.BLUE}, new float[]{0.25f, 0.5f, 0.75f}, mTileMode);

        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), mPaint);
    }
}
