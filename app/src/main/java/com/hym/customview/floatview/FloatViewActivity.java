package com.hym.customview.floatview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
public class FloatViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        initActionBar(getResources().getString(R.string.float_view_title), true);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_open).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_open:
                startActivity(new Intent(mContext, FloatViewActivity2.class));
                break;
        }
    }
}
