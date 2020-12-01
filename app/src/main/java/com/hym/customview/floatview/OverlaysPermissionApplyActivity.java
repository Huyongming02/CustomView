package com.hym.customview.floatview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hym.customview.R;
import com.hym.customview.floatview.manager.MirrorModeWindowManager;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v3.1.0
 * @date 2020/11/30-10:57
 * @annotation 悬浮窗权限申请（解决在授权界面授权之后返回，当前界面不能立即获取到权限的问题）
 */
public class OverlaysPermissionApplyActivity extends Activity {
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlays_permission_applay);
        MirrorModeWindowManager.getInstance().applyPermission(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        MirrorModeWindowManager.getInstance().onActivityResult(this, requestCode, resultCode, data);
        isBack = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isBack) {
            Log.d("onActivityResumed", "onResume ：" + isBack);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 5 * 100);
        }
    }
}
