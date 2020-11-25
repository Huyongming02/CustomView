package com.hym.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/19-20:41
 * @annotation ....
 */
public class PaintActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        initActionBar(getResources().getString(R.string.paint_page_title), true);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_shaper).setOnClickListener(this);
        findViewById(R.id.btn_effect).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_shaper:
                startActivity(new Intent(mContext, PaintShaderActivity.class));
                break;
            case R.id.btn_effect:
                startActivity(new Intent(mContext, PaintEffectActivity.class));
                break;
        }
    }
}
