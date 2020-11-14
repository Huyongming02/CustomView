package com.hym.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hym.customview.path.PathFillTypeView;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/11/13-17:44
 * @annotation Path学习
 */
public class PathActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initActionBar(getResources().getString(R.string.path_page_title), true);
        initView();
    }

    private void initView() {
        /**
         * 1. addXXX
         * 2. xxxTo
         * 3. setFillType
         * 4. op
         */
        findViewById(R.id.btn_add_xxx).setOnClickListener(this);
        findViewById(R.id.btn_xxx_to).setOnClickListener(this);
        findViewById(R.id.btn_fillType).setOnClickListener(this);
        findViewById(R.id.btn_op).setOnClickListener(this);
        findViewById(R.id.btn_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_add_xxx:
                startActivity(new Intent(mContext, PathAddXXXActivity.class));
                break;
            case R.id.btn_xxx_to:
                startActivity(new Intent(mContext, PathXxxToActivity.class));
                break;
            case R.id.btn_fillType:
                startActivity(new Intent(mContext, PathFillTypeActivity.class));
                break;
            case R.id.btn_op:
                startActivity(new Intent(mContext, PathOpActivity.class));
                break;
            case R.id.btn_other:
                startActivity(new Intent(mContext, PathOtherActivity.class));
                break;
        }
    }
}
