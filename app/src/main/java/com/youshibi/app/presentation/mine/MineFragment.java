package com.youshibi.app.presentation.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.youshibi.app.AppRouter;
import com.youshibi.app.R;
import com.youshibi.app.base.BaseFragment;
import com.youshibi.app.pref.AppConfig;
import com.youshibi.app.user.LoginActivity;
import com.youshibi.app.user.UserDetailActivity;
import com.youshibi.app.util.SharedPreferencesUtil;
import com.youshibi.app.util.Shares;
import com.youshibi.app.util.ToastUtil;

/**
 * Created by Chu on 2017/5/28.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private CompoundButton swNightMode;
    private TextView tv_login_flag, tv_login_hint, tv_action_login;
    private RelativeLayout rl_root;
    private boolean isInit = false;

    public static MineFragment newInstance() {

        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swNightMode = view.findViewById(R.id.sw_night_mode);
        swNightMode.setChecked(AppConfig.isNightMode());
        swNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                if (!b) {
                    activity
                            .getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    AppConfig.setNightMode(false);
                    swNightMode.setChecked(false);
                } else {
                    activity
                            .getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    AppConfig.setNightMode(true);
                    swNightMode.setChecked(true);
                }
                activity.recreate();
            }
        });
        bindOnClickLister(view, this,
                R.id.tv_action_login,
                R.id.mine_app_setting,
                R.id.mine_app_night_mode,
                R.id.mine_app_share,
                R.id.mine_app_good_reputation,
                R.id.mine_app_feedback,
                R.id.mine_app_qqgroup,
                R.id.mine_app_about);

        tv_login_flag = view.findViewById(R.id.include_mine).findViewById(R.id.tv_login_flag);
        tv_login_hint = view.findViewById(R.id.include_mine).findViewById(R.id.tv_login_hint);
        tv_action_login = view.findViewById(R.id.include_mine).findViewById(R.id.tv_action_login);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            MobclickAgent.onPageEnd(getClass().getPackage().getName() + ".MineFragment");
        } else {
            MobclickAgent.onPageStart(getClass().getPackage().getName() + ".MineFragment");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_action_login:

                if (SharedPreferencesUtil.getSharedPreferences().getBoolean("isLogin", false)) {
                    SharedPreferencesUtil.getEditor().putBoolean("isLogin", false).commit();
                    ToastUtil.showToast("您已成功退出登录");
                }
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.mine_app_setting:
                ToastUtil.showToast(getString(R.string.developing));
                break;
            case R.id.mine_app_night_mode:
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                if (AppConfig.isNightMode()) {
                    activity
                            .getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    AppConfig.setNightMode(false);
                    swNightMode.setChecked(false);
                } else {
                    activity
                            .getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    AppConfig.setNightMode(true);
                    swNightMode.setChecked(true);
                }
                activity.recreate();
                break;
            case R.id.mine_app_share:
                Shares.share(getContext(), R.string.share_text);
                break;
            case R.id.mine_app_good_reputation:
                AppRouter.showAppMarket(getContext());
                break;
            case R.id.mine_app_feedback:
                startActivity(new Intent(getActivity(), BbssdkActivity.class));

//                MainView mainView = new MainView(getActivity());
//                mainView.loadData();


                break;

            case R.id.mine_app_qqgroup:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://jq.qq.com/?_wv=1027&k=5jWLGuO");
                intent.setData(content_url);
                startActivity(intent);
                break;


            case R.id.mine_app_about:
                AppRouter.showAboutActivity(getContext());
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();

            if (SharedPreferencesUtil.getSharedPreferences().getBoolean("isLogin", false)) {
                tv_login_flag.setText("已登录");
                tv_login_hint.setText("欢迎回来");
                tv_action_login.setText("注销");
                getView().findViewById(R.id.include_mine).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(), UserDetailActivity.class));
                    }
                });
            } else {
                tv_login_flag.setText("未登录");
                tv_login_hint.setText("登录更安全,可以同步书籍,多设备切换");
                tv_action_login.setText("点击登录");
                getView().findViewById(R.id.include_mine).setOnClickListener(null);
            }
    }
}
