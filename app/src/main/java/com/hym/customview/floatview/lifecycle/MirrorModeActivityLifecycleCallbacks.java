package com.hym.customview.floatview.lifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hym.customview.R;
import com.hym.customview.floatview.FloatViewActivity;
import com.hym.customview.floatview.FloatViewActivity2;
import com.hym.customview.floatview.OverlaysPermissionApplyActivity;
import com.hym.customview.floatview.manager.MirrorModeWindowManager;


/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v3.1.0
 * @date 2020/11/26-16:11
 * @annotation 镜面模式悬浮窗Activity生命周期回调
 */
public class MirrorModeActivityLifecycleCallbacks extends ActivityLifecycleCallbacksEmptyImpl {
    private static final String TAG = "MirrorModeLifecycle";
    public static MirrorModeActivityLifecycleCallbacks instance;

    //记录当前打开了几个界面，当所有界面都关闭或者退出到后台时关闭悬浮窗和镜面模式
    private int mSum;
    private FrameLayout.LayoutParams mParams;
    private AlertDialog mAlertDialog;

    private MirrorModeActivityLifecycleCallbacks() {
    }

    public static MirrorModeActivityLifecycleCallbacks getInstance() {
        if (instance == null) {
            synchronized (MirrorModeActivityLifecycleCallbacks.class) {
                if (instance == null) {
                    instance = new MirrorModeActivityLifecycleCallbacks();
                }
            }
        }
        return instance;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        super.onActivityStarted(activity);
        if (!isMirror()) {
            return;
        }
        mSum++;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        super.onActivityStopped(activity);
        if (!isMirror()) {
            return;
        }
        mSum--;
        if (mSum == 0) {
            //关闭悬浮窗
            MirrorModeWindowManager.getInstance().dismissWindow();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, " onActivityResumed:" + activity.getClass().getSimpleName());
        if (!isMirror()) {
            return;
        }
        showOrHideWindow(activity);
    }

    private boolean isMirror() {
        return true;
    }

    private void showOrHideWindow( Activity activity) {
        if (mSum > 0 && isNeedMirrorMode(activity)) {
            //如果已经打开了镜面模式，则不需要检查是否开启开启悬浮窗
            //检查悬浮窗是否打开了，没有打开则打开
            MirrorModeWindowManager.getInstance().showPermissionWindow(activity, new MirrorModeWindowManager.OnPermissionListener() {
                @Override
                public void showPermissionDialog() {
                    showDialog(activity);
                }
            });
            MirrorModeWindowManager.getInstance().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity.getApplicationContext(), "floatview onclick", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //如果此时打开了悬浮窗，则应该关闭
            MirrorModeWindowManager.getInstance().dismissWindow();
        }
    }

    private void dismissDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        mAlertDialog = null;//避免因为mPromptDialog不能是否，引起activity泄漏
    }

    @Override
    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
        dismissDialog();
    }

    private void showDialog(final Activity activity) {
        dismissDialog();
        mAlertDialog = new AlertDialog.Builder(activity).setTitle(R.string.float_window_permission_apply_title)
                .setMessage(R.string.float_window_permission_apply_message)
                .setPositiveButton(R.string.float_window_permission_goto_set, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1 || Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
                            activity.startActivity(new Intent(activity, OverlaysPermissionApplyActivity.class));
                        } else {
                            MirrorModeWindowManager.getInstance().applyPermission(activity);
                        }
                    }
                })
                .setNegativeButton(R.string.float_window_permission_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
    }


    private boolean isNeedMirrorMode(Activity activity) {
        return activity instanceof FloatViewActivity ||
                activity instanceof FloatViewActivity2;
    }
}
