package com.hym.customview.floatview;

import android.os.Bundle;

import com.hym.customview.BaseActivity;
import com.hym.customview.R;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/12/1-17:30
 * @annotation 悬浮窗
 */
public class FloatViewActivity2 extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view2);
        initActionBar(getResources().getString(R.string.float_view2_title), true);
        initView();
    }

    private void initView() {
    }
}
