package com.youshibi.app.presentation.mine;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.youshibi.app.R;
import com.youshibi.app.mvp.MvpActivity;
import com.youshibi.app.presentation.bookcase.CacheBookcaseFragment;
import com.youshibi.app.presentation.main.MainContract;
import com.youshibi.app.presentation.main.MainPresenter;
import com.youshibi.app.ui.help.ToolbarHelper;

/**
 * Created by Aoba Suzukaze on 2017-11-19 0019.
 */

public class BbssdkActivity extends MvpActivity<MainContract.Presenter> implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener, CacheBookcaseFragment.OnBookCaseEditListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private BottomNavigationView bottomNavigation;
    @IdRes
    private int selectedTabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbssdk);
        ToolbarHelper.initToolbar(this, R.id.toolbar, true, "缓存列表");
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        getPresenter().initContentContainer(getSupportFragmentManager(), R.id.content_view);
        if (savedInstanceState != null) {
            savedInstanceState.getInt("selectedTabId", R.id.tab_bookcase);
        } else {
            getPresenter().dispatchTabSelectedTabId(R.id.tab_bookcase);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTabId", selectedTabId);
    }

    @NonNull
    @Override
    public MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected boolean isEnableSlideFinish() {
        return false;
    }

    @Override
    protected boolean isCountingPage() {
        return false;
    }

    @Override
    public void switchBookcase(@IdRes int tabId) {
        selectedTabId = tabId;
    }

    @Override
    public void switchExplore(@IdRes int tabId) {
        selectedTabId = tabId;
    }

    @Override
    public void switchMine(@IdRes int tabId) {
        selectedTabId = tabId;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return getPresenter().dispatchTabSelectedTabId(item.getItemId());
    }

    @Override
    public ViewGroup getBottomGroup() {
        return bottomNavigation;
    }
}