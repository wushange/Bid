package com.dmcc.bid.bean;

/**
 * Created by wushange on 2016/10/24.
 * 登录返回的bena
 */

public class Login {


    /**
     * errmsg : user logined successful!
     * errno : 0
     */

    private String errmsg;
    private String errno;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    @Override
    public String toString() {
        return "Login{" +
                "errmsg='" + errmsg + '\'' +
                ", errno='" + errno + '\'' +
                '}';
    }
}
