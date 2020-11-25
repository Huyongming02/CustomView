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
 * @date 2020/11/24-14:29
 * @annotation Paint详解：效果
 */
public class PaintEffectActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_effect);
        initActionBar(getResources().getString(R.string.paint_effect_color), true);
        initView();
    }

    private void initView() {
    }
}
