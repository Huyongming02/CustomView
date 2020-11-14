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
 * @date 2020/11/13-17:48
 * @annotation Path：addXXX
 */
public class PathAddXXXActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_addxxx);
        initActionBar(getResources().getString(R.string.path_addxxx_page_title), true);
        initView();
    }

    private void initView() {
    }
}
