package com.hym.customview.floatview.manager;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.reflect.Method;

/**
 * ------------------------------------------------
 * Copyright © 2014-2019 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @author huyongming
 * @version v3.1.0
 * @date 2020/11/26-16:45
 * @annotation 镜面模式窗口管理类
 */
public class MirrorModeWindowManager implements View.OnClickListener {
    public static final int REQUEST_PERMISSION_CODE = 0x0110;

    public static volatile MirrorModeWindowManager instance;
    private FloatView mFloatView;
    //悬浮窗是否显示
    private boolean isShowing;
    //是否获取到了权限
    private boolean hasPermission;

    private View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setFloatView(FloatView floatView) {
        this.mFloatView = floatView;
        this.mFloatView.setOnClickListener(this);
    }

    private MirrorModeWindowManager() {
    }


    public static MirrorModeWindowManager getInstance() {
        if (instance == null) {
            synchronized (MirrorModeWindowManager.class) {
                if (instance == null) {
                    instance = new MirrorModeWindowManager();
                }
            }
        }
        return instance;
    }

    /**
     * 显示悬浮窗
     *
     * @param context
     * @param onPermissionListener
     */
    public void showPermissionWindow(Activity context, OnPermissionListener onPermissionListener) {
        if (checkFloatWindowPermission(context)) {
            showWindow(context);
        } else {
            onPermissionListener.showPermissionDialog();
        }
    }

    private void showWindow(Activity context) {
        hasPermission = true;
        if (isShowing) {
            return;
        }
        if (mFloatView != null) {
            mFloatView.showWindow(context);
            isShowing = true;
        }

    }

    /**
     * 隐藏悬浮窗
     */
    public void dismissWindow() {
        if (!hasPermission) {
            return;
        }
        if (!isShowing) {
            return;
        }
        mFloatView.dismissWindow();
        isShowing = false;
    }

    public void applyPermission(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivityForResult(intent, REQUEST_PERMISSION_CODE);
        }
    }

    public void onActivityResult(Activity context, int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkFloatWindowPermission(context)) {
                    showWindow(context);
                }
            }
        }
    }


    private boolean checkFloatWindowPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        } else {
            return hasPermissionBelowMarshmallow(context);
        }
    }

    /**
     * 6.0以下判断是否有权限
     * 理论上6.0以上才需处理权限，但有的国内rom在6.0以下就添加了权限
     * 其实此方式也可以用于判断6.0以上版本，只不过有更简单的canDrawOverlays代替
     */
    static boolean hasPermissionBelowMarshmallow(Context context) {
        try {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Method dispatchMethod = AppOpsManager.class.getMethod("checkOp", int.class, int.class, String.class);
            //AppOpsManager.OP_SYSTEM_ALERT_WINDOW = 24
            return AppOpsManager.MODE_ALLOWED == (Integer) dispatchMethod.invoke(
                    manager, 24, Binder.getCallingUid(), context.getApplicationContext().getPackageName());
        } catch (Exception e) {
            return false;
        }
    }

    public int pt2px(Context context, int value) {
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View view) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(view);
        }
    }


    public static interface OnPermissionListener {
        /**
         * 显示申请权限的对话框
         */
        void showPermissionDialog();
    }


    public static abstract class FloatView extends FrameLayout {

        public FloatView(Context context) {
            super(context);
        }

        public abstract void showWindow(Context context);

        public abstract void dismissWindow();

    }


}
