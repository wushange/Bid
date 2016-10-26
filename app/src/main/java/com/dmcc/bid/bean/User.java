package com.dmcc.bid.bean;

import java.io.Serializable;

/**
 * Created by wushange on 2016/10/26.
 */

public class User implements Serializable {

    String userName;
    String userPwd;
    boolean isAutoLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        isAutoLogin = autoLogin;
    }
}
