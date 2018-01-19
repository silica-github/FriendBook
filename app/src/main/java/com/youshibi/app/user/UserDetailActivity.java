package com.youshibi.app.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.youshibi.app.R;
import com.youshibi.app.base.BaseActivity;
import com.youshibi.app.ui.help.ToolbarHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Suzukaze on 2017-12-18 018.
 */

public class UserDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = ToolbarHelper.initToolbar(this, R.id.toolbar, true, "个人中心");

        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_get_vip})
    void btn(View v){
        switch (v.getId()){
            case R.id.lin_get_vip:
                startActivity(new Intent(this, BuyVipActivity.class));
                break;
        }
    }
}
