package com.youshibi.app.user;

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
import com.youshibi.app.util.ToastUtil;

/**
 * Created by Suzukaze on 2017-12-18 018.
 */

public class RegActivity extends BaseActivity {

    private static final String TAG = "RegActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        Toolbar toolbar = ToolbarHelper.initToolbar(this, R.id.toolbar, true, "注册");

        (findViewById(R.id.rl_reg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!((EditText) findViewById(R.id.et_password)).getText().toString().trim().equals(((EditText) findViewById(R.id.et_password_again)).getText().toString().trim())) {
                    ToastUtil.showToast("两次密码输入不同，请重新输入");
                    return;
                }

                OkGo.<String>post(NetRequest.host)
                        .params("action", "user.reg")
                        .params("ajax", "yes")
                        .params("name", ((EditText) findViewById(R.id.et_username)).getText().toString().trim())
                        .params("psw", ((EditText) findViewById(R.id.et_password)).getText().toString().trim())
                        .params("repsw", ((EditText) findViewById(R.id.et_password_again)).getText().toString().trim())
                        .params("email", ((EditText) findViewById(R.id.et_email)).getText().toString().trim())
                        .params("apireg", 1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                initData(response.body());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToast("注册失败: 网络连接失败");
                            }
                        });
            }
        });
    }

    // 处理数据
    private void initData(String data) {

        LoginBean loginBean;

        try {
            loginBean = (LoginBean) GsonHelper.fromJson(data, LoginBean.class);
        } catch (Exception e) {
            ToastUtil.showToast("解析失败");
            return;
        }

        if (loginBean.getCode() == 200) {
            ToastUtil.showToast("注册成功");
            finish();
        } else {
            ToastUtil.showToast("注册失败");
        }
    }
}
