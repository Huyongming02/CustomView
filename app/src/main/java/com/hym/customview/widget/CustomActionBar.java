package com.hym.customview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hym.customview.R;

/**
 * Created by huyongming on 2016/5/3.
 */
public class CustomActionBar extends RelativeLayout implements View.OnClickListener {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private FrameLayout mFlGroup;
    private OnMenuClickListener mMenuClickListener;
    private LayoutInflater mLayoutInflater;

    public CustomActionBar(Context context) {
        super(context);
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        mMenuClickListener = listener;
    }

    public void setTitle(String title){
        mTvTitle.setText(title);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLayoutInflater = LayoutInflater.from(getContext());
        mIvBack = (ImageView) findViewById(R.id.iv_customactionbar_back);
        mTvTitle = (TextView) findViewById(R.id.tv_customactionbar_title);
        mFlGroup = (FrameLayout) findViewById(R.id.fl_customactionbar_right_group);
        setHomeButtonEnabled(false);
    }

    public void setHomeButtonEnabled(boolean enabled) {
        if(enabled) {
            mIvBack.setOnClickListener(this);
            mIvBack.setVisibility(View.VISIBLE);
        }else {
            mIvBack.setOnClickListener(null);
            mIvBack.setVisibility(View.GONE);
        }
    }

  /*  public void addCustomActionBarImageMenu(int menuId,int icon){
        View menuItem = mLayoutInflater.inflate(R.layout.customactionbar_icon_menu, mFlGroup, false);
        menuItem.setTag(menuId);
        menuItem.setOnClickListener(this);
        ((ImageView) menuItem.findViewById(R.id.iv_menu)).setImageResource(icon);
        mFlGroup.addView(menuItem);
    }
*/


 /*   public void addCustomActionBarTextMenu(int menuId, String title) {
        View menuItem = mLayoutInflater.inflate(R.layout.customactionbar_text_menu, mFlGroup, false);
        menuItem.setTag(menuId);
        menuItem.setOnClickListener(this);
        ((TextView) menuItem.findViewById(R.id.tv_menu)).setText(title);
        mFlGroup.addView(menuItem);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_customactionbar_back:
                if (mMenuClickListener != null) {
                    mMenuClickListener.onMenuClick(v.getId());
                }
                break;
            /*case R.id.customactionbar_icon_menu_id:
                if (mMenuClickListener != null) {
                    mMenuClickListener.onMenuClick((Integer)v.getTag());
                }
                break;
            case R.id.customactionbar_text_menu_id:
                if (mMenuClickListener != null) {
                    mMenuClickListener.onMenuClick((Integer)v.getTag());
                }
                break;*/


            default:
                break;
        }


    }

    public static interface OnMenuClickListener {
        public void onMenuClick(int menuId);
    }

}
