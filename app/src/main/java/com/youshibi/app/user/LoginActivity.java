package com.youshibi.app.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youshibi.app.R;
import com.youshibi.app.base.BaseActivity;
import com.youshibi.app.data.bean.LoginBean;
import com.youshibi.app.data.net.NetRequest;
import com.youshibi.app.ui.help.ToolbarHelper;
import com.youshibi.app.util.GsonHelper;
import com.youshibi.app.util.SharedPreferencesUtil;
import com.youshibi.app.util.ToastUtil;

/**
 * Created by Suzukaze on 2017-12-18 018.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = ToolbarHelper.initToolbar(this, R.id.toolbar, true, "登录");

        // 注册
        (findViewById(R.id.rl_reg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });

        // 登录
        (findViewById(R.id.rl_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkGo.<String>post(NetRequest.host)
                        .params("action", "user.login")
                        .params("tp", "wap")
                        .params("ajax", "yes")
                        .params("name", ((EditText) findViewById(R.id.et_username)).getText().toString().trim())
                        .params("psw", ((EditText) findViewById(R.id.et_password)).getText().toString().trim())
                        .params("time", 86400)
                        .params("remember", 1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                initData(response.body());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToast("网络错误!");
                            }
                        });

            }
        });
    }

    // 处理登录数据
    private void initData(String data) {

        LoginBean loginBean;

        try {
            loginBean = (LoginBean) GsonHelper.fromJson(data, LoginBean.class);
        } catch (Exception e) {
            ToastUtil.showToast("登录失败: 数据解析失败");
            return;
        }

        if (loginBean.getCode() == 200) {
            ToastUtil.showToast("登录成功");
            SharedPreferencesUtil.getEditor().putBoolean("isLogin", true).commit();
            finish();
        } else {
            ToastUtil.showToast("账户名或密码错误");
        }
    }
}
