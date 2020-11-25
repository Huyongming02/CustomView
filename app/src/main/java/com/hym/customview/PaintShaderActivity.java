package com.hym.customview;

import android.os.Bundle;

import com.hym.customview.paint.shader.LinearShaderTextView;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/13-17:48
 * @annotation Paint:基本颜色
 */
public class PaintShaderActivity extends BaseActivity {
    private LinearShaderTextView mLinearShaderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_shader);
        initActionBar(getResources().getString(R.string.paint_shader), true);
        initView();
    }

    private void initView() {
        mLinearShaderTextView = findViewById(R.id.linear_shader_tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLinearShaderTextView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLinearShaderTextView.onPause();
    }
}
