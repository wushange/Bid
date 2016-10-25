package com.dmcc.bid.ui.login;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.ui.home.MainActivity;
import com.github.florent37.diagonallayout.DiagonalLayout;

import javax.inject.Inject;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wushange on 2016/10/21.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button mLoginBtn;
    @BindView(R.id.head_view)
    DiagonalLayout mHeadView;

    @BindView(R.id.input_username)
    EditText mUserName;
    @BindView(R.id.input_password)
    EditText mUserPwd;

    @Inject
    LoginPresenter presenter;

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().activityModule(getActivityModule()).applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login_main_view;
    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        setTranslucentStatus();
        mUserName.setText("18519232094");
        mUserPwd.setText("123456");
        mLoginBtn.setOnClickListener(v -> presenter.login());
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mUserPwd.getText().toString();
    }

    @Override
    public void loginSuccess() {
        mBaseOperation.dissLoading();
        mBaseOperation.forwardAndFinish(MainActivity.class);
    }

    @Override
    public void loginError(String errinfo) {
        mBaseOperation.getmSweetAlertDialog(SweetAlertDialog.WARNING_TYPE).setTitleText("错误").setContentText(errinfo).show();
    }

    @Override
    public void startLoading() {
        mBaseOperation.showSweetLoading("正在登录...");
    }

    @Override
    public void endLoading() {
        mBaseOperation.dissLoading();
    }

    @Override
    public void showErrorInfo(String error) {
        mBaseOperation.getmSweetAlertDialog(SweetAlertDialog.WARNING_TYPE).setTitleText("错误").setContentText(error).show();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
