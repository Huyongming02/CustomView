package com.hym.customview;

import android.app.Application;

import com.hym.customview.floatview.lifecycle.MirrorModeActivityLifecycleCallbacks;
import com.hym.customview.floatview.manager.MirrorModeWindowManager;
import com.hym.customview.floatview.view.MirrorFloatView;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v1.3.2
 * @date 2020/12/1-20:03
 * @annotation ....
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MirrorModeWindowManager.getInstance().setFloatView(new MirrorFloatView(getApplicationContext(), MirrorFloatView.LocationGravity.START_CENTER, (int) MirrorFloatView.dp2px(this, 300)));
        registerActivityLifecycleCallbacks(MirrorModeActivityLifecycleCallbacks.getInstance());
    }
}
