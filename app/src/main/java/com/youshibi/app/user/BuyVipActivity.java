package com.youshibi.app.user;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youshibi.app.R;
import com.youshibi.app.base.BaseActivity;
import com.youshibi.app.bean.BuyVipBean;
import com.youshibi.app.ui.help.ToolbarHelper;
import com.youshibi.app.util.GsonHelper;
import com.youshibi.app.util.ToastUtil;
import com.zchu.rxcache.utils.LogUtils;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Suzukaze on 2017-12-18.
 * ====
 * BuyVipActivity.
 */

public class BuyVipActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_vip);
        Toolbar toolbar = ToolbarHelper.initToolbar(this, R.id.toolbar, true, "开通会员");

        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_buy})
    void btn(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:

                OkGo.<String>post("http://xs.cnei.cc/wmcms/action/index.php?action=api.pay&type=1")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(final Response<String> response) {

                                BuyVipBean buyVipBean;

                                try {
                                    buyVipBean = (BuyVipBean) GsonHelper.fromJson(response.body().replace("<pre>", ""), BuyVipBean.class);
                                } catch (Exception e) {
                                    ToastUtil.showToast("支付失败: 解析数据失败");
                                    return;
                                }

                                final String data = "app_id=" + buyVipBean.getApp_id() + "&" +
                                        "biz_content=" + buyVipBean.getBiz_content() + "&" +
                                        "charset=" + buyVipBean.getCharset() + "&" +
                                        "method=" + buyVipBean.getMethod() + "&" +
                                        "sign_type=" + buyVipBean.getSign_type() + "&" +
                                        "timestamp=" + buyVipBean.getTimestamp() + "&" +
                                        "version=" + buyVipBean.getVersion() + "&" +
                                        "sign=" + buyVipBean.getSign();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        PayTask payTask = new PayTask(BuyVipActivity.this);
                                        final Map<String, String> payResull = payTask.payV2(data, true);

                                        Log.d("Alipay", payResull.toString());

                                        // 回调
                                        final int code = Integer.valueOf(payResull.get("resultStatus"));

                                        if (code == 9000) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtil.showToast("支付成功");
                                                }
                                            });

                                        } else if (code == 6001) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtil.showToast("支付取消");
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtil.showToast("支付失败: " + code);
                                                    LogUtils.debug("支付结果: " + payResull.toString());
                                                }
                                            });
                                        }
                                    }
                                }).start();
                            }
                        });
        }
    }

}
