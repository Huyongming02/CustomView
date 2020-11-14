package com.hym.customview;

import android.os.Bundle;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/9-19:25
 * @annotation Canvas基础学习
 */
public class CanvasBaseActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_base);
        initActionBar(getResources().getString(R.string.canvas_base_title), true);
        initView();
    }

    private void initView() {
    }
}
