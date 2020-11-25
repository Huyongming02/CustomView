package com.hym.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hym.customview.paintbase.PaintBaseView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar(getResources().getString(R.string.main_page_title));
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_paint_base).setOnClickListener(this);
        findViewById(R.id.btn_canvse_base).setOnClickListener(this);
        findViewById(R.id.btn_path).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_paint_base:
                startActivity(new Intent(mContext, PaintActivity.class));
                break;
            case R.id.btn_canvse_base:
                startActivity(new Intent(mContext, CanvasBaseActivity.class));
                break;
            case R.id.btn_path:
                startActivity(new Intent(mContext, PathActivity.class));
                break;
        }
    }
}
