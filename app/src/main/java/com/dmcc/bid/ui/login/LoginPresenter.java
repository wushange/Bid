package com.dmcc.bid.ui.login;

import android.content.Context;

import com.dmcc.bid.api.AccountApi;
import com.dmcc.bid.base.BasePresenter;
import com.dmcc.bid.bean.Login;
import com.dmcc.bid.injector.PerActivity;
import com.dmcc.bid.util.StringUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * p层
 */
@PerActivity
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Prestener {

    Context context;
    AccountApi accountApi;

    /**
     * 登陆界面构造器
     */
    @Inject
    public LoginPresenter(AccountApi api, Context context) {
        this.accountApi = api;
        this.context = context;
    }


    @Override
    public void login() {
        Logger.e("点及登录");

        if (StringUtil.isBlank(mView.getUserName())) {
            mView.showErrorInfo("用户名不能为空");
            return;
        }

        if (StringUtil.isBlank(mView.getPassword())) {

            mView.showErrorInfo("密码不能为空");

            return;
        }
        mView.startLoading();
        mCompositeSubscription.add(accountApi.login(mView.getUserName(), mView.getPassword()).subscribe(new Subscriber<Login>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorInfo(e.getMessage().toString());
                Logger.e("登录结果" + e.getMessage().toString());
            }

            @Override
            public void onNext(Login s) {
                Logger.e("登录结果" + s.toString());
                if ("0".equals(s.getErrno())) {
                    mView.loginSuccess();
                } else {
                    mView.loginError(s.getErrmsg());
                }
            }
        }));
    }
}
