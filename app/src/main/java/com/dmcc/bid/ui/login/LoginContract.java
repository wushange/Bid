package com.dmcc.bid.ui.login;

import com.dmcc.bid.base.BaseView;

/**
 * Created by wushange on 2016/10/21.
 */

public class LoginContract {

    public interface View extends BaseView {

        String getUserName();

        String getPassword();

        void loginSuccess();

        void loginError(String errinfo);
    }

    public interface Prestener {
        void login();

    }
}
