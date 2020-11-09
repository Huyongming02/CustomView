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
 * @annotation Paint基础学习
 */
public class PaintBaseActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_base);
        initActionBar(getResources().getString(R.string.paint_base_title), true);
        initView();
    }

    private void initView() {
    }
}
