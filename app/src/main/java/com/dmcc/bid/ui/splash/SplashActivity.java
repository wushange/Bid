package com.dmcc.bid.ui.splash;

import android.content.Context;
import android.view.View;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.ui.login.DaggerLoginComponent;
import com.dmcc.bid.ui.login.LoginActivity;
import com.dmcc.bid.ui.login.LoginContract;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by wushange on 2016/07/01.
 */
public class SplashActivity extends BaseActivity implements LoginContract.View {


    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().activityModule(getActivityModule()).applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash_main_view;
    }

    @Override
    public void initView(View view) {


    }

    @Override
    public void doBusiness(Context mContext) {

        Observable.timer(2, TimeUnit.SECONDS).subscribe(o -> {
            mBaseOperation.forwardAndFinish(LoginActivity.class);
        });
    }


    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showErrorInfo(String error) {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(String errinfo) {

    }

}
